package internalTable;

import java.util.*;
import java.util.stream.Collectors;

public class InternalTable {

    private ArrayList<ArrayList<String>> table;

    public InternalTable(List<String[]> lines) {

        List<ArrayList<String>> temp = lines.stream().map(line -> {
            return new ArrayList<>(Arrays.asList(line));
        }).collect(Collectors.toList());

        table = new ArrayList<>(temp);

    }

    public int getRowCount() {
        return table.size();
    }

    public int getColumnCount() {
        return table.get(0).size();
    }

    public void reduce() {
        reduce(0, table.get(0).size() - 1, 0);
    }

    private void reduce(int fromColumn, int toColumn, int row) {

        Collection<ArrayList<Integer>> pathsByValue = pickOutSameNode(fromColumn, toColumn, row);

        for (ArrayList<Integer> paths : pathsByValue) {

            if (paths.size() > 1) {

                if (hasSameEndingPoint(paths)) {
                    merge(paths, row + 1);
                } else {
                    reduce(paths.get(0), paths.get(paths.size() - 1), row + 1);
                }
            }
        }
    }

    private boolean hasSameEndingPoint(ArrayList<Integer> paths) {

        String previousEndingPoint = null;
        boolean sameEndingPoint = true;

        for (Integer column : paths) {

            String currentEndingPoint = table.get(table.size() - 1).get(column);

            if (previousEndingPoint == null) {
                previousEndingPoint = currentEndingPoint;
            } else if (!previousEndingPoint.equals(currentEndingPoint)) {

                sameEndingPoint = false;
                break;

            }
        }
        return sameEndingPoint;

    }

    private  Collection<ArrayList<Integer>> pickOutSameNode(int fromColumn, int toColumn, int row) {

        Map<String, ArrayList<Integer>> pathsByValue = new HashMap<>();

        for (int column = fromColumn; column <= toColumn; column++) {

            String currentValue = table.get(row).get(column);
            ArrayList<Integer> currentSetOfValues = pathsByValue.get(currentValue);

            if (currentSetOfValues == null) {
                currentSetOfValues = new ArrayList<>();
            }

            currentSetOfValues.add(column);

            pathsByValue.put(currentValue, currentSetOfValues);

        }
        return pathsByValue.values();

    }

    private void merge(ArrayList<Integer> columns, int fromRow) {

        int lowestColumn = columns.remove(0); // Merge columns into the lowest one. Arbitrary choice.

        Collections.sort(columns, Comparator.reverseOrder()); // Sort in reverse order so that several indices can be removed without problems with shifting.

        table = new ArrayList<>(table.stream().peek(line -> {

            columns.forEach(column -> line.remove((int) column));

        }).collect(Collectors.toList()));

        for (int i = fromRow; i < table.size() - 1; i++) { // ToDo: The minus one is there so that stars do not get added to the result row. This is ugly and should be handled by treating result and header rows separately.
            table.get(i).set(lowestColumn, "*");
        }
    }

    public ArrayList<ArrayList<String>> getTable() {
        return new ArrayList<>(table);
    }
}
