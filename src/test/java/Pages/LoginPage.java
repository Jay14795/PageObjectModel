package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    // constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath = "//input[@name='email']")
    WebElement email;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement login;

    // Methods
    public void enterUsername(String username) {
        email.sendKeys(username);
    }

    public void enterPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public void clickLogin() {
        login.click();
    }

}
