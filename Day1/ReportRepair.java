import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ReportRepair {
    
    public static int sum(List<Integer> listOfNums) {
        Collections.sort(listOfNums);
        int ans = 0;

        for (int i = 0; i < listOfNums.size() - 2; i++) {
            int left = i + 1;
            int right = listOfNums.size() - 1;

            while (left <= right) {
                int sum = listOfNums.get(i) + listOfNums.get(left) + listOfNums.get(right);
                if (sum == 2020) {
                    ans = (listOfNums.get(i) * listOfNums.get(left) * listOfNums.get(right));
                    break;
                } else if (sum < 2020) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return ans;
    }


    public static List<Integer> readInput(Scanner sc) {
        List<Integer> listOfNums  = new ArrayList<>();

        while (sc.hasNextLine()) {
            int numberToAdd = sc.nextInt();
            listOfNums.add(numberToAdd);
        }

        return listOfNums;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> listOfNums  = readInput(sc);
        int ans = sum(listOfNums);
        System.out.println(ans);
    }
}