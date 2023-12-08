package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseUrl {

    // Nome do arquivo de propriedades
    private static final String PROPERTIES_FILE = "application-dev.properties";

    // Propriedades carregadas do arquivo
    private static final Properties properties = loadProperties();

    // URL base
    private static final String BASE_URL = properties.getProperty("application.dev.zrobank");

    static {
        // Configura a URL base do RestAssured no bloco estático
        RestAssured.baseURI = BASE_URL;

        // Configura outras propriedades, se necessário
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType("application/json");
        RestAssured.requestSpecification = requestSpecBuilder.build();
    }

    // Método para obter a URL base
    public static String getBaseUrl() {
        return BASE_URL;
    }

    // Método para carregar propriedades do arquivo
    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = BaseUrl.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Unable to find " + PROPERTIES_FILE + " file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}