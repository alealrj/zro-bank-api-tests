package factory.pix;

import model.request.PixKeysRequest;

public class PixKeysRequestFactory  {

    public PixKeysRequest postCreateKey(){

        String type = "EVP";
        String key = null;

        PixKeysRequest body = PixKeysRequest.builder()
                .type(type)
                .key(key)
                .build();

        return body;
    }
}
