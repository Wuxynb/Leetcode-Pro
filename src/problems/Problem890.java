package problems;

import java.util.ArrayList;
import java.util.List;

public class Problem890 {
    public static void main(String[] args) {
        System.out.println(findAndReplacePattern(new String[]{"abc","deq","mee","aqq","dkd","ccc"}, "abb"));
    }

    /*
     * 输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
     * 输出：["mee","aqq"]
     * 解释：
     *   "mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。
     *   "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。
     *   因为 a 和 b 映射到同一个字母。
     */
    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (word.length() != pattern.length()) continue;
            int[] hash1 = new int[26], hash2 = new int[26];
            boolean flag = true;
            for (int i = 0; i < word.length() && flag; i++) {
                int c1 = word.charAt(i), c2 = pattern.charAt(i);
                if (hash1[c1 - 'a'] == 0) hash1[c1 - 'a'] = c2;
                if (hash2[c2 - 'a'] == 0) hash2[c2 - 'a'] = c1;
                if (hash1[c1 - 'a'] != c2 || hash2[c2 - 'a'] != c1) flag = false;
            }
            if (flag) ans.add(word);
        }
        return ans;
    }
}
