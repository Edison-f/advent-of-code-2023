import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day8.txt");
        Scanner in = new Scanner(fs);
        long result = 1;
        int index;
        long steps;
        HashMap<String, Route> map = new HashMap<>();
        String tape = in.nextLine();
        in.nextLine();
        ArrayList<String> starts = new ArrayList<>();
        while (in.hasNextLine()) {
            String[] split = in.nextLine().split(" = ");
            String[] dirs = split[1].split(", ");
            map.put(split[0], new Route(dirs[0].substring(1), dirs[1].substring(0, 3)));
            if (split[0].charAt(2) == 'A') {
                starts.add(split[0]);
            }
        }

        ArrayList<Long> stepsArr = new ArrayList<>();
        for (String s :
                starts) {
            String curr = s;
            steps = 0;
            index = 0;
            while(curr.charAt(2) != 'Z') {
                if(tape.charAt(index) == 'L') {
                    curr = map.get(curr).left;
                } else {
                    curr = map.get(curr).right;
                }
                index++;
                steps++;
                if(index == tape.length()) {
                    index = 0;
                }
            }
            if(steps != 1) {
                stepsArr.add(steps);
            }
        }

        for(int i = 0; i < stepsArr.size(); i++) {
            result = lcm(result, stepsArr.get(i));
            System.out.println(result);
        }

        //

//        String curr = "AAA";
//        while(!curr.equals("ZZZ")) {
//            if(tape.charAt(steps) == 'L') {
//                curr = map.get(curr).left;
//            } else {
//                curr = map.get(curr).right;
//            }
//            steps++;
//            result++;
//            if(steps == tape.length()) {
//                steps = 0;
//            }
//        }
        System.out.println(result);
    }

    public static long lcm(long x, Long y) {
        long multi = x * y;
        long gcd = gcd(x, y);
        System.out.println(multi + " " + gcd);
        return multi / gcd;
    }

    public static long gcd(long x, long y) {
        long currX = x;
        long currY = y;
        while(currX != currY) {
            if(currX > currY) {
                currX -= currY;
            } else {
                currY -= currX;
            }
        }
        return x;
    }

    public static class Route {
        public String left;
        public String right;

        public Route(String left, String right) {
            this.left = left;
            this.right = right;
        }
    }
}
