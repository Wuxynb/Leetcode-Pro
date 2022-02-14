package offers;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 */
public class Offer54 {
    public static void main(String[] args) {

    }

    public int kthLargestNone(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.left;
        }
        return -1;
    }

    int ans = 0, k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        inorder(root);
        return ans;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.right);
        if (--k == 0) {
            ans = root.val;
            return;
        }
        inorder(root.left);
    }
}
