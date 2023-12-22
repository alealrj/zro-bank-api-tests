package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class TokenManager {

    private static final String TOKEN_ENDPOINT = "/v2/auth/signin";
    private static final String RECAPTCHA_KEY = "recaptcha-app-key";
    private static final String RECAPTCHA_TOKEN = "action-token";
    private static final String RECAPTCHA_ACTION = "action-name";
    private static final String USERNAME = "+5511912345678";
    private static final String PASSWORD = "abcd1234";

    private static String authToken;

    static {
        generateToken();
    }

    public static void generateToken() {
        BaseUrl baseUrl = new BaseUrl();  // Instância da classe BaseUrl
        String baseUri = baseUrl.getBaseUrl();

        Map<String, String> params = new HashMap<>();
        params.put("recaptcha_key", RECAPTCHA_KEY);
        params.put("recaptcha_token", RECAPTCHA_TOKEN);
        params.put("recaptcha_action", RECAPTCHA_ACTION);
        params.put("username", USERNAME);
        params.put("password", PASSWORD);

        // Chama o serviço para gerar o token de autenticação
        Response response = RestAssured.given()
                .baseUri(baseUri)
                .headers("nonce", FileOperations.random())
                .body(params)  // Envie os parâmetros como corpo da requisição JSON
                .when()
                .post(TOKEN_ENDPOINT);

        // Extrai o token da resposta
        authToken = response.jsonPath().getString("data.access_token");

        // Configura o token para ser usado em todas as requisições subsequentes
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + authToken)
                .setContentType("application/json")
                .build();
    }

    public static String getAuthToken() {
        // Verifica se o token expirou ou é nulo
        if (isTokenExpired() || authToken == null) {
            generateToken();
        }
        return authToken;
    }

    private static boolean isTokenExpired() {
        // Lógica para verificar se o token expirou
        // Se o token expirou, retorne true; caso contrário, retorne false
        // Você pode implementar sua própria lógica de verificação de expiração aqui
        return false;
    }
}
