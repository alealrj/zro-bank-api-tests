package pix;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.pix.devolutions.PixDevolutionsPayloads;
import utils.FileOperations;

public class PixDevolutionsRunnerTest {

    final PixDevolutionsPayloads pixDevolutionsPayloads = new PixDevolutionsPayloads();

    private static final String RESPONSE_POST_PIX_DEVOLUTIONS = "src/test/resources/test_output/pix_devolutions/post_devolutions.json";
    @Test(description = "", priority = 1)
    public void pixDevolutions() {

        String operation_id = "3df8ed4b-5564-461b-80ca-4189a47cc2f3";
        Number amount=  50;
        String pin = "1234";
        String description = "Testet";

        Response response = pixDevolutionsPayloads.postPixDevolutions(operation_id, amount, pin, description);
        String responseBody = response.body().asString();
        FileOperations.saveJsonToFile(RESPONSE_POST_PIX_DEVOLUTIONS, responseBody);

    }
}
