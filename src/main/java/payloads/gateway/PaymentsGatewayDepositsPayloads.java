package payloads.gateway;

import io.restassured.response.Response;
import utils.FileOperations;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class PaymentsGatewayDepositsPayloads extends TokenManager {

    private final FileOperations fileOperations = new FileOperations();

    private static final String GET_PAYMENTS_GATEWAY_DEPOSITS = "/payments-gateway/deposits/";
    private static final String GET_PAYMENTS_GATEWAY_DEPOSITS_ID = "/payments-gateway/deposits/{id}";

    public Response getPaymentesGatewayDeposits() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_DEPOSITS)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response getPaymentesGatewayDepositsId(String id) {

        Response response = given()
                .pathParam("id", id)   
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_DEPOSITS_ID)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}
