package payloads.pix.payments;

import factory.pix.payments.PixPaymentsKeysRequestFactory;
import factory.pix.payments.PixPaymentsQrCodeRequestFactory;
import io.restassured.response.Response;
import utils.FileOperations;
import utils.TokenManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PixPaymentsPayload extends TokenManager {

    private final PixPaymentsKeysRequestFactory pixPaymentsKeysRequestFactory = new PixPaymentsKeysRequestFactory();
    private final PixPaymentsQrCodeRequestFactory pixPaymentsQrCodeRequestFactory = new PixPaymentsQrCodeRequestFactory();

    //Endpoints Api
    private static final String GET_PIX_PAYMENTS_KEY_DECODE = "/pix/keys/decode";
    private static final String GET_PIX_PAYMENTS_QRCODE_DECODE = "/pix/payments/decode";
    private static final String POST_PIX_PAYMENTS_KEY_INSTANT_BILLING = "/pix/payments/by-key/instant-billing";
    private static final String POST_PIX_PAYMENTS_QRCODE_STATIC_INSTANT_BILLING = "/pix/payments/by-qr-code/static/instant-billing";
    private static final String POST_PIX_PAYMENTS_QRCODE_DYNAMIC_INSTANT_BILLING = "/pix/payments/by-qr-code/dynamic/instant-billing";
    private static final String POST_PIX_PAYMENTS_QRCODE_DYNAMIC_DUE_DATE_BILLING = "/pix/payments/by-qr-code/dynamic/due-date-billing";

    //Metodo para decodificar chaves pix (documento, telefone, aleatória, email)
    public Response getPixKeysDecode(String type, String key) {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-transaction-uuid", FileOperations.random());
        headers.put("nonce", FileOperations.random());

        Response response = given()
                .param("type", type)
                .param("key", key)
                .headers(headers)
                .log().all()
                .get(GET_PIX_PAYMENTS_KEY_DECODE)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    //Metodo para decodificar qualquer tipo de QRCode
    public Response getPixQrCodeDecode(String qrCode) {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-transaction-uuid", FileOperations.random());
        headers.put("nonce", FileOperations.random());

        Response response = given()
                .param("emv", qrCode)
                .headers(headers)
                .log().all()
                .get(GET_PIX_PAYMENTS_QRCODE_DECODE)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response postPixPaymentsKeys(String pin, String decodePixKeyId, Number value, String description, String paymentDate) {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-transaction-uuid", FileOperations.random());
        headers.put("nonce", FileOperations.random());

        Response response = given()
                .headers(headers)
                .body(pixPaymentsKeysRequestFactory.postPixPaymentsKeys(pin, decodePixKeyId, value, description, paymentDate))
                .log().all()
                .post(POST_PIX_PAYMENTS_KEY_INSTANT_BILLING)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response postPixPaymentsQrCodeStatic(String pin, String decodePixKeyId, Number value, String description, String paymentDate) {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-transaction-uuid", FileOperations.random());
        headers.put("nonce", FileOperations.random());

        Response response = given()
                .headers(headers)
                .body(pixPaymentsQrCodeRequestFactory.pixPaymentsQrCode(pin, decodePixKeyId, value, description, paymentDate))
                .log().all()
                .post(POST_PIX_PAYMENTS_QRCODE_STATIC_INSTANT_BILLING)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response postPixPaymentQrCodeDynamic(String pin, String decodePixKeyId, Number value, String description, String paymentDate) {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-transaction-uuid", FileOperations.random());
        headers.put("nonce", FileOperations.random());

        Response response = given()
                .headers(headers)
                .body(pixPaymentsQrCodeRequestFactory.pixPaymentsQrCode(pin, decodePixKeyId, value, description, paymentDate))
                .log().all()
                .post(POST_PIX_PAYMENTS_QRCODE_DYNAMIC_INSTANT_BILLING)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response postPixPaymentQrCodeDynamicDueDate(String pin, String decodePixKeyId, Number value, String description, String paymentDate) {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-transaction-uuid", FileOperations.random());
        headers.put("nonce", FileOperations.random());

        Response response = given()
                .headers(headers)
                .body(pixPaymentsQrCodeRequestFactory.pixPaymentsQrCode(pin, decodePixKeyId, value, description, paymentDate))
                .log().all()
                .post(POST_PIX_PAYMENTS_QRCODE_DYNAMIC_DUE_DATE_BILLING)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
}

