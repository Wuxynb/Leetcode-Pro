package problems;

import util.TreeNode;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * 示例 3：
 *
 * 输入：root = []
 * 输出：true
 *
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree/
 */
public class Problem110 {
    public static void main(String[] args) {
        System.out.println(isBalanced(TreeNode.createBinaryTree(new int[]{3, 9, 0, 0, 20, 15, 0, 0, 7})));
        System.out.println(isBalanced(TreeNode.createBinaryTree(new int[]{1, 2, 3, 4, 0, 0, 4, 0, 0, 3, 0, 0, 2})));
        System.out.println(isBalanced_(TreeNode.createBinaryTree(new int[]{3, 9, 0, 0, 20, 15, 0, 0, 7})));
        System.out.println(isBalanced_(TreeNode.createBinaryTree(new int[]{1, 2, 3, 4, 0, 0, 4, 0, 0, 3, 0, 0, 2})));
    }

    /*
        自顶向下
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 &&
                isBalanced(root.left) && isBalanced(root.right);
    }

    public static int depth(TreeNode p) {
        if (p == null) return 0;
        else return Math.max(depth(p.left), depth(p.right)) + 1;
    }

    /*
        自底向上
     */
    public static boolean isBalanced_(TreeNode root) {
        return balance(root) != -1;
    }

    public static int balance(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = balance(root.left), rightHeight = balance(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) return -1;
        else return Math.max(leftHeight, rightHeight) + 1;
    }
}
