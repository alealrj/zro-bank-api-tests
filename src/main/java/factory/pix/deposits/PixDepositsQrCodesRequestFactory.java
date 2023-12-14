package factory.pix.deposits;

import model.request.pix.deposits.PixDepositsQrCodesRequest;

public class PixDepositsQrCodesRequestFactory {

    public PixDepositsQrCodesRequest postPixDepositsQrCodesRequest (String key_id, Number value, String summary, String description) {


        PixDepositsQrCodesRequest body = PixDepositsQrCodesRequest.builder()
                .key_id(key_id)
                .value(value)
                .summary(summary)
                .description(description)
                .build();

        return body;
    }
}
