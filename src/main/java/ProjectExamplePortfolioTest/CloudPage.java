package ProjectExamplePortfolioTest;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CloudPage extends BaseView {
    WebDriver driver;

    @FindBy (xpath = "//div[contains(@class,'DataListItemCreateNew__root')]")
    public WebElement createCloudButton;

    @FindBy (xpath = "//div[contains(text(), 'Папку')]")
    public WebElement choseFolderButton;

    @FindBy(xpath = "//*[contains(text(), 'Папку')]")
    public WebElement folderNameField;

    @FindBy (xpath = "//input[contains(@placeholder,'Введите имя папки')]")
    public WebElement placeHolder;

    @FindBy(xpath = "//button[.= 'Создать']")
    public WebElement createFolderButton;

    public CloudPage(WebDriver driver) { super(driver);}

    @Step("Нажимам кнопку 'Создать облако'")
    public CloudPage createCloud () throws InterruptedException {
        Thread.sleep(5000);
        createCloudButton.click();
        return this;
    }

    @Step("Заполняем поле наименования папки в облаке")
    public CloudPage folderNameWrite (String name) throws InterruptedException {
        Thread.sleep(5000);
        folderNameField.click();
        Thread.sleep(5000);
        placeHolder.sendKeys(name);
        return this;
    }

    @Step("Нажимаем кнопку 'Создать папку'")
    public CloudPage createFolderButtonClick () throws InterruptedException {
        Thread.sleep(5000);
        createFolderButton.click();
        return this;
    }
}
