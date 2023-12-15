package payloads.pix.devolutions;

import factory.pix.devolutions.PixDevolutionsRequestFactory;
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

}
