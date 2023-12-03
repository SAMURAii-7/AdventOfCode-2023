package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeConundrum {
    static int sum(String input) {
        String[] parts = input.split(":");

        int gameId = Integer.parseInt(parts[0].substring(5, parts[0].length()));
        String cubesDrawn = parts[1].replace(" ", "");

        // 3blue,4red
        // 1red,2green,6blue
        // 2green
        for (String cubeSet : cubesDrawn.split(";")) {
            Map<String, Integer> colourFreq = new HashMap<>();
            for (String s : cubeSet.split(",")) {
                Pattern pattern = Pattern.compile("(\\d+)([a-zA-Z]+)");
                Matcher matcher = pattern.matcher(s);

                if (matcher.find()) {
                    int number = Integer.parseInt(matcher.group(1));
                    String colour = matcher.group(2);
                    colourFreq.put(colour, colourFreq.getOrDefault(colour, 0) + number);
                }
            }

            int flag = 0;
            for (Map.Entry<String, Integer> entry : colourFreq.entrySet()) {
                if (entry.getKey().equals("red") && entry.getValue() <= 12) {
                    flag++;
                }
                if (entry.getKey().equals("green") && entry.getValue() <= 13) {
                    flag++;
                }
                if (entry.getKey().equals("blue") && entry.getValue() <= 14) {
                    flag++;
                }
            }
            if (flag != colourFreq.entrySet().size()) {
                return 0;
            }
        }

        return gameId;
    }

    public static void main(String[] args) {
        int res = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("Day2/Day2Input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                res += sum(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Answer: " + res);
    }
}