package model.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixKeysRequest {

    private String type;

    private String key;
}

