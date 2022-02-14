package problems;

import java.util.*;

/**
 * 187. 重复的DNA序列
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * <p>
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 示例 2：
 * <p>
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 * <p>
 * 链接：https://leetcode-cn.com/problems/repeated-dna-sequences/
 */
public class Problem187 {
    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(findRepeatedDnaSequences("AAAAAAAAAAAAA"));
        System.out.println(findRepeatedDnaSequences("AAAAAAAAAAA"));

        System.out.println(findRepeatedDnaSequencesPlus("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(findRepeatedDnaSequencesPlus("AAAAAAAAAAAAA"));
        System.out.println(findRepeatedDnaSequencesPlus("AAAAAAAAAAA"));
    }

    public static List<String> findRepeatedDnaSequencesPlus_(String s) {
        if (s.length() <= 10 || s.length() > 10000) return new ArrayList<>();
        Set<String> set = new HashSet<>(), res = new HashSet<>();
        char[] chs = s.toCharArray();
        for (int i = 0; i <= chs.length - 10; i++) {
            String str = String.valueOf(chs, i, 10);
            if (!set.add(str)) res.add(str);
        }
        return new ArrayList<>(res);
    }

    public static List<String> findRepeatedDnaSequencesPlus(String s) {
        Set<String> ans = new HashSet<>(), set = new HashSet<>();
        char[] chars = s.toCharArray();
        int left, right;
        for (int i = 0; i < 10; i++) {
            left = right = i;
            while (right <= s.length() - 10) {
                right += 10;
                String str = String.valueOf(chars, left, 10);
                if (!set.add(str)) ans.add(str);
                left = right;
            }
        }
        return new ArrayList<>(ans);
    }

    public static List<String> findRepeatedDnaSequences_(String s) {
        List<String> ans = new ArrayList<>();
        if (s.length() <= 10) return ans;
        Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }};
        int x = 0;
        for (int i = 0; i < 10 - 1; i++) x = (x << 2) | bin.get(s.charAt(i));

        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            x = ((x << 2) | bin.get(s.charAt(i + 10 - 1))) & ((1 << (10 * 2)) - 1);
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            if (cnt.get(x) == 2) ans.add(s.substring(i, i + 10));
        }
        return ans;
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int left, right;
        for (int i = 0; i < 10; i++) {
            left = right = i;
            while (right <= s.length() - 10) {
                right += 10;
                String str = s.substring(left, right);
                map.put(str, map.getOrDefault(str, 0) + 1);
                if (map.get(str) == 2) ans.add(str);
                left = right;
            }
        }
        return ans;
    }
}
