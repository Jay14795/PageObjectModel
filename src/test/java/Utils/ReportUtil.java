package Utils;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ReportUtil {

    private WebDriver driver;
    private StringBuilder report;

    public ReportUtil(WebDriver driver) {
        this.driver = driver;
        this.report = new StringBuilder();
    }

    public ReportUtil log(String message) {
        report.append(message).append("\n");
        Reporter.log(message + "<br>"); // For TestNG HTML reports
        System.out.println(message); // For console output
        return this;
    }

    public ReportUtil logPass(String message) {
        String formattedMessage = "<span style='color:green;'>PASS: " + message + "</span>";
        report.append(formattedMessage).append("\n");
        Reporter.log(formattedMessage + "<br>");
        System.out.println("PASS: " + message);
        return this;
    }

    public ReportUtil logFail(String message) {
        String formattedMessage = "<span style='color:red;'>FAIL: " + message + "</span>";
        report.append(formattedMessage).append("\n");
        Reporter.log(formattedMessage + "<br>");
        System.out.println("FAIL: " + message);
        return this;
    }

    public String getReport() {
        return report.toString();
    }

    public void clearReport() {
        report.setLength(0);
    }

    // Add methods for screenshots, if needed
    // ...
}