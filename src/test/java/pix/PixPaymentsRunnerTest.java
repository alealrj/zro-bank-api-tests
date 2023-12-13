package pix;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.pix.payments.PixPaymentsPayload;
import utils.FileOperations;


public class PixPaymentsRunnerTest {

    final PixPaymentsPayload pixPaymentsPayload = new PixPaymentsPayload();

    private static final String RESPONSE_DECODE_PIX_KEY = "src/test/resources/test_output/pix_payments/decode/decodePixKeys.json";
    private static final String RESPONSE_DECODE_PIX_QRCODE_ESTATICO = "src/test/resources/test_output/pix_payments/decode/decodePixQrCodeStatic.json";
    private static final String RESPONSE_DECODE_PIX_QRCODE_DINAMICO = "src/test/resources/test_output/pix_payments/decode/decodePixQrCodeDynamic.json";
    private static final String RESPONSE_DECODE_PIX_QRCODE_DINAMICO_DUE_DATE = "src/test/resources/test_output/pix_payments/decode/decodePixQrCodeDynamicDueDate.json";


    @Test(description = "Decode de chave Pix", priority = 1)
    public void decodePixKey() {
        String type = "CPF";
        String key = "44974995880";

        Response response = pixPaymentsPayload.getPixKeysDecode(type, key);
        response.then().statusCode(200);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_DECODE_PIX_KEY, responseBody);
    }

    @Test(description = "Decode de QrCode Estático", priority = 2)
    public void decodePixQrCodeStatic() {
    String qrCode = "00020126820014br.gov.bcb.pix01364004901d-bd85-4769-8e52-cb4c42c506dc0220Jornada pagador 93835204000053039865406829.825802BR5903Pix6008BRASILIA62290525e33339b8d74d4b319938b68276304F307";

    Response response = pixPaymentsPayload.getPixQrCodeDecode(qrCode);
    response.then().statusCode(200);

    String responseBody = response.body().asString();
    FileOperations.saveJsonToFile(RESPONSE_DECODE_PIX_QRCODE_ESTATICO, responseBody);
    }

    @Test(description = "Decode de QrCode Estático", priority = 2)
    public void decodePixQrCodeDynamic() {
        String qrCode = "00020126940014br.gov.bcb.pix2572qr-h.sandbox.pix.bcb.gov.br/rest/api/v2/384a6c2756b44704a6e29f8046774ab25204000053039865802BR5903Pix6008BRASILIA62070503***63048EF8";

        Response response = pixPaymentsPayload.getPixQrCodeDecode(qrCode);
        response.then().statusCode(200);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_DECODE_PIX_QRCODE_DINAMICO, responseBody);
    }

    @Test(description = "Decode de QrCode Estático", priority = 2)
    public void decodePixQrCodeDynamicDueDate() {
        String qrCode = "00020126940014br.gov.bcb.pix2572qr-h.sandbox.pix.bcb.gov.br/rest/api/v2/384a6c2756b44704a6e29f8046774ab25204000053039865802BR5903Pix6008BRASILIA62070503***63048EF8";

        Response response = pixPaymentsPayload.getPixQrCodeDecode(qrCode);
        response.then().statusCode(200);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_DECODE_PIX_QRCODE_DINAMICO_DUE_DATE, responseBody);
    }
}
