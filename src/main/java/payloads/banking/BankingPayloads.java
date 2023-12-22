package payloads.banking;

import io.restassured.response.Response;
import utils.FileOperations;

import static io.restassured.RestAssured.given;

public class BankingPayloads {

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
}
