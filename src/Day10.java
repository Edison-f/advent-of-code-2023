import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Day10 {

    // Up Down Left Right
    static int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day10.txt");
        Scanner in = new Scanner(fs);
        ArrayList<String> map = new ArrayList<>();
        int[] start = new int[2];
        int row = 0;
        while (in.hasNextLine()) {
            String s = in.nextLine();
            map.add(s);
            if (s.indexOf('S') != -1) {
                start[0] = row;
                start[1] = s.indexOf('S');
            }
            row++;
        }
        boolean[][] seen = new boolean[map.size()][map.get(0).length()];
        int[][] dist = new int[map.size()][map.get(0).length()];
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{start[0], start[1], 0});
        stack.push(new int[]{start[0], start[1], 1});
        stack.push(new int[]{start[0], start[1], 2});
        stack.push(new int[]{start[0], start[1], 3});
        while (!stack.empty()) {
            int[] curr = stack.pop();
            seen[curr[0]][curr[1]] = true;
            int x = curr[0];
            int y = curr[1];
            switch (curr[2]) {
                case 0 -> x--;
                case 1 -> x++;
                case 2 -> y--;
                case 3 -> y++;
            }
            if (inBounds(dist, new int[]{x, y}) && ((dist[x][y] > (dist[curr[0]][curr[1]] + 1)) || !seen[x][y])) {
                char c = map.get(x).charAt(y);
                switch (curr[2]) {
                    case 0 -> { // Up
                        if (c == '|') {
                            dist[x][y] = dist[curr[0]][curr[1]] + 1;
                            stack.push(new int[]{x, y, 0});
                        } else if (c == '7') {
                            dist[x][y] = dist[curr[0]][curr[1]] + 1;
                            stack.push(new int[]{x, y, 2});
                        } else if (c == 'F') {
                            dist[x][y] = dist[curr[0]][curr[1]] + 1;
                            stack.push(new int[]{x, y, 3});
                        }
                    }
                    case 1 -> { // Down
                        if (c == '|') {
                            dist[x][y] = dist[curr[0]][curr[1]] + 1;
                            stack.push(new int[]{x, y, 1});
                        } else if (c == 'J') {
                            dist[x][y] = dist[curr[0]][curr[1]] + 1;
                            stack.push(new int[]{x, y, 2});
                        } else if (c == 'L') {
                            dist[x][y] = dist[curr[0]][curr[1]] + 1;
                            stack.push(new int[]{x, y, 3});
                        }
                    }
                    case 2 -> { // Left
                        if (c == '-') {
                            dist[x][y] = dist[curr[0]][curr[1]] + 1;
                            stack.push(new int[]{x, y, 2});
                        } else if (c == 'F') {
                            dist[x][y] = dist[curr[0]][curr[1]] + 1;
                            stack.push(new int[]{x, y, 1});
                        } else if (c == 'L') {
                            dist[x][y] = dist[curr[0]][curr[1]] + 1;
                            stack.push(new int[]{x, y, 0});
                        }
                    }
                    case 3 -> { // Right
                        if (c == '-') {
                            dist[x][y] = dist[curr[0]][curr[1]] + 1;
                            stack.push(new int[]{x, y, 3});
                        } else if (c == '7') {
                            dist[x][y] = dist[curr[0]][curr[1]] + 1;
                            stack.push(new int[]{x, y, 1});
                        } else if (c == 'J') {
                            dist[x][y] = dist[curr[0]][curr[1]] + 1;
                            stack.push(new int[]{x, y, 0});
                        }
                    }
                }
            }

        } // 6475

        int result = -1;
        for (int[] inner :
                dist) {
            for (int i :
                    inner) {
                result = Math.max(result, i);
            }
        }
        System.out.println(result);
    }

    public static boolean inBounds(int[][] arr, int[] coords) {
        return coords[0] < arr.length && coords[0] >= 0 && coords[1] < arr[0].length && coords[1] >= 0;
    }
}
