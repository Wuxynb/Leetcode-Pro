package problems;

import java.util.*;

/**
 * 140. 单词拆分 II
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * <p>
 * 说明：
 * <p>
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * 示例 2：
 * <p>
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-break-ii/
 */
public class Problem140 {
    public static void main(String[] args) {
        System.out.println(wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
//        System.out.println(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
//        System.out.println(wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        List<String> ans = new ArrayList<>();
        dfs(s, ans, 0, new LinkedList<>(), set);
        return ans;
    }

    public static void dfs(String s, List<String> ans, int idx, Deque<String> path, Set<String> set) {
        if (idx == s.length()) {
            ans.add(String.join(" ", path));
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            String str = s.substring(idx, i + 1);
            if (set.contains(str)) {
                path.add(str);
                dfs(s, ans, i + 1, path, set);
                path.removeLast();
            }
        }
    }

    /*
    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Map<Integer, List<String>> cache = new HashMap<>();
        return dfs(s, 0, set, cache);
    }

    public static List<String> dfs(String s, int idx, Set<String> set, Map<Integer, List<String>> cache) {
        if (cache.containsKey(idx)) return cache.get(idx);
        if (idx >= s.length()) {
            return Collections.singletonList("");
        }
        List<String> ans = new ArrayList<>();
        for (int i = idx + 1; i <= s.length(); i++) {
            String s1 = s.substring(idx, i);
            if (set.contains(s1)) {
                List<String> last = dfs(s, i, set, cache);
                for (String str : last) {
                    ans.add(!str.equals("") ? s1 + " " + str : s1);
                }
            }
        }
        cache.put(idx, ans);
        return ans;
    }
    */
}
