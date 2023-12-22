package payloads.banking;

import factory.banking.BankingTedRequestFactory;
import factory.pix.devolutions.PixDevolutionsRequestFactory;
import io.restassured.response.Response;
import utils.FileOperations;

import static io.restassured.RestAssured.given;

public class BankingPayloads {

    private final BankingTedRequestFactory bankingTedRequestFactory = new BankingTedRequestFactory();

    private static final String GET_BANKING_BANKS = "/banking/banks";
    private static final String GET_BANKING_TED_BANKS = "/banking/ted/banks";
    private static final String GET_BANKING_TED = "/banking/ted";
    private static final String POST_BANKING_TED = "/banking/ted";
    private static final String GET_BANKING_TED_ID = "/banking/ted/{id}";
    private static final String GET_BANKING_CONTACTS = "/banking/contacts";
    private static final String DEL_BANKING_CONTACTS = "/banking/contacts";

    public Response getBankingBanks() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_BANKING_BANKS)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response getBankingTedBanks() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_BANKING_TED_BANKS)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response getBankingTed() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_BANKING_TED)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response getBankingContacts() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_BANKING_CONTACTS)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public Response postBankingTed(String pin, Number amount, String beneficiary_bank_name, String beneficiary_bank_code, String beneficiary_name, String beneficiary_type, String beneficiary_document, String beneficiary_agency, String beneficiary_account, String beneficiary_account_digit, String beneficiary_account_type) {

        Response response = given()
                .headers("x-transaction-uuid", FileOperations.random())
                .headers("nonce", FileOperations.random())
                .log().all()
                .body(bankingTedRequestFactory.postBankingTedRequest(pin, amount, beneficiary_bank_name, beneficiary_bank_code, beneficiary_name, beneficiary_type, beneficiary_document, beneficiary_agency, beneficiary_account, beneficiary_account_digit, beneficiary_account_type))
                .post(POST_BANKING_TED)
                .then().log().all()
                .extract().response();

        return response;
    }

    public Response getBankingTedId(String id){

        //Get a BankingTed status.
        Response response = given()
                .pathParam("id", id)
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_BANKING_TED_ID)
                .then().log().all()
                .extract().response();

        return response;
    }

    public Response delBankingContacts(String id) {

        Response response = given()
                .param("id", id)
                .headers("nonce", FileOperations.random())
                .log().all()
                .delete(DEL_BANKING_CONTACTS)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

}
