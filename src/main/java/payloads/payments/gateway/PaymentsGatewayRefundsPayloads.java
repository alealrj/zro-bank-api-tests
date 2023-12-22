package payloads.payments.gateway;

import io.restassured.response.Response;
import utils.FileOperations;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class PaymentsGatewayRefundsPayloads extends TokenManager {

    private static final String GET_PAYMENTS_GATEWAY_REFUNDS = "/payments-gateway/refunds";
    private static final String GET_PAYMENTS_GATEWAY_REFUNDS_ID = "/payments-gateway/refunds/{id}";

    public Response getPaymentsGatewayRefunds() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_REFUNDS)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response getPaymentesGatewayRefundsId(String id) {

        Response response = given()
                .pathParam("id", id)   
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_REFUNDS_ID)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}
