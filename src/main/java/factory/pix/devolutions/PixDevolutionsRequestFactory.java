package factory.pix.devolutions;

import model.request.pix.devolutions.PixDevolutionsRequest;
import model.request.pix.devolutions.V2WarningPixDevolutionsRequest;


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
    public V2WarningPixDevolutionsRequest postV2WarningPixDevolutionsRequest (String operation_id, String pin) {

        V2WarningPixDevolutionsRequest body = V2WarningPixDevolutionsRequest.builder()
                .operation_id(operation_id)
                .pin(pin)
                .build();

        return body;
    }
}
