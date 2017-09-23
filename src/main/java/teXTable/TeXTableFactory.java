package teXTable;

/**
 * Class for constructing TableLayout objects.
 * Note that TeXTables can be trivially constructed manually and that this
 * class is mainly for improving testability, as a mock can be passed in
 * advance and made to generate mock TeXTables whenever necessary.
 */
public class TeXTableFactory {

    public TeXTable createTeXTable(TableLayout tableLayout) {
        return new TeXTable(tableLayout, new RowFactory());
    }
}
