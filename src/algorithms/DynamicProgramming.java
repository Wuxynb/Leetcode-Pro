package algorithms;

import java.util.Arrays;

public class DynamicProgramming {
    public static void main(String[] args) {
        /*long s1 = System.currentTimeMillis();
        System.out.println(fibonacci(40));
        long s2 = System.currentTimeMillis();
        System.out.println((s2 - s1) + "ms");

        System.out.println(fibonacciDp(40));
        long s3 = System.currentTimeMillis();
        System.out.println((s3 - s2) + "ms");

        System.out.println(fibonacciDpPlus(40));
        System.out.println((System.currentTimeMillis() - s3) + "ms");*/

//        System.out.println(coinRow(new int[]{5, 1, 2, 10, 6, 2}));
        System.out.println(changeMarking(new int[]{1, 5, 10, 20, 50, 100}, 637));
        System.out.println(changeMarking2(new int[]{1, 5, 10, 20, 50, 100}, 637));
//        System.out.println(change_dc(new int[]{1, 5, 10, 20, 50, 100}, 637, 5));

//        System.out.println(robotCoinCollection(new int[][]{
//                {0, 0, 0, 0, 1, 0},
//                {0, 1, 0, 1, 0, 0},
//                {0, 0, 0, 1, 0, 1},
//                {0, 0, 1, 0, 0, 1},
//                {1, 0, 0, 0, 1, 0},
//        }));
//        System.out.println(knapsack(new int[]{2, 1, 3, 2}, new int[]{12, 10, 20, 15}, 5));
//        System.out.println(knapsackPlus(new int[]{2, 1, 3, 2}, new int[]{12, 10, 20, 15}, 5));
        System.out.println(knapsackBt(new int[]{2, 1, 3, 2}, new int[]{12, 10, 20, 15}, 5));
        System.out.println(completeKnapsackBT(new int[]{2, 1, 3, 2}, new int[]{12, 10, 20, 15}, 5));
//        System.out.println(completeKnapsack(new int[]{2, 2, 6, 5, 4}, new int[]{6, 3, 5, 4, 6}, 10));
//        System.out.println(completeKnapsackBT(new int[]{2, 2, 6, 5, 4}, new int[]{6, 3, 5, 4, 6}, 10));
//        System.out.println(completeKnapsackNoRecursion(new int[]{2, 2, 6, 5, 4}, new int[]{6, 3, 5, 4, 6}, 10));
//        System.out.println(completeKnapsackNoRecursionPlus(new int[]{2, 2, 6, 5, 4}, new int[]{6, 3, 5, 4, 6}, 10));
    }

    /*
        use recursion method to implement fibonacci
        top - down
        Comment: the same sub-problems are computed repeatedly.
     */
    public static int fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("n isn't a negative number!");
        if (n <= 1) return n;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /*
        use dynamic programming(dp) method to implement fibonacci
        bottom - up
        Comment: use a extra array F[n + 1] to save each value, get result as long as by a for loop.
     */
    public static int fibonacciDp(int n) {
        if (n < 0) throw new IllegalArgumentException("n can't be a negative number!");
        int[] f = new int[n + 1];

        for (int i = 0; i < f.length; i++) {
            if (i <= 1) f[i] = i;
            else {
                f[i] = f[i - 1] + f[i - 2];
            }
        }

        return f[n];
    }

    /*
        use optimized dynamic programming(dp) method to implement fibonacci
        bottom - up
        Comment: replaced a extra array by using two variables.
     */
    public static int fibonacciDpPlus(int n) {
        if (n < 0) throw new IllegalArgumentException("n can't be a negative number!");
        int u = 0, v = 1;
        if (n == 0) return 0;

        for (int i = 2; i <= n; i++) {
            v = u + v;
            u = v - u;
        }

        return v;
    }

    public static int coinRow(int[] coins) {
        if (coins.length == 0) return 0;
        int[] F = new int[coins.length + 1];
        F[0] = 0;
        F[1] = coins[0];

        for (int i = 2; i < F.length; i++) {
            F[i] = Math.max(coins[i - 1] + F[i - 2], F[i - 1]);
        }

        return F[F.length - 1];
    }

