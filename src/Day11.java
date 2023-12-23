import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day11 {

    static ArrayList<Integer> expandRow = new ArrayList<>();
    static ArrayList<Integer> expandColumn = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day11.txt");
        Scanner in = new Scanner(fs);
        ArrayList<String> map = new ArrayList<>();
        while(in.hasNextLine()) {
            map.add(in.nextLine());
        }
        map = expand(map);
        for (String s :
                map) {
            System.out.println(s);
        }
        ArrayList<int[]> coords = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            String s = map.get(i);
            for(int j = 0; j < s.length(); j++) {
                if(s.charAt(j) == '#') {
                    coords.add(new int[] {i, j});
                }
            }
        }
        long result = 0;
        for(int i = 0; i < coords.size(); i++) {
            for(int j = i + 1; j < coords.size(); j++) {
                int x = coords.get(i)[0];
                int y = coords.get(j)[0];
                long mult = intersect(expandRow, x, y);
                result += Math.abs(x - y) + (mult * 999999L);
                x = coords.get(i)[1];
                y = coords.get(j)[1];
                mult = intersect(expandColumn, x, y);
                result += Math.abs(x - y) + (mult * 999999L);
            }
        }
        System.out.println(result);
    }

    public static ArrayList<String> expand(ArrayList<String> orig) {
        ArrayList<String> result = new ArrayList<>();
        for (int j = 0; j < orig.size(); j++) {
            String s = orig.get(j);
            if (s.indexOf('#') == -1) {
                expandRow.add(j);
            }
            result.add(s);
        }
        for(int i = 0; i < result.get(0).length(); i++) {
            boolean empty = true;
            for (String s : result) {
                if (s.charAt(i) == '#') {
                    empty = false;
                    break;
                }
            }
            if(empty) {
                expandColumn.add(i);
            }
        }
        return result;
    }

    public static int intersect(ArrayList<Integer> locs, int n1, int n2) {
        int min = Math.min(n1, n2);
        int max = Math.max(n1, n2);
        int start;
        int end;
        if(!sorted(locs)) {
            Collections.sort(locs);
        }
        int i = 0;
        while (i < locs.size() && min > locs.get(i)) {
            i++;
        }
        start = i;
        while(i < locs.size() && max > locs.get(i)) {
            i++;
        }
        end = i;
        return end - start;
    }

    public static boolean sorted(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            if(arr.get(i) > arr.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
