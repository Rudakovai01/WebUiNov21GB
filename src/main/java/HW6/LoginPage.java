package HW6;

import Lesson6.BaseView;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseView {

    @FindBy (name = "login")
    public WebElement inputLogin;
    @FindBy (xpath = "//button[contains (., 'Ввести пароль')]")
    public WebElement clickPasswordButton;
    @FindBy(name = "password")
    public WebElement inputPassword;
    @FindBy (xpath = "//button[contains(@class,'second-button svelte')]")
    public WebElement clickLoginButton;

    @Step("Заполняем поле email")
    public LoginPage fillLogin (String login) {
        inputLogin.sendKeys(login);
        return this;
    }

    @Step("Вводим пароль")
    public LoginPage fillPassword (String password) {
        clickPasswordButton.click();
        inputPassword.sendKeys(password);
        return this;
    }

    @Step("Нажимаем на кнопку входа")
    public void submitLogin () {
        clickLoginButton.click();
    }

    public LoginPage (WebDriver driver) {
        super(driver);
    }

    // вариант поставить проверку из страницы
//    public LoginPage checkSucessMessage (WebDriver driver){
//        WebElement testElement1 = driver.findElement(By.xpath("//*[contains(@class,'ph-name svelte-1vf03eq')]"));
//        Assertions.assertEquals("Алексей Максимов", testElement1.getText());
//        //Assertions.assertEquals("Алексей Максимов", testElement1.getText());
//        return this;
}

