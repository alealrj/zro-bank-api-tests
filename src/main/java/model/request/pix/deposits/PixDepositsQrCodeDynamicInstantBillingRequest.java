package model.request.pix.deposits;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixDepositsQrCodeDynamicInstantBillingRequest {

    private String key;

    private Number document_value;

    private String expiration_date;

    private String summary;

    private String description;

    private String payer_request;
}
