package problems;

import util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 112. 路径总和<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * <p>
 * 示例 3：
 * <p>
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/path-sum/
 */
public class Problem112 {
    public static void main(String[] args) {
        System.out.println(hasPathSum(TreeNode.createBinaryTree(new int[]{5, 4, 11, 7, 0, 0, 2, 0, 0, 8, 13, 0, 0, 4, 0, 1}), 22));
        System.out.println(hasPathSum(TreeNode.createBinaryTree(new int[]{1, 2, 0, 0, 3}), 5));
        System.out.println(hasPathSum(TreeNode.createBinaryTree(new int[]{1, 2}), 1));

        System.out.println(hasPathSumNone(TreeNode.createBinaryTree(new int[]{5, 4, 11, 7, 0, 0, 2, 0, 0, 8, 13, 0, 0, 4, 0, 1}), 22));
        System.out.println(hasPathSumNone(TreeNode.createBinaryTree(new int[]{1, 2, 0, 0, 3}), 5));
        System.out.println(hasPathSumNone(TreeNode.createBinaryTree(new int[]{1, 2}), 1));
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.right == null && root.left == null) return targetSum == root.val;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public static boolean hasPathSumNone(TreeNode root, int targetSum) {
        if (root == null) return false;
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> sums = new LinkedList<>();
        nodes.offer(root);
        sums.offer(root.val);
        while (!nodes.isEmpty()) {
            root = nodes.poll();
            int tmp = sums.poll();
            if (root.left == null && root.right == null)
                if (tmp == targetSum) return true;
                else continue;
            if (root.left != null) {
                nodes.offer(root.left);
                sums.offer(tmp + root.left.val);
            }
            if (root.right != null) {
                nodes.offer(root.right);
                sums.offer(tmp + root.right.val);
            }
        }
        return false;
    }
}
