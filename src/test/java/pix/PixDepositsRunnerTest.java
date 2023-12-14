package pix;

import io.restassured.response.Response;
import org.hamcrest.SelfDescribing;
import org.testng.annotations.Test;
import payloads.pix.deposits.PixDepositsPayloads;
import utils.FileOperations;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.nullValue;

public class PixDepositsRunnerTest {

    final PixDepositsPayloads pixDepositsPayloads = new PixDepositsPayloads();

    private static final String RESPONSE_POST_PIX_DEPOSITS_QRCODES = "src/test/resources/test_output/pix_deposits/post_deposits_qr-codes.json";


    @Test(description = "Gerar QR Code Estático", priority = 1)
    public void postPixDepositsQrCodes() {
        String key_id = "d3e25b95-6bc4-4ec0-87fc-1219159a138c";
        Number value = 15000;
        String summary = "";
        String description = "";

        Response response = pixDepositsPayloads.postDepositsQrCodes(key_id, value, summary, description);
        response.then().statusCode(201);

        response.then()
                .body("success", equalTo(true))
                .body("data", notNullValue())
                .body("data.id", isA(String.class))
                .body("data.key_id", equalTo(key_id))
                .body("data.value", equalTo(value))
                .body("data.summary", equalTo(summary))
                .body("data.description", equalTo(description))
                .body("data.state", equalTo("PENDING"))
                .body("data.created_at", isA(String.class))
                .body("error", nullValue());

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_POST_PIX_DEPOSITS_QRCODES, responseBody);
    }

    @Test(description = "Buscar Status do QR Code", priority = 1)
    public void getPixDepositsQrCodes() {

        pixDepositsPayloads.getPixDepositsQrCodes();
    }
}
