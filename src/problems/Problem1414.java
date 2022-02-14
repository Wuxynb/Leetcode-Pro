package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 1414. 和为 K 的最少斐波那契数字数目
 * 给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
 *
 * 斐波那契数字定义为：
 *
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 ， 其中 n > 2 。
 * 数据保证对于给定的 k ，一定能找到可行解。
 *
 *
 *
 * 示例 1：
 *
 * 输入：k = 7
 * 输出：2
 * 解释：斐波那契数字为：1，1，2，3，5，8，13，……
 * 对于 k = 7 ，我们可以得到 2 + 5 = 7 。
 * 示例 2：
 *
 * 输入：k = 10
 * 输出：2
 * 解释：对于 k = 10 ，我们可以得到 2 + 8 = 10 。
 * 示例 3：
 *
 * 输入：k = 19
 * 输出：3
 * 解释：对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。
 *
 *
 * 提示：
 *
 * 1 <= k <= 10^9
 *
 * link: https://leetcode-cn.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
 *
 * @author Wuxinyue
 * @version 1.0
 * @date 2022/2/14 16:41
 */
public class Problem1414 {
    public static void main(String[] args) {
        System.out.println(findMinFibonacciNumbers(7));
        System.out.println(findMinFibonacciNumbers(10));
        System.out.println(findMinFibonacciNumbers(19));
        System.out.println(findMinFibonacciNumbers(7346634));

        System.out.println(findMinFibonacciNumbersPlus(7));
        System.out.println(findMinFibonacciNumbersPlus(10));
        System.out.println(findMinFibonacciNumbersPlus(19));
        System.out.println(findMinFibonacciNumbersPlus(7346634));
    }

    public static int findMinFibonacciNumbers(int k) {
        List<Integer> fib = new ArrayList<>();
        fib.add(1);
        int a = 1, b = 1, c, ans = 0;
        while (a + b <= k) {
            fib.add((c = a + b));
            a = b;
            b = c;
        }
        for (int i = fib.size() - 1; i >= 0 && k > 0; i--) {
            int num = fib.get(i);
            if (k >= num) {
                k -= num;
                ans++;
            }
        }
        return ans;
    }

    public static int findMinFibonacciNumbersPlus(int k) {
        int a = 1, b = 1, c, ans = 0;
        while (b <= k) {
            c = a + b;
            a = b;
            b = c;
        }
        while (k != 0) {
            if (k >= b) {
                k -= b;
                ans++;
            }
            c = b - a;
            b = a;
            a = c;
        }
        return ans;
    }
}
