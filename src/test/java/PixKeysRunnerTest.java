import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.pix.keys.PixKeysPayload;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.nullValue;

public class PixKeysRunnerTest {

    final PixKeysPayload pixKeysPayload = new PixKeysPayload();

    @Test(description = "Cadastro de chaves", priority = 1)
    public void cadastrarChavesPixEvpTest() {
        Response response = pixKeysPayload.postPixKeys();
        response.then().statusCode(201);

        response.then().body("success", isA(Boolean.class));
        response.then().body("data", notNullValue());
        response.then().body("data.id", isA(String.class));
        response.then().body("data.key", nullValue());
        response.then().body("data.type", equalTo("EVP"));
        response.then().body("data.state", equalTo("CONFIRMED"));
        response.then().body("data.created_at", isA(String.class));
        response.then().body("error", nullValue());
    }

    @Test(description = "Dismiss", priority = 2)
    public void dismissCreateKey() {
        Response response = pixKeysPayload.postDismiss();
        response.then().statusCode(200);

        response.then().body("success", isA(Boolean.class));
        response.then().body("data.id", isA(String.class));
        response.then().body("data.key", isA(String.class));
        response.then().body("data.type", isA(String.class));
        response.then().body("data.state", isA(String.class));
        response.then().body("data.created_at", isA(String.class));
        response.then().body("error", nullValue());
    }

    @Test(description = "Consulta de chaves Pix", priority = 3)
    public void consultaChavesPix() {
        Response response = pixKeysPayload.getPixKeys();
        response.then().statusCode(200);

        response.then().body("success", isA(Boolean.class));
        response.then().body("data.data", isA(List.class));
        response.then().body("data.data.id", everyItem(isA(String.class)));
        response.then().body("data.data.key", everyItem(isA(String.class)));
        response.then().body("data.data.type", everyItem(isA(String.class)));
        response.then().body("data.data.state", everyItem(isA(String.class)));
        response.then().body("data.data.state_description", everyItem(isA(String.class)));
        response.then().body("data.data.created_at", everyItem(isA(String.class)));
    }

    @Test(description = "Deletar chaves Pix", priority = 4)
    public void deletarChavesPixTest() {
        Response response = pixKeysPayload.deletePixKeyId();
        response.then().statusCode(200);

        response.then().body("success", equalTo(true));
        response.then().body("data", notNullValue());
        response.then().body("data.id", isA(String.class));
        response.then().body("data.key", isA(String.class));
        response.then().body("data.type", equalTo("EVP"));
        response.then().body("data.state", isA(String.class));
        response.then().body("data.created_at", isA(String.class));
        response.then().body("error", nullValue());
    }


    @Test(description = "Dismiss", priority = 5)
    public void dismissDeleteKey() {
        Response response = pixKeysPayload.postDismiss();
        response.then().statusCode(200);

        response.then().body("success", isA(Boolean.class));
        response.then().body("data.id", isA(String.class));
        response.then().body("data.key", isA(String.class));
        response.then().body("data.type", isA(String.class));
        response.then().body("data.state", isA(String.class));
        response.then().body("data.created_at", isA(String.class));
        response.then().body("error", nullValue());
    }

}
