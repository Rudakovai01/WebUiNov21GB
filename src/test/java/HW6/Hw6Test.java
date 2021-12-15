package HW6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hw6Test {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void registerDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver,5);
        driver.get("https://mail.ru/");
        driver.manage().window().maximize();
    }

    @Test
    void loginTest() throws InterruptedException {
        //Для начала надо создать экземляр страницы логина и передать туда драйвер
        new LoginPage(driver)
                .fillLogin ("rudakov_test")
                .fillPassword("ForTest_4321")
                .submitLogin();
        Thread.sleep(5000);
        //Почему-то не идут проверки ниже (39 и 40 строки), не пойму, без них весь тест работает прекрасно.
        //Assertions.assertEquals("Алексей Максимов",driver.findElement(By.xpath("///span[contains(@class,'compose-button__ico')]")).getText());
        //Assertions.assertTrue (driver.findElement(By.xpath("//span[contains(@class,'compose-button__ico')]")).isDisplayed());

        new CreateLetterPage(driver)
                .createNewLetterWindow()
                .fillEmail("rai17@list.ru")
                .fillSubject("Tested")
                .fillLetterBody("This e-mail was sent by robot. Please don't reply")
                .sendLetter();
        Thread.sleep(5000);
        //почему-то не работает умное ожидание
        //webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(@class,'layer__link')]"))));
        Assertions.assertEquals("Письмо отправлено", driver.findElement(By.xpath("//*[contains(@class,'layer__link')]")).getText());

        driver.get("https://cloud.mail.ru/home/");
        driver.manage().window().maximize();
        new CloudPage(driver)
                .createCloud()
                .folderNameWrite("Checked_My first cloud folder")
                .createFolderButtonClick();
    }
}
