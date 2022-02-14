package problems;

/**
 * 168. Excel表列名称
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 *
 * 例如：
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 *
 * 示例 1：
 *
 * 输入：columnNumber = 1
 * 输出："A"
 * 示例 2：
 *
 * 输入：columnNumber = 28
 * 输出："AB"
 * 示例 3：
 *
 * 输入：columnNumber = 701
 * 输出："ZY"
 * 示例 4：
 *
 * 输入：columnNumber = 2147483647
 * 输出："FXSHRXW"
 */
public class Problem168 {
    public static void main(String[] args) {
        System.out.println(convertToTitle(832));
    }

    public static String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) { // 26进制是 0~25，故要进行-1操作。
            sb.append((char) ((columnNumber - 1) % 26 + 'A'));
            columnNumber = (columnNumber - 1) / 26;
        }
        return sb.reverse().toString();
    }

    public static String convertToTitle_(int columnNumber) {
        if (columnNumber <= 26) return (char) (columnNumber + 'A' - 1) + "";
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 26) {
            boolean f;
            sb.append((char) (columnNumber % 26 == 0 ? 'Z' : columnNumber % 26 + 'A' - 1));
            f = columnNumber % 26 == 0;
            columnNumber /= 26;
            if (f) columnNumber--;
        }
        sb.append((char) (columnNumber + 'A' - 1));
        return sb.reverse().toString();
    }
}
