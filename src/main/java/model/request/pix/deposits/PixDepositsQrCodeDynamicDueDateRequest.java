package model.request.pix.deposits;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixDepositsQrCodeDynamicDueDateRequest {

    private String key;

    private Number document_value;

    private String due_date;

    private String expiration_date;

    private String summary;

    private String description;

    private String payer_city;

    private String payer_person_type;

    private String payer_document;

    private String payer_name;

    private String payer_email;

    private String payer_phone;

    private String payer_address;

    private String payer_request;
}
