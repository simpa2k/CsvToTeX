package internalTable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class InternalTableTest {

    @Test
    public void testConstructWithListOfStringArrays() {

        String[] columnHeads = {"", "c1", "c2", "c3"};
        String[] r1 = {"r1", "v1", "v2", "v3"};
        String[] r2 = {"r2", "v4", "v5", "v6"};

        InternalTable internalTable = new InternalTable(Arrays.asList(columnHeads, r1, r2));

        assertEquals(3, internalTable.getRowCount());
        assertEquals(4, internalTable.getColumnCount());

    }

    @Test
    public void testReduction() {

        String[] r1 = {"k", "k", "k", "k", "k", "k", "m", "m", "m", "m", "m", "m"};
        String[] r2 = {"u", "u", "m", "m", "ä", "ä", "u", "u", "m", "m", "ä", "ä"};
        String[] r3 = {"s", "l", "s", "l", "s", "l", "s", "l", "s", "l", "s", "l"};
        String[] r4 = {"abc", "ac", "ab", "a", "b", "", "a", "a", "a", "d", "a", "a"};

        InternalTable internalTable = new InternalTable(Arrays.asList(r1, r2, r3, r4));
        internalTable.reduce();

        String[] rr1 = {"k", "k", "k", "k", "k", "k", "m", "m", "m", "m"};
        String[] rr2 = {"u", "u", "m", "m", "ä", "ä", "u", "m", "m", "ä"};
        String[] rr3 = {"s", "l", "s", "l", "s", "l", "*", "s", "l", "*"};
        String[] rr4 = {"abc", "ac", "ab", "a", "b", "", "a", "a", "a", "d", "a"};

        assertEquals(Arrays.asList(rr1, rr2, rr3, rr4), internalTable.getTable());

    }
}
