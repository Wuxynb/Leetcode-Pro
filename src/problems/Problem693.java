package problems;

public class Problem693 {
    public static void main(String[] args) {
        System.out.println(hasAlternatingBits(5));
        System.out.println(hasAlternatingBits(7));
        System.out.println(hasAlternatingBits(11));
    }

    /**
     * 输入：n = 5
     * 输出：true
     * 解释：5 的二进制表示是：101
     **/
    public static boolean hasAlternatingBits(int n) {
        int a = -1, b;
        while (n != 0) {
            if (a == (b = n & 1)) return false;
            a = b;
            n >>= 1;
        }
        return true;
    }

    public static boolean hasAlternatingBitsByBit(int n) {
        int a = n ^ (n >> 1);
        return (a & (a + 1)) == 0;
    }
}
