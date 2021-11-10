package Lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumDevStart {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://mail.ru");
        Thread.sleep(5000);
        driver.quit();
        //установка библиотеки WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();

        WebDriver fireFoxDriver = new FirefoxDriver();
        fireFoxDriver.get("https://yandex.ru");
        Thread.sleep(5000);
        fireFoxDriver.quit();
    }
}
