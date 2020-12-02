
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ReportRepair {
    
    public static int sum(List<Integer> listOfNums) {
        int startIndex = 0;
        int endIndex = listOfNums.size() - 1;
        Collections.sort(listOfNums);
        int ans = 0;
        
        while (startIndex <= endIndex) {
            int first = listOfNums.get(startIndex);
            int last = listOfNums.get(endIndex);
            int sum = first + last;

            if (sum == 2020) {
                ans = (first * last);
                break;
            } else if (sum < 2020) {
                startIndex++;
            } else {
                endIndex--;
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