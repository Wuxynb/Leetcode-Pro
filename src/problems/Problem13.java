package problems;

import java.util.HashMap;
import java.util.Map;

/**
 * 13. 罗马数字转整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 *
 *
 * 示例 1:
 *
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 *
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 *
 * 输入: "IX"
 * 输出: 9
 *
 * 链接：https://leetcode-cn.com/problems/roman-to-integer/
 */
public class Problem13 {

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));
    }

    public static int romanToInt_(String s) {
        String[] roman = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] value = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < roman.length; i++) {
            map.put(roman[i], i);
        }

        int i = 0, ans = 0;
        while (i < s.length()) {
            if (s.charAt(i) == 'C') {
                if (i + 1 < s.length() && (s.charAt(i + 1) == 'M' || s.charAt(i + 1) == 'D')) {
                    ans += value[map.get(s.charAt(i++) + "" + s.charAt(i++))];
                } else ans += value[map.get(s.charAt(i++) + "")];
            } else if (s.charAt(i) == 'X') {
                if (i + 1 < s.length() && (s.charAt(i + 1) == 'C' || s.charAt(i + 1) == 'L')) {
                    ans += value[map.get(s.charAt(i++) + "" + s.charAt(i++))];
                } else ans += value[map.get(s.charAt(i++) + "")];
            } else if (s.charAt(i) == 'I') {
                if (i + 1 < s.length() && (s.charAt(i + 1) == 'X' || s.charAt(i + 1) == 'V')) {
                    ans += value[map.get(s.charAt(i++) + "" + s.charAt(i++))];
                } else ans += value[map.get(s.charAt(i++) + "")];
            } else {
                ans += value[map.get(s.charAt(i++) + "")];
            }
        }

        return ans;
    }

    public static int romanToInt(String s) {
        char[] roman = new char[]{'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int[] value = new int[]{1000, 500, 100, 50, 10, 5, 1};

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < roman.length; i++) {
            map.put(roman[i], value[i]);
        }

        int ans = 0; char last = 0;
        for (char c:s.toCharArray()) {
            if (last != 0 && map.get(last) < map.get(c)) ans = ans + map.get(c) - 2 * map.get(last);
            else ans += map.get(c);

            last = c;
        }

        return ans;
    }
}
