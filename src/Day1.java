import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day1 {
    static String[] encoded = new String[] {"oneight", "twone", "threeight", "fiveight", "sevenine", "eightwo",
                                            "eighthree", "nineight", "one", "two", "three", "four", "five", "six",
                                            "seven", "eight", "nine"};
    static String[] decoded = new String[] {"18", "21", "38", "58", "79", "82", "83", "98", "1", "2", "3", "4", "5",
                                            "6", "7", "8", "9", };
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day1.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        while(in.hasNextLine()) {
            String curr = in.nextLine();
            for(int i = 0; i < encoded.length; i++) {
                curr = curr.replaceAll(encoded[i], decoded[i]);
            }
            curr = curr.replaceAll("[a-zA-Z]+", "");
            result += Integer.parseInt("" + curr.charAt(0) + curr.charAt(curr.length() - 1));
        }
        System.out.println(result);
    }
}
