package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    // Locators
    By emailLocator = By.xpath("//input[@name='email']");
    By passwordLocator = By.xpath("//input[@name='password']");
    By loginButtonLocator = By.xpath("//button[@type='submit']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void enterUsername(String username) {
        WebElement email = driver.findElement(emailLocator);
        email.sendKeys(username);
    }

    public void enterPassword(String pwd) {
        WebElement password = driver.findElement(passwordLocator);
        password.sendKeys(pwd);
    }

    public void clickLogin() {
        WebElement login = driver.findElement(loginButtonLocator);
        login.click();
    }
}