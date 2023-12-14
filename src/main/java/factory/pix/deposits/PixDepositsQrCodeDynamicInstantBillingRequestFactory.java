package factory.pix.deposits;

import model.request.pix.deposits.PixDepositsQrCodeDynamicInstantBillingRequest;

public class PixDepositsQrCodeDynamicInstantBillingRequestFactory {

    public PixDepositsQrCodeDynamicInstantBillingRequest postPixDepositsQrCodeDynamicInstantBillingRequest (String key,
                                                                                                            Number document_value,
                                                                                                            String expiration_date,
                                                                                                            String summary, String description,
                                                                                                            String payer_request) {

        PixDepositsQrCodeDynamicInstantBillingRequest body = PixDepositsQrCodeDynamicInstantBillingRequest.builder()
                .key(key)
                .document_value(document_value)
                .expiration_date(expiration_date)
                .summary(summary)
                .description(description)
                .payer_request(payer_request)
                .build();

        return body;
    }
}
