package payloads.payments.gateway;

import io.restassured.response.Response;
import utils.FileOperations;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class PaymentsGatewayDevolutionsPayloads extends TokenManager {

    private static final String GET_PAYMENTS_GATEWAY_DEVOLUTION = "/payments-gateway/devolutions";
    private static final String GET_PAYMENTS_GATEWAY_DEVOLUTION_ID = "/payments-gateway/devolutions/{id}";

    public Response getPaymentsGatewayDevolutions() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_DEVOLUTION)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response getPaymentesGatewayDevolutionsId(String id) {

        Response response = given()
                .pathParam("id", id)   
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_DEVOLUTION_ID)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}
