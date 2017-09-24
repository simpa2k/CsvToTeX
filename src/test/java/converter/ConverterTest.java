package converter;

import org.junit.Before;
import org.junit.Test;
import parser.CsvParser;
import parser.Parser;
import teXTable.TableLayout;
import teXTable.TableLayoutFactory;
import teXTable.TeXTable;
import teXTable.TeXTableFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConverterTest {

    private final String VALID_NON_EXISTENT_FILE = "./doesnotexist.csv";
    private final String VALID_EXISTENT_FILE = "./src/test/java/resources/table.csv";
    private final String PATH_TO_CORRECTLY_CONVERTED_FILE = "./src/test/java/resources/convertedTable.txt";

    private final String TXT_FILE = "./table.txt";

    private String correctlyConvertedFile;

    private CsvParser mockCsvParser;
    private TableLayoutFactory mockTableLayoutFactory;
    private TeXTableFactory mockTeXTableFactory;

    private Converter createConverterWithMockParserAndFactories(String pathToCsv) {
        return new Converter(pathToCsv, mockCsvParser, mockTableLayoutFactory, mockTeXTableFactory);
    }

    private Converter createValidConverter() {
        return createConverterWithMockParserAndFactories(VALID_EXISTENT_FILE);
    }

    private Converter createConverterWithValidPathAndParser(TableLayoutFactory tableLayoutFactory,
                                                            TeXTableFactory teXTableFactory) {
        return new Converter(VALID_EXISTENT_FILE, mockCsvParser, tableLayoutFactory, teXTableFactory);
    }

    private Converter createConverterWithNullTableLayoutFactory() {
        return createConverterWithValidPathAndParser(null, mockTeXTableFactory);
    }

    private Converter createConverterWithNullTeXTableFactory() {
        return createConverterWithValidPathAndParser(mockTableLayoutFactory, null);
    }

    @Before
    public void setup() throws IOException {

        correctlyConvertedFile = new String(Files.readAllBytes(Paths.get(PATH_TO_CORRECTLY_CONVERTED_FILE)));

        mockCsvParser = mock(CsvParser.class);
        mockTableLayoutFactory = mock(TableLayoutFactory.class);
        mockTeXTableFactory = mock(TeXTableFactory.class);

    }

    @Test
    public void testCreateConverterWithValidPath() {

        Converter converter = createValidConverter();
        assertEquals(VALID_EXISTENT_FILE, converter.getPathToCSV());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConverterWithNullPath() {
        createConverterWithMockParserAndFactories(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConverterWithNullTableLayoutFactory() {
        createConverterWithNullTableLayoutFactory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConverterWithNullTeXTableFactory() {
        createConverterWithNullTeXTableFactory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConverterWithNonCSVFile() {
        createConverterWithMockParserAndFactories(TXT_FILE);
    }

    @Test(expected = IOException.class)
    public void testConvertNonExistentFile() throws IOException {

        Converter converter = createConverterWithMockParserAndFactories(VALID_NON_EXISTENT_FILE);
        converter.convert();

    }

    @Test
    public void testConvertExistentFile() throws IOException {

        TableLayout mockTableLayout = mock(TableLayout.class);

        TeXTable mockTeXTable = mock(TeXTable.class);
        when(mockTeXTable.getTable()).thenReturn(correctlyConvertedFile);

        String[] columnHeads = {"", "c1", "c2", "c3"};
        String[] r1 = {"r1", "v1", "v2", "v3"};
        String[] r2 = {"r2", "v4", "v5", "v6"};

        when(mockCsvParser.parse(any(Stream.class))).thenReturn(Arrays.asList(columnHeads, r1, r2)); // ToDo: That any() matcher might not be the best solution.
        when(mockTableLayoutFactory.createTableLayout(4)).thenReturn(mockTableLayout);
        when(mockTeXTableFactory.createTeXTable(mockTableLayout)).thenReturn(mockTeXTable);

        Converter converter = createValidConverter();
        String teXTable = converter.convert();

        verify(mockTeXTable).append(columnHeads);
        verify(mockTeXTable).append(r1);
        verify(mockTeXTable).append(r2);

        assertEquals(teXTable, correctlyConvertedFile);

    }

    @Test
    public void testParseStep() throws IOException {

        TableLayout mockTableLayout = mock(TableLayout.class);

        TeXTable mockTeXTable = mock(TeXTable.class);
        when(mockTeXTable.getTable()).thenReturn(correctlyConvertedFile);

        String[] columnHeads = {"", "c1", "c2", "c3"};
        String[] r1 = {"r1", "v1", "v2", "v3"};
        String[] r2 = {"r2", "v4", "v5", "v6"};

        when(mockCsvParser.parse(any(Stream.class))).thenReturn(Arrays.asList(columnHeads, r1, r2)); // ToDo: That any() matcher might not be the best solution.
        when(mockTableLayoutFactory.createTableLayout(4)).thenReturn(mockTableLayout);
        when(mockTeXTableFactory.createTeXTable(mockTableLayout)).thenReturn(mockTeXTable);

        Converter converter = createValidConverter();

        converter.parse();
        converter.reduce();

    }
}