package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 链接：https://leetcode-cn.com/problems/multiply-strings/
 */
public class problem43 {

    public static void main(String[] args) {
        System.out.println(multiply("112", "101"));
//        System.out.println(multiply("498828660196", "9"));
//        System.out.println(multiply("123456789", "987654321"));

    }

    // add
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        List<String> list1 = new ArrayList<>();
        StringBuilder ans = new StringBuilder();
        for (int i = num2.length() - 1; i >= 0; i--) {
            StringBuilder str = new StringBuilder();
            int carry = 0;
            for (int j = num1.length() - 1; j >= 0; j--) {
                int res = (num1.charAt(j) - '0') * (num2.charAt(i) - '0');
                str.append(((res + carry) % 10));
                carry = (res + carry) / 10;
            }
            if (carry != 0) str.append(carry);
            for (int k = 0; k < num2.length() - 1 - i; k++) str.insert(0, "0");
            list1.add(str.toString());
        }
        System.out.println(list1);

        int carry = 0;
        for (int i = 0; i < list1.get(list1.size() - 1).length(); i++) {
            int sum = 0;
            for (String s : list1) {
                if (s.length() > i)
                    sum += s.charAt(i) - '0';
            }
            sum += carry;
            ans.insert(0, (sum % 10));
            carry = sum / 10;
        }
        if (carry != 0) ans.insert(0, carry);

        return ans.toString();
    }

    /*
            1  2  3  4
               5  6  7
        ------------------
                  2  8
               2  4
            2  0
     */
    public static String multiplyPlus(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int m = num1.length(), n = num2.length();
        int[] arrays = new int[m + n];
        for (int i = m - 1; i >=0 ; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                arrays[i + j + 1] += x * y;
            }
        }

        for (int i = m + n - 1; i > 0; i--) {
            arrays[i - 1] += arrays[i] / 10;
            arrays[i] = arrays[i] % 10;
        }
        int index = arrays[0] == 0 ? 1 : 0;
        StringBuilder sb = new StringBuilder();
        while (index < m + n) {
            sb.append(arrays[index]);
            index++;
        }

        return sb.toString();
    }
}
