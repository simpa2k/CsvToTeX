package teXTable;

/**
 * Class for constructing Row objects.
 * Note that Rows can be trivially constructed manually and that this class is
 * mainly for improving testability, as a mock can be passed in advance and
 * made to generate mock Rows whenever necessary.
 */
public class RowFactory {

    /**
     * Create a row.
     *
     * @param columnCount The length of the Row.
     * @return the constructed Row object.
     */
    public Row createRow(int columnCount) {
        return new Row(columnCount);
    }
}
