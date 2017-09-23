package teXTable;

public class TeXTableFactory {

    public TeXTable createTeXTable(TableLayout tableLayout) {
        return new TeXTable(tableLayout, new RowFactory());
    }
}
