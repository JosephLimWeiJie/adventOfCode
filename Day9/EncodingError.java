import java.util.*;

public class EncodingError {

    public static long sumOfSet(Set<Long> set) {
        long sum = 0;

        for (long num : set) {
            sum += num;
        }

        return sum;
    }

    public static long findEncryptionWeakness(long errorNum, List<Long> inputs) {
        long ans = 0;

        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i) == errorNum) {
                break;
            }

            boolean isFound = false;
            TreeSet<Long> window = new TreeSet<>();
            for (int j = i; j < inputs.size(); j++) {

                long sum = sumOfSet(window);
                if (sum < errorNum) {
                    window.add(inputs.get(j));

                } else if (sum > errorNum) {
                    break;
                } else {
                    long smallest = window.pollFirst();
                    long largest = window.pollLast();
                    ans += (smallest + largest);
                    isFound = true;
                    break;
                }
            }

            if (isFound) {
                break;
            }
        }

        return ans;
    }

    public static boolean checkSum(long inputToCheck, List<Long> inputs, int start, int end) {
        boolean isValidSum = false;

        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j < end; j++) {
                if ((inputs.get(i) + inputs.get(j)) == inputToCheck) {
                    isValidSum = true;
                }
            }
        }

        return isValidSum;
    }

    public static long findEncodingErrorNumber(int preamble, List<Long> inputs) {
        long ans = 0;

        for (int i = preamble; i < inputs.size(); i++) {
            long inputToCheck = inputs.get(i);
            boolean isValidSum = checkSum(inputToCheck, inputs, i - preamble, i);
            if (!isValidSum) {
                ans = inputToCheck;
                break;
            }
        }

        return ans;
    }

    public static List<Long> readInput(Scanner sc) {
        List<Long> inputs = new ArrayList<>();

        while (sc.hasNextLine()) {
            long inputToAdd = Long.parseLong(sc.nextLine());
            inputs.add(inputToAdd);
        }

        return inputs;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Long> inputs = readInput(sc);
        long errorNum = findEncodingErrorNumber(25, inputs);
        long ans = findEncryptionWeakness(errorNum, inputs);
        System.out.println(ans);

    }
}