package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeSet;
import java.util.Objects;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = parseFile(filePath1);
        Map<String, Object> data2 = parseFile(filePath2);
        return buildDiff(data1, data2);
    }

    private static Map<String, Object> parseFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        String content = Files.readString(path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    private static String buildDiff(Map<String, Object> data1, Map<String, Object> data2) {
        TreeSet<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder("{\n");

        for (String key : keys) {
            if (!data2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
            } else if (!data1.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                result.append("    ").append(key).append(": ").append(data1.get(key)).append("\n");
            } else {
                result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
                result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            }
        }

        return result.append("}").toString();
    }
}
