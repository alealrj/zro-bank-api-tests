package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static final ExtentReports extent = ExtentManager.getInstance();
    private static ExtentTest test;

    private ExtentTestManager() {
        // Construtor privado para impedir a criação de instâncias desta classe.
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void createTest(String testName, String description) {
        test = extent.createTest(testName, description);
    }
}
