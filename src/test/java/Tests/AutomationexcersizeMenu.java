package Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;
import org.uncommons.reportng.JUnitXMLReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Pages.AutomationexcersizeMenuPage;

@Listeners({ HTMLReporter.class, JUnitXMLReporter.class })
public class AutomationexcersizeMenu {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Add metadata to the report
        Reporter.log("Test Engineer: Jay Gandhi");
        Reporter.log("Organization: Openxcell");
        Reporter.log("Project: Automation Exercise");
        Reporter.log("Environment: Development");
        Reporter.log("Browser: Chrome");

        // Initialize ExtentReports and attach a SparkReporter
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Add system information to ExtentReport
        extent.setSystemInfo("Test Engineer", "Jay Gandhi");
        extent.setSystemInfo("Organization", "Openxcell");
        extent.setSystemInfo("Project", "Automation Exercise");
        extent.setSystemInfo("Environment", "Development");
        extent.setSystemInfo("Browser", "Chrome");

        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        driver.get("https://www.automationexercise.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void productMenuClick() throws InterruptedException {
        try {
            // Log steps in the report
            Reporter.log("Starting Product Menu Click Test");
            test = extent.createTest("Product Menu Click Test", "Test to validate clicking on the Product menu");
            test.log(Status.INFO, "Navigated to Automation Exercise homepage");

            // Perform actions
            AutomationexcersizeMenuPage menu = new AutomationexcersizeMenuPage(driver);
            menu.clickproduct();
            test.log(Status.INFO, "Clicked on the Product menu");
            Thread.sleep(2000);

            // Validate the result
            if (driver.getTitle().contains("Products")) {
                test.pass("Product menu clicked successfully");
                Reporter.log("Product menu clicked successfully");
            } else {
                throw new AssertionError("Failed to click on the Product menu");
            }
        } catch (AssertionError e) {
            // Capture screenshot on failure
            String screenshotPath = captureScreenshot(driver, "product_menu_failure");
            Reporter.log("Screenshot: <a href='" + screenshotPath + "'>View Screenshot</a>");
            test.fail(e.getMessage());
            throw e;
        }
    }

    @Test
    public void CartmenuClick() throws InterruptedException {
        try {
            // Log steps in the report
            Reporter.log("Starting Cart Menu Click Test");
            test = extent.createTest("Cart Menu Click Test", "Test to validate clicking on the Cart menu");
            test.log(Status.INFO, "Navigated to Automation Exercise homepage");

            // Perform actions
            AutomationexcersizeMenuPage menu = new AutomationexcersizeMenuPage(driver);
            menu.clickCart();
            test.log(Status.INFO, "Clicked on the Cart menu");
            Thread.sleep(3000);

            // Validate the result
            if (driver.getTitle().contains("Cart")) {
                test.pass("Cart menu clicked successfully");
                Reporter.log("Cart menu clicked successfully");
            } else {
                throw new AssertionError("Failed to click on the Cart menu");
            }
        } catch (AssertionError e) {
            // Capture screenshot on failure
            String screenshotPath = captureScreenshot(driver, "cart_menu_failure");
            Reporter.log("Screenshot: <a href='" + screenshotPath + "'>View Screenshot</a>");
            test.fail(e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
        test.log(Status.INFO, "Browser closed");

        // Flush the ExtentReport
        extent.flush();
    }

    public String captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
}