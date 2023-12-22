package payloads.gateway;

import io.restassured.response.Response;
import utils.FileOperations;

import static io.restassured.RestAssured.given;

public class PaymentsGatewayCompanyPayloads {

    private static final String GET_PAYMENTS_GATEWAY_COMPANY = "/payments-gateway/company";

    public Response getPaymentesGatewayCompany() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PAYMENTS_GATEWAY_COMPANY)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}
