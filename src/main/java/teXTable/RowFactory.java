package teXTable;

public class RowFactory {

    public Row createRow(int columnCount) {
        return new Row(columnCount);
    }
}
