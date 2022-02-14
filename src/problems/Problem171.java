package problems;

/**
 * 171. Excel 表列序号
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
 *
 *
 *
 * 例如，
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 *
 *
 * 示例 1:
 *
 * 输入: columnTitle = "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: columnTitle = "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: columnTitle = "ZY"
 * 输出: 701
 * 示例 4:
 *
 * 输入: columnTitle = "FXSHRXW"
 * 输出: 2147483647
 *
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-number/
 */
public class Problem171 {
    public static void main(String[] args) {
        System.out.println(titleToNumber("FXSHRXW"));
    }

    public static int titleToNumber(String columnTitle) {
        char[] chars = columnTitle.toCharArray();
        int ans = 0;
        for (char aChar : chars) {
            ans = ans * 26 + (aChar - 'A' + 1);
        }
        return ans;
    }
}
