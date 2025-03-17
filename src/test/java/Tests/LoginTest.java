package Tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.LoginPage;

public class LoginTest {

    WebDriver driver;

    @BeforeClass
    void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        driver.get("https://echopro-dev.avlonline.com/login?goto=/contacts?current=1&pageSize=10");
        driver.manage().window().maximize();
    }

    @Test
    void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("nisharg.shah@avlonline.com");
        loginPage.enterPassword("nisharg");
        // loginPage.clickLogin();

    }

    @AfterClass
    void tearDown() {
        driver.quit();
    }
}
