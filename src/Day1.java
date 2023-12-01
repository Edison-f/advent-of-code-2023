import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day1.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        while(in.hasNextLine()) {
            String curr = in.nextLine();
            curr = curr.replaceAll("[a-zA-Z]+", "");
            result += Integer.parseInt("" + curr.charAt(0) + curr.charAt(curr.length() - 1));
        }
        System.out.println(result);
    }
}
