import java.util.*;

public class Trajectory {

    public static int getAns(char[][] map) {
        int ans = (genericCountTrees(map, 1, 1) *
                   genericCountTrees(map, 3, 1) *
                   genericCountTrees(map, 5, 1) *
                   genericCountTrees(map, 7, 1) *
                   genericCountTrees(map, 1, 2)
                  );

        return ans;
    }

    public static int genericCountTrees(char[][] map, int x, int y) {
        int numTrees = 0;

        int startX = 0;
        int startY = 0;

        do {
            startX = (startX + x) % (map.length);
            System.out.println("Y" + startY);
            System.out.println("map length: " + map.length);
            System.out.println("map[0] length: " + map[0].length);

            startY += y;

            if (map[startX][startY] == '#') {
                numTrees++;
            }
        } while (startY < map[0].length - y);

        return numTrees;
    }

    public static int countTrees(char[][] map) {
        int ans = 0;

        int startX = 0;
        int startY = 0;

         do {
            startX = (startX + 3) % (map.length);
            System.out.println("Y" + startY);
            System.out.println("map length: " + map.length);
             System.out.println("map[0] length: " + map[0].length);

            startY++;

            if (map[startX][startY] == '#') {
                ans++;
            }
        } while (startY < map[0].length - 1);

        return ans;
    }

    public static char[][] createMap(List<String> listOfInputs) {
        String firstInput = listOfInputs.get(0);
        char[] firstInputArr = firstInput.toCharArray();
        int length = firstInputArr.length;
        int height = listOfInputs.size();

        char[][] map = new char[length][height];
        for (int j = 0; j < listOfInputs.size(); j++) {
            String input = listOfInputs.get(j);
            char[] inputArr = input.toCharArray();

            for (int k = 0; k < inputArr.length; k++) {
                map[k][j] = inputArr[k];
            }
        }
        return map;
    }

    public static List<String> readInput(Scanner sc) {
        List<String> listOfInputs = new ArrayList<>();

        while (sc.hasNextLine()) {
            String stringToAdd = sc.nextLine();
            listOfInputs.add(stringToAdd);
        }

        return listOfInputs;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> listOfInputs = readInput(sc);
        char[][] map = createMap(listOfInputs);
        int ans = getAns(map);
        System.out.println(ans);
    }
}