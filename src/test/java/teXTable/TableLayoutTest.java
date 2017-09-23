package teXTable;

import org.junit.Test;

import static org.junit.Assert.*;

public class TableLayoutTest {

    @Test
    public void testNoArgumentConstructor() {

        TableLayout tableLayout = new TableLayout();

        assertEquals(1, tableLayout.getColumnCount());
        assertEquals("{| c |}", tableLayout.toString());

    }

    @Test
    public void testZeroColumnCount() {

        TableLayout tableLayout = new TableLayout(0);

        assertEquals(0, tableLayout.getColumnCount());
        assertEquals("{||}", tableLayout.toString());
    }

    @Test
    public void testSeveralColumns() {

        TableLayout tableLayout = new TableLayout(5);

        assertEquals(5, tableLayout.getColumnCount());
        assertEquals("{| c | c | c | c | c |}", tableLayout.toString());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeColumnCount() {
        new TableLayout(-1);
    }
}