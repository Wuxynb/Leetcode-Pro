package problems;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class Problem14 {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));

    }

    public static String longestCommonPrefix(String[] strs) {
        int c = 0, n = strs[0].length();

        for (int i = 1; i < strs.length; i++) {
            n = Math.min(strs[i].length(), n);
        }
        while (c < n) {
            char cha = strs[0].charAt(c);
            boolean f = true;
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(c) != cha) {
                    f = false;
                    break;
                }
            }
            if (f) c++;
            else break;
        }

        return strs[0].substring(0, c);
    }
}
