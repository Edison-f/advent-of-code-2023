import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day8.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        int steps = 0;
        HashMap<String, Route> map = new HashMap<>();
        String tape = in.nextLine();
        in.nextLine();
        while(in.hasNextLine()) {
            String[] split = in.nextLine().split(" = ");
            String[] dirs = split[1].split(", ");
            map.put(split[0], new Route(dirs[0].substring(1), dirs[1].substring(0, 3)));
        }
        String curr = "AAA";
        while(!curr.equals("ZZZ")) {
            if(tape.charAt(steps) == 'L') {
                curr = map.get(curr).left;
            } else {
                curr = map.get(curr).right;
            }
            steps++;
            result++;
            if(steps == tape.length()) {
                steps = 0;
            }
        }
        System.out.println(result);
    }

    public static class Route {
        public String left;
        public String right;

        public Route (String left, String right) {
            this.left = left;
            this.right = right;
        }
    }
}
