package offers;

/**
 * 剑指 Offer 65. 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 *
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *
 * 链接：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
 */
public class Offer65 {
    public static void main(String[] args) {
        System.out.println(add(4, 7));
        System.out.println(Math.addExact(4, 7));
    }

    /*
        ^ 异或 ----相当于无进位的求和
        & 与 ----相当于求每位的进位数
     */
    public static int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }
}
