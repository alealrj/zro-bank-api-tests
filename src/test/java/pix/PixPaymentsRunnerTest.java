package pix;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.pix.payments.PixPaymentsPayloads;
import utils.FileOperations;

public class PixPaymentsRunnerTest {

    private final PixPaymentsPayloads pixPaymentsPayloads = new PixPaymentsPayloads();
    private final FileOperations fileOperations = new FileOperations();


    private static final String RESPONSE_DECODE_PIX_KEY = "src/test/resources/test_output/pix_payments/decode/decodePixKeys.json";
    private static final String RESPONSE_DECODE_PIX_QRCODE_ESTATICO = "src/test/resources/test_output/pix_payments/decode/decodePixQrCodeStatic.json";
    private static final String RESPONSE_DECODE_PIX_QRCODE_DINAMICO = "src/test/resources/test_output/pix_payments/decode/decodePixQrCodeDynamic.json";
    private static final String RESPONSE_DECODE_PIX_QRCODE_DINAMICO_DUE_DATE = "src/test/resources/test_output/pix_payments/decode/decodePixQrCodeDynamicDueDate.json";
    private static final String RESPONSE_PAYMENTS_PIX_KEY = "src/test/resources/test_output/pix_payments/payments/decodePixKeys.json";

    @Test(description = "Decode de chave Pix", priority = 1)
    public void decodePixKey() {
        String type = "CPF";
        String key = "44974995880";

        Response response = pixPaymentsPayloads.getPixKeysDecode(type, key);
        response.then().statusCode(200);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_DECODE_PIX_KEY, responseBody);
    }

    @Test(description = "Decode de QrCode Estático", priority = 2)
    public void decodePixQrCodeStatic() {
        String qrCode = "00020126830014br.gov.bcb.pix01364004901d-bd85-4769-8e52-cb4c42c506dc0221Jornada pagador 330955204000053039865406105.905802BR5903Pix6008BRASILIA62290525eb6854c75c9841f7b9b174150630467A5";

        Response response = pixPaymentsPayloads.getPixQrCodeDecode(qrCode);
        response.then().statusCode(200);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_DECODE_PIX_QRCODE_ESTATICO, responseBody);
    }

    @Test(description = "Decode de QrCode Dinâmico", priority = 3)
    public void decodePixQrCodeDynamic() {
        String qrCode = "00020126940014br.gov.bcb.pix2572qr-h.sandbox.pix.bcb.gov.br/rest/api/v1/818e47c6ca3f47869bf76df985d1970f5204000053039865802BR5903Pix6008BRASILIA62070503***6304E95F";

        Response response = pixPaymentsPayloads.getPixQrCodeDecode(qrCode);
        response.then().statusCode(200);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_DECODE_PIX_QRCODE_DINAMICO, responseBody);
    }

    @Test(description = "Decode de QrCode Dinâmico com Juros e Multa", priority = 4)
    public void decodePixQrCodeDynamicDueDate() {
        String qrCodeWithDueDate = "00020126990014br.gov.bcb.pix2577qr-h.sandbox.pix.bcb.gov.br/rest/api/v2/cobv/74aee29021db46e4a624d393ab1de78b5204000053039865802BR5903Pix6008BRASILIA62070503***6304E51A";

        Response response = pixPaymentsPayloads.getPixQrCodeDecode(qrCodeWithDueDate);
        response.then().statusCode(200);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_DECODE_PIX_QRCODE_DINAMICO_DUE_DATE, responseBody);
    }

    @Test(description = "Pagamento via chave pix (CPF, E-mail, Telefone ou Aleatória)", priority = 5)
    public void paymentPixKeys() {
        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_DECODE_PIX_KEY);

        final String decodeId = jsonPathKeys.getString("data.id");
        final String pin = "1234";
        final String decodePixKeyId = decodeId;
        final Number value = 1000;
        final String description = null;
        final String paymentDate = null;

        Response response = pixPaymentsPayloads.postPixPaymentsKeys(pin, decodePixKeyId, value, description, paymentDate);
        response.then().statusCode(201);

        String responseBody = response.body().asString();
        fileOperations.saveJsonToFile(RESPONSE_PAYMENTS_PIX_KEY, responseBody);
    }
}
