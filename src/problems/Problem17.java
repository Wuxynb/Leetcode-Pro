package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 *
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class Problem17 {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");

        backtrack(digits, list, map, new StringBuffer(), 0);

        return list;
    }

    public static void backtrack(String digits, List<String> list, Map<String, String> map, StringBuffer sb, int t) {
        if (t == digits.length()) list.add(sb.toString());
        else {
            String string = map.get(digits.charAt(t) + "");
            for (int i = 0; i < string.length(); i++) {
                sb.append(string.charAt(i));
                backtrack(digits, list, map, sb, t + 1);
                sb.deleteCharAt(t); // remove last char, back to the previous live node
            }
        }
    }
}
