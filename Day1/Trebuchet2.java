package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Trebuchet2 {

    static String replaceWords(String s) {
        Map<String, String> numStrings = new HashMap<>();
        numStrings.put("one", "1");
        numStrings.put("two", "2");
        numStrings.put("three", "3");
        numStrings.put("four", "4");
        numStrings.put("five", "5");
        numStrings.put("six", "6");
        numStrings.put("seven", "7");
        numStrings.put("eight", "8");
        numStrings.put("nine", "9");

        int index = Integer.MAX_VALUE;
        while (index == Integer.MAX_VALUE) {
            String first = "";
            for (Map.Entry<String, String> entry : numStrings.entrySet()) {
                int pos = s.indexOf(entry.getKey());
                if (pos != -1 && pos < index) {
                    index = s.indexOf(entry.getKey());
                    first = entry.getKey();
                }
            }
            index = -1;
            if (numStrings.containsKey(first)) {
                s = s.replace(first.substring(0, first.length() - 1), numStrings.get(first));
                index = Integer.MAX_VALUE;
            }
        }
        // System.out.print(s + " ");
        return s;
    }

    static int sum(String str) {
        int flag = 0;
        int num = 0;
        char currChar = '\0';

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                currChar = str.charAt(i);
                if (flag == 0) {
                    num += 10 * (currChar - '0');
                    flag++;
                }
            }
        }

        num += (currChar - '0');
        // System.out.println(num);
        return num;
    }

    public static void main(String[] args) {
        long res = 0L;

        String filePath = "Day1/Day1Input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // System.out.print(line + " ");
                String str = replaceWords(line);
                res += sum(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Answer: " + res);
    }
}