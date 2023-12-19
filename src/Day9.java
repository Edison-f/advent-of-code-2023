import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day9.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        while (in.hasNextLine()) {
            String[] split = in.nextLine().split(" ");
            ArrayList<ArrayList<Integer>> diffs = new ArrayList<>();
            diffs.add(new ArrayList<>());
            for (String s :
                    split) {
                diffs.get(0).add(Integer.parseInt(s));
            }
            while (!allZero(diffs.get(diffs.size() - 1))) {
                ArrayList<Integer> curr = new ArrayList<>();
                for (int i = 0; i < diffs.get(diffs.size() - 1).size() - 1; i++) {
                    curr.add(diffs.get(diffs.size() - 1).get(i + 1) - diffs.get(diffs.size() - 1).get(i));
                }
                diffs.add(curr);
            }
            for(int i = diffs.size() - 1; i > 0; i--) {
                diffs.get(i - 1).add(
                        diffs.get(i - 1).get(diffs.get(i - 1).size() - 1) +
                                diffs.get(i).get(diffs.get(i).size() - 1));
            }
            result += diffs.get(0).get(diffs.get(0).size() - 1);
            System.out.println(diffs.get(0).get(diffs.get(0).size() - 1));
        }
        System.out.println("\n-===-\n" + result);
    }

    public static boolean allZero(ArrayList<Integer> arr) {
        for (int i :
                arr) {
            if(i != 0) {
                return false;
            }
        }
        return true;
    }
}
