package teXTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a TeX tabular environment row. That is, a series of
 * ampersand separated values followed by a line break.
 */
public class Row {

    private int columnCount;
    private List<String> values = new ArrayList<>();

    /**
     * Constructs a Row object.
     * @param columnCount The number of columns making up the row. Must be >= 0.
     * @throws IllegalArgumentException if columnCount < 0.
     */
    public Row(int columnCount) {

        if (columnCount < 0) {
            throw new IllegalArgumentException("Column count must be positive.");
        }

        this.columnCount = columnCount;

    }

    public int getColumnCount() {
        return columnCount;
    }

    /**
     * Inserts a value into the row.
     *
     * @param value The value to be inserted, as a String.
     * @return false if the row already contains an amount of values equal to
     * the column count specified on creating the row, true if not.
     */
    public boolean insert(String value) { // ToDo: Needs input validation.

        /*
         * If there is still room on the row, add the value and return whatever
         * the list of values returns on calling add.
         */
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
