package model.request.pix.keys;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixKeysRequest {

    private String type;

    private String key;
}

