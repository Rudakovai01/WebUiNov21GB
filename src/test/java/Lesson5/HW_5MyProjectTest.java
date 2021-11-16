package Lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HW_5MyProjectTest {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initBrowser() {
        webDriver = new ChromeDriver();
        webDriverWait = new WebDriverWait(webDriver, 5);
    }

    @Test
    @DisplayName("1-Тестирование логина")
    void loginTest() throws InterruptedException {
        webDriver.get("https://mail.ru/");
        WebElement element = webDriver.findElement(By.name("login"));
        element.sendKeys("rudakov_test");
        webDriver.findElement(By.xpath("//button[contains (., 'Ввести пароль')]")).click();
        webDriver.findElement(By.name("password")).sendKeys("ForTest_4321");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.xpath("//button[contains(@class,'second-button svelte')]")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//span[contains(@class,'ph-dropdown-icon')]")).click();
        WebElement testElement1 = webDriver.findElement(By.xpath("//*[contains(@class,'ph-name svelte-1vf03eq')]"));
        Assertions.assertEquals("Алексей Максимов", testElement1.getText());
    }

    @Test
    @DisplayName("2-Отправка письма")
    void sendLetter() throws InterruptedException {
       login(webDriver);
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//span[@class='compose-button__wrapper']")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//input[contains(@class,'container')]/ancestor::label")).sendKeys("rai17@list.ru");
        webDriver.findElement(By.name("Subject")).sendKeys("Тест");
        webDriver.findElement(By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[5]/div/div/div[2]/div[1]")).sendKeys("I'm robot");
        webDriver.findElement((By.xpath("//span[contains(@class,'button2 button2_base button2_pr')]"))).click();
        Thread.sleep(5000);
        WebElement testElement2 = webDriver.findElement(By.xpath("//*[contains(@class,'layer__link')]"));
        Assertions.assertEquals("Письмо отправлено", testElement2.getText());
    }

    @Test
    @DisplayName("3-Выход из системы")
    void quit () throws InterruptedException {
        login(webDriver);
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//span[contains(@class,'ph-dropdown-icon')]")).click();
        webDriver.findElement(By.xpath("//*[contains(text(), 'Выйти')]")).click();
        WebElement testElement3 = webDriver.findElement(By.xpath("//button[contains (., 'Ввести пароль')]"));
        Assertions.assertEquals("Ввести пароль", testElement3.getText());
    }

    @AfterEach
    void tearTown() {
        webDriver.quit();
    }

    static void login (WebDriver driver) throws InterruptedException {
        driver.get("https://mail.ru/");
        WebElement element = driver.findElement(By.name("login"));
        element.sendKeys("rudakov_test");
        driver.findElement(By.xpath("//button[contains (., 'Ввести пароль')]")).click();
        Thread.sleep(5000);
        driver.findElement(By.name("password")).sendKeys("ForTest_4321");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//button[contains(@class,'second-button svelte')]")).click();
    }
}
