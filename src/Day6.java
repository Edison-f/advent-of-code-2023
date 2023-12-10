import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day6.txt");
        Scanner in = new Scanner(fs);
        int result = -1;
        ArrayList<Integer> time = new ArrayList<>();
        ArrayList<Integer> distance = new ArrayList<>();

        String[] read = in.nextLine().split("\\s+");
        for (int i = 1; i < read.length; i++) {
            time.add(Integer.parseInt(read[i]));
        }
        read = in.nextLine().split("\\s");
        for (int i = 1; i < read.length; i++) {
            try {
                distance.add(Integer.parseInt(read[i]));
            } catch (Exception ignored) {}
        }

        for(int i = 0; i < time.size(); i++) {
            int count = 0;
            for(int j = 0; j <= time.get(i); j++) {
                if(((time.get(i) - j) * j) > distance.get(i)) {
                    count++;
                }
            }
            if(result == -1) {
                result = count;
            } else {
                result *= count;
            }
        }
        System.out.println(result);
    }
}
