import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day5.txt");
        Scanner in = new Scanner(fs);
        long result = Long.MAX_VALUE;

        ArrayList<Long> seeds = new ArrayList<>();
        String read = in.nextLine();
        String split[] = read.split(": ")[1].split(" ");
        ArrayList<Converter> converters = new ArrayList<>();

        final String convertRegex = "[a-zA-Z]";
        final Pattern convertPattern = Pattern.compile(convertRegex);
        final String numberRegex = "[0-9]";
        final Pattern numberPattern = Pattern.compile(numberRegex);

        for (String s :
                split) {
            seeds.add(Long.parseLong(s));
        }
        while (in.hasNextLine()) {
            String curr = in.nextLine();
            if (convertPattern.matcher(curr).find()) {
                converters.add(new Converter());
            } else if (numberPattern.matcher(curr).find()) {
                split = curr.split(" ");
                converters.get(converters.size() - 1).add(split[0], split[1], split[2]);
            }
        }
        for (long i : seeds) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (Converter converter : converters) {
            seeds.replaceAll(converter::convert);
            for (long i : seeds) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        for (long i : seeds) {
            result = Math.min(i, result);
        }

        System.out.println(result);
    }

    static class Converter {

        public ArrayList<long[]> chart = new ArrayList<>();

        public void add(String rangeStart, String domainStart, String length) {
            chart.add(new long[]{Long.parseLong(domainStart),
                    Long.parseLong(domainStart) + Long.parseLong(length), Long.parseLong(rangeStart)});
        }

        public long convert(long n) {
            for (long[] arr : chart) {
                if (n >= arr[0] && n < arr[1]) {
                    return arr[2] + n - arr[0];
                }
            }
            return n;
        }
    }
}
