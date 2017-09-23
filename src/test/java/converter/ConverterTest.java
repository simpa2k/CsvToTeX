package converter;

import org.junit.Before;
import org.junit.Test;
import teXTable.TableLayout;
import teXTable.TableLayoutFactory;
import teXTable.TeXTable;
import teXTable.TeXTableFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConverterTest {

    private final String VALID_NON_EXISTENT_FILE = "./doesnotexist.csv";
    private final String VALID_EXISTENT_FILE = "./src/test/java/resources/table.csv";
    private final String PATH_TO_CORRECTLY_CONVERTED_FILE = "./src/test/java/resources/convertedTable.txt";

    private final String TXT_FILE = "./table.txt";

    private String correctlyConvertedFile;

    private TableLayoutFactory mockTableLayoutFactory;
    private TeXTableFactory mockTeXTableFactory;

    @Before
    public void setup() throws IOException {

        correctlyConvertedFile = new String(Files.readAllBytes(Paths.get(PATH_TO_CORRECTLY_CONVERTED_FILE)));

        mockTableLayoutFactory = mock(TableLayoutFactory.class);
        mockTeXTableFactory = mock(TeXTableFactory.class);

    }

    @Test
    public void testCreateConverterWithValidPath() {

        Converter converter = new Converter(VALID_EXISTENT_FILE, mockTableLayoutFactory, mockTeXTableFactory);
        assertEquals(VALID_EXISTENT_FILE, converter.getPathToCSV());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConverterWithNullPath() {
        new Converter(null, mockTableLayoutFactory, mockTeXTableFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConverterWithNullTableLayoutFactory() {
        new Converter(VALID_EXISTENT_FILE, null, mockTeXTableFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConverterWithNullTeXTableFactory() {
        new Converter(VALID_EXISTENT_FILE, mockTableLayoutFactory, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConverterWithNonCSVFile() {
        new Converter(TXT_FILE, mockTableLayoutFactory, mockTeXTableFactory);
    }

    @Test(expected = IOException.class)
    public void testConvertNonExistentFile() throws IOException {

        Converter converter = new Converter(VALID_NON_EXISTENT_FILE, mockTableLayoutFactory, mockTeXTableFactory);
        converter.convert();

    }

    @Test
    public void testConvertExistentFile() throws IOException {

        TableLayout mockTableLayout = mock(TableLayout.class);

        TeXTable mockTeXTable = mock(TeXTable.class);
        when(mockTeXTable.getTable()).thenReturn(correctlyConvertedFile);

        when(mockTableLayoutFactory.createTableLayout(4)).thenReturn(mockTableLayout);
        when(mockTeXTableFactory.createTeXTable(mockTableLayout)).thenReturn(mockTeXTable);

        String[] columnHeads = {"", "c1", "c2", "c3"};
        String[] r1 = {"r1", "v1", "v2", "v3"};
        String[] r2 = {"r2", "v4", "v5", "v6"};

        Converter converter = new Converter(VALID_EXISTENT_FILE, mockTableLayoutFactory, mockTeXTableFactory);
        String teXTable = converter.convert();

        verify(mockTeXTable).append(columnHeads);
        verify(mockTeXTable).append(r1);
        verify(mockTeXTable).append(r2);

        assertEquals(teXTable, correctlyConvertedFile);

    }
}