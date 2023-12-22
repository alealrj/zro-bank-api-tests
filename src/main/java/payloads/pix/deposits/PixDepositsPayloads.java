package payloads.pix.deposits;

import factory.pix.deposits.PixDepositsQrCodesRequestFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.FileOperations;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class PixDepositsPayloads extends TokenManager {

    //Inicialização de serviços e utilidades
    private final PixDepositsQrCodesRequestFactory pixDepositsQrCodesRequestFactory = new PixDepositsQrCodesRequestFactory();
    private final FileOperations fileOperations = new FileOperations();

    //Endpoints da API
    private static final String POST_PIX_DEPOSITS_QRCODES_DYNAMIC_INSTANT_BILLING = "/pix/deposits/qr-codes/dynamic/instant-billing";
    private static final String POST_PIX_DEPOSITS_QRCODES_DYNAMIC_DUE_DATE = "/pix/deposits/qr-codes/dynamic/due-date";
    private static final String POST_PIX_DEPOSITS_QRCODES = "/pix/deposits/qr-codes";
    private static final String GET_PIX_DEPOSITS_QRCODES_DYNAMIC_ID = "/pix/deposits/qr-codes/dynamic/{id}";
    private static final String GET_V2_PIX_DEPOSITS_BYOPERATION_ID = "/v2/pix/deposits/by-operation/{id}";
    private static final String GET_PIX_DEPOSITS_QRCODES_ID = "/pix/deposits/qr-codes/{id}";
    private static final String GET_PIX_DEPOSITS_QRCODES = "/pix/deposits/qr-codes";
    private static final String DEL_PIX_DEPOSITS_QRCODES_ID = "/pix/deposits/qr-codes/{id}";

    private static final String RESPONSE_POST_PIX_KEYS = "src/test/resources/test_output/pix_keys/post_pix_keys.json";


    public Response postDepositsQrCodes(String key_id, Number value, String summary, String description) {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .body(pixDepositsQrCodesRequestFactory.postPixDepositsQrCodesRequest(key_id, value, summary, description))
                .log().all()
                .post(POST_PIX_DEPOSITS_QRCODES)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response postDepositsQrCodesDynamic(String key, Number document_value, String expiration_date, String summary, String description,
                                               String payer_request) {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .body(pixDepositsQrCodesRequestFactory.postPixDepositsQrCodeDynamicInstantBillingRequest(key, document_value, expiration_date, summary,
                        description, payer_request))
                .log().all()
                .post(POST_PIX_DEPOSITS_QRCODES_DYNAMIC_INSTANT_BILLING)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response postDepositsQrCodesDynamicDueDate(String key, Number document_value, String due_date, String expiration_date, String summary, String description,
                                                      String payer_city, String payer_person_type, String payer_document, String payer_name, String payer_email, String payer_phone,
                                                      String payer_address, String payer_request) {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .body(pixDepositsQrCodesRequestFactory.postPixDepositsQrCodeDynamicDueDateRequest(key, document_value, due_date, expiration_date, summary, description,
                        payer_city, payer_person_type, payer_document, payer_name, payer_email, payer_phone,
                        payer_address, payer_request))
                .log().all()
                .post(POST_PIX_DEPOSITS_QRCODES_DYNAMIC_DUE_DATE)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response getPixDepositsQrCodes() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PIX_DEPOSITS_QRCODES)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response getPixDepositsQrCodesId(String id) {

        Response response = given()
                .pathParam("id", id)
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PIX_DEPOSITS_QRCODES_ID)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response getPixDepositsQrCodesDynamicId(String id) {

        Response response = given()
                .pathParam("id", id)
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PIX_DEPOSITS_QRCODES_DYNAMIC_ID)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response delPixDepositsQrCodesId(String id) {

        Response response = given()
                .pathParam("id", id)
                .headers("nonce", FileOperations.random())
                .log().all()
                .delete(DEL_PIX_DEPOSITS_QRCODES_ID)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}
