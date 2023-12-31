package factory.pix.deposits;

import model.request.pix.deposits.PixDepositsQrCodeDynamicDueDateRequest;
import model.request.pix.deposits.PixDepositsQrCodeDynamicInstantBillingRequest;
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

    public PixDepositsQrCodeDynamicDueDateRequest postPixDepositsQrCodeDynamicDueDateRequest(String key, Number document_value, String due_date,
                                                                                             String expiration_date, String summary, String description,
                                                                                             String payer_city, String payer_person_type, String payer_document,
                                                                                             String payer_name, String payer_email, String payer_phone,
                                                                                             String payer_address, String payer_request) {

        PixDepositsQrCodeDynamicDueDateRequest body = PixDepositsQrCodeDynamicDueDateRequest.builder()
                .key(key)
                .document_value(document_value)
                .due_date(due_date)
                .expiration_date(expiration_date)
                .summary(summary)
                .description(description)
                .payer_city(payer_city)
                .payer_person_type(payer_person_type)
                .payer_document(payer_document)
                .payer_name(payer_name)
                .payer_email(payer_email)
                .payer_phone(payer_phone)
                .payer_address(payer_address)
                .payer_request(payer_request)
                .build();

        return body;
    }
}
