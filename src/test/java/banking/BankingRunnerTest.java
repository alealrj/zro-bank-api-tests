package banking;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.banking.BankingPayloads;
import utils.FileOperations;
import utils.TokenManager;

import static org.testng.Assert.assertEquals;

public class BankingRunnerTest extends TokenManager {

    final BankingPayloads bankingPayloads = new BankingPayloads();
    private final FileOperations fileOperations = new FileOperations();

    private  static final String GET_BANKING_TED_ID = "schemas/banking/getBankingTedId.json";

    private static final String GET_BANKING_BANKS = "src/test/resources/test_output/banking/getBankingBanks.json";
    private static final String GET_BANKING_TED_BANKS = "src/test/resources/test_output/banking/getBankingTedBanks.json";
    private static final String GET_BANKING_TED = "src/test/resources/test_output/banking/getBankingTed.json";
    private static final String GET_BANKING_CONTACTS = "src/test/resources/test_output/banking/getBankingContacts.json";

    private static final String RESPONSE_POST_BANKING_TED = "src/test/resources/test_output/banking/postBankingTed.json";
    private static final String RESPONSE_GET_BANKING_TED_ID  = "src/test/resources/test_output/banking/getBankingTedId.json";
    private static final String RESPONSE_DEL_BANKING_CONTACTS = "src/test/resources/test_output/banking/delBankingContacts.json";

    @Test(description = "Get Banking Banks", priority = 1)
    public void getBankingBanks() {

        Response response = bankingPayloads.getBankingBanks();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Banking Banks" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_BANKING_BANKS, responseBody);
    }

    @Test(description = "Get Banking Ted Banks", priority = 2)
    public void getBankingTedBanks() {

        Response response = bankingPayloads.getBankingTedBanks();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Banking Ted Banks" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_BANKING_TED_BANKS, responseBody);
    }

    @Test(description = "Get Banking Ted", priority = 3)
    public void getBankingTed() {

        Response response = bankingPayloads.getBankingTed();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Banking Ted" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_BANKING_TED, responseBody);
    }

    @Test(description = "Get Banking Contacts", priority = 4)
    public void getBankingContacts() {

        Response response = bankingPayloads.getBankingContacts();
        int statusCode = 200;
        assertEquals(response.getStatusCode(), statusCode,
                "Falha ao buscar Banking Contacts" + statusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(GET_BANKING_CONTACTS, responseBody);
    }

    @Test(description = "Crie um banco de moedaTed", priority = 5)
    public void postBankingTed() {
        String pin = "1234";
        Number amount = 2300;
        String beneficiary_bank_name = "Banco Bradesco S.A.";
        String beneficiary_bank_code = "237";
        String beneficiary_name = "Automation test";
        String beneficiary_type = "fisico";
        String beneficiary_document = "90933356005";
        String beneficiary_agency = "0001";
        String beneficiary_account = "111111";
        String beneficiary_account_digit = "10";
        String beneficiary_account_type= "CC";


        Response response = bankingPayloads.postBankingTed( pin, amount, beneficiary_bank_name, beneficiary_bank_code, beneficiary_name, beneficiary_type, beneficiary_document, beneficiary_agency, beneficiary_account, beneficiary_account_digit, beneficiary_account_type);
        response.then().statusCode(201);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_POST_BANKING_TED, responseBody);
    }

    @Test(description = "Obtenha um status BankingTed.", priority = 5)
    public void getBankingTedId() {

    JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_BANKING_TED);
        String id = jsonPathKeys.getString( "data.id");

        Response response = bankingPayloads.getBankingTedId(id);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_BANKING_TED_ID));
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao buscar um status BankingTed." + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_GET_BANKING_TED_ID, responseBody);
    }

    @Test(description = "Deleta Banking Contacts", priority = 6)
    public void delBankingContacts() {
        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(GET_BANKING_CONTACTS);
        String id = jsonPathKeys.getString("data.data.id[3]");

        Response response = bankingPayloads.delBankingContacts(id);
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                    "Falha ao Deletar Contacts " + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_DEL_BANKING_CONTACTS, responseBody);
    }
}
