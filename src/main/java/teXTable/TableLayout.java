package teXTable;

public class TableLayout {

    private int columnCount;
    private String alignment = "c";

    public TableLayout(int columnCount) {

        if (columnCount < 0) {
            throw new IllegalArgumentException("Number of columns must be positive.");
        }

        this.columnCount = columnCount;

    }

    public TableLayout() {
        this(1);
    }

    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String toString() {

        StringBuilder layout = new StringBuilder();

        layout.append("{|");

        for (int i = 0; i < columnCount; i++) {

            layout.append(" ");
            layout.append(alignment);
            layout.append(" |");

        }

        if (columnCount == 0) { // ToDo: Is this a good way of handling  columnCount == 0?
            layout.append("|");
        }

        layout.append("}");

        return layout.toString();

    }
}
