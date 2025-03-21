package Tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Utilities.ReadExcelData;

public class LoginTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        driver.get("https://echopro-dev.avlonline.com/login?goto=/contacts?current=1&pageSize=10");
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "Login", dataProviderClass = ReadExcelData.class)
    public void testLogin(String Email, String Password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(Email);
        Thread.sleep(2000);
        loginPage.enterPassword(Password);
        Thread.sleep(3000);

        // loginPage.clickLogin();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
