package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 1.0",
    description = "Compares two configuration files and shows a difference.",
    sortOptions = false,
    customSynopsis = "gendiff [-hV] [-f=format] filepath1 filepath2"
)
public class App implements Runnable {

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
        defaultValue = "stylish",
        paramLabel = "format"
    )
    private String format;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public void run() {
        String result = Differ.generate(filePath1, filePath2, format);
        System.out.println(result);
    }
}
