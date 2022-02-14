package problems;

/**
 * 123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1:
 *
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2：
 *
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 示例 4：
 *
 * 输入：prices = [1]
 * 输出：0
 *
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class Problem123 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(maxProfitDp(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }

    /*
        五个状态：
            · 未进行过任何操作；
            · 只进行过一次买操作；
            · 进行了一次买操作和一次卖操作，即完成了一笔交易；
            · 在完成了一笔交易的前提下，进行了第二次买操作；
            · 完成了全部两笔交易。
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int cash1 = 0, stock1 = -prices[0]; // 第一次不买持有的现金数，第一次买股票为股票的负价格
        int cash2 = 0, stock2 = -prices[0]; // 第二次不买持有的现金数，第二次买股票为股票的负价格
        for (int i = 1; i < n; i++) {
            stock1 = Math.max(stock1, -prices[i]); // 前一天的股票，今天的股票价格
            cash1 = Math.max(cash1, stock1 + prices[i]); // （前一天的现金，将前一天的股票在今天卖出去）
            stock2 = Math.max(stock2, cash1 - prices[i]); // （前一天的股票，用前一次交易的钱买今天的股票后的钱）
            cash2 = Math.max(cash2, stock2 + prices[i]);
        }

        return cash2;
    }

    /*
        到最后交易结束时，一共会有5种状态：
            dp0：一直不买
            dp1：到最后也只买入了一笔
            dp2：到最后买入一笔，卖出一笔
            dp3：到最后买入两笔，卖出一笔
            dp4：到最后买入两笔，卖出两笔

        初始化5种状态：
            dp0 = 0
            dp1 = - prices[0]
            因为第一天不可能会有dp2，dp3，dp4三种状态，因此将这三者置为负无穷(Java中置为int的下边界)
            dp2 = float("-inf")
            dp3 = float("-inf")
            dp4 = float("-inf")

        对5种状态进行状态转移。
            dp0 = 0
            #一直为0
            dp1 = max(dp1, dp0 - prices[i])
            #前一天也是dp1状态，或者前一天是dp0状态，今天买入一笔变成dp1状态
            dp2 = max(dp2, dp1 + prices[i])
            #前一天也是dp2状态，或者前一天是dp1状态，今天卖出一笔变成dp2状态
            dp3 = max(dp3, dp2 - prices[i])
            #前一天也是dp3状态，或者前一天是dp2状态，今天买入一笔变成dp3状态
            dp4 = max(dp4, dp3 + prices[i])
            #前一天也是dp4状态，或者前一天是dp3状态，今天卖出一笔变成dp4状态

           最后一定是手里没有股票赚的钱最多，但不一定交易次数越多赚得越多，应该要返回dp0，dp2，dp4的最大值；但是我代码的写法中其实允许了同一天买入
        卖出的，只不过因为一天的价格不变而不会对结果有影响，因此最大值是必然会转移到dp4的，所以只要返回dp4就可以了
     */
    public static int maxProfit_(int[] prices) {
        if(prices.length < 2) return 0;

        int dp0 = 0;                 // 一直不买
        int dp1 = -prices[0];       // 到最后也只买入了一笔
        int dp2 = Integer.MIN_VALUE; // 到最后买入一笔，卖出一笔
        int dp3 = Integer.MIN_VALUE; // 到最后买入两笔，卖出一笔
        int dp4 = Integer.MIN_VALUE; // 到最后买入两笔，卖出两笔

        for(int i = 1; i < prices.length; i++){
            dp1 = Math.max(dp1, dp0 - prices[i]);
            dp2 = Math.max(dp2, dp1 + prices[i]);
            dp3 = Math.max(dp3, dp2 - prices[i]);
            dp4 = Math.max(dp4, dp3 + prices[i]);
        }
        return dp4;
    }

    public static int maxProfitDp(int[] prices) {
        if (prices.length < 2) return 0;
        int[][] dp = new int[prices.length][5];
        dp[0][1] = -prices[0]; // 第一次买股
        dp[0][3] = -prices[0]; // 第二次买股
        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); // 买一支股
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]); // 买了一只股，然后卖出去一支股
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]); // 买两支股，并卖出去一支
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]); // 卖出去两支股
        }

        return Math.max(dp[prices.length - 1][2], dp[prices.length - 1][4]);
    }
}
