import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day3.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        ArrayList<String> rows = new ArrayList<>();
        while (in.hasNextLine()) {
            rows.add(in.nextLine());
        }
        ArrayList<String> matches;
        for (int i = 0; i < rows.size(); i++) {
            String currRow = rows.get(i);
            matches = new ArrayList<>();
            for (int j = 0; j < currRow.length(); j++) {
                System.out.print(currRow.charAt(j));
                if (currRow.charAt(j) == '*') {
                    int[] adj = adjacent(i, j, rows);
                    if(adj[0] == 2) {
                        result += adj[1] * adj[2];
                        matches.add("(" + adj[1] + " " + adj[2] + ")");
                    }
                }
            }
            for (String s : matches) {
                System.out.print("\t" + s);
            }
            System.out.println();
        }
        System.out.println(result);
    }

    public static int[] adjacent(int x, int y, ArrayList<String> rows) {
        int[] result = new int[3];
        int count = 1;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                try {
                    if ((rows.get(i).charAt(j) + "").matches("[0-9]")) {
                        result[0]++;
                        result[count] = expand(i, j, rows);
                        count++;
                        while((rows.get(i).charAt(j) + "").matches("[0-9]")) {
                            j++;
                        }
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return result;
    }

    public static int expand(int x, int y, ArrayList<String> rows) {
        String result = rows.get(x).charAt(y) + "";
        int i = y - 1;
        while (i != -1 && (rows.get(x).charAt(i) + "").matches("[0-9]")) {
            result = rows.get(x).charAt(i) + result;
            i--;
        }
        i = y + 1;
        while (i != rows.get(x).length() && (rows.get(x).charAt(i) + "").matches("[0-9]")) {
            result += rows.get(x).charAt(i);
            i++;
        }
        return Integer.parseInt(result);
    }
}
