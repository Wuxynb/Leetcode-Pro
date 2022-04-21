package problems;

import java.util.Arrays;

/**
 * 821. 字符的最短距离
 * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
 * <p>
 * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
 * <p>
 * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "loveleetcode", c = "e"
 * 输出：[3,2,1,0,1,0,0,1,2,2,1,0]
 * 解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
 * 距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
 * 距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
 * 对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
 * 距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
 * 示例 2：
 * <p>
 * 输入：s = "aaab", c = "b"
 * 输出：[3,2,1,0]
 * <p>
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s[i] 和 c 均为小写英文字母
 * 题目数据保证 c 在 s 中至少出现一次
 * <p>
 * link: https://leetcode-cn.com/problems/shortest-distance-to-a-character/
 */
public class Problem821 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(shortestToChar("loveleetcode", 'e')));
        System.out.println(Arrays.toString(shortestToChar("aaab", 'b')));
        System.out.println(Arrays.toString(shortestToChar("islxy", 'i')));
    }

    public static int[] shortestToChar(String s, char c) {
        char[] chars = s.toCharArray();
        int n = s.length();
        int[] ans = new int[n];
        Arrays.fill(ans, n + 1);
        for (int i = 0, j = -1; i < n; i++) {
            if (chars[i] == c) j = i;
            if (j != -1) ans[i] = i - j;
        }
        for (int i = n - 1, j = -1; i >= 0; i--) {
            if (chars[i] == c) j = i;
            if (j != -1) ans[i] = Math.min(ans[i], j - i);
        }
        return ans;
    }

    /**
     * 输入：s = "loveleetcode", c = "e"
     * <p>
     * 输出：[3,2,1,0,1,0,0,1,2,2,1,0]
     */
    public static int[] shortestToCharComplex(String s, char c) {
        char[] chars = s.toCharArray();
        int n = s.length();
        int[] left = new int[n], right = new int[n], ans = new int[n];
        left[0] = chars[0] == c ? 0 : Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (chars[i] == c) left[i] = i;
            else left[i] = left[i - 1];
        }
        right[n - 1] = chars[n - 1] == c ? n - 1 : Integer.MAX_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            if (chars[i] == c) right[i] = i;
            else right[i] = right[i + 1];
            ans[i] = Math.min(Math.abs(left[i] - i), Math.abs(right[i] - i));
        }
        ans[n - 1] = Math.min(Math.abs(left[n - 1] - n + 1), Math.abs(right[n - 1] - n + 1));
        return ans;
    }
}
