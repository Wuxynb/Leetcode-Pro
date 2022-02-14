package problems;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 98. 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [2,1,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 *
 *
 * 提示：
 *
 * 树中节点数目范围在[1, 104] 内
 * -2^31 <= Node.val <= 2^31 - 1
 *
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class Problem98 {
    public static void main(String[] args) {
        System.out.println(isValidBST(TreeNode.createBinaryTree(new LinkedList<>(Arrays.asList(2, 1, null, null, 3)))));
        System.out.println(isValidBST(TreeNode.createBinaryTree(new LinkedList<>(Arrays.asList(5, 1, 4, null, null, 3, 6)))));
        System.out.println(isValidBSTNone(TreeNode.createBinaryTree(new LinkedList<>(Arrays.asList(2, 1, null, null, 3)))));
        System.out.println(isValidBSTNone(TreeNode.createBinaryTree(new LinkedList<>(Arrays.asList(5, 1, 4, null, null, 3, 6)))));
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean isValidBST(TreeNode root, long lower, long upper) {
        if (root == null) return true;
        if (root.val <= lower || root.val >= upper) return false;

        return isValidBST(root.left, lower, root.val) && isValidBST(root.right, root.val, upper);
    }

    public static boolean isValidBSTNone(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        double value = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= value) return false;
            value = root.val;
            root = root.right;
        }

        return true;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            TreeNode root = this;
            StringBuilder str = new StringBuilder();
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    str.append(root.val).append(" ");
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop().right;
            }

            return str.toString();
        }

        static TreeNode createBinaryTree(Deque<Integer> list) {
            if (list.size() == 0) return null;
            Integer data = list.removeFirst();
            if (data == null) return null;
            TreeNode root = new TreeNode(data);
            root.left = createBinaryTree(list);
            root.right = createBinaryTree(list);

            return root;
        }
    }
}
