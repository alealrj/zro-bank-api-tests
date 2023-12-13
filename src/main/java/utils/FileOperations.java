package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class FileOperations {
    public static void saveJsonToFile(String filePath, String content) {
        try {
            FileUtils.writeStringToFile(new File(filePath), content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a resposta em arquivo: " + e.getMessage());
        }
    }

    public static String readJsonFromFile(String filePath) {
        try {
            return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo JSON: " + e.getMessage());
            return null;
        }
    }
    public static String random() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
