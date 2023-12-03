import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day2.txt");
        Scanner in = new Scanner(fs);
//        int reds = 12;
//        int greens = 13;
//        int blues = 14;
//        int game = 0;
        int result = 0;
        while(in.hasNextLine()) {
            int reds = 0;
            int greens = 0;
            int blues = 0;
//            game++;

            String curr = in.nextLine();
            curr = curr.split(": ")[1];
            String[] guesses = curr.split("; ");
            boolean impossible = false;
            for(int i = 0; i < guesses.length && !impossible; i++) {
                String[] numColors = guesses[i].split(", ");
                for (String s :
                        numColors) {
                    String[] split = s.split(" ");
                    int num = Integer.parseInt(split[0]);
                    String color = split[1];
                    switch (color) {
                        case "red":
                            reds = Math.max(reds, num);
                            break;
                        case "green":
                            greens = Math.max(greens, num);
                            break;
                        case "blue":
                            blues = Math.max(blues, num);
                            break;
                    }
                }

            }
            result += reds * greens * blues;
        }
        System.out.println(result);
    }
}
