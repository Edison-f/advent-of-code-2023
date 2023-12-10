import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

// Range Start, Domain Start, length

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day5.txt");
        Scanner in = new Scanner(fs);
        long result = Long.MAX_VALUE;

        String read = in.nextLine();
        String split[] = read.split(": ")[1].split(" ");
        ArrayList<Converter> converters = new ArrayList<>();

        ArrayList<long[]> seeds = new ArrayList<>();
        Stack<long[]> currRanges = new Stack<>();
        Stack<long[]> nextRanges = new Stack<>();
        for (int i = 0; i < split.length; i += 2) {
            seeds.add(new long[]{Long.parseLong(split[i]), Long.parseLong(split[i]) + Long.parseLong(split[i + 1]) - 1});
        }

        final String convertRegex = "[a-zA-Z]";
        final Pattern convertPattern = Pattern.compile(convertRegex);
        final String numberRegex = "[0-9]";
        final Pattern numberPattern = Pattern.compile(numberRegex);

        while (in.hasNextLine()) {
            String curr = in.nextLine();
            if (convertPattern.matcher(curr).find()) {
                converters.add(new Converter());
            } else if (numberPattern.matcher(curr).find()) {
                split = curr.split(" ");
                converters.get(converters.size() - 1).add(split[0], split[1], split[2]);
            }
        }
        /*
        Stack<long[]> potentialResults = new Stack<>();
        for (long[] pair : seeds) {
            int i = 0;
            currRanges.add(pair);
            while (!currRanges.empty() && i < converters.size()) {
                long[] curr = currRanges.pop();
                nextRanges.addAll(converters.get(i).match(curr));
                if (currRanges.empty()) {
                    currRanges.addAll(nextRanges);
                    nextRanges.clear();
                    i++;
                }
            }
            potentialResults.addAll(currRanges);
        }
        System.out.println("-===-");
        while (!potentialResults.empty()) {
            long[] curr = potentialResults.pop();
            result = Math.min(curr[0], result);
            System.out.println(Arrays.toString(curr));
        }
        */

        System.out.println(bruteForce(converters, seeds));
    }

    public static long bruteForce(ArrayList<Converter> converters, ArrayList<long[]> seeds) {
        long result = Long.MAX_VALUE;
        for (long[] pair : seeds) {
            System.out.println("NEXT");
            for (long i = pair[0]; i <= pair[1]; i++) {
                long curr = i;
                for (Converter converter : converters) {
                    curr = converter.convert(curr);
                }
                if(curr < result) {
                    System.out.println(curr);
                    result = curr;
                }
            }
        }
        System.out.println(result + " result");
        return result;
    }

    static class Converter {

        public ArrayList<long[]> chart = new ArrayList<>();
        public ArrayList<long[]> range = new ArrayList<>();
        public ArrayList<long[]> domain = new ArrayList<>();
        public ArrayList<long[]> offsets = new ArrayList<>();

        public void add(String rangeStart, String domainStart, String length) {
            long domainEnd = Long.parseLong(domainStart) + Long.parseLong(length);
            chart.add(new long[]{Long.parseLong(domainStart),
                    domainEnd, Long.parseLong(rangeStart)});
            domain.add(new long[]{Long.parseLong(domainStart),
                    domainEnd - 1});
            range.add(new long[]{Long.parseLong(rangeStart),
                    Long.parseLong(rangeStart) + Long.parseLong(length) - 1});
            offsets.add(new long[]{Long.parseLong(length)});
        }

        public long convert(long n) {
            for (long[] arr : chart) {
                if (n >= arr[0] && n < arr[1]) {
                    return arr[2] + n - arr[0];
                }
            }
            return n;
        }

        public Stack<long[]> match(long[] pair) {
            Stack<long[]> result = new Stack<>();
            boolean found = false;
            int i = 0;
            while (i < domain.size() && !found) {
                if (inside(pair, domain.get(i))) {
                    result.push(bump(domain.get(i), range.get(i), pair));
                    found = true;
                } else if (partialAbove(pair, domain.get(i)) || partialBelow(pair, domain.get(i))) {
                    result.push(bump(domain.get(i), range.get(i), pair));
                    result.push(partialBump(domain.get(i), pair));
                    found = true;
                }
                i++;
            }
            if (!found) {
                result.push(pair);
            }
            return result;
        }

        public boolean inside(long[] pair1, long[] pair2) {
            return pair1[0] >= pair2[0] && pair1[1] <= pair2[1];
        }

        public boolean partialBelow(long[] pair1, long[] pair2) {
            return pair1[0] <= pair2[0] && pair1[1] >= pair2[1] && pair1[1] >= pair2[0];
        }

        public boolean partialAbove(long[] pair1, long[] pair2) {
            return pair1[0] >= pair2[0] && pair1[1] >= pair2[1] && pair1[0] <= pair2[1];
        }

        public long[] bump(long[] domain, long[] range, long[] input) {
            long change = input[0] - domain[0];
            long stretch = input[1] - input[0];
            return new long[]{Math.max(range[0], range[0] + change),
                    Math.min(range[1], range[0] + change + stretch)};
        }

        public long[] partialBump(long[] domain, long[] input) {
            if (input[0] < domain[0]) {
                return new long[]{input[0], domain[0] - 1};
            } else {
                return new long[]{domain[1] + 1, input[1]};
            }
        }
    }
}
//7971591