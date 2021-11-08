package Lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hw3 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://mail.ru/");
        login(driver);
        Thread.sleep(5000);
        sendLetter(driver);

    }
    static void login (WebDriver driver) throws InterruptedException {
        //Можно написать в 2 строчки
        WebElement element = driver.findElement(By.name("login"));
        element.sendKeys("rudakov_test");
        //А можно написать и в одну
        driver.findElement(By.xpath("//button[contains (., 'Ввести пароль')]")).click();
        driver.findElement(By.name("password")).sendKeys("ForTest_4321");
        driver.findElement(By.xpath("//*[@id=\"mailbox\"]/form[1]/button[2]")).click();
        Thread.sleep(5000);

     }
     static void sendLetter (WebDriver driver) throws InterruptedException {
         driver.findElement(By.xpath("//span[@class='compose-button__txt']")).click();
         Thread.sleep(5000);
         driver.findElement(By.xpath("//input[contains(@class,'container')]/ancestor::label")).sendKeys("rai17@list.ru");
         driver.findElement(By.name("Subject")).sendKeys("Тест");
         driver.findElement((By.xpath("//span[contains(@class,'button2 button2_base button2_primary button2_compact button2_hover-support js-shortcut')]"))).click();
         //не смог привязаться к более вменяемому xpath, чтобы нажать на подтверждение отправки письма.
         driver.findElement((By.xpath("/html/body/div[16]/div/div/div[2]/button[1]/span"))).click();
         Thread.sleep(5000);
         driver.quit();
     }
}
