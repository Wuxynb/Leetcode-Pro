package offer2s;

import java.util.*;

/**
 * 剑指 Offer II 114. 外星文字典
 * 现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。
 *
 * 给定一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
 *
 * 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。
 *
 * 字符串 s 字典顺序小于 字符串 t 有两种情况：
 *
 * 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
 * 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
 *
 *
 * 示例 1：
 *
 * 输入：words = ["wrt","wrf","er","ett","rftt"]
 * 输出："wertf"
 * 示例 2：
 *
 * 输入：words = ["z","x"]
 * 输出："zx"
 * 示例 3：
 *
 * 输入：words = ["z","x","z"]
 * 输出：""
 * 解释：不存在合法字母顺序，因此返回 "" 。
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] 仅由小写英文字母组成
 *
 * link: https://leetcode.cn/problems/Jf1JuT/
 */
public class OfferII114 {
    public static void main(String[] args) {
        System.out.println(alienOrder(new String[]{"wrt","wrf","er","ett","rftt"}));
        System.out.println(alienOrder(new String[]{"z","x"}));
        System.out.println(alienOrder(new String[]{"z","x","z"}));
        System.out.println(alienOrder(new String[]{"ac","ab","zc","zb"}));
    }

    public static String alienOrder(String[] words) {
        List<Integer>[] adjacency = new ArrayList[26];
        boolean[] visited = new boolean[26];
        int cnt = 0;
        int[] indegrees = new int[26];
        Arrays.fill(indegrees, -1);
        for (String word : words) {
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (visited[idx]) continue;
                adjacency[idx] = new ArrayList<>();
                indegrees[idx] = 0;
                visited[idx] = true;
                cnt++;
            }
        }
        for (int i = 1; i < words.length; i++) {
            String word1 = words[i - 1], word2 = words[i];
            int index = 0, len1 = word1.length(), len2 = word2.length(), len = Math.min(len1, len2);
            while (index < len) {
                char c1 = word1.charAt(index), c2 = word2.charAt(index);
                if (c1 != c2) {
                    adjacency[c1 - 'a'].add(c2 - 'a');
                    indegrees[c2 - 'a']++;
                    break;
                }
                index++;
            }
            if (index == len && len1 > len2) return "";
        }
        int front = 0, rear = 0;
        int[] que = new int[26];
        for (int i = 0; i < 26; i++) if (indegrees[i] == 0) que[rear++] = i;
        StringBuilder sb = new StringBuilder();
        while (rear - front > 0) {
            int curr = que[front++];
            sb.append((char) (curr + 'a'));
            for (int next : adjacency[curr]) {
                if (--indegrees[next] == 0) que[rear++] = next;
            }
        }

        return cnt == sb.length() ? sb.toString() : "";
    }
}
