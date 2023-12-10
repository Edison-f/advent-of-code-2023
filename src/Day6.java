import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day6.txt");
        Scanner in = new Scanner(fs);
        long result = -1;
        ArrayList<Long> time = new ArrayList<>();
        ArrayList<Long> distance = new ArrayList<>();

        String[] read = in.nextLine().split(":\\s+")[1].split("\\s+");
        String join = "";
        for (int i = 0; i < read.length; i++) {
            join += read[i];
        }
        time.add(Long.parseLong(join));
        join = "";
        read = in.nextLine().split(":\\s+")[1].split("\\s+");
        for (int i = 0; i < read.length; i++) {
            join += read[i];
        }
        distance.add(Long.parseLong(join));

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
