package algorithms;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 状态压缩DP（状态DP）算法
 * <p>
 * 状压dp的特点一般是规模比较小，n一般小于15。而且一般只有两种决策
 * </p>
 */
public class StateDynamicProgram {

    public static void main(String[] args) {
        System.out.println(knapsack(new int[]{2, 1, 3, 2}, new int[]{12, 10, 20, 15}, 5));

        System.out.println(deliverTakeout());

        System.out.println(travelSalesman());

        System.out.println(travelingSalesmanManyTimes());
    }

    public static int knapsack(int[] w, int[] v, int W) {
        int n = w.length, len = 1 << n, ans = -1;
        // dp记录价值，dp2记录重量
        int[] dp = new int[len], dp2 = new int[len];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if ((1 & (i >> j)) != 1) { // 第j件物品未放入
                    dp[i | (1 << j)] = dp[i] + v[j];
                    dp2[i | (1 << j)] = dp2[i] + w[j];
                }
            }
        }

        for (int i = 0; i < (1 << n); i++) {
            if (dp2[i] == W) ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 送外卖
     * <p>
     * 题目描述  Description
     * </p>
     * 有一个送外卖的，他手上有n份订单，他要把n份东西，分别送达n个不同的客户的手上。n个不同的客户分别在1~n个编号的城市中。送外卖的从0号城市出发，
     * 然后n个城市都要走一次（一个城市可以走多次），最后还要回到0点（他的单位），请问最短时间是多少。现在已知任意两个城市的直接通路的时间。
     * <p>
     * 输入描述  Input Description
     * </p>
     * 第一行一个正整数n （1<=n<=15）
     * <p>
     * 接下来是一个（n+1）*(n+1)的矩阵，矩阵中的数均为不超过10000的正整数。矩阵的i行j列表示第i-1号城市和j-1号城市之间直接通路的时间。当然城市a
     * 到城市b的直接通路时间和城市b到城市a的直接通路时间不一定相同，也就是说道路都是单向的。
     * </p>
     * <p>
     * 输出描述  Output Description
     * </p>
     * 一个正整数表示最少花费的时间
     * <p>
     * 样例输入  Sample Input
     * </p>
     * 3 <br>
     * 0 1 10 10 <br>
     * 1 0 1 2 <br>
     * 10 1 0 10 <br>
     * 10 2 10 0 <br>
     * <p>
     * 样例输出  Sample Output
     * </p>
     * 8
     *
     * <p>
     * link: http://t.zoukankan.com/wangrunhu-p-9482829.html
     * </p>
     *
     * @return 最少花费时间
     */
    public static int deliverTakeout() {
        /*Scanner input = new Scanner(System.in);
        int n = input.nextInt() + 1, m = 1 << n, max = 0x7ffff;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = input.nextInt();
            }
        }*/

        int n = 4, m = 1 << n, max = 0x7ffff;
        int[][] graph = new int[][]{
                {0, 1, 10, 10},
                {1, 0, 1, 2},
                {10, 1, 0, 10},
                {10, 2, 10, 0}
        };

        // 全连通图可以不用初始化，但是非全连通图必须 初始化，预处理，对角线为0，其余为0设为 max
        // todo: initialize graph

        // Floyed算法，先用输入的矩阵跑一遍floyed求出点到点的最短路
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        System.out.println("Floyed: " + Arrays.deepToString(graph));
        /*
         * dp[i][j] 表示 在状态 i 下，现在处在 j 点，（此时 j 点 包含在 i 状态中）要去访问剩余的点
         *
         * 转移就是：dp[i][j]=min{dp[i][j] , dp[i - k][k] + dis[j][k]}，其中v是当前位置，u是上一个状态的位置。
         */
        int[][] dp = new int[m][n]; // dp[i][j] -- i表示之前走的情况i，j表示现在在j点，的最短距离
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], max); // 初始化 dp 数组全为最大值
        dp[0][0] = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) { // 从 j 出发
                if (((i >> j) & 1) == 1) { // 如果 j 包含在状态 i 中
                    for (int k = 0; k < n; k++) { // 到 k 中距离最短
                        dp[i][j] = Math.min(dp[i][j], dp[i - (1 << j)][k] + graph[k][j]);
                    }
                }
            }
        }

        System.out.println(Arrays.toString(dp[m - 1]));

        return dp[m - 1][0];
    }

    /**
     * 旅行商问题
     * <p>
     * 题目要求，从0出发，经过[1,2,3]这几个城市，然后回到0，使得花费最少。要实现这个要求，需要从下面三个实现方案中选择花费最少的方案。
     *      <ol>
     *          <li>从0出发，到1，然后再从1出发，经过[2,3]这几个城市，然后回到0，使得花费最少。</li>
     *          <li>从0出发，到2，然后再从2出发，经过[1,3]这几个城市，然后回到0，使得花费最少。</li>
     *          <li>从0出发，到3，然后再从3出发，经过[1,2]这几个城市，然后回到0，使得花费最少。</li>
     *      </ol>
     * </p>
     *
     * <p>
     *    假设从顶点s出发，令d(i, V)表示从顶点i出发经过V(是一个点的集合)中各个顶点一次且仅一次，最后回到出发点s的最短路径长度。
     * <p>
     * 　　可以发现，三个小的解决方案的最优解，构成了大的解决方案，所以这个问题具有最优子结构，可以用动态规划来实现。
     * <p>
     * 　　设置一个二维的动态规划表dp,定义符号{1,2,3}表示经过[1,2,3]这几个城市，然后回到0。
     * <p>
     * 　　那么题目就是求dp[0][{1,2,3}]。将{1,2,3}表示成二进制，就是111，对应10进制的7，所以题目是在求dp[0][7];
     * </p>
     * <p>
     * 　　要求三个方案的最小值意味：
     *  <ul>
     *      <li>dp[0][{1,2,3}] = min{ C01+dp[1][{2,3}] ，C02+dp[2][{1,3}] ，C03+dp[3][{1,2}]}</li>
     *      <li>其中C01 表示从0出发到1的距离。</li>
     *      <li>dp[1][{2,3}] = min{ C12+dp[2][{3}] ，C13+dp[3][{1}]}</li>
     *      <li>dp[2][{3}] = C23+dp[3][{}]</li>
     *      <li>dp[3][{}]就是从3出发，不经过任何城市，回到0的花费，所以dp[3][{}] = C30</li>
     *  </ul>
     * </p>
     * 　　先确定一下dp表的大小，有n个城市，从0开始编号，那么dp表的行数就是n，列数就是2^(n-1)，即1 << （n – 1）,集合{1,2,3}的子集个数。
     * 在求解的时候，第一列的值对应这从邻接矩阵可以导出，后面的列可以有前面的列和邻接矩阵导出。所以求出的动态规划表就是：
     *
     * @return 花费的最少时间
     */
    public static int travelSalesman() {
        int n = 4, max = 0x7ffff, m = 1 << (n - 1); // 0x7ffff = 524287
        int[][] matrix = new int[][]{
                {0, 10, 15, 0},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {0, 25, 30, 0}
        };

        /*int[][] matrix = new int[][]{
                {0, 1, 10, 10},
                {1, 0, 1, 2},
                {10, 1, 0, 10},
                {10, 2, 10, 0}
        };*/

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) matrix[i][j] = max;
            }
        }

        int[][] dp = new int[n][m]; // 1000 => 000 ~ 111

        // 初始化 dp[i][0]
        for (int i = 0; i < n; i++) dp[i][0] = matrix[i][0];

        // 先更新列，再更新行
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) { // 从 i 出发
                dp[i][j] = max;
                if (((j >> (i - 1)) & 1) == 1) continue; // 如果 i 此时 在 j 状态已经走过，跳过
                for (int k = 1; k < n; k++) { // 看看能不能先到 k 城市
                    if (((j >> (k - 1)) & 1) == 0) continue; // 如果 k 不在 j 状态中，跳过
                    dp[i][j] = Math.min(dp[i][j], matrix[i][k] + dp[k][j ^ (1 << (k - 1))]); // j ^ x <=> j - x
                }
            }
        }

        return dp[0][m - 1];
    }

    public static int travelingSalesmanManyTimes() {
        int n = 4, max = 0x7ffff, m = 1 << n; // 0x7ffff = 524287
        /*int[][] matrix = new int[][]{
                {0, 1, 10, 10},
                {1, 0, 1, 2},
                {10, 1, 0, 10},
                {10, 2, 10, 0}
        };*/

        int[][] matrix = new int[][]{
                {0, 10, 15, 0},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {0, 25, 30, 0}
        };

        // 全连通图可以不用初始化，但是非全连通图必须 初始化，预处理，对角线为0，其余为0设为 max
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) matrix[i][j] = i == j ? 0 : max;
            }
        }
        // floyed
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        // origin -> [[0, 1, 2, 3], [1, 0, 1, 2], [2, 1, 0, 3], [3, 2, 3, 0]]
        // none   -> [[0, 1, 2, 3], [1, 0, 1, 2], [2, 1, 0, 3], [3, 2, 3, 0]]
        System.out.println("Matrix: " + Arrays.deepToString(matrix));
        int[][] dp = new int[m][n]; // dp[i][j] --> 遍历完 i 状态时，此时在 j 处时的最短距离
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], max);
        dp[1][0] = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) == 0 || dp[i][j] == max) continue;
                for (int k = 0; k < n; k++) {
                    if (((1 << k) & i) == 1) continue;
//                    if (((1 << k) & i) == 1 || matrix[j][k] == 0) continue; // 图未初始化
                    dp[i | (1 << k)][k] = Math.min(dp[i | (1 << k)][k], dp[i][j] + matrix[j][k]);
                }
            }
        }
        // 从每个点回到 起始点 0
        for (int i = 0; i < n; i++) dp[m - 1][i] += matrix[i][0];
//        for (int i = 0; i < n; i++) dp[m - 1][i] += matrix[i][0] == 0 ? max : matrix[i][0]; // 图未初始化
        System.out.println(Arrays.deepToString(dp));
        return Arrays.stream(dp[m - 1]).min().orElse(-1);
    }
}
