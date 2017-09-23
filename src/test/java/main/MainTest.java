package main;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Test of complete functionality. Note that this is not a unit test.
 */
public class MainTest {

    private final String PATH_TO_CSV = "./src/test/java/resources/table.csv";
    private final String OUTPUT_PATH = "./src/test/java/main/";
    private final String OUTPUT_FILE = "./src/test/java/main/table.tex";
    private final String PATH_TO_CORRECTLY_CONVERTED_TABLE = "./src/test/java/resources/convertedTable.txt";

    @Test
    public void testValidInputAndOutput() throws IOException {

        Main.main(new String[] {PATH_TO_CSV, OUTPUT_PATH});

        String correctlyConvertedTable = new String(Files.readAllBytes(Paths.get(PATH_TO_CORRECTLY_CONVERTED_TABLE)));
        String convertedTable = new String(Files.readAllBytes(Paths.get(OUTPUT_FILE)));

        assertEquals(correctlyConvertedTable, convertedTable);

        Files.delete(Paths.get(OUTPUT_FILE));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooFewArguments() {
        Main.main(new String[] {});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoOutputPath() {
        Main.main(new String[] {PATH_TO_CSV});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooManyArguments() {
        Main.main(new String[] {PATH_TO_CSV,  OUTPUT_PATH, "Additional argument"});
    }
}