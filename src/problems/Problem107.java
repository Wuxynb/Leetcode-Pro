package problems;

import util.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. 二叉树的层序遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7], <br>
 *
 *     3                <br>
 *    / \               <br>
 *   9  20              <br>
 *     /  \             <br>
 *    15   7                <br>
 * 返回其自底向上的层序遍历为：
 * <p>
 * [                    <br>
 *   [15,7],            <br>
 *   [9,20],            <br>
 *   [3]                <br>
 * ]                    <br>
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 */
public class Problem107 {
    public static void main(String[] args) {
        System.out.println(levelOrderBottom(TreeNode.createBinaryTree(new int[]{})));
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                list.add(root.val);
                if (root.left != null) queue.offer(root.left);
                if (root.right != null) queue.offer(root.right);
            }
            ans.add(0, list);
        }
        return ans;
    }
}
