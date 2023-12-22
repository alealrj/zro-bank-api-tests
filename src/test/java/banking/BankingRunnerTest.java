package banking;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.banking.BankingPayloads;
import utils.FileOperations;
import utils.TokenManager;

import static org.testng.Assert.assertEquals;

public class BankingRunnerTest extends TokenManager {

    final BankingPayloads bankingPayloads = new BankingPayloads();

    private static final String GET_BANKING_BANKS = "src/test/resources/test_output/banking/getBankingBanks.json";
    private static final String GET_BANKING_TED_BANKS = "src/test/resources/test_output/banking/getBankingTedBanks.json";
    private static final String GET_BANKING_TED = "src/test/resources/test_output/banking/getBankingTed.json";
    private static final String POST_BANKING_TED = "src/test/resources/test_output/banking/postBankingTed.json";
    private static final String GET_BANKING_TED_ID = "src/test/resources/test_output/banking/getBankingTedId.json";
    private static final String GET_BANKING_CONTACTS = "src/test/resources/test_output/banking/getBankingContacts.json";
    private static final String DEL_BANKING_CONTACTS = "src/test/resources/test_output/banking/delBankingContacts.json";

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
}
