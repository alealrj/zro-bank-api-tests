package payloads.pix.deposits;

import factory.pix.deposits.PixDepositsQrCodesRequestFactory;
import utils.FileOperations;

public class PixDepositsPayloads {

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


}
