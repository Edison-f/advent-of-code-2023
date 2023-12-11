import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day7.txt");
        Scanner in = new Scanner(fs);
        HashMap<String, Integer> bets = new HashMap<>();
        ArrayList<String> five = new ArrayList<>();
        ArrayList<String> four = new ArrayList<>();
        ArrayList<String> full = new ArrayList<>();
        ArrayList<String> three = new ArrayList<>();
        ArrayList<String> two = new ArrayList<>();
        ArrayList<String> one = new ArrayList<>();
        ArrayList<String> high = new ArrayList<>();
        long result = 0;
        while(in.hasNextLine()) {
            String[] split = in.nextLine().split(" ");
            String hand = split[0];
            String bet = split[1];
            bets.put(hand, Integer.parseInt(bet));
            HashMap<Character, Integer> map = new HashMap<>();
            int[] counter = new int[6];
            for (int i = 0; i < hand.length(); i++) {
                if(map.containsKey(hand.charAt(i))) {
                    map.put(hand.charAt(i), map.get(hand.charAt(i)) + 1);
                } else {
                    map.put(hand.charAt(i), 1);
                }
            }
            Object[] vals = map.values().toArray();
            for (Object o :
                    vals) {
                counter[(Integer) o]++;
            }
            if(counter[5] == 1) {
                five.add(hand);
            } else if(counter[4] == 1) {
                four.add(hand);
            } else if(counter[3] == 1 && counter[2] == 1) {
                full.add(hand);
            } else if(counter[3] == 1) {
                three.add(hand);
            } else if(counter[2] == 2) {
                two.add(hand);
            } else if(counter[2] == 1) {
                one.add(hand);
            } else {
                high.add(hand);
            }


        }

        ArrayList<ArrayList<String>> all = new ArrayList<>();

        all.add(five);
        all.add(four);
        all.add(full);
        all.add(three);
        all.add(two);
        all.add(one);
        all.add(high);

        int size = 0;
        for(ArrayList<String> arr : all) {
            arr.sort(new CamelComparator());
            size += arr.size();
        }

        for(ArrayList<String> arr : all) {
            for (int i = 0; i < arr.size(); i++) {
                String s = arr.get(i);
                result += (long) bets.get(s) * (long) size;
                size--;
            }
        }

        System.out.println(result);
    }

    static class CamelComparator implements Comparator<String> {

        public char[] sequence = new char[] {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};

        @Override
        public int compare(String s1, String s2) {
            for(int i = 0; i < s1.length(); i++) {
                boolean found = false;
                for (int j = 0; j < sequence.length && !found; j++) {
                    char c = sequence[j];
                    if (s1.charAt(i) == c && s2.charAt(i) == c) {
                        found = true;
                    } else if(s1.charAt(i) == c) {
                        return -1;
                    } else if(s2.charAt(i) == c) {
                        return 1;
                    }
                }
            }
            return 0;
        }
    }
}
