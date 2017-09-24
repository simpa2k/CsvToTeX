package parser;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    private final String VALID_EXISTENT_FILE = "./src/test/java/resources/table.csv";
    private final String VALID_EMPY_FILE = "./src/test/java/resources/emptyFile.csv";

    private Stream<String> createFileStream(String path) throws IOException {
        return Files.lines(Paths.get(path));
    }

    @Test
    public void testParseValidStream() throws IOException {

        Parser parser = new CsvParser();

        Stream<String> fileStream = createFileStream(VALID_EXISTENT_FILE);
        List<String[]> result = parser.parse(fileStream);

        assertEquals(3, result.size());

        for (String[] line : result) {
            assertEquals(4, line.length);
        }
    }

    @Test
    public void testParseEmptyStream() throws IOException {

        Parser parser = new CsvParser();

        Stream<String> fileStream = createFileStream(VALID_EMPY_FILE);
        List<String[]> result = parser.parse(fileStream);

        assertEquals(0, result.size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseNullStream() {

        Parser parser = new CsvParser();
        parser.parse(null);

    }
}
