package problems;

import java.util.Arrays;

/**
 * 942. 增减字符串匹配
 * 由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
 *
 * 如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I'
 * 如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D'
 * 给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "IDID"
 * 输出：[0,4,1,3,2]
 * 示例 2：
 *
 * 输入：s = "III"
 * 输出：[0,1,2,3]
 * 示例 3：
 *
 * 输入：s = "DDI"
 * 输出：[3,2,0,1]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 只包含字符 "I" 或 "D"
 *
 * link: https://leetcode.cn/problems/di-string-match/
 */
public class Problem942 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(diStringMatch("IDID")));
    }

    /**
     * 输入：s = "IDID"
     * 输出：[0,4,1,3,2]
     */
    public static int[] diStringMatch(String s) {
        int min = 0, right = s.length(), idx = 0;
        int[] ans = new int[right + 1];
        for (char c : s.toCharArray()) {
            if (c == 'I') ans[idx++] = min++;
            else ans[idx++] = right--;
        }
        ans[idx] = min;
        return ans;
    }
}
