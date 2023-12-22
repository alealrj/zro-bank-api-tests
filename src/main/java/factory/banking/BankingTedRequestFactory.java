package factory.banking;

import model.request.banking.BankingTedRequest;

public class BankingTedRequestFactory {

    public BankingTedRequest postBankingTedRequest(String pin, Number amount, String beneficiary_bank_name, String beneficiary_bank_code, String beneficiary_name, String beneficiary_type, String beneficiary_document, String beneficiary_agency, String beneficiary_account, String beneficiary_account_digit, String beneficiary_account_type)  {

        BankingTedRequest body = BankingTedRequest.builder()
                .pin(pin)
                .amount(amount)
                .beneficiary_bank_name(beneficiary_bank_name)
                .beneficiary_bank_code(beneficiary_bank_code)
                .beneficiary_name(beneficiary_name)
                .beneficiary_type(beneficiary_type)
                .beneficiary_document(beneficiary_document)
                .beneficiary_agency(beneficiary_agency)
                .beneficiary_account(beneficiary_account)
                .beneficiary_account_digit(beneficiary_account_digit)
                .beneficiary_account_type(beneficiary_account_type)
                .build();

        return body;
    }
}
