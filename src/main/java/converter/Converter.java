package converter;

import org.apache.commons.io.FilenameUtils;
import teXTable.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Converter {

    private String pathToCSV;

    private TableLayoutFactory tableLayoutFactory;
    private TeXTableFactory teXTableFactory;

    public Converter(String pathToCSV, TableLayoutFactory tableLayoutFactory, TeXTableFactory teXTableFactory) {

        if (pathToCSV == null) {
            throw new IllegalArgumentException("Path to CSV may not be null.");
        }

        if (!FilenameUtils.getExtension(pathToCSV).equals("csv")) {
            throw new IllegalArgumentException("File must have csv extension.");
        }

        this.pathToCSV = pathToCSV;
        this.tableLayoutFactory = tableLayoutFactory;
        this.teXTableFactory = teXTableFactory;

    }

    public String getPathToCSV() {
        return pathToCSV;
    }

    public String convert() throws IOException {

        Stream<String> stream = Files.lines(Paths.get(pathToCSV));
        List<String[]> lines = stream.map(line -> line.split(",")).collect(Collectors.toList());

        TableLayout tableLayout = tableLayoutFactory.createTableLayout(lines.get(0).length);
        TeXTable teXTable = teXTableFactory.createTeXTable(tableLayout);

        for (String[] line : lines) {
            teXTable.append(line);
        }

        return teXTable.getTable();

    }
}
