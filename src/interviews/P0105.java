package interviews;

/**
 * 面试题 01.05. 一次编辑
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 *
 *
 *
 * 示例 1:
 *
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 *
 *
 * 示例 2:
 *
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 *
 * link: https://leetcode.cn/problems/one-away-lcci/
 */
public class P0105 {
    public static void main(String[] args) {
        System.out.println(oneEditAway("pale", "ple"));
        System.out.println(oneEditAway("pales", "pal"));
        System.out.println(oneEditAway("", "a"));
        System.out.println(oneEditAway("a", "ab"));
    }

    public static boolean oneEditAway(String first, String second) {
        int m = first.length(), n = second.length(), abs, diff = 0, idx = 0;
        if ((abs = Math.abs(m - n)) > 1) return false;
        if (m < n) return oneEditAway(second, first);
        char[] fs = first.toCharArray(), ss = second.toCharArray();
        if (abs == 0) {
            for (int i = 0; i < m; i++) {
                if (fs[i] != ss[i]) diff++;
            }
            return diff <= 1;
        } else { // 1
            for (char c : fs) {
                if (idx < n && c == ss[idx]) idx++;
            }
            return n == idx;
        }
    }
}
