package offers;

/**
 * 剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 */
public class Offer5 {
    public static void main(String[] args) {

    }

    public static String replaceSpacePlus(String s) {
        char[] ss = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : ss) {
            if (c == ' ') sb.append("%20");
            else sb.append(c);
        }
        return sb.toString();
    }

    public static String replaceSpace(String s) {
        int n = s.length(), size = 0;
        char[] ss = s.toCharArray(), chars = new char[3 * n];
        for (char c : ss) {
            if (c == ' ') {
                chars[size++] = '%';
                chars[size++] = '2';
                chars[size++] = '0';
            } else chars[size++] = c;
        }
        // s.replace(" ", "%20");
        return new String(chars, 0, size);
    }
}
