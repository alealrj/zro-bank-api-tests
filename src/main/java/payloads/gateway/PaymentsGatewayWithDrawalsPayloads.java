package payloads.gateway;

import io.restassured.response.Response;
import utils.FileOperations;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class PaymentsGatewayWithDrawalsPayloads extends TokenManager {

    private static final String GET_PAYMENTS_GATEWAY_WITHDRAWALS = "/payments-gateway/withdrawals";
    private static final String GET_PAYMENTS_GATEWAY_WITHDRAWALS_ID = "/payments-gateway/withdrawals/{id}";

    public Response getPaymentsGatewayWithdrawals() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_WITHDRAWALS)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response getPaymentesGatewayWithdrawalsId(String id) {

        Response response = given()
                .pathParam("id", id)   
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_WITHDRAWALS_ID)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}
