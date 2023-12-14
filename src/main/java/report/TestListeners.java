package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.response.Response;
import org.testng.*;
import utils.BaseUrl;

import java.util.HashMap;
import java.util.Map;

import static com.aventstack.extentreports.markuputils.ExtentColor.*;
import static com.aventstack.extentreports.markuputils.ExtentColor.RED;

public class TestListeners implements ITestListener, IInvokedMethodListener {

    private static final ExtentReports extent = ExtentManager.getInstance();

    public void gerarLog(String descricaoLog, String mensagem) {
        String logMessage = descricaoLog + ": " + mensagem;
        System.out.println(logMessage);
        ExtentTestManager.getTest().info(MarkupHelper.createCodeBlock(logMessage));
    }

    public void printLogHashMap(String descricao, HashMap<String, String> log) {
        System.out.println("\n" + descricao.toUpperCase());
        if (log.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
        } else {
            for (Map.Entry<String, String> entry : log.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue() + " - ");
            }
        }
    }

    public void logsExecucaoApi(String nomeServico, String url, String request, Response response) {

        System.out.println("\n*********************************************************");

        // LOGAR URL
        String apiUrl = url.contains("k8s") ? url : BaseUrl.getBaseUrl() + url;
        System.out.println("\nAPI: " + apiUrl);
        ExtentTestManager.getTest().info(MarkupHelper.createLabel(nomeServico, url.contains("k8s") ? BLUE : BLACK));
        ExtentTestManager.getTest().info(MarkupHelper.createCodeBlock("\nAPI: " + apiUrl));

        // LOGAR REQUEST
        if (request != null) {
            System.out.println("\nREQUEST: \n" + request);
            ExtentTestManager.getTest().info(MarkupHelper.createCodeBlock(request));
        }

        // LOGAR RESPONSE
        String log = response.getBody().asString();
        System.out.println("\nRESPONSE: \n" + log);
        ExtentTestManager.getTest().info(MarkupHelper.createCodeBlock(log, CodeLanguage.JSON));

    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.createTest(result.getMethod().getDescription(), result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass(MarkupHelper.createLabel("Cenário executado com sucesso.", GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().fail(MarkupHelper.createLabel("Erro na validação.", RED));
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Implemente lógica para lidar com testes pulados (se necessário).
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
        // Implemente lógica de inicialização (se necessário).
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        // Implemente lógica pré-invocação (se necessário).
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        // Implemente lógica pós-invocação (se necessário).
    }
}
