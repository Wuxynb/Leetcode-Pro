package problems;

import util.TreeNode;

import java.util.Arrays;

/**
 * 124. 二叉树中的最大路径和 （字节、虾皮）
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 *
 *
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 */
public class Problem124 {
    public static void main(String[] args) {
        System.out.println(maxPathSum(TreeNode.createBinaryTree(Arrays.asList(-10, 9, null, null, 20, 15, null, null, 7))));
    }

    static int ans = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        maxGain(root);
        return ans;
    }

    public static int maxGain(TreeNode root) {
        if (root == null) return 0;
        int leftCain = Math.max(maxGain(root.left), 0);
        int rightGain = Math.max(maxGain(root.right), 0);
        int newPath = root.val + leftCain + rightGain;
        ans = Math.max(ans, newPath);
        return root.val + Math.max(leftCain, rightGain);
    }
}
