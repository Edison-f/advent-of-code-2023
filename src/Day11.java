import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {
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
                result += Math.abs(coords.get(i)[0] - coords.get(j)[0]);
                result += Math.abs(coords.get(i)[1] - coords.get(j)[1]);
            }
        }
        System.out.println(result);
    }

    public static ArrayList<String> expand(ArrayList<String> orig) {
        ArrayList<String> result = new ArrayList<>();
        for (String s : orig) {
            if(s.indexOf('#') == -1) {
                result.add(s);
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
                for (int j = 0; j < result.size(); j++) {
                    result.set(j, result.get(j).substring(0, i) + "." + result.get(j).substring(i));
                }
                i++;
            }
        }
        return result;
    }
}
