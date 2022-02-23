package problems;

/**
 * 917. 仅仅反转字母
 * 给你一个字符串 s ，根据下述规则反转字符串：
 * <p>
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 * <p>
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 * <p>
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 * <p>
 * <p>
 * 提示
 * <p>
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 * <p>
 *
 * link: https://leetcode-cn.com/problems/reverse-only-letters/
 */
public class Problem917 {
    public static void main(String[] args) {

    }

    public String reverseOnlyLetters(String s) {
        int left = 0, right = s.length() - 1;
        char[] cc = s.toCharArray();
        while (left < right) {
            while (left < right && !Character.isLetter(cc[left])) left++;
            while (left < right && !Character.isLetter(cc[right])) right--;
            char c = cc[left];
            cc[left++] = cc[right];
            cc[right--] = c;
        }
        return new String(cc);
    }
}
