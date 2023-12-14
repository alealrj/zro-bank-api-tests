package model.request.pix.devolutions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixDevolutionsRequest {

    private String operation_id;

    private Number amount;

    private String  pin;

    private String description;

}
