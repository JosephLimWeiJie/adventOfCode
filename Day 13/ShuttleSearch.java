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

    public static Pair<Integer, List<Integer>> readInputPartOne(Scanner sc) {
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

    /** Part One's solution. */
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

    public static List<Integer> readInputPartTwo(Scanner sc) {
        int timestamp = 0;
        List<Integer> listOfInts = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                String toDiscard = sc.nextLine();
                continue;
            } else if (i == 1) {
                String[] strArr = (sc.nextLine()).split(",");
                for (String str : strArr) {
                    try {
                        int toAdd = Integer.parseInt(str);
                        listOfInts.add(toAdd);
                    } catch(NumberFormatException nfe) {
                        listOfInts.add(-1);
                    }
                }
            }
        }

        return listOfInts;
    }

    /** Part Two's solution. */
    // using chinese remainder theorem
    // (t + i) % k == 0
    // t: time, i: index, k: busID, N: input size
    // index i = (k - (i%k)) % k to ensure index is within range of 0 to N
    public static long partTwo(List<Integer> listOfInts) {
        List<Pair<Integer, Integer>> ids = new ArrayList<>();
        long fullProduct = 1;
        for (int i = 0; i < listOfInts.size(); i++) {
            int busId = listOfInts.get(i);
            if (busId != (-1)) {
                int idx = i % busId;
                ids.add(Pair.of((busId - idx) % busId, busId));
                fullProduct *= busId;
            }
        }

        long total = 0;
        for (Pair<Integer, Integer> p : ids) {
            long partialProduct = fullProduct / p.getSecondVal();
            long inverse = modInverse(partialProduct, p.getSecondVal());
            long term = inverse * partialProduct * p.getFirstVal();
            total += term;
        }

        return total % fullProduct;
    }

    public static long modInverse(long a, long m) {
        a = a % m;
        for (long x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> inputs = readInputPartTwo(sc);
        long ans = partTwo(inputs);
        System.out.println(ans);
    }
}