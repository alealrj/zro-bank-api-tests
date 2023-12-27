package model.request.banking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankingTedRequest {

       private String pin;

       private Number amount;

       private String beneficiary_bank_name;

       private String beneficiary_bank_code;

       private String beneficiary_name;

       private String beneficiary_type;

       private String beneficiary_document;

       private String beneficiary_agency;

       private String beneficiary_account;

       private String beneficiary_account_digit;

       private String beneficiary_account_type;

}
