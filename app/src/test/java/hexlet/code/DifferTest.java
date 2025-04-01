package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String expectedStylish;

    @BeforeAll
    public static void beforeAll() throws Exception {
        Path path = Paths.get("src/test/resources/expected_stylish.txt");
        expectedStylish = Files.readString(path).trim();
    }

    @Test
    public void testGenerate() throws Exception {
        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";

        String actual = Differ.generate(filePath1, filePath2);
        System.out.println("=== ACTUAL ===");
        System.out.println(actual);
        System.out.println("=== EXPECTED ===");
        System.out.println(expectedStylish);
        System.out.println("==============");

        assertEquals(expectedStylish, actual);
    }


}
