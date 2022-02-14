package problems;

import java.util.PriorityQueue;

/**
 * 1405. 最长快乐字符串
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 *
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 *
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 *
 *
 *
 * 示例 1：
 *
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 * 示例 2：
 *
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 * 示例 3：
 *
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 *
 *
 * 提示：
 *
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 *
 * 链接：https://leetcode-cn.com/problems/longest-happy-string/
 *
 * @author Wuxinyue
 * @version 1.0
 * @date 2022/2/7 16:01
 */
public class Problem1405 {
    public static void main(String[] args) {
        System.out.println(longestDiverseString(1, 1, 7));
        System.out.println(longestDiverseString(2, 2, 1));
        System.out.println(longestDiverseString(7, 1, 0));
    }

    @SuppressWarnings("all")
    public static String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> (y[1] - x[1]));
        StringBuilder sb = new StringBuilder();
        if (a > 0) pq.offer(new int[]{'a', a});
        if (b > 0) pq.offer(new int[]{'b', b});
        if (c > 0) pq.offer(new int[]{'c', c});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int n = sb.length();
            if (n >= 2 && sb.charAt(n - 1) == curr[0] && sb.charAt(n - 2) == curr[0]) {
                if (pq.isEmpty()) break;
                int[] next = pq.poll();
                sb.append((char) next[0]);
                if (--next[1] != 0) pq.offer(next);
                pq.offer(curr);
            } else {
                sb.append((char) curr[0]);
                if (--curr[1] != 0) pq.offer(curr);
            }
        }
        return sb.toString();
    }
}
