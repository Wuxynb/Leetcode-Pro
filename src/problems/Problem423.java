package problems;

/**
 * 423. 从英文中重建数字
 * 给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "owoztneoer"
 * 输出："012"
 * 示例 2：
 *
 * 输入：s = "fviefuro"
 * 输出："45"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
 * s 保证是一个符合题目要求的字符串
 *
 * 链接：https://leetcode-cn.com/problems/reconstruct-original-digits-from-english/
 */
public class Problem423 {
    public static void main(String[] args) {
        System.out.println(originalDigits("fviefuro"));
    }

    public static String originalDigits(String s) {
        int[] hash = new int[26];
        for (char c : s.toCharArray()) hash[c - 'a']++;
        int[] cnts = new int[10];
        // zero one two three four five six seven eight nine
        cnts[0] = hash['z' - 'a'];
        cnts[2] = hash['w' - 'a'];
        cnts[4] = hash['u' - 'a'];
        cnts[6] = hash['x' - 'a'];
        cnts[8] = hash['g' - 'a'];

        cnts[3] = hash['h' - 'a'] - cnts[8];
        cnts[5] = hash['f' - 'a'] - cnts[4];
        cnts[7] = hash['s' - 'a'] - cnts[6];

        cnts[1] = hash['o' - 'a'] - cnts[0] - cnts[2] - cnts[4];
        cnts[9] = hash['i' - 'a'] - cnts[5] - cnts[6] - cnts[8];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnts.length; i++) {
            for (int j = 0; j < cnts[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

}
