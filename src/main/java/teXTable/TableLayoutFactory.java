package teXTable;

/**
 * Class for constructing TableLayout objects.
 * Note that TableLayouts can be trivially constructed manually and that this
 * class is mainly for improving testability, as a mock can be passed in
 * advance and made to generate mock Rows whenever necessary.
 */
public class TableLayoutFactory {

    /**
     * Creates a TableLayout.
     *
     * @param columnCount The number of columns in the table.
     * @return the constructed TableLayout.
     */
    public TableLayout createTableLayout(int columnCount) {
        return new TableLayout(columnCount);
    }
}
