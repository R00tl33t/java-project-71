package hexlet.code;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) {
        return String.format("Comparing %s and %s with format %s",
            filePath1, filePath2, format);
    }
}
