package hexlet.code;

import java.util.Map;
import java.util.TreeSet;
import java.util.Objects;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = Parser.parse(filePath1);
        Map<String, Object> data2 = Parser.parse(filePath2);
        return buildDiff(data1, data2);
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