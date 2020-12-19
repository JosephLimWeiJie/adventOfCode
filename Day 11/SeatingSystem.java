import java.util.*;

public class SeatingSystem {
    public static List<Pair<Integer, Integer>> directions = direction();

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

    public static List<Pair<Integer, Integer>> direction() {
        List<Pair<Integer, Integer>> directions = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                directions.add(Pair.of(i, j));
            }
        }
        return directions;
    }

    public static int countOccupiedSeatsPartOne(char[][] graph, int seatRow, int seatCol,
                                                List<Pair<Integer, Integer>> directions) {
        int count = 0;
        int graphColLen = graph[0].length;
        int graphRowLen = graph.length;

        for (Pair<Integer, Integer> p : directions) {
            int newRow = seatRow + p.getFirstVal();
            int newCol = seatCol + p.getSecondVal();
            if (newRow < 0 || newRow > graphRowLen - 1 || newCol < 0 || newCol > graphColLen - 1) {
                continue;
            }
            if (graph[newRow][newCol] == '#') {
                count++;
                break;
            }
        }

        return count;
    }

    public static int countOccupiedSeatsPartTwo(char[][] graph, int seatRow, int seatCol,
                                                List<Pair<Integer, Integer>> directions) {
        int count = 0;

        for (Pair<Integer, Integer> p : directions) {
            boolean hasOccupied = checkDirection(graph, seatRow + (p.getFirstVal()),
                    seatCol + (p.getSecondVal()), p);
            if (hasOccupied) {
                count++;
            }

        }

        return count;
    }

    public static boolean checkDirection(char[][] graph, int seatRow, int seatCol, Pair<Integer, Integer> p) {
        int graphColLen = graph[0].length;
        int graphRowLen = graph.length;

        if (seatRow < 0 || seatRow > graphRowLen - 1 || seatCol < 0 || seatCol > graphColLen - 1) {
            return false;
        }
        if (graph[seatRow][seatCol] == '#') {
            return true;
        }
        if (graph[seatRow][seatCol] == 'L') {
            return false;
        }

        return checkDirection(graph,seatRow + p.getFirstVal(), seatCol + p.getSecondVal(), p);
    }

    public static char[][] deepCopy(char[][] graph) {
        char[][] copyArr = new char[graph.length][graph[0].length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                copyArr[i][j] = graph[i][j];
            }
        }

        return copyArr;
    }

    public static boolean equals(char[][] graphOne, char[][] graphTwo) {
        for (int i = 0; i < graphOne.length; i++) {
            for (int j = 0; j < graphOne[i].length; j++) {
                if (graphOne[i][j] != graphTwo[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int getTotalOccupiedSeats(char[][] graph) {
        char[][] prevGraph = deepCopy(graph);
        char[][] newGraph = deepCopy(graph);
        char[][] tempGraph;
        boolean hasChanged = false;

        do {
            newGraph = deepCopy(prevGraph);
            tempGraph = deepCopy(prevGraph);

            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    if (prevGraph[i][j] == '.') {
                        continue;
                    }
                    int count = countOccupiedSeatsPartTwo(prevGraph, i, j, directions);
                    if (count == 0 && prevGraph[i][j] == 'L') {
                        newGraph[i][j] = '#';
                        hasChanged = true;
                    } else if (count >= 5 && prevGraph[i][j] == '#') {
                        newGraph[i][j] = 'L';
                        hasChanged = true;
                    }
                }
            }
            prevGraph = deepCopy(newGraph);
        } while (hasChanged && !equals(newGraph, tempGraph));

        return countTotalOccupiedSeats(prevGraph);
    }

    public static int countTotalOccupiedSeats(char[][] graph) {
        int count = 0;
        for (char[] row : graph) {
            for (char c : row) {
                if (c == '#') {
                    count++;
                }
            }
        }

        return count;
    }

    public static void printGraph(char[][] graph) {
        for (char[] row : graph) {
            StringBuilder sb = new StringBuilder();
            for (char c : row) {
                sb.append(c);
            }
            System.out.println(sb);
        }
    }

    public static char[][] createGraph(List<String> listOfInputs) {
        int rowSize = listOfInputs.size();
        int colSize = listOfInputs.get(0).toCharArray().length;
        char[][] graph = new char[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            char[] col = listOfInputs.get(i).toCharArray();
            for (int j = 0; j < colSize; j++) {
                graph[i][j] = col[j];
            }
        }

        return graph;
    }

    public static List<String> readInput(Scanner sc) {
        List<String> listOfInputs = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            listOfInputs.add(line);
        }

        return listOfInputs;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> listOfInputs = readInput(sc);
        char[][] graph = createGraph(listOfInputs);
        //printGraph(graph);
        int ans = getTotalOccupiedSeats(graph);
        System.out.println("Occupied Seats: " + ans);
        //System.out.println(direction());

    }
}