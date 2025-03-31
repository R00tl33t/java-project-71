package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
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
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        TreeMap<String, String> diff = new TreeMap<>();

        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!data1.containsKey(key)) {
                diff.put(key, "+ " + key + ": " + value2);
            } else if (!data2.containsKey(key)) {
                diff.put(key, "- " + key + ": " + value1);
            } else if (value1.equals(value2)) {
                diff.put(key, "  " + key + ": " + value1);
            } else {
                diff.put(key, "- " + key + ": " + value1 + "\n" +
                    "+ " + key + ": " + value2);
            }
        }
        StringBuilder result = new StringBuilder("{\n");
        for (Map.Entry<String, String> entry : diff.entrySet()) {
            result.append("  ").append(entry.getValue().replace("\n", "\n  ")).append("\n");
        }
        result.append("}");
        return result.toString();
    }
}
