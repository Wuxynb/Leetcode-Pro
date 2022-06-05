package problems;

/**
 * 829. 连续整数求和
 * 给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: n = 5
 * 输出: 2
 * 解释: 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
 * 示例 2:
 *
 * 输入: n = 9
 * 输出: 3
 * 解释: 9 = 4 + 5 = 2 + 3 + 4
 * 示例 3:
 *
 * 输入: n = 15
 * 输出: 4
 * 解释: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *
 *
 * 提示:
 *
 * 1 <= n <= 10^9
 *
 * link: https://leetcode.cn/problems/consecutive-numbers-sum/
 */
public class Problem829 {
    public static void main(String[] args) {
//        System.out.println(consecutiveNumbersSum(5));
//        System.out.println(consecutiveNumbersSum(9));
        System.out.println(consecutiveNumbersSum(15));
    }

    // https://leetcode.cn/problems/consecutive-numbers-sum/solution/by-deathpure-2-mcke/
    // 将 n 表示成 k 个连续的正整数之和。
    // 设第一个数为x，连续的k个数之和为x+(x+1)+ ... + (x+k-1) = k * x + (1+2+...+k-1) = k * x + k(k-1)/2。
    // 能表示的条件为n=k * x + k(k-1)/2 (x≥1,k≥1), 即 (n−sum) mod k = 0。
    public static int consecutiveNumbersSum(int n) {
        int sum = 0, ans = 0;
        for (int i = 1; sum < n; i++) { // 枚举的个数k
            if ((n - sum) % i == 0) {
                ans++;
//                System.out.println("x: " + ((n - sum) / i));
            }
            sum += i;
        }
        return ans;
    }

    public static int consecutiveNumbersSumW(int n) {
        int ans = 0, l = 1, r = 1, cur = 0;
        while (r <= n) {
            cur += r++;
            while (cur > n) {
                cur -= l++;
            }
            if (cur == n) ans++;
        }
        return ans;
    }
}
