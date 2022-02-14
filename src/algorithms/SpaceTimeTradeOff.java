package algorithms;

import java.util.HashMap;
import java.util.Map;

public class SpaceTimeTradeOff {

    public static void main(String[] args) {
        System.out.println(stringMatch("bbbb", "ababbbbaaabbbaaa"));
        System.out.println(stringMatch( "BARBER","JIM_SAW_ME_IN_A_BARBERSHOP"));
        System.out.println(stringMatch( "ab" ,"aaabb"));
    }

    /*
     * Horspool
     */
    public static int stringMatch(String p, String t) {
        if (null == p || null == t || p.length() > t.length()) return -1;
        if (p.length() == t.length() && p.length() == 0) return -1;

        Map<Character, Integer> shiftTable = new HashMap<>();
        char[] pattern = p.toCharArray();
        char[] text = t.toCharArray();

        for (int i = 0; i < pattern.length - 1; i++) {
            shiftTable.put(pattern[i], pattern.length - i - 1);
        }

        int i = pattern.length - 1;
        while (i < text.length) {
            int k = 0;
            while (k < pattern.length && pattern[pattern.length - 1 - k] == text[i - k]) {
                k++;
            }
            if (k == pattern.length) {
                return i - pattern.length + 1;
            } else {
                i += shiftTable.get(text[i]) != null ? shiftTable.get(text[i]) : pattern.length;
            }
        }

        return -1;
    }
}
