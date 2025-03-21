package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AutomationexcersizeMenuPage {
    WebDriver driver;

    // Locators
    By products = By.xpath("//a[@href='/products']");
    By cart = By.xpath("//body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/a[1]");
    By loginButtonLocator = By.xpath("//button[@type='submit']");

    // Constructor
    public AutomationexcersizeMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void clickproduct() {
        WebElement Product_locator = driver.findElement(products);
        Product_locator.click();
    }

    public void clickCart() {
        WebElement C = driver.findElement(cart);
        C.click();
    }

}