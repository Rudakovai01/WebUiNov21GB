package HW6;

import Lesson6.BaseView;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CreateLetterPage extends BaseView {

    @FindBy(xpath = "//span[@class='compose-button__wrapper']")
    public WebElement newLetterButtonClick;
    @FindBy (xpath = "//input[contains(@class,'container')]/ancestor::label")
    public WebElement inputEmailAddress;
    @FindBy (name = "Subject")
    public WebElement inputSubject;
    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[5]/div/div/div[2]/div[1]")
    public WebElement writeLetter;
    @FindBy(xpath = "//span[contains(@class,'button2 button2_base button2_pr')]")
    public WebElement sendButtonClick;

    public CreateLetterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие формы для нового письма")
    public CreateLetterPage createNewLetterWindow() throws InterruptedException {
        newLetterButtonClick.click();
        Thread.sleep(5000);
        return this;
    }

    @Step("Заполняем поле 'кому'")
    public CreateLetterPage fillEmail(String email){
        inputEmailAddress.sendKeys(email);
        return this;
    }

    @Step("Заполняем поле 'тема письма'")
    public CreateLetterPage fillSubject (String subject){
        inputSubject.sendKeys(subject);
        return this;
    }

    @Step("Набираем текст самого письма")
    public CreateLetterPage fillLetterBody(String letter){
        writeLetter.sendKeys(letter);
        return this;
    }

    @Step("Нажимаем кнопку 'Отправить письмо'")
    public void sendLetter(){
        sendButtonClick.click();
    }
}
