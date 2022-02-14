package offers;

public class Offer44 {
    public static void main(String[] args) {
        System.out.println(getKthNum(100));
    }

    /**
     *  1~9         1           9           9
     *  10~99       2           90          180
     *  100~999     3           900         2700
     *  ...         ..          ...         ...
     *  start-end   digit       9*start     9*start*digit
     */
    public static int findNthDigit(int n) {
        int digit = 1; // 位数
        long start = 1, count = 9; // 起始数字1,10,...
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }

    /**
     * 总结: 对于位数digit(用i表示), 当前字符数量一共为 i * (10 ^ i)
     * i为1时, 0123456789很好理解, 共 1 * (10 ^ 1) = 10 个
     * i为2时, 把每个数字补成两位数, 即00 01 02 ~ 99, 当前字符数量为 2 * (10 ^ 2) = 200个, 而k要加上 10 ^ 1
     * i为3时, 把每个数字补成三位数, 即000 001 002 003 ... 099 100 101 ... 999, 此时k再加上10 ^ 2
     * 依次类推
     * 当满足时, 每个数字字符宽度都为i, 那么返回第k//i个数 的 第 k%i 位即可
     */
    public static int getKthNum(long k) {
        for (int i = 1; ;i++) {
            if (i * Math.pow(10, i) > k) {
                return Long.toString(k / i).charAt((int) (k % i)) - '0';
            }
            k += Math.pow(10, i);
        }
    }
}
