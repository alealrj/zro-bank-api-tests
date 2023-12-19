package model.request.pix.devolutions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class V2WarningPixDevolutionsRequest {

    private String operation_id;

    private String  pin;

}