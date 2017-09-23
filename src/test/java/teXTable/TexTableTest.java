package teXTable;

import org.junit.Before;
import org.junit.Test;
import teXTable.TeXTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TexTableTest {

    private final String PATH_TO_EMPTY_TEX_TABLE = "./src/test/java/resources/sampleEmptyTeXTable.txt";
    private final String PATH_TO_EMPTY_TWO_COLUMN_TEX_TABLE = "./src/test/java/resources/sampleEmptyTwoColumnTeXTable.txt";
    private final String PATH_TO_ONE_ROW_FIVE_COLUMN_TABLE = "./src/test/java/resources/oneRowFiveColumnTable.txt";
    private final String PATH_TO_THREE_ROW_FIVE_COLUMN_TABLE = "./src/test/java/resources/threeRowFiveColumnTable.txt";

    private String sampleTeXTable;
    private String sampleTwoColumnTeXTable;
    private String oneRowFiveColumnTable;
    private String threeRowFiveColumnTable;

    private TableLayout twoColumnTableLayout;
    private TableLayout fiveColumnTableLayout;
    private RowFactory mockRowFactory;


    private String readTable(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    @Before
    public void setup() throws IOException {

        sampleTeXTable = readTable(PATH_TO_EMPTY_TEX_TABLE);
        sampleTwoColumnTeXTable = readTable(PATH_TO_EMPTY_TWO_COLUMN_TEX_TABLE);
        oneRowFiveColumnTable = readTable(PATH_TO_ONE_ROW_FIVE_COLUMN_TABLE);
        threeRowFiveColumnTable = readTable(PATH_TO_THREE_ROW_FIVE_COLUMN_TABLE);

        twoColumnTableLayout = mock(TableLayout.class);
        fiveColumnTableLayout = mock(TableLayout.class);

        when(twoColumnTableLayout.toString()).thenReturn("{| c | c |}");
        when(twoColumnTableLayout.getColumnCount()).thenReturn(2);

        when(fiveColumnTableLayout.toString()).thenReturn("{| c | c | c | c | c |}");
        when(fiveColumnTableLayout.getColumnCount()).thenReturn(5);

        Row mockRow = mock(Row.class);
        when(mockRow.toString()).thenReturn("1 & 2 & 3 & 4 & 5 \\\\");

        mockRowFactory = mock(RowFactory.class);
        when(mockRowFactory.createRow(5)).thenReturn(mockRow);

    }

    @Test
    public void testConstructTable() {

        TeXTable teXTable = new TeXTable(twoColumnTableLayout, mockRowFactory);
        assertEquals(sampleTwoColumnTeXTable, teXTable.getTable());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullTableLayout() {
        new TeXTable(null, mockRowFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullRowFactory() {
        new TeXTable(twoColumnTableLayout, null);
    }

    @Test
    public void testAppendRow() {

        TeXTable teXTable = new TeXTable(fiveColumnTableLayout, mockRowFactory);
        teXTable.append("1", "2", "3", "4", "5");

        assertEquals(oneRowFiveColumnTable, teXTable.getTable());

    }

    @Test
    public void testAppendSeveralRows() {

        TeXTable teXTable = new TeXTable(fiveColumnTableLayout, mockRowFactory);

        teXTable.append("1", "2", "3", "4", "5");
        teXTable.append("1", "2", "3", "4", "5");
        teXTable.append("1", "2", "3", "4", "5");

        assertEquals(threeRowFiveColumnTable, teXTable.getTable());

    }
}
