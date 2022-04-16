package problems;

/**
 * 479. 最大回文数乘积
 * 给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入：n = 2
 * 输出：987
 * 解释：99 x 91 = 9009, 9009 % 1337 = 987
 * 示例 2:
 * <p>
 * 输入： n = 1
 * 输出： 9
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= n <= 8
 * 通过次数15,495提交次数25,647
 * <p>
 * link: https://leetcode-cn.com/problems/largest-palindrome-product/
 */
public class Problem479 {
    public static void main(String[] args) {
        System.out.println(largestPalindrome(1));
        System.out.println(largestPalindrome(2));
        System.out.println(largestPalindrome(3));
        System.out.println(largestPalindrome(4));
        System.out.println(largestPalindrome(5));
        System.out.println(largestPalindrome(6));
        System.out.println(largestPalindrome(7));
        System.out.println(largestPalindrome(8));
    }

    /**
     * 对于数位为n 的两个数而言，其乘积的位数要么是2∗n，要么是 2∗n−1。
     *
     * 当数位n>1 时，我们总能在数位为2∗n 中找到答案。
     *
     * 利用回文串的特性，我们只需枚举回文串的前半部分即可（后半部分唯一确定），我们只要在枚举前半部分时按照「从大到小」进行，即可确保找到的第一个合
     * 法值为最大数，对于一个数位为 nn 的最大数为 10^n −1。
     *
     * 具体的，当枚举到回文串的前半部分 i 时，我们利用回文串特性构造出具实际的回文数值nums，随后检查nums 能否分解成数位为 n 的数
     * 对 (a,b)，利用乘法具有交换律，我们只需要枚举数对中的较大数即可。
     */
    public static int largestPalindrome(int n) { // 90 09
        if (n == 1) return 9;
        int max = (int) Math.pow(10, n) - 1;
        for (int i = max; i >= 0; i--) {
            long num = i, t = i; // 回文的前半部分
            while (t != 0) { // 利用前半部分拼出后半部分
                num = num * 10 + (t % 10);
                t /= 10;
            }
            for (long j = max; j * j >= num; j--) {
                if (num % j == 0) return (int) (num % 1337);
            }
        }
        return -1;
    }
}
