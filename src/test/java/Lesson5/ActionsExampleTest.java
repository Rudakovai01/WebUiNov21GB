package Lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import static Lesson5.Helpers.clickWithJs;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class ActionsExampleTest {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeAll
    @DisplayName("Идет регистрация вебдрайвера")
    static void setupDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    @DisplayName("Инициализируем хром драйвер и ожидание")
    void initBrowser(){
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver,5);//передаем наш драйвер и время ожидания
        login(driver);
    }

    @Test
    void dragAndDropTest(){
        driver.get("https://crm.geekbrains.space/dashboard");
        driver.findElement(By.xpath("//div[@class='column-manager dropdown']")).click();
        //класс actions как раз позволяет производить все необходимые действия
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(By.xpath("//label[.='Владелец']/ancestor::tr//span")))
                .dragAndDrop(driver.findElement(By.xpath("//label[.='Владелец']/ancestor::tr")),
        driver.findElement(By.xpath("//label[.='Наименование']/ancestor::tr")))
                .build()
                .perform();
        webDriverWait.until(d ->d.findElements(
                By.xpath("//thead[@class='grid-header']//tr[@class='grid-header-row']/th[contains(@class,'sorta')]"))
                .get(0).getText().equals("ВЛАДЕЛЕЦ"));
        //Сначала создаем коллекцию Web элементов из встроенного класса WebElement
        //Это по сути хэдеры, у каждого хэдера будут свои индексы [0][1][2]... ит.д.
        List<WebElement> headers = driver.findElements(
                By.xpath("//thead[@class='grid-header']//tr[@class='grid-header-row']/th[contains(@class,'sorta')]"));
        //Потом ассерт
        Assertions.assertEquals("ВЛАДЕЛЕЦ", headers.get(0).getText());
        //Еще пара ассертов через более удобную библиотеку от yandex htmlelements
        assertThat(headers.get(0), isDisplayed());
        assertThat(headers.get(0), hasText("ВЛАДЕЛЕЦ"));
        //assertThat(headers.get(0), not(isDisplayed())); просто примера с not
    }
    //В примере ассерта выше по сути проверка уже прошла на этапе 43 строки, тк раз мы дождались, что то появилось
    //По сути это уже и была сделана та самая проверка

    @AfterEach
    @DisplayName("Очищаем деятельность всего браузера после каждого теста")
    void tearDown(){
        driver.quit();
    }

    @Test
    void afishaTest() throws InterruptedException {
        driver.get("https://afisha.ru/msk/events");
        driver.manage().window().setSize(new Dimension(700,700));
        //----подключии через JS дополнительный скрол страницы, чтобы не вывалиться на ненужный элемент
        //WebElement element = driver.findElement(By.xpath("//button[.='Детские']"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        //Thread.sleep(500);
        //-------------------
        //driver.findElement(By.xpath("//button[.='Детские']")).click();
        //-------------------
        //А теперь ниже попробывали тоже самое почти, но более классным методом, через
        //javaScriptExecutor (соответствующий метод клика создалив классе Helpers)
        //Он просто позволяет кликнуть на элемент несмотря ни на что, ни на какие всплывающие элементы.
        clickWithJs(driver, driver.findElement(By.xpath("//button[.='Детские']")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Куда сходить с ребенком в Москве']")));
        }

    //Метод логин для входа на сайт
    //Этот метод можно в дальнейшем копировать в другие тесты проекты - стандартных метод логина.
    static void login (WebDriver driver){
        driver.get("https://crm.geekbrains.space/");
        WebElement element = driver.findElement(By.id("prependedInput"));
        element.sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();
    }
}