    /*
        F(n) = min{F(n - vj) + 1}  0<=j<=m-1
        F(0) = 0
     */
    public static int changeMarking(int[] coins, int n) {
        int[] F = new int[n + 1];
        F[0] = 0;
        for (int i = 1; i < F.length; i++) {
            int temp = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length && i >= coins[j]; j++) {
                temp = Math.min(F[i - coins[j]], temp);
            }
            F[i] = temp + 1;
        }

        return F[n];
    }

    /*
        dp[0] = 0; dp[i] = dp[i - vj] + 1, 0<=j<coins.length
     */
    public static int changeMarking2(int[] coins, int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < coins.length && i >= coins[j]; j++) {
                dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
            }
        }

        return dp[n] > n ? -1 : dp[n];
    }

    /*
     Divide and Conquer（分治法）-- Recursion

     * f(x, i) representation of the minimum the number of coins required to change x with v[0],v[2],...,v[i - 1],
     * the recurrence formula is as follow:
     * f(x, i) = min( f(x, i-1),  x/v[i] + f(x % v[i], vmax(x%v[i], v)))

    v: the array of change value
    x: number of change
    i：the i in f(x,i)，representation of changing with v[0],v[2],...,v[i]
    */
    static int change_dc(int[] v, int x, int i) {
        if (x <= 1 || i == 0) return x; //当i为0时，必须有这个出口；没有的话在下一个f(x,i-1)中，i会为-1

        // 查找使用 x / v[i] 张 v[i]后，剩余x % v[i]找零数中所有小于或等于的硬币值中数组的最大索引
        int j = 0;
        while ((j < v.length) && (x % v[i] >= v[j])) {
            j++;
        }

        return Math.min(change_dc(v, x, i - 1), x / v[i] + change_dc(v, x % v[i], j - 1));
    }

    /*
     * The recurrence:
     * F(i, j) = max{F(i - 1, j) , F(i, j - 1)} + Cij      1 <= i <= n, 1 <= j <= m
     * F(i, 0) = 0; F(0, j) = 0,                           1 <= i <= n, 1 <= j <= m
     *
     */
    static int robotCoinCollection(int[][] coins) {
        int[][] F = new int[coins.length][coins[0].length];
        F[0][0] = coins[0][0]; // starting point

        for (int j = 1; j < coins[0].length; j++) {
            F[0][j] = F[0][j - 1] + coins[0][j]; // fill the first row and go right
        }

        for (int i = 1; i < coins.length; i++) {
            F[i][0] = F[i - 1][0] + coins[i][0]; // fill the first col and go down

            for (int j = 1; j < coins[0].length; j++) { // fill F(i, j)
                F[i][j] = Math.max(F[i - 1][j], F[i][j - 1]) + coins[i][j];
            }
        }

        return F[coins.length - 1][coins[0].length - 1];
    }

    /*

        F(i, j) = max{F(i - 1, j), F(i - 1, j - wi) + vi}       j - wi >= 0
        F(i, j) = F(i - 1, j)                                   j - wi < 0
        F(0, j) = F(i, 0) = 0
     */
    public static int knapsack(int[] w, int[] v, int W) { // 0, 1背包 -- 动态规划二维数组
        int[][] F = new int[w.length + 1][W + 1];
        // 初始化
        for (int j = 0; j < F[0].length; j++) {
            F[0][j] = 0;
        }
        for (int i = 0; i < F.length; i++) {
            F[i][0] = 0;
        }

        for (int i = 1; i < F.length; i++) { // 遍历物品
            for (int j = 1; j < F[0].length; j++) { // 遍历背包重量
                F[i][j] = (j - w[i - 1] >= 0) ? Math.max(F[i - 1][j], F[i - 1][j - w[i - 1]] + v[i - 1]) : F[i - 1][j];
            }
        }

        return F[F.length - 1][F[0].length - 1];
    }

    public static int knapsackPlus(int[] w, int[] v, int W) {// 0, 1背包 -- 动态规划一维数组
        int[] dp = new int[W + 1];
        for (int i = 1; i <= w.length; i++) {
            for (int j = W; j >= 0; j--) { // for (int j = 0; j <= W; j++) { // 则变为完全背包
                if (j >= w[i - 1])
                    dp[j] = Math.max(dp[j], dp[j - w[i - 1]] + v[i - 1]);
            }
        }

        return dp[W];
    }

    /*
        F(i, j) = max{F(i - 1, j - k*wi) + k * vi}
     */
    public static int completeKnapsack(int[] w, int[] v, int W) {
        int[][] dp = new int[w.length + 1][W + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i < dp.length; i++) { // N -- 物体个数
            for (int j = 1; j < dp[0].length; j++) { // W -- 背包载重
                for (int k = 0; k <= j / w[i - 1]; k++) {
                    if (j >= k * w[i - 1]) {
                        dp[i][j] = Math.max(dp[i - 1][j - k * w[i - 1]] + k * v[i - 1], dp[i][j]);
                    } else dp[i][j] = dp[i][j]; // 此时不能等于dp[i - 1][j]，因为刚开始k=0，j >= 0 * weight[i]肯定成立，此时F[i][j] = F[i - 1][j]
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static int completeKnapsackNoRecursion(int[] w, int[] v, int W) {
        int[] dp = new int[W + 1];

        for (int i = 1; i <= w.length; i++) {
            for (int j = 1; j <= W; j++) {
                // 第二层循环必须为升序
                if (j >= w[i - 1])
                    dp[j] = Math.max(dp[j], dp[j - w[i - 1]] + v[i - 1]);

                // 第二层可升序和逆序，因为可以放入物品的个数无限制，因此可以重复计算（多重背包必须如下写）
                /*for (int k = 0; k <= W / w[i - 1]; k++) {
                    if (j >= k * w[i - 1])
                        dp[j] = Math.max(dp[j - k * w[i - 1]] + k * v[i - 1], dp[j]);
                }*/
            }
        }

        return dp[dp.length - 1];
    }

    public static int completeKnapsackNoRecursionPlus(int[] w, int[] v, int W) {
        int[] dp = new int[W + 1];

        for (int i = 1; i <= w.length; i++) {
            for (int j = 1; j <= W; j++) {
                if (j >= w[i - 1])
                    dp[j] = Math.max(dp[j - w[i - 1]] + v[i - 1], dp[j]);
            }
        }

        return dp[dp.length - 1];
    }

    public static int knapsackBt(int[] w, int[] v, int W) {
        int[] maxV = new int[1];
        backtrack(w, v, W, maxV, 0, 0, 0);
        return maxV[0];
    }

    public static void backtrack(int[] w, int[] v, int W, int[] maxV, int currW, int currV, int idx) {
        if (idx == w.length) {
            if (currV > maxV[0]) maxV[0] = currV;
        } else {
            // put down the idx-th item
            if (currW + w[idx] <= W) {
                backtrack(w, v, W, maxV, currW + w[idx], currV + v[idx], idx + 1);
            }
            // don't put the idx-th item
            backtrack(w, v, W, maxV, currW, currV, idx + 1);
        }
    }

    public static int completeKnapsackBT(int[] w, int[] v, int W) {
        int[] maxV = new int[1];
//        backtrack_(w, v, W, maxV, 0, 0, 0);
        backtrack__(w, v, W, maxV, 0, 0, 0);
        return maxV[0];
    }

    public static void backtrack_(int[] w, int[] v, int W, int[] maxV, int currW, int currV, int idx) {
        if (idx == w.length) {
            maxV[0] = Math.max(maxV[0], currV);
        } else {
            if (currW + w[idx] <= W) {
                backtrack_(w, v, W, maxV, currW + w[idx], currV + v[idx], idx); // 拿
            }
            backtrack_(w, v, W, maxV, currW, currV, idx + 1); // 不拿
        }
    }

    public static void backtrack__(int[] w, int[] v, int W, int[] maxV, int currW, int currV, int idx) {
//        if (idx == w.length) return;
//        else {
            for (int i = idx; i < w.length; i++) {
                if (currW + w[i] <= W)
                    backtrack__(w, v, W, maxV, currW + w[i], currV + v[i], i);
                if (i == w.length - 1) maxV[0] = Math.max(maxV[0], currV);
            }
//        }
    }

}
