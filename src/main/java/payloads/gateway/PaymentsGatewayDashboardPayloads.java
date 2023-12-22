package payloads.gateway;

import io.restassured.response.Response;
import utils.FileOperations;

import static io.restassured.RestAssured.given;

public class PaymentsGatewayDashboardPayloads {

    private static final String GET_PAYMENTS_GATEWAY_DASHBOARD = "/payments-gateway/dashboard";

    public Response getPaymentesGatewayDashboard() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_DASHBOARD)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}
