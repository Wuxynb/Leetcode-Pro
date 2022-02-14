package offers;

import util.TreeNode;

/**
 * 剑指 Offer 55 - II. 平衡二叉树
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 *
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 * 链接：https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
 */
public class Offer55_II {
    public static void main(String[] args) {

    }

    public static boolean isBalanced(TreeNode root) {
        return balance(root) != -1;
    }

    public static int balance(TreeNode root) {
        if (root == null) return 0;
        int leftH = balance(root.left), rightH = balance(root.right);
        if (leftH == -1 || rightH == -1 || Math.abs(leftH - rightH) > 1) return -1;
        else return Math.max(leftH, rightH) + 1;
    }
}
