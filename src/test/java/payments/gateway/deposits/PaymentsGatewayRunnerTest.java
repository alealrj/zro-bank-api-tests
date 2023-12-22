package payments.gateway.deposits;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.payments.gateway.*;
import utils.FileOperations;

import static org.testng.Assert.assertEquals;

public class PaymentsGatewayRunnerTest {

    final PaymentsGatewayDepositsPayloads paymentsGatewayDepositsPayloads = new PaymentsGatewayDepositsPayloads();
    final PaymentsGatewayCompanyPayloads paymentsGatewayCompanyPayloads = new PaymentsGatewayCompanyPayloads();
    final PaymentsGatewayDevolutionsPayloads paymentsGatewayDevolutionsPayloads = new PaymentsGatewayDevolutionsPayloads();
    final PaymentsGatewayOrdersPayloads paymentsGatewayOrdersPayloads = new PaymentsGatewayOrdersPayloads();
    final PaymentsGatewayOrdersRefundsPayloads paymentsGatewayOrdersRefundsPayloads = new PaymentsGatewayOrdersRefundsPayloads();
    final PaymentsGatewayWithDrawalsPayloads paymentsGatewayWithDrawalsPayloads = new PaymentsGatewayWithDrawalsPayloads();
    final PaymentsGatewayRefundsPayloads paymentsGatewayRefundsPayloads = new PaymentsGatewayRefundsPayloads();
    final PaymentsGatewayDashboardPayloads paymentsGatewayDashboardPayloads = new PaymentsGatewayDashboardPayloads();
    final PaymentsGatewayExportsPayloads paymentsGatewayExportsPayloads = new PaymentsGatewayExportsPayloads();
    final FileOperations fileOperations = new FileOperations();

    private static final String GET_PAYMENT_GATEWAY_DEPOSITS = "src/test/resources/test_output/payments/gateway/deposits/getPaymentsGatewayDeposits.json";
    private static final String GET_PAYMENT_GATEWAY_DEPOSITS_ID = "src/test/resources/test_output/payments/gateway/deposits/getPaymentsGatewayDepositsId.json";
    private static final String GET_PAYMENT_GATEWAY_COMPANY = "src/test/resources/test_output/payments/gateway/company/getPaymentsGatewayCompany.json";
    private static final String GET_PAYMENT_GATEWAY_DEVOLUTIONS = "src/test/resources/test_output/payments/gateway/devolutions/getPaymentsGatewayDevolutions.json";
    private static final String GET_PAYMENT_GATEWAY_DEVOLUTION_ID = "src/test/resources/test_output/payments/gateway/devolutions/getPaymentsGatewayDevoltuinsId.json";
    private static final String GET_PAYMENT_GATEWAY_ORDERS = "src/test/resources/test_output/payments/gateway/orders/getPaymentsGatewayOrders.json";
    private static final String GET_PAYMENT_GATEWAY_ORDERS_ID = "src/test/resources/test_output/payments/gateway/orders/getPaymentsGatewayOrdersId.json";
    private static final String GET_PAYMENT_GATEWAY_ORDERS_REFUNDS = "src/test/resources/test_output/payments/gateway/orders/refunds/getPaymentsGatewayOrdersRefunds.json";
    private static final String GET_PAYMENT_GATEWAY_ORDERS_REFUNDS_ID = "src/test/resources/test_output/payments/gateway/orders/refunds/getPaymentsGatewayOrdersRefundsId.json";
    private static final String GET_PAYMENT_GATEWAY_WITHDRAWALS = "src/test/resources/test_output/payments/gateway/withdrawals/getPaymentsGatewayWithdrawals.json";
    private static final String GET_PAYMENT_GATEWAY_WITHDRAWALS_ID = "src/test/resources/test_output/payments/gateway/withdrawals/getPaymentsGatewayWithdrawalsId.json";
    private static final String GET_PAYMENT_GATEWAY_REFUNDS = "src/test/resources/test_output/payments/gateway/refunds/getPaymentsGatewayRefunds.json";
    private static final String GET_PAYMENT_GATEWAY_REFUNDS_ID = "src/test/resources/test_output/payments/gateway/refunds/getPaymentsGatewayRefundsId.json";
    private static final String GET_PAYMENT_GATEWAY_DASHBOARD = "src/test/resources/test_output/payments/gateway/dashboard/getPaymentsGatewayDashboard.json";
    private static final String GET_PAYMENT_GATEWAY_EXPORT = "src/test/resources/test_output/payments/gateway/export/getPaymentsGatewayExport.csv";

    @Test(description = "Get Payments Gateway Deposits", priority = 1)
    public void getPaymentsGatewayDeposits() {

        Response response = paymentsGatewayDepositsPayloads.getPaymentesGatewayDeposits();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Deposits" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_DEPOSITS, responseBody);
    }

