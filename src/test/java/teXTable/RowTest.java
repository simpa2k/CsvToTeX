package teXTable;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RowTest {

    @Test
    public void testConstructRow() {

        Row row = new Row(5);

        row.insert("1");
        row.insert("2");
        row.insert("3");
        row.insert("4");
        row.insert("5");

        assertEquals("1 & 2 & 3 & 4 & 5 \\\\", row.toString());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeColumnCount() {
        new Row(-1);
    }

    @Test
    public void testMaxNumberOfElements() {

        Row row = new Row(5);

        assertTrue(row.insert("1"));
        assertTrue(row.insert("2"));
        assertTrue(row.insert("3"));
        assertTrue(row.insert("4"));
        assertTrue(row.insert("5"));

        assertFalse(row.insert("6"));

    }
}
