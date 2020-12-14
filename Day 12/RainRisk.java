import java.util.*;

public class RainRisk {

    enum Direction {
        NORTH, SOUTH, EAST, WEST;
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

    public static Direction getDirectionCounterClockwise(Direction oldDir, int value) {
        Direction newDir = oldDir;

        String[] dirOne = new String[]{"north", "west", "south", "east"};
        int i = 0;
        if (oldDir == Direction.EAST) {
            i = 3;
        } else if (oldDir == Direction.WEST) {
            i = 1;
        } else if (oldDir == Direction.NORTH) {
            i = 0;
        } else if (oldDir == Direction.SOUTH) {
            i = 2;
        }

        int steps = value / 90;
        i = Math.abs(i + steps) % 4;

        if (dirOne[i] == "north") {
            newDir = Direction.NORTH;
        } else if (dirOne[i] == "west") {
            newDir = Direction.WEST;
        } else if (dirOne[i] == "south") {
            newDir = Direction.SOUTH;
        } else if (dirOne[i] == "east") {
            newDir = Direction.EAST;
        }

        return newDir;
    }

    public static Direction getDirectionClockwise(Direction oldDir, int value) {
        Direction newDir = oldDir;

        String[] dirTwo = new String[]{"north", "east", "south", "west"};
        int j = 0;
        if (oldDir == Direction.EAST) {
            j = 1;
        } else if (oldDir == Direction.WEST) {
            j = 3;
        } else if (oldDir == Direction.NORTH) {
            j = 0;
        } else if (oldDir == Direction.SOUTH) {
            j = 2;
        }

        int stepsTwo = value / 90;
        j = Math.abs(j + stepsTwo) % 4;

        if (dirTwo[j] == "north") {
            newDir = Direction.NORTH;
        } else if (dirTwo[j] == "west") {
            newDir = Direction.WEST;
        } else if (dirTwo[j] == "south") {
            newDir = Direction.SOUTH;
        } else if (dirTwo[j] == "east") {
            newDir = Direction.EAST;
        }

        return newDir;
    }

    /** Part Two solution. */
    public static int findManhattanDistancePartTwo(List<Pair<Character, Integer>> listOfInputs) {
        int x = 0;
        int y = 0;
        int startPosX = 0;
        int startPosY = 0;
        int waypointX = 10;
        int waypointY = 1;

        Direction waypointDirX = Direction.EAST;
        Direction waypointDirY = Direction.NORTH;

        for (Pair<Character, Integer> p : listOfInputs) {
            char dir = p.getFirstVal();
            int val = p.getSecondVal();
            switch (dir) {
            case 'N':
                waypointY += val;
                break;
            case 'S':
                waypointY -= val;
                break;
            case 'E':
                waypointX += val;
                break;
            case 'W':
                waypointX -= val;
                break;
            case 'L':
                int val2 = 360 - 90;
                if (val2 == 90) {
                    int temp = waypointX;
                    waypointX = waypointY;
                    waypointY = (-1) * temp;
                } else if (val2 == 180) {
                    waypointX = (-1) * waypointX;
                    waypointY = (-1) * waypointY;
                } else if (val2 == 270) {
                    int temp = waypointX;
                    waypointX = (-1) * waypointY;
                    waypointY = (-1) * temp;
                }
                break;
            case 'R':
                if (val == 90) {
                    int temp = waypointX;
                    waypointX = waypointY;
                    waypointY = (-1) * temp;
                } else if (val == 180) {
                    waypointX = (-1) * waypointX;
                    waypointY = (-1) * waypointY;
                } else if (val == 270) {
                    int temp = waypointX;
                    waypointX = (-1) * waypointY;
                    waypointY = (-1) * temp;
                }
                break;
            case 'F':
                int newX = val * waypointX;
                int newY = val * waypointY;
                x += newX;
                y += newY;
                System.out.println("Ship at " + x + " " + y + " ");
                System.out.println("Waypoint: " + waypointX + " " + waypointY);
                break;
            }
        }
        //System.out.println("x: " + x + ", y: " + y);
        return Math.abs(x - startPosX) + Math.abs(y - startPosY);
    }

    /** Part One solution. */
    public static int findManhattanDistancePartOne(List<Pair<Character, Integer>> listOfInputs) {
        int x = 0;
        int y = 0;
        int startPosX = 0;
        int startPosY = 0;

        Direction currDir = Direction.EAST;

        for (Pair<Character, Integer> p : listOfInputs) {
            char dir = p.getFirstVal();
            int val = p.getSecondVal();
            switch (dir) {
            case 'N':
                y += val;
                break;
            case 'S':
                y -= val;
                break;
            case 'E':
                x += val;
                break;
            case 'W':
                x -= val;
                break;
            case 'L':
                String[] dirOne = new String[]{"north", "west", "south", "east"};
                int i = 0;
                if (currDir == Direction.EAST) {
                    i = 3;
                } else if (currDir == Direction.WEST) {
                    i = 1;
                } else if (currDir == Direction.NORTH) {
                    i = 0;
                } else if (currDir == Direction.SOUTH) {
                    i = 2;
                }

                int steps = val / 90;
                i = Math.abs(i + steps) % 4;

                if (dirOne[i] == "north") {
                    currDir = Direction.NORTH;
                } else if (dirOne[i] == "west") {
                    currDir = Direction.WEST;
                } else if (dirOne[i] == "south") {
                    currDir = Direction.SOUTH;
                } else if (dirOne[i] == "east") {
                    currDir = Direction.EAST;
                }

                break;
            case 'R':
                String[] dirTwo = new String[]{"north", "east", "south", "west"};
                int j = 0;
                if (currDir == Direction.EAST) {
                    j = 1;
                } else if (currDir == Direction.WEST) {
                    j = 3;
                } else if (currDir == Direction.NORTH) {
                    j = 0;
                } else if (currDir == Direction.SOUTH) {
                    j = 2;
                }

                int stepsTwo = val / 90;
                j = Math.abs(j + stepsTwo) % 4;

                if (dirTwo[j] == "north") {
                    currDir = Direction.NORTH;
                } else if (dirTwo[j] == "west") {
                    currDir = Direction.WEST;
                } else if (dirTwo[j] == "south") {
                    currDir = Direction.SOUTH;
                } else if (dirTwo[j] == "east") {
                    currDir = Direction.EAST;
                }
                break;
            case 'F':
                if (currDir == Direction.NORTH) {
                    y += val;
                } else if (currDir == Direction.SOUTH) {
                    y -= val;
                } else if (currDir == Direction.WEST) {
                    x -= val;
                } else if (currDir == Direction.EAST) {
                    x += val;
                }
                break;

            }
        }
        System.out.println("x: " + x + ", y: " + y);
        return Math.abs(x - startPosX) + Math.abs(y - startPosY);
    }

    public static List<Pair<Character, Integer>> readInput(Scanner sc) {
        List<Pair<Character, Integer>> listOfInputs = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            char c = line.charAt(0);
            int val = Integer.parseInt(line.substring(1, line.length()));
            Pair<Character, Integer> p = Pair.of(c, val);
            listOfInputs.add(p);
        }

        return listOfInputs;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Pair<Character, Integer>> listOfInputs = readInput(sc);
        int ans = findManhattanDistancePartTwo(listOfInputs);
        System.out.println(ans);
    }
}