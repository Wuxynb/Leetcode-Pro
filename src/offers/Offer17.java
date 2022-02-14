package offers;

import java.util.Arrays;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 * 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 */
public class Offer17 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(printNumbers(3)));
        System.out.println(Arrays.toString(printNumbersNormal(3)));
    }

    static int count = 0;
    public static int[] printNumbers(int n) {
        int[] ans = new int[(int) Math.pow(10, n) - 1];
        for (int i = 1; i <= n; i++) { // 位数
            for (char c = '1'; c <= '9'; c++) { // 首位
                char[] nums = new char[i];
                nums[0] = c;
                dfs(ans, nums, 1, i);
            }
        }
        return ans;
    }

    public static void dfs(int[] ans, char[] nums, int idx, int digits) {
        if (idx == digits) {
            ans[count++] = Integer.parseInt(String.valueOf(nums));
            return;
        }
        for (char c = '0'; c <= '9'; c++) {
            nums[idx] = c;
            dfs(ans, nums, idx + 1, digits);
        }
    }

    public static int[] printNumbersNormal(int n) {
        int[] array = new int[(int) Math.pow(10, n) - 1];
        for (int i = 1; i <= array.length; i++) array[i - 1] = i;
        return array;
    }
}
