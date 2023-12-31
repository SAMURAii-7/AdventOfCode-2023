package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Trebuchet1 {

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
        return num;
    }

    public static void main(String[] args) {
        long res = 0L;

        String filePath = "Day1/Day1Input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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