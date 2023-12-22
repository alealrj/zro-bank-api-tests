package payloads.gateway;

import io.restassured.response.Response;
import utils.FileOperations;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class PaymentsGatewayOrdersPayloads extends TokenManager {

    private static final String GET_PAYMENTS_GATEWAY_ORDERS = "/payments-gateway/orders";
    private static final String GET_PAYMENTS_GATEWAY_ORDERS_ID = "/payments-gateway/orders/{id}";

    public Response getPaymentsGatewayOrders() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_ORDERS)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response getPaymentesGatewayOrdersId(String id) {

        Response response = given()
                .pathParam("id", id)   
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_ORDERS_ID)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}
