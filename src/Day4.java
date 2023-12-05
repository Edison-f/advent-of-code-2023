import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day4.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        int[] reruns = new int[204];
        int[] totals = new int[204];
        boolean[] seen = new boolean[204];
        int i = 1;
        String currString = in.nextLine();
        int currCard;
        while(!seen[i] || (i != reruns.length && reruns[i] > 0)) {
            int matches = 0;
            seen[i] = true;
            currCard = Integer.parseInt(currString.split(":")[0].split(" ")[1]);
            if(i != currCard) {
                currString = in.nextLine();
            }
            String[] split = currString.split(" \\| ");
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
                    matches++;
                    reruns[i]--;
                }
            }
            for(int j = i + 1; j < i + 1 + matches; j++) {
                reruns[j]++;
                totals[j]++;
            }
            if(reruns[i] == 0) {
                i++;
            }
            System.out.println(matches);
        }
        for(int j : totals) {
            result += j;
        }
        System.out.println(result);
    }
}
