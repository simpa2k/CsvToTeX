package converter;

import org.apache.commons.io.FilenameUtils;
import parser.Parser;
import teXTable.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for converting a table in .csv (Comma-Separated Values) format to a
 * TeX tabular environment table.
 */
public class Converter {

    private String pathToCSV;

    private Parser parser;
    private TableLayoutFactory tableLayoutFactory;
    private TeXTableFactory teXTableFactory;

    private List<String[]> lines;

    /**
     * Constructs a CSV to TeX tabular environment table converter.
     *
     * @param pathToCSV A path to a valid .csv file. Must have a .csv extension. Null throws IllegalArgumentException.
     * @param tableLayoutFactory A TableLayout factory. Null throws IllegalArgumentException.
     * @param teXTableFactory A TeXTable factory. Null throws IllegalArgumentException.
     * @throws IllegalArgumentException If pathToCSV, tableLayoutFactory or
     * teXTableFactory is/are null, or if the specified CSV file does not have
     * a .csv extension.
     */
    public Converter(String pathToCSV,
                     Parser parser,
                     TableLayoutFactory tableLayoutFactory,
                     TeXTableFactory teXTableFactory) {

        if (pathToCSV == null) {
            throw new IllegalArgumentException("Path to CSV may not be null.");
        }

        if (tableLayoutFactory == null) {
            throw new IllegalArgumentException("tableLayoutFactory may not be null.");
        }

        if (teXTableFactory == null) {
            throw new IllegalArgumentException("teXTableFactory may not be null.");
        }

        if (!FilenameUtils.getExtension(pathToCSV).equals("csv")) {
            throw new IllegalArgumentException("File must have .csv extension.");
        }

        this.pathToCSV = pathToCSV;
        this.parser = parser;
        this.tableLayoutFactory = tableLayoutFactory;
        this.teXTableFactory = teXTableFactory;

    }

    public String getPathToCSV() {
        return pathToCSV;
    }

    /**
     * Parses the contents of the CSV file specified at construction.
     */
    public void parse() throws IOException {

        Stream<String> stream = Files.lines(Paths.get(pathToCSV));
        lines = parser.parse(stream);

    }

    /**
     * Analyzes the underlying table/graph structure and removes redundant
     * paths.
     */
    public void reduce() {

    }

    /**
     * Converts the contents of the CSV file specified at construction to a TeX
     * tabular environment table.
     * @return The TeX tabular environment table, as a String.
     * @throws IOException If the CSV file specified on constructing the
     * converter can not be opened.
     */
    public String convert() throws IOException {

        Stream<String> stream = Files.lines(Paths.get(pathToCSV));
        List<String[]> lines = parser.parse(stream);

        /*
         * Create a TeXTable with a column count equal to that of the first line.
         * ToDo: This should probably pick out the longest line, or somehow notify if lines are not of equal length.
         */
        TableLayout tableLayout = tableLayoutFactory.createTableLayout(lines.get(0).length);
        TeXTable teXTable = teXTableFactory.createTeXTable(tableLayout);

        /*
         * Append the lines to the table.
         */
        for (String[] line : lines) {
            teXTable.append(line);
        }

        return teXTable.getTable();

    }
}
