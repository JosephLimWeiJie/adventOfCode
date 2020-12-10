import java.util.*;

public class Custom {

    public static int countYesPartTwo (List<String> listOfInputs) {

        int count = 0;
        int groupSize = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (String input : listOfInputs) {
            System.out.println(input);
            if (input.isBlank()) {
                for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                    if (entry.getValue() == groupSize) {
                        count++;
                    }
                }
                groupSize = 0;
                map.clear();
            } else {
                groupSize++;
                char[] charArr = input.toCharArray();

                for (char c : charArr) {
                    if (map.containsKey(c)) {
                        map.computeIfPresent(c, (key, value) -> value += 1);
                    } else {
                        map.put(c, 1);
                    }
                }
            }
        }

        return count;
    }

    public static int countYes (List<String> processedInputs) {
        int sum = 0;

        for (String input : processedInputs) {
            System.out.println(input.length());
            sum += (input.length());
        }

        return sum;
    }

    public static List<String> removeDuplicates (List<String> listOfInputs) {
        List<String> processedInputs = new ArrayList<>();

        for (String input : listOfInputs) {
            Set<Character> set = new HashSet<>();
            char[] inputArr = input.toCharArray();
            for (char c : inputArr) {
                set.add(c);
            }

            StringBuilder sb = new StringBuilder();
            for (char c : set) {
                sb.append(c);
            }

            processedInputs.add(sb.toString());
        }

        return processedInputs;
    }

    public static List<String> readInput(Scanner sc) {
        List<String> listOfInputs = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            listOfInputs.add(input);
        }

        // Add a blank at the end of list for corner case in countYesPartTwo().
        listOfInputs.add("");
        return listOfInputs;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> listOfInputs = readInput(sc);
        int ans = countYesPartTwo(listOfInputs);
        System.out.println(ans);
    }
}