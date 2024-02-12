import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day12.txt");
        Scanner in = new Scanner(fs);
        long result = 0;
        while (in.hasNextLine()) {
            String[] curr = in.nextLine().split(" ");
            ArrayList<Integer> sizes = new ArrayList<>();
            String[] split = curr[1].split(",");
            for (String s :
                    split) {
                sizes.add(Integer.parseInt(s));
            }
            SpringSolver solver = new SpringSolver(curr[0], sizes);
            int solve = solver.solve();
            System.out.println(solve);
            result += solve;
        }
        System.out.println(result);
    }

    static class SpringSolver {

        // 0 - Empty, 1 - Maybe, 2 - Yes
        private int[] possibilities;
        private ArrayList<Integer> sizes;
        private HashSet<ArrayList<Integer>> set;

        public SpringSolver(String layout, ArrayList<Integer> sizes) {
            this.possibilities = new int[layout.length()];
            for (int i = 0; i < layout.length(); i++) {
                char c = layout.charAt(i);
                switch (c) {
                    case '.' -> possibilities[i] = 0;
                    case '?' -> possibilities[i] = 1;
                    case '#' -> possibilities[i] = 2;
                }
            }
            Collections.sort(sizes);
            Collections.reverse(sizes);
            this.sizes = sizes;
            set = new HashSet<>();
        }

        public int solve() {
            int[] clone = new int[possibilities.length];
            System.arraycopy(possibilities, 0, clone, 0, clone.length);
            return solve(0, clone);
        }


        // {#, location}
        public int solve(int sizeStart, int[] currPoss) {
            int result = 0;
            ArrayList<Integer> id = new ArrayList<>();
            for (int i = 0; i < currPoss.length; i++) {
                id.add(currPoss[i]);
            }
            try {
                id.add(sizes.get(sizeStart));
            } catch (Exception ignored) {
                id.add(-1);
            }
            if (!set.add(id)) {
                return 0;
            }
            if (sizeStart == sizes.size()) {
                return goodPoss(currPoss) ? 1 : 0;
            }
            for (int i = 0; i < currPoss.length - (sizes.get(sizeStart) - 1); i++) {
                if (valid(sizes.get(sizeStart), i, currPoss)) {
                    int[] newPoss = new int[currPoss.length];
                    for (int j = 0; j < i; j++) {
                        newPoss[j] = currPoss[j];
                    }
                    for (int j = i + sizes.get(sizeStart) + 1; j < currPoss.length; j++) {
                        newPoss[j] = currPoss[j];
                    }
                    result += solve(sizeStart + 1, newPoss);
                }
            }
            return result;
        }

        public boolean valid(int size, int start, int[] currPoss) {
            for (int i = start; i < start + size; i++) {
                if (currPoss[i] == 0) {
                    return false;
                }
            }
            try {
                return (currPoss[size + start] != 2) && (currPoss[start - 1] != 2);
            } catch (Exception ignored) {
                try {
                    return (currPoss[start - 1] != 2);
                } catch (Exception ignoreAgain) {
                    return true;
                }
            }
        }

        public boolean goodPoss(int[] currPoss) {
            for (int i :
                    currPoss) {
                if (i == 2) {
                    return false;
                }
            }
            return true;
        }
    }
}