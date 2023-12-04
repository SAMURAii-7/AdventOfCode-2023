package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GearRatios1 {
    static boolean isSymbol(String str) {
        if (!Character.isDigit(str.charAt(0)) && !str.equals(".")) {
            return true;
        }

        return false;
    }

    static boolean isEnginePart(int row, int col, List<String[]> lines) {
        List<int[]> dir = new ArrayList<>(List.of(
                new int[] { 1, 1 },
                new int[] { 1, 0 },
                new int[] { 1, -1 },
                new int[] { 0, -1 },
                new int[] { -1, -1 },
                new int[] { -1, 0 },
                new int[] { -1, 1 },
                new int[] { 0, 1 }));

        for (int[] d : dir) {
            int x = d[0];
            int y = d[1];
            int dx = row + x;
            int dy = col + y;

            if (dx >= 0 && dx < lines.size() && dy >= 0 && dy < lines.get(0).length && isSymbol(lines.get(dx)[dy])) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int res = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("Day3/Day3Input.txt"))) {
            List<String[]> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.split(""));
            }

            int row = 0;
            for (String[] strArray : lines) {
                int i = 0, j = 0;

                while (j < strArray.length) {
                    if (isSymbol(strArray[j]) || strArray[j].equals(".")) {
                        i++;
                        j++;
                        continue;
                    }
                    StringBuilder num = new StringBuilder();
                    while (j < strArray.length && !isSymbol(strArray[j]) && !strArray[j].equals(".")) {
                        num.append(strArray[j]);
                        j++;
                    }
                    // check if it is engine part
                    boolean flag = false;
                    for (int k = i; k < j; k++) {
                        if (isEnginePart(row, k, lines)) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        // System.out.println(num.toString());
                        res += Integer.parseInt(num.toString());
                    }
                    // update i and j
                    i = j;
                }

                row++;
            }

            System.out.println("Answer: " + res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}