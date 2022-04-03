package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2049. 统计最高分的节点数目
 * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。
 * <p>
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
 * <p>
 * 请你返回有 最高得分 节点的 数目 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * example-1
 * <p>
 * 输入：parents = [-1,2,0,2,0]
 * 输出：3
 * 解释：
 * - 节点 0 的分数为：3 * 1 = 3
 * - 节点 1 的分数为：4 = 4
 * - 节点 2 的分数为：1 * 1 * 2 = 2
 * - 节点 3 的分数为：4 = 4
 * - 节点 4 的分数为：4 = 4
 * 最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
 * 示例 2：
 * <p>
 * example-2
 * <p>
 * 输入：parents = [-1,2,0]
 * 输出：2
 * 解释：
 * - 节点 0 的分数为：2 = 2
 * - 节点 1 的分数为：2 = 2
 * - 节点 2 的分数为：1 * 1 = 1
 * 最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == parents.length
 * 2 <= n <= 105
 * parents[0] == -1
 * 对于 i != 0 ，有 0 <= parents[i] <= n - 1
 * parents 表示一棵二叉树。
 * <p>
 * link:
 */
public class Problem2049 {
    public static void main(String[] args) {
        System.out.println(countHighestScoreNodes(new int[]{-1, 2, 0, 2, 0}));
    }

    /**
     * 二叉树写法
     */
    static long maxScore = 0;
    static int cnt = 0;

    public static int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        int[] left = new int[n], right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        for (int i = 1; i < n; i++) {
            if (left[parents[i]] == -1) left[parents[i]] = i;
            else right[parents[i]] = i;
        }
        dfs(left, right, 0, n);
        return cnt;
    }

    public static int dfs(int[] left, int[] right, int node, int n) {
        if (node == -1) return 0; // null 树
        int leftNodes = dfs(left, right, left[node], n);
        int rightNodes = dfs(left, right, right[node], n);
        long score = (long) (leftNodes == 0 ? 1 : leftNodes) *
                (rightNodes == 0 ? 1 : rightNodes) *
                (n - leftNodes - rightNodes - 1 == 0 ? 1 : (n - leftNodes - rightNodes - 1));
        if (score == maxScore) cnt++;
        else if (score > maxScore) {
            maxScore = score;
            cnt = 1;
        }
        return leftNodes + rightNodes + 1;
    }

    /**
     * 多叉树做法
     */
    public static int countHighestScoreNodesMulti(int[] parents) {
        int n = parents.length;
        int[] ans = new int[2]; // maxScore, cnt
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) if (parents[i] != -1) children[parents[i]].add(i);
        dfsMulti(children, n, 0, ans);
        return ans[1];
    }

    public static int dfsMulti(List<Integer>[] children, int n, int node, int[] ans) {
        int score = 1, size = n - 1;
        for (int c : children[node]) {
            int t = dfsMulti(children, n, c, ans);
            score *= t;
            size -= t;
        }
        if (node != 0) score *= size; // 不是开始节点
        if (score == ans[0]) ans[1]++;
        else if (score > ans[0]) {
            ans[0] = score;
            ans[1] = 1;
        }
        return n - size;
    }

}
