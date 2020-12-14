import java.util.*;

public class ShuttleSearch {

    public static class Pair<T, U> {
        private final T firstVal;
        private final U secondVal;

        private Pair(T firstVal, U secondVal) {
            this.firstVal = firstVal;
            this.secondVal = secondVal;
        }

        public static <T, U> Pair<T, U> of(T firstVal, U secondVal) {
            return new Pair<>(firstVal, secondVal);
        }

        public T getFirstVal() {
            return this.firstVal;
        }

        public U getSecondVal() {
            return this.secondVal;
        }
    }

    public static Pair<Integer, List<Integer>> readInput(Scanner sc) {
        int timestamp = 0;
        List<Integer> listOfInts = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                timestamp = Integer.parseInt(sc.nextLine());
            } else {
                String[] strArr = (sc.nextLine()).split(",");
                for (String str : strArr) {
                    try {
                        int toAdd = Integer.parseInt(str);
                        listOfInts.add(toAdd);
                    } catch(NumberFormatException nfe) {
                        continue;
                    }
                }
            }
        }

        return Pair.of(timestamp, listOfInts);
    }

    public static int partOne(Pair<Integer, List<Integer>> inputs) {
        int timestamp = inputs.getFirstVal();
        List<Integer> listOfInts = inputs.getSecondVal();
        int nextArrival = timestamp;
        int busId = 0;

        while (true) {
            boolean hasFound = false;
            for (int i = 0; i < listOfInts.size(); i++) {
                int num = listOfInts.get(i);
                if (nextArrival % num == 0) {
                    hasFound = true;
                    busId = num;
                    break;
                }
            }
            if (hasFound) {
                break;
            }
            nextArrival += 1;
        }

        return busId * (nextArrival - timestamp);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pair<Integer, List<Integer>> inputs = readInput(sc);
        int ans = partOne(inputs);
        System.out.println(ans);
    }
}