package offers;

import util.TreeNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 剑指 Offer 27. 二叉树的镜像
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * <p>
 * 例如输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 镜像输出：
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * <p>
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
 */
public class Offer27 {
    public static void main(String[] args) {
        System.out.println(mirrorTree(TreeNode.createBinaryTree(Arrays.asList(4, 2, 1, null, null, 3, null, null, 7, 6, null, null, 9))));
    }

    public static TreeNode mirrorTreeNone(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<TreeNode>(){{push(root);}}; // 用stack，保证可以按序访问
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }

    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode b = new TreeNode(root.val);
        b.right = mirrorTree(root.left);
        b.left = mirrorTree(root.right);
        return b;
    }
}
