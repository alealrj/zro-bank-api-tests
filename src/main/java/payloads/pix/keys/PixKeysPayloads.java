package payloads.pix.keys;

import factory.pix.keys.PixKeysRequestFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.FileOperations;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class PixKeysPayloads extends TokenManager {

    // Inicialização de serviços e utilidades
    private final PixKeysRequestFactory pixKeysRequestFactory = new PixKeysRequestFactory();
    private final FileOperations fileOperations = new FileOperations();

    // Endpoints da API
    private static final String POST_PIX_KEYS = "/pix/keys";
    private static final String GET_PIX_KEYS = "/v2/pix/keys";
    private static final String GET_PIX_KEYS_ID = "/pix/keys/{id}";
    private static final String POST_PIX_KEYS_ID_DISMISS = "/pix/keys/{id}/dismiss";
    private static final String DELETE_PIX_ID = "/pix/keys/{id}";

    private static final String RESPONSE_POST_PIX_KEYS = "src/test/resources/test_output/pix/keys/post_pix_keys.json";
    private static final String RESPONSE_GET_PIX_KEYS = "src/test/resources/test_output/pix/keys/get_v2_pix_keys.json";


    public Response postPixKeys() {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .body(pixKeysRequestFactory.postCreateKey())
                .post(POST_PIX_KEYS)
                .then().log().all()
                .extract().response();

        return response;
    }

    public Response postDismiss() {
        // Leia o arquivo de resposta da criação de chaves Pix
        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_PIX_KEYS);
        String id = jsonPathKeys.getString("data.id");

        // Faça a solicitação POST para realizar o dismiss de chaves Pix
        Response response = given()
                .pathParam("id", id)
                .headers("nonce", FileOperations.random())  // Defina os headers
                .log().all()
                .post(POST_PIX_KEYS_ID_DISMISS)
                .then().log().all()
                .extract().response();

        return response;
    }

    public Response getPixKeys() {

        // Faça a solicitação GET para obter chaves Pix
        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PIX_KEYS)
                .then().log().all()
                .extract().response();

        return response;
    }


    public Response getPixKeysId(){

        // Lê o JSON para obter o ID da chave que você deseja excluir
        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_PIX_KEYS);
        String id = jsonPathKeys.getString("data.id");

        // Faça a solicitação GET para obter chaves Pix
        Response response = given()
                .pathParam("id", id)
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PIX_KEYS_ID)
                .then().log().all()
                .extract().response();

        return response;
    }

    public Response deletePixKeyId() {

        // Lê o JSON para obter o ID da chave que você deseja excluir
        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_PIX_KEYS);
        String id = jsonPathKeys.getString("data.id");

        // Faça a solicitação DELETE para excluir a chave
        Response response = given()
                .pathParam("id", id)
                .headers("nonce", FileOperations.random())
                .log().all()
                .delete(DELETE_PIX_ID)
                .then().log().all()
                .extract().response();

        return response;
    }
}
