package factory.pix.payments;

import model.request.pix.payments.PixPaymentsKeys;

public class PixPaymentsKeysRequestFactory {

    public PixPaymentsKeys postPixPaymentsKeys (String pin, String decodePixKeyId, Number value, String description, String paymentDate) {

        PixPaymentsKeys pixPaymentsKeys = PixPaymentsKeys.builder()
                .pin(pin)
                .decoded_pix_key_id(decodePixKeyId)
                .value(value)
                .description(description)
                .payment_date(paymentDate)
                .build();

        return pixPaymentsKeys;
    }
}
