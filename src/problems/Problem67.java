package problems;

/**
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 * 链接：https://leetcode-cn.com/problems/add-binary/
 */
public class Problem67 {
    public static void main(String[] args) {
        System.out.println(addBinaryApi("1111", "1111"));
        System.out.println(addBinary("1111", "1111"));
    }

    public static String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        StringBuilder ans = new StringBuilder();
        while (i >= 0 || j >= 0) {
            carry += i >= 0 ? a.charAt(i--) - '0' : 0;
            carry += j >= 0 ? b.charAt(j--) - '0' : 0;
            ans.append(carry & 1);
            carry >>= 1;
        }
        if (carry == 1) ans.append(1);

        return ans.reverse().toString();
    }

    public static String addBinaryApi(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }

    public static String addBinary_(String a, String b) {
        StringBuilder ans = new StringBuilder();
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        int aLen = aa.length, bLen = bb.length;
        for (int i = 0; i < aLen / 2; i++) {
            char temp = aa[i];
            aa[i] = aa[aLen - i - 1];
            aa[aLen - i - 1] = temp;
        }
        for (int i = 0; i < bLen / 2; i++) {
            char temp = bb[i];
            bb[i] = bb[bLen - i - 1];
            bb[bLen - i - 1] = temp;
        }

        int carry = 0;
        for (int i = 0; i < Math.max(aLen, bLen); i++) {
            int t1 = i < aLen ? aa[i] - '0' : 0, t2 = i < bLen ? bb[i] - '0' : 0, sum = 0;
            sum = t1 + t2 + carry;
            ans.append(sum & 1);
            carry = sum >> 1;
        }
        if (carry == 1) ans.append("1");
        return ans.reverse().toString();
    }
}
