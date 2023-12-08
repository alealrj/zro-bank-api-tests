import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.pix.keys.PixKeysPayload;

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
    public void dismiss() {
        Response response = pixKeysPayload.postDismiss();
    }

    @Test(description = "Consulta de chaves Pix", priority = 3)
    public void consultaChavesPix() {
        Response response = pixKeysPayload.getPixKeys();
    }
}
