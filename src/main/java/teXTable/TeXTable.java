package teXTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a TeX tabular environment table.
 */
public class TeXTable {

    private static final String BEGIN = "\\begin{tabular}";
    private static final String END = "\\end{tabular}";

    private TableLayout tableLayout;
    private RowFactory rowFactory;

    private List<Row> rows = new ArrayList<>();

    /**
     * Constructs a TeXTable.
     * @param tableLayout A TableLayout object, indicating number of columns
     *                    and alignment of cell content. Null throws
     *                    IllegalArgumentException.
     * @param rowFactory A RowFactory object. Null throws
     *                   IllegalArgumentException.
     * @throws IllegalArgumentException If tableLayout or rowFactory is/are null.
     */
    public TeXTable(TableLayout tableLayout, RowFactory rowFactory) {

        if (tableLayout == null) {
            throw new IllegalArgumentException("Layout may not be null.");
        }

        if (rowFactory == null) {
            throw new IllegalArgumentException("Row factory may not be null.");
        }

        this.tableLayout = tableLayout;
        this.rowFactory = rowFactory;

    }

    /**
     * Appends an array of Strings. to the table.
     * @param values
     */
    public void append(String... values) {

        Row row = rowFactory.createRow(tableLayout.getColumnCount());

        for (String value : values) {
            row.insert(value);
        }

        rows.add(row);

    }

    /**
     * Returns the table in TeX tabular environment format, as a String.
     * Formatted for readability.
     *
     * @return a TeX tabular environment table, as a String.
     */
    public String getTable() {

        StringBuilder table = new StringBuilder();
        
        table.append(BEGIN);
        table.append(tableLayout.toString());

        table.append("\n");

        if (rows.size() > 0) {
            table.append("    \\hline");
        }

        for (Row row : rows) {

            table.append("\n    ");
            table.append(row.toString());
            table.append("\n    ");

            table.append("\\hline");

        }

        table.append("\n");
        table.append(END);
        
        return table.toString();

    }
}
