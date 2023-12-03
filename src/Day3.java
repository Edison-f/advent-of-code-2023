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
        while(in.hasNextLine()) {
            rows.add(in.nextLine());
        }
        String part = "";
        boolean adjacent = false;
        ArrayList<String> matches;
        for(int i = 0; i < rows.size(); i++) {
            String currRow = rows.get(i);
            matches = new ArrayList<>();
            for(int j = 0; j < currRow.length(); j++) {
                System.out.print(currRow.charAt(j));
                if(currRow.charAt(j) != '.' && (currRow.charAt(j) + "").matches("[0-9]")) {
                    part += currRow.charAt(j) + "";
                    adjacent = adjacent || adjacent(i, j, rows);
                } else {
                    if(adjacent) {
//                        System.out.println(part);
                        matches.add(part);
                        result += Integer.parseInt(part);
                    }
                    adjacent = false;
                    part = "";
                }
            }
            for (String s : matches) {
                System.out.print("\t" + s);
            }
            System.out.println();
        }
        System.out.println(result);
    }

    public static boolean adjacent(int x, int y, ArrayList<String> rows) {
        boolean result = false;
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                if(i == x && j == y) { continue; }
                try {
                    result = result || !(rows.get(i).charAt(j) + "").matches("[0-9]|\\.");
                } catch (Exception ignored) {}
            }
        }
        return result;
    }
}
