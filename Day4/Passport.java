import java.util.*;

public class Passport {

    public static boolean checkValidPassportData(String[] fieldsArr) {
        if (fieldsArr.length >= 7 && fieldsArr.length <= 8) {
            boolean isValidBYR = false;
            boolean isValidIYR = false;
            boolean isValidEYR = false;
            boolean isValidHGT = false;
            boolean isValidHCL = false;
            boolean isValidECL = false;
            boolean isValidPID = false;
            boolean isValidCID = false;

            for (String str : fieldsArr) {
                if (str.isBlank()) {
                    continue;
                }
                String[] strArr = str.split(":");
                String field = strArr[0].trim();
                String data = strArr[1].trim();
                switch (field) {
                case "byr":
                    if (Integer.parseInt(data) >= 1920 && Integer.parseInt(data) <= 2002) {
                        isValidBYR = true;
                    }
                    break;
                case "iyr":
                    if (Integer.parseInt(data) >= 2010 && Integer.parseInt(data) <= 2020) {
                        isValidIYR = true;
                    }
                    break;
                case "eyr":
                    if (Integer.parseInt(data) >= 2020 && Integer.parseInt(data) <= 2030) {
                        isValidEYR = true;
                    }
                    break;
                case "hgt":
                    String subStr = data.substring(0, data.length() - 2);
                    if (data.contains("cm")) {
                        if (Integer.parseInt(subStr) >= 150 && Integer.parseInt(subStr) <= 193) {
                            isValidHGT = true;
                        }
                    } else if (data.contains("in")) {
                        if (Integer.parseInt(subStr) >= 59 && Integer.parseInt(subStr) <= 76) {
                            isValidHGT = true;
                        }
                    }
                    break;
                case "hcl":
                    if (data.length() == 7 && data.charAt(0) == '#') {
                        boolean isWithinRange = true;
                        char[] charArr = data.toCharArray();
                        for (int i = 1; i < charArr.length; i++) {
                            int ascii = (int) charArr[i];

                            // Check for range (a -> f) and (0 -> 9) using ascii
                            if (!((ascii >= 97 && ascii <= 102) || (ascii >=48 && ascii <= 57))) {
                                isWithinRange = false;
                                break;
                            }
                        }
                        isValidHCL = isWithinRange;
                    }
                    break;
                case "ecl":
                    if (data.equals("amb") || data.equals("blu") || data.equals("brn")
                            || data.equals("gry") || data.equals("grn")
                            || data.equals("hzl") || data.equals("oth")) {
                        isValidECL = true;
                    }
                    break;
                case "pid":
                    if (data.length() == 9) {
                        isValidPID = true;
                    }
                    break;
                case "cid":
                    isValidCID = false;
                    break;
                }
            }
            if (isValidBYR && isValidIYR && isValidEYR && isValidHGT
                    && isValidHCL && isValidECL && isValidPID) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public static boolean checkValidPassportField(String[] fieldsArr) {
        if (fieldsArr.length == 8) {
            return true;
        } else if (fieldsArr.length == 7) {
            boolean isMissingBYR = true;
            boolean isMissingIYR = true;
            boolean isMissingEYR = true;
            boolean isMissingHGT = true;
            boolean isMissingHCL = true;
            boolean isMissingECL = true;
            boolean isMissingPID = true;
            boolean isMissingCID = true;

            for (String str : fieldsArr) {
                if (str.isBlank()) {
                    continue;
                }
                String[] strArr = str.split(":");
                String field = strArr[0].trim();
                switch (field) {
                case "byr":
                    isMissingBYR = false;
                    break;
                case "iyr":
                    isMissingIYR = false;
                    break;
                case "eyr":
                    isMissingEYR = false;
                    break;
                case "hgt":
                    isMissingHGT = false;
                    break;
                case "hcl":
                    isMissingHCL = false;
                    break;
                case "ecl":
                    isMissingECL = false;
                    break;
                case "pid":
                    isMissingPID = false;
                    break;
                case "cid":
                    isMissingCID = false;
                    break;
                }
            }

            if (!isMissingBYR && !isMissingIYR && !isMissingEYR && !isMissingHGT
                    && !isMissingHCL && !isMissingECL && !isMissingPID) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public static int countValidPassport(List<String> parsedInputs) {
        int ans = 0;

        int count = 0;

        for (String input : parsedInputs) {
            String[] fieldsArr = input.trim().split(" ");
            System.out.println(count + ": " + checkValidPassportData(fieldsArr));
            count++;
            boolean isValidPassport = checkValidPassportField(fieldsArr) && checkValidPassportData(fieldsArr);
            if (isValidPassport) {
                ans++;
            }
        }

        return ans;
    }

    public static List<String> parseInput(List<String> listOfInputs) {
        List<String> parsedInputs = new ArrayList<>();
        String combinedInput = "";

        for (int i = 0; i < listOfInputs.size(); i++) {

            if (i == listOfInputs.size() - 1) {
                combinedInput += (" " + listOfInputs.get(i));
                parsedInputs.add(combinedInput);
            }

            if (listOfInputs.get(i).isBlank()) {
                parsedInputs.add(combinedInput);
                combinedInput = "";
            } else {
                combinedInput += (" " + listOfInputs.get(i));
            }
        }

        return parsedInputs;
    }

    public static List<String> readInput(Scanner sc) {
        List<String> listOfInputs = new ArrayList<>();

        while (sc.hasNextLine()) {
            String stringToAdd = sc.nextLine();
            listOfInputs.add(stringToAdd);
        }

        return listOfInputs;
    }

    public static void print(List<String> listOfInputs) {
        for (String line : listOfInputs) {
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> listOfInputs = readInput(sc);
        List<String> parsedInputs = parseInput(listOfInputs);
        int ans = countValidPassport(parsedInputs);
        System.out.println(ans);
    }
}