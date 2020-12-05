import java.util.*;

public class Boarding {

    public static int getSeatID(List<Integer> listOfIDs) {
        List<Integer> possibleRange = new ArrayList<>();
        Collections.sort(listOfIDs);

        for (int i = 1; i < listOfIDs.size() - 1; i++) {
            int currentID = listOfIDs.get(i);
            int prevID = listOfIDs.get(i - 1);
            int nextID = listOfIDs.get(i + 1);

            if (!((currentID - prevID == 1) && (nextID - currentID == 1))) {
                possibleRange.add(currentID);
            }
        }

        int seatID = (possibleRange.get(0) + possibleRange.get(1)) / 2;
        return seatID;
    }

    public static int getMaxSeatID(List<Integer> listOfIDs) {
        int max = Collections.max(listOfIDs);
        return max;
    }

    public static int getColNum(char[] colDataArr) {
        int left = 0;
        int right = 7;

        for (char c : colDataArr) {
            if (c == 'R') {
                int newLeft = ((left + right + 1) / 2);
                left = newLeft;
            } else {
                int newRight = ((left + right) / 2);
                right = newRight;
            }
        }

        return right;
    }

    public static int getRowNum(char[] rowDataArr) {
        int low = 0;
        int high = 127;

        for (char c : rowDataArr) {
            if (c == 'F') {
                int newHigh = ((low + high) / 2);
                high = newHigh;
            } else {
                int newLow = ((low + high + 1) / 2);
                low = newLow;
            }
        }

        return low;
    }

    public static List<Integer> getBoardingSeatID(List<String> listOfInputs) {
        List<Integer> listOfIDs = new ArrayList<>();

        for (String input : listOfInputs) {
            String rowData = input.substring(0, 7);
            String colData = input.substring(7, 10);

            char[] rowDataArr = rowData.toCharArray();
            char[] colDataArr = colData.toCharArray();

            int rowNum = getRowNum(rowDataArr);
            int colNum = getColNum(colDataArr);

            int seatID = (rowNum * 8) + colNum;
            listOfIDs.add(seatID);
        }

        return listOfIDs;
    }

    public static List<String> readInput(Scanner sc) {
        List<String> listOfInputs = new ArrayList<>();

        while (sc.hasNextLine()) {
            String stringToAdd = sc.nextLine();
            listOfInputs.add(stringToAdd);
        }

        return listOfInputs;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> listOfInputs = readInput(sc);
        List<Integer> listOfIDs = getBoardingSeatID(listOfInputs);
        int ans = getSeatID(listOfIDs);
        System.out.println(ans);
    }
}