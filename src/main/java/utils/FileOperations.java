package utils;

import io.restassured.path.json.JsonPath;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FileOperations {
    public static void saveJsonToFile(String filePath, String content) {
        try {
            FileUtils.writeStringToFile(new File(filePath), content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a resposta em arquivo: " + e.getMessage());
        }
    }

    public JsonPath readJsonFileAsJsonPath(String filePath) {
        try {
            String content = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
            return new JsonPath(content);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo JSON: " + e.getMessage());
            return null;
        }
    }

    public static String getFormattedExpirationDate() {
        // Obtém a data atual
        LocalDate currentDate = LocalDate.now();

        // Adiciona três dias
        LocalDate expirationDate = currentDate.plusDays(3);

        // Formata a data no formato desejado ("yyyy-MM-dd")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return expirationDate.format(formatter);
    }

    public static String random() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
