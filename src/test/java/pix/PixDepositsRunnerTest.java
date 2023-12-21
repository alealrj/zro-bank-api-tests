package pix;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.pix.deposits.PixDepositsPayloads;
import utils.FileOperations;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class PixDepositsRunnerTest {

    //Classes Inicializaveis
    final PixDepositsPayloads pixDepositsPayloads = new PixDepositsPayloads();
    final FileOperations fileOperations = new FileOperations();

    //Validar o schema do contrato
    final String POST_PIX_DEPOSITS_QRCODES = "schemas/pix/deposits/postPixDepositsQrCodes.json";
    final String GET_PIX_DEPOSITS_QRCODES = "schemas/pix/deposits/getPixDepositsQrCodes.json";
    final String GET_PIX_DEPOSITS_QRCODES_ID = "schemas/pix/deposits/getPixDepositsQrCodesId.json";
    final String POST_PIX_DEPOSITS_QRCODES_DYNAMIC = "schemas/pix/deposits/postPixDepositsQrCodesDynamico.json";
    final String GET_PIX_DEPOSITS_QRCODES_DYNAMIC = "schemas/pix/deposits/getPixDepositsQrCodesDynamicoId.json";

    //Salvar o json
    private static final String RESPONSE_POST_PIX_DEPOSITS_QRCODES = "src/test/resources/test_output/pix_deposits/post_deposits_qr-codes.json";
    private static final String RESPONSE_GET_PIX_DEPOSITS_QRCODES_ID = "src/test/resources/test_output/pix_deposits/get_deposits_qr-codes_id.json";
    private static final String RESPONSE_GET_PIX_DEPOSITS_QRCODES_DYNAMIC_ID = "src/test/resources/test_output/pix_deposits/get_deposits_qr-codes_dynamic_id.json";
    private static final String RESPONSE_GET_PIX_DEPOSITS_QRCODES = "src/test/resources/test_output/pix_deposits/get_deposits_qr-codes.json";
    private static final String RESPONSE_POST_PIX_DEPOSITS_QRCODES_DYNAMICO = "src/test/resources/test_output/pix_deposits/post_deposits_qr-codes_dynamico.json";
    private static final String RESPONSE_POST_PIX_DEPOSITS_QRCODES_DYNAMICO_DUE_DATE = "src/test/resources/test_output/pix_deposits/post_deposits_qr-codes_dynamico_due_date.json";
    private static final String RESPONSE_POST_PIX_DEPOSITS_QRCODES_DYNAMICO_ID_DUE_DATE = "src/test/resources/test_output/pix_deposits/post_deposits_qr-codes_dynamico_id_due_date.json";


    @Test(description = "Gerar QR Code Estático", priority = 1)
    public void postPixDepositsQrCodes() {

        String key_id = "d3e25b95-6bc4-4ec0-87fc-1219159a138c";
        Number value = 15000;
        String summary = "";
        String description = "";

        Response response = pixDepositsPayloads.postDepositsQrCodes(key_id, value, summary, description);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(POST_PIX_DEPOSITS_QRCODES));
        int expectedStatusCode = 201;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao realizar o deposito do QR Code Estático" + expectedStatusCode);

        response.then()
                .body("success", equalTo(true))
                .body("data.key_id", equalTo(key_id))
                .body("data.value", equalTo(value))
                .body("data.summary", equalTo(summary))
                .body("data.description", equalTo(description))
                .body("data.state", equalTo("PENDING"));

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_POST_PIX_DEPOSITS_QRCODES, responseBody);
    }

    @Test(description = "Buscar QrCode Estático por ID", priority = 2)
    public void getPixDepositsQrCodesId() {

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_PIX_DEPOSITS_QRCODES);
        String id = jsonPathKeys.getString("data.id");

        Response response = pixDepositsPayloads.getPixDepositsQrCodesId(id);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_PIX_DEPOSITS_QRCODES_ID));
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao buscar QR por ID" + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_GET_PIX_DEPOSITS_QRCODES_ID, responseBody);
    }

    @Test(description = "Gerar QrCode Dinâmico", priority = 3)
    public void postPixDepositsQrCodesDynamic() {

        String key = "b57e042f-37e3-4f82-9a5d-3384781e7c91";
        Number document_value = 2300;
        String expiration_date = FileOperations.getFormattedExpirationDate();
        String summary = "party-payment";
        String description = "The party payment.";
        String payer_request = "Send receipt to my email.";

        Response response = pixDepositsPayloads.postDepositsQrCodesDynamic(key, document_value, expiration_date, summary, description, payer_request);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(POST_PIX_DEPOSITS_QRCODES_DYNAMIC));
        int expectedStatusCode = 201;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao realizar o deposito do QR Code Estático" + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_POST_PIX_DEPOSITS_QRCODES_DYNAMICO, responseBody);
    }

    @Test(description = "Buscar QrCode Dinâmico por ID", priority = 4)
    public void getPixDepositsQrCodesDynamicId() {

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_PIX_DEPOSITS_QRCODES_DYNAMICO);
        String id = jsonPathKeys.getString("data.id");

        Response response = pixDepositsPayloads.getPixDepositsQrCodesDynamicId(id);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_PIX_DEPOSITS_QRCODES_DYNAMIC));
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao buscar QR por ID" + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_GET_PIX_DEPOSITS_QRCODES_DYNAMIC_ID, responseBody);
    }

    @Test(description = "Gerar QrCode Dinâmico Due Date (Juros e Multa)", priority = 5)
    public void postPixDepositsQrCodesDynamicDueDate() {

        String key = "b57e042f-37e3-4f82-9a5d-3384781e7c91";
        Number document_value = 2300;
        String due_date = "2023-12-11";
        String expiration_date = "2023-12-11";
        String summary = "party-payment";
        String description = "User defined description";
        String payer_city = "2611606";
        String payer_person_type = "LEGAL_PERSON";
        String payer_document = "78762893041";
        String payer_name = "Jonh Doe";
        String payer_email = "nobody@zrobank.biz";
        String payer_phone = "5581987654321";
        String payer_address = "Main Street 1, 9.";
        String payer_request = "Send receipt to my email.";

        Response response = pixDepositsPayloads.postDepositsQrCodesDynamicDueDate(key, document_value, due_date, expiration_date, summary, description,
                payer_city, payer_person_type, payer_document, payer_name, payer_email, payer_phone, payer_address, payer_request);

        int expectedStatusCode = 201;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao realizar o deposito do QR Code Dynamico Due Date" + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_POST_PIX_DEPOSITS_QRCODES_DYNAMICO_DUE_DATE, responseBody);
    }

    @Test(description = "Buscar QrCode Dinâmico Due Date (Juros e Multa) por Id", priority = 6)
    public void getPixDepositsQrCodesDynamicDueDateId() {

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_PIX_DEPOSITS_QRCODES_DYNAMICO_DUE_DATE);
        String id = jsonPathKeys.getString("data.id");

        Response response = pixDepositsPayloads.getPixDepositsQrCodesDynamicId(id);
//        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_PIX_DEPOSITS_QRCODES_DYNAMIC));

        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao buscar QR por ID" + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_POST_PIX_DEPOSITS_QRCODES_DYNAMICO_ID_DUE_DATE, responseBody);
    }

    @Test(description = "Buscar QR Code", priority = 7)
    public void getPixDepositsQrCodes() {

        Response response = pixDepositsPayloads.getPixDepositsQrCodes();
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_PIX_DEPOSITS_QRCODES));
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao Buscar Depositos QR Code " + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_GET_PIX_DEPOSITS_QRCODES, responseBody);
    }
}
