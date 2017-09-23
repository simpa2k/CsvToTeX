package teXTable;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private int columnCount;
    private List<String> values = new ArrayList<>();

    public Row(int columnCount) {

        if (columnCount < 0) {
            throw new IllegalArgumentException("Column count must be positive.");
        }

        this.columnCount = columnCount;

    }

    public int getColumnCount() {
        return columnCount;
    }

    public boolean insert(String value) {
        return values.size() != columnCount && values.add(value);
    }

    @Override
    public String toString() {

        StringBuilder rowString = new StringBuilder();

        boolean first = true;

        for (String value : values) {

            if (first) {
                first = false;
            } else {
                rowString.append( " & ");
            }

            rowString.append(value);

        }

        rowString.append(" \\\\");

        return rowString.toString();

    }
}
