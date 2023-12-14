package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    private ExtentManager() {
        // Construtor privado para impedir a criação de instâncias desta classe.
    }

    public static ExtentReports createInstance() {
        // start reporters
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("Report de testes.html");
        htmlReporter.config().setReportName("Zro Bank");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, dd/MM/yyyy, HH:mm");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Sistema operacional", "MAC");
        extent.setSystemInfo("Ferramenta de automação", "RestAssured");

        return extent;
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }
}
