package problems;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 *
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 2 * 105
 * 字符串 s 由 ASCII 字符组成
 *
 * 链接：https://leetcode-cn.com/problems/valid-palindrome/
 */
public class Problem125 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
    }

    // 0 - Z : 48 - 90, a - z : 97 - 122
    public static boolean isPalindrome(String s) {
        char[] chars = s.toUpperCase().toCharArray();
        int i = 0, j = chars.length - 1;
        while (i <= j) {
            while (i <= j && !((chars[i] >= '0' && chars[i] <= '9') || (chars[i] >= 'A' && chars[i] <= 'Z'))) i++;
            while (i <= j && !((chars[j] >= '0' && chars[j] <= '9') || (chars[j] >= 'A' && chars[j] <= 'Z'))) j--;
            if (i <= j && chars[i++] != chars[j--]) return false;
        }

        return true;
    }
}
