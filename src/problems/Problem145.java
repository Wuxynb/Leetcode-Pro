package problems;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class Problem145 {
    public static void main(String[] args) {
        System.out.println(postorderTraversal(TreeNode.createBinaryTree(new int[]{1, 2, 3, -1, -1, 4, -1, -1, 5})));
        System.out.println(postorderTraversalNone(TreeNode.createBinaryTree(new int[]{1, 2, 3, -1, -1, 4, -1, -1, 5})));
    }

    public static List<Integer> postorderTraversalNone(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.peek().right;
            if (root == null || root == pre) {
                root = stack.pop();
                ans.add(root.val);
                pre = root;
                root = null;
            }
        }
        return ans;
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        postorder(root, ans);
        return ans;
    }

    public static void postorder(TreeNode root, List<Integer> ans) {
        if (root != null) {
            postorder(root.left, ans);
            postorder(root.right, ans);
            ans.add(root.val);
        }
    }
}
