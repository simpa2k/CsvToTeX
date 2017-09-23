package teXTable;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RowFactoryTest {

    @Test
    public void testCreateRow() {

        RowFactory rowFactory = new RowFactory();
        Row row = rowFactory.createRow(5);

        assertEquals(5, row.getColumnCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateRowWithNegativeColumnCount() {

        RowFactory rowFactory = new RowFactory();
        rowFactory.createRow(-1);

    }
}
