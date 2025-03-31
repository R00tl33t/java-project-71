package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format)
        throws Exception {
        Map<String, Object> data1 = parseFile(filePath1);
        Map<String, Object> data2 = parseFile(filePath2);
        return String.format("File1: %s\nFile2: %s\nFormat: %s",
            data1.toString(), data2.toString(), format);
    }

    private static Map<String, Object> parseFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        String content = Files.readString(path);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(content, Map.class);
        } catch (Exception e) {
            throw new Exception("Failed to parse JSON from file '" + path + "'", e);
        }
    }
}
