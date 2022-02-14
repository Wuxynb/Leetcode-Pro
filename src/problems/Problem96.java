package problems;

/**
 * 96. 不同的二叉搜索树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：5
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= n <= 19
 *
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees/
 */
public class Problem96 {
    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    /*
            假设 n 个节点存在二叉排序树的个数是 G (n)，令 f(i) 为以 i 为根的二叉搜索树的个数，则:
        G(n) = f(1) + f(2) + f(3) + ... + f(n)
            当 i 为根节点时，其左子树节点个数为 i-1 个，右子树节点为 n-i，则
        f(i) = G(i - 1) * G(n - i)
            综合两个公式可以得到 卡特兰数 公式
        G(n) = G(0) * G(n - 1) + G(1) * G(n - 2) + ... + G(n - 1) * G(0).
     */
    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }
}
