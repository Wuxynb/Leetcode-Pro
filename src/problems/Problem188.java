package problems;

/**
 * 188. 买卖股票的最佳时机 IV
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 *
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class Problem188 {
    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{2, 4, 1}));
        System.out.println(maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));

        System.out.println(maxProfitDp(2, new int[]{2, 4, 1}));
        System.out.println(maxProfitDp(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

    /*
        dp[i][0] // 不做任何操作
        dp[i][1] // 第一次买入
        dp[i][2] // 第一次卖出
        dp[i][3] // 第二次买入
        dp[i][4] // 第二次卖出
        ......
        ......
        dp[i][2 * k - 1] // 第k次买入
        dp[i][2 * k] // 第k次卖出
     */
    public static int maxProfit(int k, int[] prices) {
        if (prices.length < 2) return 0;
        int[][] dp = new int[prices.length][2 * k + 1];
        dp[0][1] = -prices[0]; // dp[0][0] = 0; dp[0][2] = 0;
        for (int i = 2; i < 2 * k + 1; i++) {
            if (i % 2 == 1) dp[0][i] = Integer.MIN_VALUE;
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= 2 * k; j = j + 2) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]); // 买股
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] + prices[i]); // 卖股
            }
        }

        int ans = 0;
        for (int i :dp[prices.length - 1]) ans = Math.max(ans, i);
//        return Arrays.stream(dp[prices.length - 1]).max().getAsInt();
        return ans;
    }

    /*
      dp[0] // 不做任何操作
      dp[1] // 第一次买入
      dp[2] // 第一次卖出
      dp[3] // 第二次买入
      dp[4] // 第二次卖出
      ......
      ......
      dp[2 * k - 1] // 第k次买入
      dp[2 * k] // 第k次卖出
   */
    public static int maxProfitDp(int k, int[] prices) {
        if (k == 0 || prices.length < 2) return 0;
        int[] dp = new int[2 * k + 1];
        dp[0] = 0;
        dp[1] = -prices[0];
        for (int i = 2; i < 2 * k + 1; i++) dp[i] = Integer.MIN_VALUE;

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= 2 * k; j = j + 2) {
                dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]); // 买股
                dp[j + 1] = Math.max(dp[j + 1], dp[j] + prices[i]); // 卖股
            }
        }

        return dp[2 * k];
    }
}
