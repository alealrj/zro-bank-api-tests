package payloads.pix.devolutions;

import factory.pix.devolutions.PixDevolutionsRequestFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.FileOperations;
import utils.TokenManager;

import static io.restassured.RestAssured.given;
public class PixDevolutionsPayloads extends TokenManager {

    //Inicialização de serviços e utilidades
    private final PixDevolutionsRequestFactory pixDevolutionsRequestFactory = new PixDevolutionsRequestFactory();
    private final FileOperations fileOperations = new FileOperations();

    //Endpoints da API
    private static final String POST_PIX_DEVOLUTIONS = "/pix/devolutions";
    private static final String GET_PIX_DEVOLUTIONS_ID = "/pix/devolutions/{id}";
    private static  final String GET_PIX_DEVOLUTIONS = "/pix/devolutions";
    private static final String GET_PIX_DEVOLUTIONS_RECEIVED = "/pix/devolutions-received";
    private static final String GET_PIX_DEVOLUTIONS_RECEIVED_ID = "/pix/devolutions-received/{id}";
    private static final String POST_V2_PIX_WARNING_DEVOLUTIONS = "/v2/pix/warning-pix-devolution";
    private static final String GET_WARNING_PIX_DEVOLUTIONS_ID = "/pix/warning-pix-devolution/{id}";

    private static final String RESPONSE_POST_PIX_DEVOLUTIONS = "src/test/resources/test_output/pix_devolutions/post_devolutions.json";
    private static final String RESPONSE_POST_V2_WARNING_PIX_DEVOLUTIONS = "src/test/resources/test_output/pix_devolutions/post_v2_warning_pix_devolutions.json";

    public Response postPixDevolutions(String operation_id, Number amount, String pin, String description) {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .body(pixDevolutionsRequestFactory.postPixDevolutionsRequest(operation_id, amount, pin, description))
                .post(POST_PIX_DEVOLUTIONS)
                .then().log().all()
                .extract().response();

        return response;
    }

    public Response getPixDevolutionId(){

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_PIX_DEVOLUTIONS);
        String id = jsonPathKeys.getString("data.id");

        //Faça a solicitação GET para obter Pix Devolutions by ID
        Response response = given()
                .pathParam("id", id)
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PIX_DEVOLUTIONS_ID)
                .then().log().all()
                .extract().response();

        return response;
    }
    public Response getPixDevolutions(){

        String page = "1";
        String size = "20";
        String order = "desc";
        String sort = "created_at";
        String states = "CONFIRMED";

        //Faça a solicitação GET para obter Pix Devolutions
        Response response = given()
                .pathParams(page, size, order, sort, states)
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PIX_DEVOLUTIONS)
                .then().log().all()
                .extract().response();

        return response;
    }
    public Response getPixDevolutionsReceived(){

        //Faça a solicitação GET para obter Pix Devolutions
        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PIX_DEVOLUTIONS_RECEIVED)
                .then().log().all()
                .extract().response();

        return response;
    }
    public Response getPixDevolutionsReceivedId(){

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_PIX_DEVOLUTIONS);
        String id = jsonPathKeys.getString("data.id");

        //Faça a solicitação GET para obter Pix Devolutions
        Response response = given()
                .param("id", id)
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_PIX_DEVOLUTIONS_RECEIVED_ID)
                .then().log().all()
                .extract().response();

        return response;
    }
    public Response postV2WarningPixDevolutions(String pin, String operation_id) {

        Response response = given()
                .headers("nonce", FileOperations.random())
                .log().all()
                .body(pixDevolutionsRequestFactory.postV2WarningPixDevolutionsRequest(operation_id, pin))
                .post(POST_V2_PIX_WARNING_DEVOLUTIONS)
                .then().log().all()
                .extract().response();

        return response;
    }
    public Response getWarningPixDevolutionId(){

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_V2_WARNING_PIX_DEVOLUTIONS);
        String id = jsonPathKeys.getString("data.id");

        //Faça a solicitação GET para obter Warning Pix Devolutions by ID
        Response response = given()
                .pathParam("id", id)
                .headers("nonce", FileOperations.random())
                .log().all()
                .get(GET_WARNING_PIX_DEVOLUTIONS_ID)
                .then().log().all()
                .extract().response();

        return response;
    }
}
