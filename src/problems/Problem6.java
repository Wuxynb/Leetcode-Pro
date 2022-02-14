package problems;

import java.util.LinkedList;
import java.util.List;

/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 *
 * 示例 1：
 *
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem6 {

    /*
     length = 14,

      P A Y P A L I S H I R I N G

      // 3, 3     3 + (3 - 3)
         4, 5     4 + (4 - 3)
         5, 7     5 + (5 - 3)
         6, 9     6 + (6 - 3)
         7, 11    7 + (7 - 3)

      P   A   H   N      // 4, 4
      A P L S I I G      // 2, 2,
      Y   I   R          // 4, 4

      P A Y P A L I S H I R I N G

      P     I    N       // 5
      A   L S  I G       // 3, 1, 3, 1
      Y A   H R          // 1, 3, 1,
      P     I            // 5

      P A Y P A L I S H I R I N G

      P       H            // 7
      A     S I            // 5, 1,
      Y   I   R            // 3, 3
      P L     I G          // 1, 5, 1
      A       N            // 7

      P A Y P A L I S H I R I N G

      P        R           // 10
      A      I I           // 8, 2, 8, 2
      Y     H  N           // 6, 4, 6, 4
      P   S    G           // 4, 6, 4, 6
      A I                  // 2, 8, 8, 2
      L                    // 10

      P A Y P A L I S H I R I N G

      P           N        // 11
      A         I G        // 9, 1, 9, 1
      Y       R            // 7, 3, 7, 3
      P     I              // 5, 5,
      A   H                // 3, 7, 3, 7
      L S                  // 1, 9, 1, 9
      I                    // 11

      P A Y P A L I S H I R I N G

      P             x       // 13
      A           G x       // 11, 1, 11, 1
      Y         N   x       // 9, 3, 9, 3
      P       I     x       // 7, 5, 7, 5
      A     R       x       // 5, 7, 5, 7
      L   I         x       // 3, 9, 3, 9
      I H           x       // 1, 11, 1, 11
      S             x       // 13

     */
    public static String convert(String s, int numRows) {
        if (s.length() == 1 || numRows == 1) {
            return s;
        }

        String str = "";
        int skip1 = 0;
        int skip2 = 0;
        for (int i = 0; i < numRows; i++) {
            skip1 = i != numRows - 1 ? 2 * (numRows - i) - 2 : 2 * numRows - 2;
            System.out.println("i = " + i + ", skip1: " + skip1);
            if (i == 0 || i == numRows - 1) {
                int j = i;
                while (j <= s.length() - 1) {
                    System.out.print("i + " + i + ", j = " + j);
                    str += s.charAt(j) + "";
                    j += skip1;

                    System.out.println(", next = " + j);
                }
            } else {
                skip2 = 2 * numRows - 2 - skip1;
                System.out.println("skip2: " + skip2);
                int k = 0, j = i;
                while (j <= s.length() - 1) {
                    k++;
                    System.out.print("i = " + i + ", j = " + j);
                    str += s.charAt(j) + "";

                    if (k % 2 == 1) {
                        j += skip1;
                    } else {
                        j += skip2;
                    }
                    System.out.println(", next = " + j);
                }
            }
        }

        return str;
    }


    /*

    0     6       12
    1   5 7    11 13
    2 4   8 10    14
    3     9

    numRows = 4
    row = i % (2 * n - 2)

    if row >= numRows:
        row = (2 * n - 2) - row

     */
    public static String convert_(String s, int numRows) {
        if (s.length() == 1 || numRows == 1) return s;
        StringBuilder[] strings = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) strings[i] = new StringBuilder();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int row = i % (2 * numRows - 2);
            row = row < numRows ? row : 2 * (numRows - 1) - row;
            strings[row].append(s.charAt(i));
        }
        for (StringBuilder string:strings) str.append(string.toString());

        return str.toString();
    }

    public static String convertPlus(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder sb = new StringBuilder();
        int n = s.length(), cycleLen = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                sb.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    sb.append(s.charAt(j + cycleLen - i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String convertStr = convert_("PAYPALISHIRING", 3);
        System.out.println(convertStr.equals("PAHNAPLSIIGYIR"));
        System.out.println(convertStr);
    }
}
