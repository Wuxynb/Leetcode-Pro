package offers;

/**
 * 剑指 Offer 63. 股票的最大利润
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 链接：https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/
 */
public class Offer63 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfitDp(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public static int maxProfit(int[] prices) {
        int profit = 0, price = Integer.MAX_VALUE;
        for (int j : prices) {
            price = Math.min(price, j);
            profit = Math.max(profit, j - price);
        }
        return profit;
    }

    public static int maxProfitDp(int[] prices) {
        int[][] dp = new int[prices.length + 1][2];
        dp[0][0] = 0; // 卖股
        dp[0][1] = Integer.MIN_VALUE; // 买股
        for (int i = 1; i <= prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], prices[i - 1] + dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i - 1]);
        }
        return dp[prices.length][0];
    }

    public static int maxProfitMulti(int[] prices) {
        int preCash = 0, preStock = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            preCash = Math.max(preCash, prices[i] + preStock);
            preStock = Math.max(preStock, preStock - prices[i]);
        }
        return preCash;
    }

    public static int maxProfitTwo(int[] prices) {
        int[][] dp = new int[prices.length][5];
        dp[0][0] = 0; // 什么都不做
        dp[0][1] = -prices[0]; // 第一次买股
        dp[0][3] = -prices[0]; // 第二次卖股
        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return Math.max(dp[prices.length - 1][2], dp[prices.length - 1][4]);
    }

    public static int maxProfitTwoOpti(int[] prices) {
        int dp0 = 0;
        int dp1 = -prices[0];
        int dp2 = Integer.MIN_VALUE;
        int dp3 = -prices[0];
        int dp4 = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            dp1 = Math.max(dp1, dp0 - prices[i]);
            dp2 = Math.max(dp2, dp1 + prices[i]);
            dp3 = Math.max(dp3, dp2 - prices[i]);
            dp4 = Math.max(dp4, dp3 + prices[i]);
        }
        return dp4;
    }
}
