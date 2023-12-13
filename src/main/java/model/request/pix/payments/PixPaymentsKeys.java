package model.request.pix.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixPaymentsKeys {

    private String pin;

    private String decoded_pix_key_id;

    private Number value;

    private String payment_date;

    private String description;
}
