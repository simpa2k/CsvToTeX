package internalTable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class InternalTableTest {

    @Test
    public void testConstructWithListOfStringArrays() {

        String[] columnHeads = {"", "c1", "c2", "c3"};
        String[] r1 =          {"r1", "v1", "v2", "v3"};
        String[] r2 =          {"r2", "v4", "v5", "v6"};

        InternalTable internalTable = new InternalTable(Arrays.asList(columnHeads, r1, r2));

        assertEquals(3, internalTable.getRowCount());
        assertEquals(4, internalTable.getColumnCount());

    }

    @Test
    public void testReduction() {

        String[] r1 = {"k"  , "k" , "k" , "k", "k", "k", "m", "m", "m", "m", "m", "m"};
        String[] r2 = {"u"  , "u" , "m" , "m", "ä", "ä", "u", "u", "m", "m", "ä", "ä"};
        String[] r3 = {"s"  , "l" , "s" , "l", "s", "l", "s", "l", "s", "l", "s", "l"};
        String[] r4 = {"abc", "ac", "ab", "a", "b", "" , "a", "a", "a", "d", "a", "a"};

        InternalTable internalTable = new InternalTable(Arrays.asList(r1, r2, r3, r4));
        internalTable.reduce();

        String[] rr1 = {"k"  , "k" , "k" , "k", "k", "k", "m", "m", "m", "m"};
        String[] rr2 = {"u"  , "u" , "m" , "m", "ä", "ä", "u", "m", "m", "ä"};
        String[] rr3 = {"s"  , "l" , "s" , "l", "s", "l", "*", "s", "l", "*"};
        String[] rr4 = {"abc", "ac", "ab", "a", "b", "" , "a", "a", "d", "a"};

        ArrayList<String> correctR1 = new ArrayList<>(Arrays.asList(rr1));
        ArrayList<String> correctR2 = new ArrayList<>(Arrays.asList(rr2));
        ArrayList<String> correctR3 = new ArrayList<>(Arrays.asList(rr3));
        ArrayList<String> correctR4 = new ArrayList<>(Arrays.asList(rr4));

        ArrayList<ArrayList<String>> correct = new ArrayList<>();
        correct.add(correctR1);
        correct.add(correctR2);
        correct.add(correctR3);
        correct.add(correctR4);

        assertEquals(correct, internalTable.getTable());

    }

    @Test
    public void testReductionOfPermutedMatrix() {

        String[] r1 = {"s"  , "s", "s" ,"s", "s", "s", "l" , "l", "l", "l", "l", "l"};
        String[] r2 = {"u"  , "u", "m" ,"m", "ä", "ä", "u" , "u", "m", "m", "ä", "ä"};
        String[] r3 = {"k"  , "m", "k" ,"m", "k", "m", "k" , "m", "k", "m", "k", "m"};
        String[] r4 = {"abc", "a", "ab","a", "b", "a", "ac", "a", "a", "d", "" , "a"};

        InternalTable internalTable = new InternalTable(Arrays.asList(r1, r2, r3, r4));
        internalTable.reduce();

        /*String[] rr1 = {"k"  , "k" , "k" , "k", "k", "k", "m", "m", "m", "m"};
        String[] rr2 = {"u"  , "u" , "m" , "m", "ä", "ä", "u", "m", "m", "ä"};
        String[] rr3 = {"s"  , "l" , "s" , "l", "s", "l", "*", "s", "l", "*"};
        String[] rr4 = {"abc", "ac", "ab", "a", "b", "" , "a", "a", "d", "a"};

        ArrayList<String> correctR1 = new ArrayList<>(Arrays.asList(rr1));
        ArrayList<String> correctR2 = new ArrayList<>(Arrays.asList(rr2));
        ArrayList<String> correctR3 = new ArrayList<>(Arrays.asList(rr3));
        ArrayList<String> correctR4 = new ArrayList<>(Arrays.asList(rr4));

        ArrayList<ArrayList<String>> correct = new ArrayList<>();
        correct.add(correctR1);
        correct.add(correctR2);
        correct.add(correctR3);
        correct.add(correctR4);

        assertEquals(correct, internalTable.getTable());*/
        //System.out.println(internalTable.getTable());

    }

    @Test
    public void testSmallExample() {

        String[] r1 = {"t", "t", "f", "f"};
        String[] r2 = {"t", "f", "t", "f"};
        String[] r3 = {"t", "f", "f", "f"};

        InternalTable internalTable = new InternalTable(Arrays.asList(r1, r2, r3));
        internalTable.reduce();

        String[] rr1 = {"t", "t", "f"};
        String[] rr2 = {"t", "f", "*"};
        String[] rr3 = {"t", "f", "f"};

        ArrayList<String> correctR1 = new ArrayList<>(Arrays.asList(rr1));
        ArrayList<String> correctR2 = new ArrayList<>(Arrays.asList(rr2));
        ArrayList<String> correctR3 = new ArrayList<>(Arrays.asList(rr3));

        ArrayList<ArrayList<String>> correct = new ArrayList<>();
        correct.add(correctR1);
        correct.add(correctR2);
        correct.add(correctR3);

        assertEquals(correct, internalTable.getTable());

    }
}
