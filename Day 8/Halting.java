import java.util.*;

public class Halting {

    public static List<Pair<Pair<String, String>, Integer>> copyInstructions(
            List<Pair<Pair<String, String>, Integer>> instructions) {

        List<Pair<Pair<String, String>, Integer>> newInstructions = new ArrayList<>();

        for (Pair<Pair<String, String>, Integer> pair : instructions) {
            newInstructions.add(pair);
        }

        return newInstructions;
    }

    public static List<Pair<Pair<String, String>, Integer>> resetCount(
            List<Pair<Pair<String, String>, Integer>> instructions) {

        for (int i = 0; i < instructions.size(); i++) {
            Pair<String, String> pair = instructions.get(i).getFirstVal();
            int newCount = 0;
            instructions.set(i, Pair.of(pair, newCount));
        }

        return instructions;
    }

    public static int fixInstructions(List<Pair<Pair<String, String>, Integer>> instructions) {
        int ans = 0;
        for (int i = 0; i < instructions.size(); i++) {

            List<Pair<Pair<String, String>, Integer>> newInstructions = copyInstructions(instructions);
            String currKeyword = newInstructions.get(i).getFirstVal().getFirstVal();
            String currValue = newInstructions.get(i).getFirstVal().getSecondVal();
            int count = newInstructions.get(i).getSecondVal();

            Pair<Boolean, Integer> result = executeInstructions(newInstructions);
                if (result.getFirstVal()) {
                    if (currKeyword.equals("nop")) {
                        newInstructions.set(i, Pair.of(Pair.of("jmp", currValue), count));
                    } else if (currKeyword.equals("jmp")) {
                        newInstructions.set(i, Pair.of(Pair.of("nop", currValue), count));
                    }

                    Pair<Boolean, Integer> newResult = executeInstructions(resetCount(newInstructions));
                    if (newResult.getFirstVal()) {
                        continue;
                    } else {
                        ans = newResult.getSecondVal();
                        break;
                    }
                } else {
                    ans = result.getSecondVal();
                    break;
                }
        }

        return ans;
    }

    public static Pair<Boolean, Integer> executeInstructions(
            List<Pair<Pair<String, String>, Integer>> instructions) {

        int ans = 0;
        boolean hasCycle = false;

        for (int j = 0; j < instructions.size(); j++) {
            int count = instructions.get(j).getSecondVal();
            instructions.set(j, Pair.of(instructions.get(j).getFirstVal(), count += 1));
            if (count == 2) {
                hasCycle = true;
                break;
            }
            String keyword = instructions.get(j).getFirstVal().getFirstVal();
            String value = instructions.get(j).getFirstVal().getSecondVal();

            switch (keyword) {
            case "nop":
                break;
            case "acc":
                int number = Integer.parseInt(value.substring(1, value.length()));
                if (value.contains("+")) {
                    ans += number;
                } else {
                    ans -= number;
                }
                break;
            case "jmp":
                int step = Integer.parseInt(value.substring(1, value.length()));
                if (value.contains("+")) {
                    j += (step - 1);
                } else {
                    j -= (step + 1);
                }
                break;
            }
        }

        return Pair.of(hasCycle, ans);
    }

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

        @Override
        public String toString() {
            return "(" + this.firstVal + ", " + this.secondVal + ")";
        }
    }

    public static List<Pair<Pair<String, String>, Integer>> readInput(Scanner sc) {
        List<Pair<Pair<String, String>, Integer>> instructions = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            Pair<String, String> pair = Pair.of(inputArr[0], inputArr[1]);
            Pair<Pair<String, String>, Integer> pairToAdd = Pair.of(pair, 0);
            instructions.add(pairToAdd);
        }

        return instructions;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Pair<Pair<String, String>, Integer>> instructions = readInput(sc);
        int ans = fixInstructions(instructions);
        System.out.println(ans);
    }
}
