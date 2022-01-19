package ProjectExamplePortfolioTest;
import Lesson7.CustomLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Iterator;

public class Test {
    EventFiringWebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void registerDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new CustomLogger());
        webDriverWait = new WebDriverWait(driver,5);
        driver.get("https://mail.ru/");
        driver.manage().window().maximize();
    }

    @TmsLink("MYP-1")
    @DisplayName("UI автотест mail.ru")
    @org.junit.jupiter.api.Test
    @Description("Логин на mail.ru, создание и отправка письма и создание облака")
    void loginTest() throws InterruptedException {
        new LoginPage(driver)
                .fillLogin ("rudakov_test")
                .fillPassword("ForTest_4321")
                .submitLogin();
        Thread.sleep(5000);

        new CreateLetterPage(driver)
                .createNewLetterWindow()
                .fillEmail("rai17@list.ru")
                .fillSubject("Tested")
                .fillLetterBody("This e-mail was sent by robot. Please don't reply")
                .sendLetter();
        Thread.sleep(5000);
        Assertions.assertEquals("Письмо отправлено", driver.findElement(By.xpath("//*[contains(@class,'layer__link')]")).getText());

        driver.get("https://cloud.mail.ru/home/");
        driver.manage().window().maximize();
        new CloudPage(driver)
                .createCloud()
                .folderNameWrite("Checked_My first cloud folder")
                .createFolderButtonClick();
    }

    @AfterEach
    void TearDown (){
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        Iterator<LogEntry> iterator = browserLogs.iterator();
        while (iterator.hasNext()){
            Allure.addAttachment("Лог в консоли браузера", iterator.next().getMessage());
        }
        for (LogEntry log: browserLogs) {
            System.out.println(log.getMessage());
        }
        driver.quit();
    }
}
