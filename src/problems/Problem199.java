package problems;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 示例 2:
 *
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 *
 * 输入: []
 * 输出: []
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view/
 */
public class Problem199 {
    public static void main(String[] args) {

    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans, 0);
        return ans;
    }

    public static void dfs(TreeNode root, List<Integer> list, int level) {
        if (root == null) return;
        // 判断传进来的结点是不是这层的第一个
        // 一条通路走到最下面，size一直++，递归返回后，在访问level，此时size数值已经等于第一条通路最下面的一层level
        if (list.size() == level) list.add(root.val);
        level++;
        dfs(root.right, list, level);
        dfs(root.left, list, level);
    }

    public static List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                if (root.left != null) queue.offer(root.left);
                if (root.right != null) queue.offer(root.right);
                if (i == size - 1) list.add(root.val);
            }
        }
        return list;
    }
}
