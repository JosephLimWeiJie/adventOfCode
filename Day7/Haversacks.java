import java.util.*;

public class Haversacks {
    private int v;
    private List<List<Pair<String, Integer>>> adjList;
    private Map<Integer, String> mapVertexToName;
    private Map<String, Integer> mapNameToVertex;
    private Map<Integer, Integer> mapVertexToWeight;

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
            return "(" + this.firstVal + ": " + this.secondVal + ")";
        }
    }

    public Haversacks(int v) {
        this.v = v;
        this.adjList = new ArrayList<>();
        this.mapNameToVertex = new HashMap<>();
        this.mapVertexToName = new HashMap<>();
        this.mapVertexToWeight = new HashMap<>();

        for (int i = 0; i < v; i++) {
            this.adjList.add(new ArrayList<>());
        }
    }

    public static int countBagsUtil(int index, Haversacks hs, List<String> singleBags) {
        int total = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (Pair<String, Integer> neighbor : hs.adjList.get(v)) {
                int weight = neighbor.getSecondVal();
                int neighborIndex = hs.mapNameToVertex.get(neighbor.getFirstVal());
                for (int i = 0; i < weight; i++) {
                    queue.add(neighborIndex);
                    total += 1;
                }
            }
        }

        return total;
    }

    public static int countBagsInShinyGoldBag(Haversacks hs) {
        int count = 0;
        int shinyGoldBagIndex = hs.mapNameToVertex.get("shinygold");
        List<String> singleBags = new ArrayList<>();
        for (int i = 0; i < hs.adjList.size(); i++) {
            if (hs.adjList.get(i).isEmpty()) {
                singleBags.add(hs.mapVertexToName.get(i));
            }
        }

        count += (countBagsUtil(shinyGoldBagIndex, hs, singleBags));

        return count;
    }

    public static List<String> reachDestination(int startVertex, int endVertex, Haversacks hs, List<String> path) {
        path.add(hs.mapVertexToName.get(startVertex));

        if (startVertex == endVertex) {
            return path;
        }

        for (Pair<String, Integer> neighbor : hs.adjList.get(startVertex)) {
            reachDestination(hs.mapNameToVertex.get(neighbor.getFirstVal()), endVertex, hs, path);
        }

        return path;
    }

    public static List<List<String>> findPossibleRoutes(String endVertex, Haversacks hs) {
        int possibleRoutes = 0;
        List<List<String>> allPaths = new ArrayList<>();

        int endVertexIndex = hs.mapNameToVertex.get(endVertex);
        for (int i = 0; i < hs.adjList.size(); i++) {
            List<String> path = reachDestination(i, endVertexIndex, hs, new ArrayList<String>());
            allPaths.add(path);
        }

        return allPaths;
    }

    public static int countPossibleRoutes(List<List<String>> allPaths, String endVertex) {
        int count = 0;
        for (int i = 0; i < allPaths.size(); i++) {
            for (int j = 1; j < allPaths.get(i).size(); j++) {
                if (allPaths.get(i).get(j).equals(endVertex)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    public static void printAllPaths(List<List<String>> allPaths) {
        for (int i = 0; i < allPaths.size(); i++) {
            System.out.println(allPaths.get(i).get(0) + ": " + allPaths.get(i));
        }
    }

    public void printAdjList() {
        for (int i = 0; i < this.adjList.size(); i++) {
            if (this.adjList.get(i).isEmpty()) {
                System.out.print(this.mapVertexToName.get(i) + ": ()" + System.lineSeparator());
            } else {
                for (Pair pair : this.adjList.get(i)) {
                    System.out.print(this.mapVertexToName.get(i) + ": " + pair.toString() + System.lineSeparator());
                }
            }
        }
    }

    public void parseInput(List<String> listOfInputs) {
        for (int i = 0; i < listOfInputs.size(); i++) {
            String[] inputArr = listOfInputs.get(i).split(" ");
            String vertex = "";

            int indexOfContain = 0;
            for (int j = 0; j < inputArr.length; j++) {
                if (inputArr[j].trim().equals("contain")) {
                    indexOfContain = j;
                    break;
                }

                if (inputArr[j].trim().contains("bag")) {
                    continue;
                }
                vertex += inputArr[j].trim();
            }

            this.mapVertexToName.put(i, vertex);
            this.mapNameToVertex.put(vertex, i);

            String neighborVertex = "";
            int weight = 0;
            for (int k = indexOfContain + 1; k < inputArr.length; k++) {


                try {
                    weight = Integer.parseInt(inputArr[k].trim());
                } catch (NumberFormatException noe) {
                    if (inputArr[k].contains("bag,") || inputArr[k].contains("bag.") ||
                        inputArr[k].contains("bags,") || inputArr[k].contains("bags.")) {

                        if (neighborVertex.equals("noother")) {
                            continue;
                        } else {
                            this.adjList.get(i).add(Pair.of(neighborVertex, weight));
                        }

                        weight = 0;
                        neighborVertex = "";
                    } else {
                        neighborVertex += (inputArr[k].trim());
                    }
                }
            }
        }
    }

    public static List<String> readInput(Scanner sc) {
        List<String> listOfInputs = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            listOfInputs.add(input);
        }

        return listOfInputs;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> listOfInputs = readInput(sc);
        Haversacks hs = new Haversacks(listOfInputs.size());
        hs.parseInput(listOfInputs);
        List<List<String>> possibleRoutes = findPossibleRoutes("shinygold", hs);
        int ans = countBagsInShinyGoldBag(hs);
        System.out.println(ans);

        // For troublshooting:
        // hs.printAdjList();
        // printAllPaths(possibleRoutes);
    }
}