    @Test(description = "Get Payments Gateway Deposits Id", priority = 2)
    public void getPaymentsGatewayDepositsId() {

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(GET_PAYMENT_GATEWAY_DEPOSITS);
        String id = jsonPathKeys.getString("data.data.id[0]");

        Response response = paymentsGatewayDepositsPayloads.getPaymentesGatewayDepositsId(id);
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Deposits Id" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_DEPOSITS_ID, responseBody);
    }

    @Test(description = "Get Payments Gateway Company", priority = 3)
    public void getPaymentsGatewayCompany() {

        Response response = paymentsGatewayCompanyPayloads.getPaymentesGatewayCompany();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Company" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_COMPANY, responseBody);
    }

    @Test(description = "Get Payments Gateway Devolutions", priority = 4)
    public void getPaymentsGatewayDevolutions() {

        Response response = paymentsGatewayDevolutionsPayloads.getPaymentsGatewayDevolutions();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Devolutions" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_DEVOLUTIONS, responseBody);
    }

    @Test(description = "Get Payments Gateway Devolutions Id", priority = 5)
    public void getPaymentsGatewayDevolutionsId() {

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(GET_PAYMENT_GATEWAY_DEVOLUTIONS);
        String id = jsonPathKeys.getString("data.data.id[0]");

        Response response = paymentsGatewayDevolutionsPayloads.getPaymentesGatewayDevolutionsId(id);
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Devolutions Id" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_DEVOLUTION_ID, responseBody);
    }

    @Test(description = "Get Payments Gateway Orders", priority = 6)
    public void getPaymentsGatewayOrders() {

        Response response = paymentsGatewayOrdersPayloads.getPaymentsGatewayOrders();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Orders" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_ORDERS, responseBody);
    }

    @Test(description = "Get Payments Gateway Orders Id", priority = 7)
    public void getPaymentsGatewayOrdersId() {

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(GET_PAYMENT_GATEWAY_ORDERS);
        String id = jsonPathKeys.getString("data.data.id[0]");

        Response response = paymentsGatewayOrdersPayloads.getPaymentesGatewayOrdersId(id);
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Orders Id" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_ORDERS_ID, responseBody);
    }

    @Test(description = "Get Payments Gateway Orders Refunds", priority = 8)
    public void getPaymentsGatewayOrdersRefunds() {

        Response response = paymentsGatewayOrdersRefundsPayloads.getPaymentsGatewayOrdersRefunds();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Orders Refunds" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_ORDERS_REFUNDS, responseBody);
    }

    @Test(description = "Get Payments Gateway Orders Refunds Id", priority = 9)
    public void getPaymentsGatewayOrdersRefundsId() {

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(GET_PAYMENT_GATEWAY_ORDERS_REFUNDS);
        String id = jsonPathKeys.getString("data.data.id[0]");

        Response response = paymentsGatewayOrdersRefundsPayloads.getPaymentesGatewayOrdersRefundsId(id);
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Orders Refunds Id" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_ORDERS_REFUNDS_ID, responseBody);
    }

    @Test(description = "Get Payments Gateway Withdrawals", priority = 10)
    public void getPaymentsGatewayWithdrawals() {

        Response response = paymentsGatewayWithDrawalsPayloads.getPaymentsGatewayWithdrawals();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Withdrawals" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_WITHDRAWALS, responseBody);
    }

    @Test(description = "Get Payments Gateway Withdrawals Id", priority = 11)
    public void getPaymentsGatewayWithdrawalsId() {

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(GET_PAYMENT_GATEWAY_WITHDRAWALS);
        String id = jsonPathKeys.getString("data.data.id[0]");

        Response response = paymentsGatewayWithDrawalsPayloads.getPaymentesGatewayWithdrawalsId(id);
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Withdrawals Id" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_WITHDRAWALS_ID, responseBody);
    }

    @Test(description = "Get Payments Gateway Refunds", priority = 12)
    public void getPaymentsGatewayRefunds() {

        Response response = paymentsGatewayRefundsPayloads.getPaymentsGatewayRefunds();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Refunds" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_REFUNDS, responseBody);
    }

    @Test(description = "Get Payments Gateway Refunds Id", priority = 13)
    public void getPaymentsGatewayRefundsId() {

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(GET_PAYMENT_GATEWAY_REFUNDS);
        String id = jsonPathKeys.getString("data.data.id[0]");

        Response response = paymentsGatewayRefundsPayloads.getPaymentesGatewayRefundsId(id);
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Refunds Id" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_REFUNDS_ID, responseBody);
    }

    @Test(description = "Get Payments Gateway Dashboard", priority = 14)
    public void getPaymentsGatewayDashboard() {

        Response response = paymentsGatewayDashboardPayloads.getPaymentesGatewayDashboard();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Dashboard" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_DASHBOARD, responseBody);
    }

    @Test(description = "Get Payments Gateway Export", priority = 15)
    public void getPaymentsGatewayExport() {

        Response response = paymentsGatewayExportsPayloads.getPaymentesGatewayExport();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Payments Gateway Export" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_PAYMENT_GATEWAY_EXPORT, responseBody);
    }
}
