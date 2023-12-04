package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Scratchcards1 {
    static int points(String[] winningNums, String[] cardNums) {
        int pointsEarned = 0;
        Map<String, Integer> freq = new HashMap<>();
        for (String s : winningNums) {
            freq.put(s, freq.getOrDefault(freq, 0) + 1);
        }

        for (String s : cardNums) {
            if (freq.containsKey(s)) {
                freq.put(s, freq.get(s) - 1);
            }
        }

        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 0) {
                if (pointsEarned == 0) {
                    pointsEarned = 1;
                } else {
                    pointsEarned *= 2;
                }
            }
        }

        return pointsEarned;
    }

    public static void main(String[] args) {
        int res = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("Day4/Day4Input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] firstSplit = line.split(":");
                String[] secondSplit = firstSplit[1].split("\\|");
                String[] winningNums = secondSplit[0].trim().split("\\s+");
                String[] cardNums = secondSplit[1].trim().split("\\s+");

                res += points(winningNums, cardNums);
            }
            System.out.println("Answer: " + res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
