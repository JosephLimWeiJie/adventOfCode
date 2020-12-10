import java.util.*;

public class AdaptedArray {

    public static int countPossibleWays(int idx, List<Integer> order) {
        int count = 0;

        if (idx - 1 >= 0 && order.get(idx) - order.get(idx - 1) <= 3) {
            count++;
        }

        if (idx - 2 >= 0 && order.get(idx) - order.get(idx - 2) <= 3) {
            count++;
        }

        if (idx - 3 >= 0 && order.get(idx) - order.get(idx - 3) <= 3) {
            count++;
        }

        return count;
    }

    public static long distinctArrangments(List<Integer> listOfInputs) {
        Collections.sort(listOfInputs);
        List<Integer> order = new ArrayList<>();
        order.add(0);
        for (int i = 0; i < listOfInputs.size(); i++) {
            order.add(listOfInputs.get(i));
        }
        order.add(listOfInputs.get(listOfInputs.size() - 1) + 3);

        long[] possArr = new long[order.size()];
        possArr[0] = 1;
        for (int i = 1; i < order.size(); i++) {
            int ways = countPossibleWays(i, order);
            if (ways == 1) {
                possArr[i] = possArr[i - 1];
            } else if (ways == 2) {
                possArr[i] = possArr[i - 1] + possArr[i - 1];
            } else if (ways == 3) {
                possArr[i] = possArr[i - 1] + possArr[i - 2] + possArr[i - 3];
            }
        }

        return possArr[order.size() - 1];
    }

    public static int multiplyDifferences(List<Integer> listOfInputs) {
        Collections.sort(listOfInputs);
        Map<Integer, Integer> map = new HashMap<>();

        int chargingOutlet = 0;
        for (int i = 0; i < listOfInputs.size(); i++) {
            int currVal = listOfInputs.get(i);
            if (i == 0) {
                int diff = currVal - chargingOutlet;

                map.put(diff, 1);
            } else {
                int prevVal = listOfInputs.get(i - 1);
                int diff = currVal - prevVal;
                if (map.containsKey(diff)) {
                    map.computeIfPresent(diff, (key, currCount) -> currCount + 1);
                } else {
                    map.put(diff, 1);
                }
            }

        }

        // Add a diff of 3 for built-in adapter.
        map.computeIfPresent(3, (key, currCount) -> currCount + 1);

        int oneDiff = 0;
        int threeDiff = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            //System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
            if (entry.getKey() == 1) {
                oneDiff = entry.getValue();
            } else if (entry.getKey() == 3) {
                threeDiff = entry.getValue();
            }
        }

        int ans = oneDiff * threeDiff;
        return ans;
    }


    public static List<Integer> readInput(Scanner sc) {
        List<Integer> listOfInputs = new ArrayList<>();

        while (sc.hasNextLine()) {
            int input = Integer.parseInt(sc.nextLine());
            listOfInputs.add(input);
        }

        return listOfInputs;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> listOfInputs = readInput(sc);
        long ans = distinctArrangments(listOfInputs);
        System.out.println(ans);
    }
}