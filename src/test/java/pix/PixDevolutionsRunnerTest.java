package pix;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.pix.devolutions.PixDevolutionsPayloads;
import utils.FileOperations;

import static org.testng.Assert.assertEquals;

public class PixDevolutionsRunnerTest {

    final PixDevolutionsPayloads pixDevolutionsPayloads = new PixDevolutionsPayloads();
    private final FileOperations fileOperations = new FileOperations();

    final String GET_PIX_DEVOLUTIONS = "schemas/pix/devolutions/getPixDevolutions.json";
    final String GET_PIX_DEVOLUTIONS_ID = "schemas/pix/devolutions/getPixDevolutionsId.json";
    final  String GET_PIX_DEVOLUTIONS_RECEIVED = "schemas/pix/devolutions/getPixDevolutionsReceived.json";
    final  String GET_PIX_DEVOLUTIONS_RECEIVED_ID = "schemas/pix/devolutions/getPixDevolutionsReceivedId.json";
    final  String GET_WARNING_PIX_DEVOLUTIONS_ID = "schemas/pix/devolutions/getWarningPixDevolutionsId.json";

    private static final String RESPONSE_POST_PIX_DEVOLUTIONS = "src/test/resources/test_output/pix_devolutions/post_devolutions.json";
    private static final String RESPONSE_GET_PIX_DEVOLUTIONS_ID = "src/test/resources/test_output/pix_devolutions/get_devolutions_id.json";
    private static final String RESPONSE_GET_PIX_DEVOLUTIONS = "src/test/resources/test_output/pix_devolutions/get_devolutions.json";
    private static final String RESPONSE_GET_PIX_DEVOLUTIONS_RECEIVED = "src/test/resources/test_output/pix_devolutions/get_devolutions_received.json";
    private static final String RESPONSE_GET_PIX_DEVOLUTIONS_RECEIVED_ID = "src/test/resources/test_output/pix_devolutions/get_devolutions_received_id.json";
    private static final String RESPONSE_POST_V2_WARNING_PIX_DEVOLUTIONS_ID = "rc/test/resources/test_output/pix_devolutions/post_v2_warning_pix_devolutions_id.json";
    private static final String RESPONSE_GET_WARNING_PIX_DEVOLUTIONS_RECEIVED_ID = "src/test/resources/test_output/pix_devolutions/get_warning_devolutions_id.json";


    @Test(description = "Create Pix devolutions", priority = 1)
    public void pixDevolutions() {

        String operation_id = "2bed301a-6da9-483f-b887-100e00e305fc";
        Number amount=  7;
        String pin = "1234";
        String description = "Testet";

        Response response = pixDevolutionsPayloads.postPixDevolutions(operation_id, amount, pin, description);
        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_POST_PIX_DEVOLUTIONS, responseBody);

    }

    @Test(description = "Buscar Pix Devolutions by ID", priority = 2)
    public void getPixDevolutionsId() {

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_PIX_DEVOLUTIONS);
        String id = jsonPathKeys.getString( "data.id");

        Response response = pixDevolutionsPayloads.getPixDevolutionsId(id);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_PIX_DEVOLUTIONS_ID));
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao buscar Pix Devolutions por ID" + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_GET_PIX_DEVOLUTIONS_ID, responseBody);
    }
    @Test(description = "List Pix Devolutions", priority = 3)
    public void getPixDevolutions() {

        Response response = pixDevolutionsPayloads.getPixDevolutions();
//        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_PIX_DEVOLUTIONS));
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao Buscar Pix Devolutions " + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_GET_PIX_DEVOLUTIONS, responseBody);
    }

    @Test(description = "Buscar Pix Devolutions Received", priority = 4)
    public void getPixDevolutionsReceived() {

        Response response = pixDevolutionsPayloads.getPixDevolutionsReceived();
//        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_PIX_DEVOLUTIONS_RECEIVED));
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao Buscar Pix Devolutions Received" + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_GET_PIX_DEVOLUTIONS_RECEIVED, responseBody);
    }

    @Test(description = "Buscar Pix Devolutions Received ID", priority = 5)
    public void getPixDevolutionsReceivedId() {

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_GET_PIX_DEVOLUTIONS_RECEIVED);
        String id = jsonPathKeys.getString( "data.data.id[0]");

        Response response = pixDevolutionsPayloads.getPixDevolutionsReceivedId(id);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_PIX_DEVOLUTIONS_RECEIVED_ID));
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao buscar Pix Devolutions por ID" + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_GET_PIX_DEVOLUTIONS_RECEIVED_ID, responseBody);
    }

    @Test(description = "Buscar por Warning Pix Devolutions ID", priority = 6)
    public void getWarningPixDevolutionId() {

        JsonPath jsonPathKeys = fileOperations.readJsonFileAsJsonPath(RESPONSE_POST_V2_WARNING_PIX_DEVOLUTIONS_ID);
        String id = jsonPathKeys.getString( "data.id");

        Response response = pixDevolutionsPayloads.getWarningPixDevolutionId(id);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_WARNING_PIX_DEVOLUTIONS_ID));
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Falha ao buscar Warning Pix Devolutions por ID" + expectedStatusCode);

        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_GET_WARNING_PIX_DEVOLUTIONS_RECEIVED_ID, responseBody);
    }
}
