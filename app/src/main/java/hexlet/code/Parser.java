package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        String content = Files.readString(path);
        String extension = getFileExtension(filePath);

        ObjectMapper mapper;
        if (extension.equals("json")) {
            mapper = new ObjectMapper();
        } else if (extension.equals("yml") || extension.equals("yaml")) {
            mapper = new ObjectMapper(new YAMLFactory());
        } else {
            throw new Exception("Unsupported file format: " + extension);
        }

        return mapper.readValue(content, Map.class);
    }

    private static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filePath.substring(dotIndex + 1);
    }
}
