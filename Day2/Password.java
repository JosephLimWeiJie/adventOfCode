import java.util.*;

public class Password {

    public static class Pair<T, U> {
        private final T firstVal;
        private final U secondVal;
        
        private Pair(T firstVal, U secondVal) {
            this.firstVal = firstVal;
            this.secondVal = secondVal;
        }        

        public static <T, U> Pair<T, U> of(T firstVal, U secondVal) {
            return new Pair<T, U>(firstVal, secondVal);
        }
        
        public T getFirstVal() {
            return this.firstVal;
        }
        
        public U getSecondVal() {
            return this.secondVal;
        }
    }
    
    public static class Info {
        private Pair<Integer, Integer> range;
        private char alphabet;
        private String password;

        public Info (Pair<Integer, Integer> range, char alphabet, String password) {
            this.range = range;
            this.alphabet = alphabet;
            this.password = password;
        }

        public Pair<Integer, Integer> getRange() {
            return this.range;
        }

        public char getAlphabet() {
            return this.alphabet;
        }

        public String getPassword() {
            return this.password;
        }
    }

    public static Info parseUserInput(String userInput) {
        String[] arr = userInput.split(" ");

        String[] numRange = arr[0].split("-");
        Pair<Integer, Integer> range = Pair.of(
            Integer.parseInt(numRange[0]), Integer.parseInt(numRange[1]));

        char alphabet = arr[1].charAt(0);
        String password = arr[2];

        return new Info(range, alphabet, password);
    }

    public static int countValidPassword(List<String> listOfInputs) {
        int ans = 0;

        List<Info> listOfParsedInputs = new ArrayList<>();
        for (String input : listOfInputs) {
            listOfParsedInputs.add(parseUserInput(input));
        }

        for (Info info : listOfParsedInputs) {
            int low = info.getRange().getFirstVal();
            int high = info.getRange().getSecondVal();

            char[] charArr = info.getPassword().toCharArray();
            char alphabet = info.getAlphabet();

            int count = 0;
            for (char c : charArr) {
                if (c == alphabet) {
                    count++;
                }
            }

            if (count >= low && count <= high) {
                ans++;
            }
        }

        return ans;
    }

    public static List<String> readInput(Scanner sc) {
        List<String> listOfInputs  = new ArrayList<>();

        while (sc.hasNextLine()) {
            String stringToAdd = sc.nextLine();
            listOfInputs.add(stringToAdd);
        }

        return listOfInputs;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> listOfInputs  = readInput(sc);
        int ans = countValidPassword(listOfInputs);
        System.out.println(ans);
    }
}
