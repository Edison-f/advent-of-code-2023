import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day4.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        while(in.hasNextLine()) {
            int pts = 0;
            String[] split = in.nextLine().split(" \\| ");
            String[] winning = split[0].split(": ")[1].split(" ");
            String[] numbers = split[1].split(" ");
            HashSet<Integer> winSet = new HashSet<>();
            for (String s :
                    winning) {
                if(!s.isEmpty()) {
                    winSet.add(Integer.parseInt(s));
                }
            }
            for (String s :
                    numbers) {
                if (!s.isEmpty() && winSet.contains(Integer.parseInt(s))) {
                    if(pts == 0) {
                        pts = 1;
                    } else {
                        pts = pts << 1;
                    }
                }
            }
            result += pts;
            System.out.println(pts);
        }
        System.out.println(result);
    }
}
