package payloads.payments.gateway;

import io.restassured.response.Response;
import utils.FileOperations;

import static io.restassured.RestAssured.given;

public class PaymentsGatewayExportsPayloads {

    private static final String GET_PAYMENTS_GATEWAY_EXPORT = "/payments-gateway/transactions/exports";

    public Response getPaymentesGatewayExport() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_EXPORT)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}
