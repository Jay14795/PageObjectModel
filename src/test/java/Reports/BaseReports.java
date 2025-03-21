package Reports;

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
import org.uncommons.reportng.HTMLReporter;
import org.uncommons.reportng.JUnitXMLReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

@Listeners({ HTMLReporter.class, JUnitXMLReporter.class })
public class BaseReports {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeClass
    public void setup() {
        // Initialize ExtentReports
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

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
            log(Status.INFO, "Browser closed");
        }

        // Flush the ExtentReport
        if (extent != null) {
            extent.flush();
        }
    }

    protected void log(Status status, String message) {
        test.log(status, message);
        Reporter.log(message);
    }

    protected void pass(String message) {
        test.pass(message);
        Reporter.log(message);
    }

    protected void fail(String message) {
        test.fail(message);
        Reporter.log(message);
    }

    protected void captureScreenshot(String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
            test.addScreenCaptureFromPath(destination);
            Reporter.log("Screenshot: <a href='" + destination + "'>View Screenshot</a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}