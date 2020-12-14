import java.util.*;

public class SeatingSystem {

    public static int countOccupiedSeatsPartTwo(char[][] graph, int seatRow, int seatCol) {
        int row = graph.length;
        int col = graph[0].length;

        int count = 0;

        // check left
        if (seatCol > 0) {
            for (int i = seatCol - 1; i >= 0; i--) {
                if (graph[seatRow][i] == 'L') {
                    break;
                }
                else if (graph[seatRow][i] == '#') {
                    count++;
                    break;
                }
            }
        }
        // check right
        if (seatCol < col - 1) {
            for (int i = seatCol + 1; i < col; i++) {
                if (graph[seatRow][i] == 'L') {
                    break;
                }
                else if (graph[seatRow][i] == '#') {
                    count++;
                    break;
                }
            }
        }
        // check top
        if (seatRow > 0) {
            for (int i = seatRow - 1; i >= 0; i--) {
                if (graph[i][seatCol] == 'L') {
                    break;
                }
                if (graph[i][seatCol] == '#') {
                    count++;
                    break;
                }
            }
        }
        // check bottom
        if (seatRow < row - 1) {
            for (int i = seatRow; i < row; i++) {
                if (graph[i][seatCol] == 'L') {
                    break;
                }
                else if (graph[i][seatCol] == '#') {
                    count++;
                    break;
                }
            }
        }
        // check diagonal (top left)
        if (seatRow > 0 && seatCol > 0) {
            int j = seatCol;
            for (int i = seatRow - 1; i >= 0; i--) {
                j--;
                if (j < 0) {
                    break;
                }
                if (graph[i][j] == 'L') {
                    break;
                }
                if (graph[i][j] == '#') {
                    count++;
                    break;
                }
            }
        }
        // check diagonal (top right)
        if (seatRow > 0 && seatCol < col - 1) {
            int j = seatCol;
            for (int i = seatRow - 1; i >= 0; i--) {
                j++;
                if (j > col - 1) {
                    break;
                }
                //System.out.println("now checking seatRow " + seatRow + " seatCol " + seatCol);
                if (graph[i][j] == 'L') {
                    break;
                }
                if (graph[i][j] == '#') {
                    count++;
                    break;
                }
            }
        }
        // check diagonal (bottom left)
        if (seatRow < row - 1 && seatCol > 0) {
            int j = seatCol;
            for (int i = seatRow + 1; i < row; i++) {
                j--;
                if (j < 0) {
                    break;
                }
                if (graph[i][j] == 'L') {
                    break;
                }
                if (graph[i][j] == '#') {
                    count++;
                    break;
                }
            }
        }
        // check diagonal (bottom right)
        if (seatRow < row - 1 && seatCol < col - 1) {
            int j = seatCol;
            for (int i = seatRow + 1; i < row; i++) {
                j++;
                if (j > col - 1) {
                    break;
                }
                if (graph[i][j] == 'L') {
                    break;
                }
                if (graph[i][j] == '#') {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    public static int countOccupiedSeatsPartOne(char[][] graph, int seatRow, int seatCol) {
        int row = graph.length;
        int col = graph[0].length;

        int count = 0;
        // check left
        if (seatCol > 0 && graph[seatRow][seatCol - 1] == '#') {
            count++;
        }
        // check right
        if (seatCol < col - 1 && graph[seatRow][seatCol + 1] == '#') {
            count++;
        }
        // check top
        if (seatRow > 0 && graph[seatRow - 1][seatCol] == '#') {
            count++;
        }
        // check bottom
        if (seatRow < row - 1 && graph[seatRow + 1][seatCol] == '#') {
            count++;
        }
        // check top left
        if (seatRow > 0 && seatCol > 0 && graph[seatRow - 1][seatCol - 1] == '#') {
            count++;
        }
        // check top right
        if (seatRow > 0 && seatCol < col - 1 && graph[seatRow - 1][seatCol + 1] == '#') {
            count++;
        }
        // check bottom left
        if (seatRow < row - 1 && seatCol > 0 && graph[seatRow + 1][seatCol - 1] == '#') {
            count++;
        }
        // check bottom right
        if (seatRow < row - 1 && seatCol < col - 1 && graph[seatRow + 1][seatCol + 1] == '#') {
            count++;
        }

        return count;
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
        do {
            newGraph = deepCopy(prevGraph);
            tempGraph = deepCopy(prevGraph);

            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    if (prevGraph[i][j] == '.') {
                        continue;
                    }
                    int count = countOccupiedSeatsPartTwo(prevGraph, i, j);
                    if (count == 0 && prevGraph[i][j] == 'L') {
                        newGraph[i][j] = '#';
                        //System.out.println("checking pos " + i + " " + j + "and count of " + count);
                    } else if (count >= 5 && prevGraph[i][j] == '#') {
                        //System.out.println("checking pos " + i + " " + j + "and count of " + count);
                        newGraph[i][j] = 'L';
                    }
                }
            }
            prevGraph = deepCopy(newGraph);
            System.out.println("-- new round --");
            printGraph(newGraph);
        } while (!equals(newGraph, tempGraph));

        //printGraph(newGraph);
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
        int ans = getTotalOccupiedSeats(graph);
        System.out.println("Occupied Seats: " + ans);

    }
}