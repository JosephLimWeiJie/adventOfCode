import java.util.*;

public class Custom {

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

        String stringToAdd = "";
        while (sc.hasNextLine()) {

            String nextLine = sc.nextLine();
            stringToAdd += (nextLine);

            if (nextLine.isBlank()) {
                listOfInputs.add(stringToAdd);
                stringToAdd = "";
            }

        }

        if (!(stringToAdd.equals(""))) {
            listOfInputs.add(stringToAdd);
        }

        return listOfInputs;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> listOfInputs = readInput(sc);
        List<String> processedInputs = removeDuplicates(listOfInputs);
        int ans = countYes(processedInputs);
        System.out.println(ans);
    }
}