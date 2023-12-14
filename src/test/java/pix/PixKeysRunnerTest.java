package pix;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.pix.keys.PixKeysPayloads;
import utils.FileOperations;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.nullValue;

public class PixKeysRunnerTest {

    final PixKeysPayloads pixKeysPayloads = new PixKeysPayloads();

    private static final String RESPONSE_POST_PIX_KEYS = "src/test/resources/test_output/pix_keys/post_pix_keys.json";
    private static final String RESPONSE_POST_PIX_DISMISS = "src/test/resources/test_output/pix_keys/post_pix_keys_id_dismiss.json";
    private static final String RESPONSE_GET_PIX_KEYS = "src/test/resources/test_output/pix_keys/get_v2_pix_keys.json";
    private static final String RESPONSE_DELETE_PIX_KEYS = "src/test/resources/test_output/pix_keys/del_pix_keys_id.json";
    private static final String RESPONSE_DELETE_PIX_KEYS_DISMISS = "src/test/resources/test_output/pix_keys/del_pix_keys_id_dismiss.json";

    @Test(description = "Cadastro de chaves", priority = 1)
    public void cadastrarChavesPixEvpTest() {
        Response response = pixKeysPayloads.postPixKeys();
        response.then().statusCode(201);

        response.then().body("success", isA(Boolean.class));
        response.then().body("data", notNullValue());
        response.then().body("data.id", isA(String.class));
        response.then().body("data.key", nullValue());
        response.then().body("data.type", equalTo("EVP"));
        response.then().body("data.state", equalTo("CONFIRMED"));
        response.then().body("data.created_at", isA(String.class));
        response.then().body("error", nullValue());

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_POST_PIX_KEYS, responseBody);
    }

    @Test(description = "Dismiss", priority = 2)
    public void dismissCreateKey() {
        Response response = pixKeysPayloads.postDismiss();
        response.then().statusCode(200);

        response.then().body("success", isA(Boolean.class));
        response.then().body("data.id", isA(String.class));
        response.then().body("data.key", isA(String.class));
        response.then().body("data.type", isA(String.class));
        response.then().body("data.state", isA(String.class));
        response.then().body("data.created_at", isA(String.class));
        response.then().body("error", nullValue());

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_POST_PIX_DISMISS, responseBody);
    }

    @Test(description = "Consulta de chaves Pix", priority = 3)
    public void consultaChavesPix() {
        Response response = pixKeysPayloads.getPixKeys();
        response.then().statusCode(200);

        response.then().body("success", isA(Boolean.class));
        response.then().body("data.data", isA(List.class));
        response.then().body("data.data.id", everyItem(isA(String.class)));
        response.then().body("data.data.key", everyItem(isA(String.class)));
        response.then().body("data.data.type", everyItem(isA(String.class)));
        response.then().body("data.data.state", everyItem(isA(String.class)));
        response.then().body("data.data.state_description", everyItem(isA(String.class)));
        response.then().body("data.data.created_at", everyItem(isA(String.class)));

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_GET_PIX_KEYS, responseBody);
    }

    @Test(description = "Deletar chaves Pix", priority = 4)
    public void deletarChavesPixTest() {
        Response response = pixKeysPayloads.deletePixKeyId();
        response.then().statusCode(200);

        response.then().body("success", equalTo(true));
        response.then().body("data", notNullValue());
        response.then().body("data.id", isA(String.class));
        response.then().body("data.key", isA(String.class));
        response.then().body("data.type", isA(String.class));
        response.then().body("data.state", isA(String.class));
        response.then().body("data.created_at", isA(String.class));
        response.then().body("error", nullValue());

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_DELETE_PIX_KEYS, responseBody);
    }


    @Test(description = "Dismiss", priority = 5)
    public void dismissDeleteKey() {
        Response response = pixKeysPayloads.postDismiss();
        response.then().statusCode(200);

        response.then().body("success", isA(Boolean.class));
        response.then().body("data.id", isA(String.class));
        response.then().body("data.key", isA(String.class));
        response.then().body("data.type", isA(String.class));
        response.then().body("data.state", isA(String.class));
        response.then().body("data.created_at", isA(String.class));
        response.then().body("error", nullValue());

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_DELETE_PIX_KEYS_DISMISS, responseBody);
    }

}
