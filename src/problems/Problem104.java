package problems;

import util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class Problem104 {
    public static void main(String[] args) {
        System.out.println(maxDepth(TreeNode.createBinaryTree(Arrays.asList(3, 9, null, null, 20, 15, null, null, 7))));
        System.out.println(maxDepthNone(TreeNode.createBinaryTree(Arrays.asList(3, 9, null, null, 20, 15, null, null, 7))));
    }

    public static int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static int maxDepthNone(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                root = queue.poll();
                if (root.left != null) queue.offer(root.left);
                if (root.right != null) queue.offer(root.right);
                size--;
            }
            ans++;
        }

        return ans;
    }
}
