import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day4.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        int[] reruns = new int[203];
        Arrays.fill(reruns, 1);
        int[] totals = new int[203];
        int i = 0;
        int times;
        int currCard;
        while (in.hasNextLine()) {
            times = 0;
            int matches = 0;
            String[] split = in.nextLine().split(" \\| ");
            String[] winning = split[0].split(": ")[1].split(" ");
            String[] numbers = split[1].split(" ");
            HashSet<Integer> winSet = new HashSet<>();
            for (String s :
                    winning) {
                if (!s.isEmpty()) {
                    winSet.add(Integer.parseInt(s));
                }
            }
            for (String s :
                    numbers) {
                if (!s.isEmpty() && winSet.contains(Integer.parseInt(s))) {
                    matches++;
                }
            }
            for (int k = 0; k < reruns[i]; k++) {
                for (int j = i + 1; j < i + 1 + matches; j++) {
                    reruns[j]++;
//                    totals[j]++;
                }
            }
            i++;
            System.out.println(matches);
        }
        for (int k = 0; k < reruns.length; k++) {
            int j = reruns[k];
            result += j;
        }
        System.out.println(result);
    }
}
