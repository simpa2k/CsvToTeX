package teXTable;

import java.util.ArrayList;
import java.util.List;

public class TeXTable {

    private static final String BEGIN = "\\begin{tabular}";
    private static final String END = "\\end{tabular}";

    private TableLayout tableLayout;
    private RowFactory rowFactory;

    private List<Row> rows = new ArrayList<>();

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

    public void append(String... values) {

        Row row = rowFactory.createRow(tableLayout.getColumnCount());

        for (String value : values) {
            row.insert(value);
        }

        rows.add(row);

    }

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
