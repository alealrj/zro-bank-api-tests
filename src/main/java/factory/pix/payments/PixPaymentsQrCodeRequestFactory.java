package factory.pix.payments;

import model.request.pix.payments.PixPaymentsQrCode;

public class PixPaymentsQrCodeRequestFactory {

    public PixPaymentsQrCode pixPaymentsQrCode(String pin, String decodeQrCodeId, Number value, String description, String paymentDate) {

     PixPaymentsQrCode pixPaymentsQrCode = PixPaymentsQrCode.builder()
             .pin(pin)
             .decoded_qr_code_id(decodeQrCodeId)
             .value(value)
             .description(description)
             .payment_date(paymentDate)
             .build();

     return pixPaymentsQrCode;
    }
}
