package problems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 *
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 */
public class Problem121 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));

        System.out.println(maxProfitDp(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfitDp(new int[]{7, 6, 4, 3, 1}));

        System.out.println(maxProfitDp2(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfitDp2(new int[]{7, 6, 4, 3, 1}));

        System.out.println(maxProfitStack(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfitStack(new int[]{7, 6, 4, 3, 1}));


        System.out.println(maxProfitMonotoneStack(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfitMonotoneStack(new int[]{7, 6, 4, 3, 1}));

        System.out.println(maxProfitMonotoneStackOpti(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfitMonotoneStackOpti(new int[]{7, 6, 4, 3, 1}));
    }

    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) minPrice = price;
            else maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }

    public static int maxProfitDp(int[] prices) {
        int[] dp = new int[prices.length];
        dp[0] = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        return dp[prices.length - 1];
    }

    /*
        dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
        dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数
     */
    public static int maxProfitDp2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2]; // i表示第几天，j表示持股状态（0-持有现金（不买），1-持有股票（买））
        dp[0][0] = 0; // 初始不买，现金为0
        dp[0][1] = -prices[0]; // 初始买，现金为股票价格的负数（看作是借钱买股）
        for (int i = 1; i < n; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]); // 昨天持有股票更贵还是今天的贵，选便宜的
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]); // 昨天持有现金数，昨天持有股票今天卖出
        }

        return dp[n - 1][0];
    }

    public static int maxProfitStack(int[] prices) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int price : prices) {
            if (!stack.isEmpty() && stack.peek() < price) {
                ans = Math.max(ans, price - stack.peek());
            } else stack.push(price);
        }

        return ans;
    }

    public static int maxProfitStackOpti(int[] prices) {
        int ans = 0, top = -1;
        int[] stack = new int[prices.length];
        for (int price : prices) {
            if (top != -1 && stack[top] < price) {
                ans = Math.max(ans, price - stack[top]);
            } else stack[++top] = price;
        }

        return ans;
    }

    public static int maxProfitMonotoneStack(int[] prices) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= prices.length; i++) {
            while (!stack.isEmpty() && (i == prices.length || stack.peek() >= prices[i])) {
                int top = stack.pop();
                if (stack.isEmpty()) continue;
                ans = Math.max(ans, top - stack.peekLast());
            }
            if (i < prices.length) stack.push(prices[i]);
        }

        return ans;
    }

    public static int maxProfitMonotoneStackOpti(int[] prices) {
        int ans = 0, top = -1;
        int[] stack = new int[prices.length];
        for (int i = 0; i <= prices.length; i++) {
            while (top != -1 && (i == prices.length || stack[top] > prices[i])) {
                if (top == 0) top--;
                else ans = Math.max(ans, stack[top--] - stack[0]);
            }
            if (i < prices.length) stack[++top] = prices[i];
        }

        return ans;
    }
}
