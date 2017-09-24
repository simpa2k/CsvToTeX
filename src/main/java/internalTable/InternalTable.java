package internalTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InternalTable {

    private ArrayList<String[]> table;

    public InternalTable(List<String[]> lines) {
        table = new ArrayList<>(lines);
    }

    public int getRowCount() {
        return table.size();
    }

    public int getColumnCount() {
        return table.get(0).length;
    }

    public void reduce() {

        /*

        1. Set currentRow to 0.
        2. Group all columns on the value found at index currentRow.
        3. For each group:
            Does every path have the same starting point and ending point?
            if yes:
                Merge them
            else:
                Increment currentRow
                Repeat from step 2

         */
        reduce(0, table.get(0).length - 1, 0);
    }

    private void reduce(int fromColumn, int toColumn, int row) {

        Map<String, ArrayList<Integer>> pathsByValue = new HashMap<>();

        for (int column = fromColumn; column <= toColumn; column++) {

            String currentValue = table.get(row)[column];
            ArrayList<Integer> currentSetOfValues = pathsByValue.get(currentValue);

            if (currentSetOfValues == null) {
                currentSetOfValues = new ArrayList<>();
            }

            currentSetOfValues.add(column);

            pathsByValue.put(currentValue, currentSetOfValues);

        }

        for (ArrayList<Integer> paths : pathsByValue.values()) {

            String previousEndingPoint = null;
            boolean sameEndingPoint = true;

            for (Integer column : paths) {

                String currentEndingPoint = table.get(table.size() - 1)[column];

                if (previousEndingPoint == null) {
                    previousEndingPoint = currentEndingPoint;
                } else {

                    if (!previousEndingPoint.equals(currentEndingPoint)) {
                        sameEndingPoint = false;
                        break;
                    }
                }
            }

            if (sameEndingPoint) {
                merge(paths, row);
            } else {

                if (paths.size() > 1) {
                    reduce(paths.get(0), paths.get(paths.size() - 1), row + 1);
                }
            }
        }
    }

    private void merge(ArrayList<Integer> columns, int fromRow) {

    }

    public ArrayList<String[]> getTable() {
        return new ArrayList<>(table);
    }
}
