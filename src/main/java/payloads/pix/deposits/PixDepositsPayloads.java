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

    public Response getPixDepositsQrCodes (){

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_PIX_KEYS);
        String id = jsonPathKeys.getString("data.id");

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
}
