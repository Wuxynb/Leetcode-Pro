package algorithms;

public class BitManipulation {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        // 按位非也叫做补，一元运算符NOT“~”是对其运算数的每一位取反
        int a = 4;
        int a2 = ~a; // 相当于 a2 = - (a + 1)
        System.out.println(a2); // -5

        // 按位与运算符“&”，如果两个运算数都是1，则结果为1。其他情况下，结果均为零
        int b = 5;
        int b2 = b & 1; // 相当于： b2 = b % 2;
        System.out.println(b2); // 1

        // 按位或运算符“|”，任何一个运算数为1，则结果为1。
        int c = 4;  // 100
        int c2 = c | 1; // 100 | 001 = 101
        System.out.println(c2); // 5

        int c3 = 5;  // 101
        int c4 = c3 | 1; // 101 | 001 = 101
        System.out.println(c4); // 5

        // 按位异或运算符“^”，只有在两个比较的位不同时其结果是 1。否则，结果是零。
        int d = 4; // 100
        int d2 = d ^ 1; // 100 ^ 001 = 100
        System.out.println(d2); // 5

        int d3 = 5; // 101
        int d4 = d3 ^ 1; // 101 ^ 001 = 100
        System.out.println(d4); // 4
    }
}
