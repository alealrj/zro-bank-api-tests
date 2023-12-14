package factory.pix.devolutions;

import model.request.pix.devolutions.PixDevolutionsRequest;


public class PixDevolutionsRequestFactory {
    public PixDevolutionsRequest postPixDevolutionsRequest (String operation_id, Number amount, String pin, String description) {


        PixDevolutionsRequest body = PixDevolutionsRequest.builder()
                .operation_id(operation_id)
                .amount(amount)
                .pin(pin)
                .description(description)
                .build();

        return body;
    }
}
