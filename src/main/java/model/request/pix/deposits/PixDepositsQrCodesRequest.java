package model.request.pix.deposits;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixDepositsQrCodesRequest {

    private String key_id;

    private Number value;

    private String summary;

    private String description;
}
