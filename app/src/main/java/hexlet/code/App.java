package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 1.0",
    description = "Compares two configuration files and shows a difference.",
    sortOptions = false,
    customSynopsis = "gendiff [-hV] [-f=format] filepath1 filepath2"
)
public class App implements Callable<Integer> {

    @Parameters(
        index = "0",
        description = "path to first file",
        paramLabel = "filepath1"
    )
    private String filePath1;

    @Parameters(
        index = "1",
        description = "path to second file",
        paramLabel = "filepath2"
    )
    private String filePath2;

    @Option(
        names = {"-f", "--format"},
        description = "output format [default: ${DEFAULT-VALUE}]",
        defaultValue = "json",
        paramLabel = "format"
    )
    private String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        try {
            String result = Differ.generate(filePath1, filePath2, format);
            System.out.println(result);
            return 0;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return 1;
        }
    }
}
