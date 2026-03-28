package interview;

import java.util.*;


class Srip {

    /*
     * Complete the 'joinDataSet' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING fieldName
     *  2. STRING_ARRAY customerFile
     *  3. STRING_ARRAY processorFile
     *  4. BOOLEAN skipUnmatched
     */

    public static List<String> joinDataSet(String fieldName, List<String> customerFile, List<String> processorFile, boolean skipUnmatched) {
        if (customerFile == null || customerFile.isEmpty() || processorFile == null || processorFile.isEmpty()) {
            return Collections.emptyList();
        }

        String[] cHeader = customerFile.get(0).split(",");
        String[] pHeader = processorFile.get(0).split(",");

        int cjIndex = findIndex(cHeader, fieldName);
        int pjIndex = findIndex(pHeader, fieldName);

        int cOrderIndex = findIndex(cHeader, "order");
        int pOrderIndex = findIndex(pHeader, "order");

        String joinHeader = String.join(",", cHeader) + "," + String.join(",", pHeader);
        String empProcRow = ",".repeat(pHeader.length -1);

        List<JoinedRow> joinedRows = new ArrayList<>();

        for (int i = 1; i < customerFile.size(); i++) {
            String cusLine = customerFile.get(i);
            String[] customerRow = cusLine.split(",");
            String cusJoinVal = customerRow[cjIndex];
            int cusOrder = Integer.parseInt(customerRow[cOrderIndex]);
            boolean found = false;

            for (int j = 1; j < processorFile.size(); j++) {
                String prosLine = processorFile.get(j);
                String[] procesRow = prosLine.split(",");
                String prosJoinVal = procesRow[pjIndex];

                if (cusJoinVal.equals(prosJoinVal)) {
                    found = true;
                    int procOrder = Integer.parseInt(procesRow[pOrderIndex]);
                    String combData = String.join(",", customerRow) + "," + String.join(",", procesRow);

                    JoinedRow jr = new JoinedRow(combData, cusOrder, procOrder);
                    joinedRows.add(jr);
                }
            }
            if (!found) {
                String combData = cusLine + "," + String.join(",", empProcRow);
                JoinedRow jr = new JoinedRow(combData, Integer.parseInt(customerRow[cOrderIndex]), Integer.MAX_VALUE);
                joinedRows.add(jr);
            }
        }

        joinedRows.sort(Comparator.comparingInt((JoinedRow jr) -> jr.customerOrder)
                .thenComparingInt(rj -> rj.processorOrder));

        List<String> result = new ArrayList<>();
        result.add(joinHeader);
        for (JoinedRow r : joinedRows) {
            result.add(r.data);
        }
        return result;
    }

    private static int findIndex(String[] headers, String target) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].trim().equalsIgnoreCase(target))
                return i;
        }
        return -1;
    }

    private static class JoinedRow {
        String data;
        int customerOrder;
        int processorOrder;

        JoinedRow(String data, int cOrder, int pOrder) {
            this.data = data;
            this.customerOrder = cOrder;
            this.processorOrder = pOrder;
        }
    }
}
