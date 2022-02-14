package problems;

/**
 * 122. 买卖股票的最佳时机 II
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: prices = [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: prices = [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class Problem122 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfitDpOpti(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfitDpOptimize(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public static int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1]; // 只要当天价格与前一天价格的差价大于零就卖出去
            if (diff > 0) res += diff;
        }

        return res;
    }

    public static int maxProfitDp(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]); // 上一次持有现金，上一次持股现在卖出
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); // 上一次持股，上一次持有现金这一次再买股
        }

        return dp[n - 1][0];
    }

    public static int maxProfitDpOpti(int[] prices) {
        int n = prices.length;
        int cash = 0, stock = -prices[0], preCash = cash, preStock = stock;
        for (int i = 1; i < n; i++) {
            cash = Math.max(preCash, preStock + prices[i]);
            stock = Math.max(preStock, cash - prices[i]);
            preCash = cash;
            preStock = stock;
        }

        return cash;
    }

    public static int maxProfitDpOptimize(int[] prices) {
        int preCash = 0, preStock = -prices[0];
        for (int price : prices) {
            preCash = Math.max(preCash, preStock + price);
            preStock = Math.max(preStock, preCash - price);
        }

        return preCash;
    }
}
