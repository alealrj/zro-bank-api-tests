package payloads.payments.gateway;

import io.restassured.response.Response;
import utils.FileOperations;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class PaymentsGatewayOrdersRefundsPayloads extends TokenManager {

    private static final String GET_PAYMENTS_GATEWAY_ORDERS_REFUNDS = "/payments-gateway/orders-refunds";
    private static final String GET_PAYMENTS_GATEWAY_ORDERS_REFUNDS_ID = "/payments-gateway/orders-refunds/{id}";

    public Response getPaymentsGatewayOrdersRefunds() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_ORDERS_REFUNDS)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response getPaymentesGatewayOrdersRefundsId(String id) {

        Response response = given()
                .pathParam("id", id)   
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_ORDERS_REFUNDS_ID)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}
