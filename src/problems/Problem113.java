package problems;

import util.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *
 * 链接：https://leetcode-cn.com/problems/path-sum-ii/
 */
public class Problem113 {

    public static void main(String[] args) {
        System.out.println(pathSum(TreeNode.createBinaryTree(new int[]{5, 4, 11, 7, 0, 0, 2, 0, 0, 8, 13, 0, 0, 4, 5, 0, 0, 1}), 22));
        System.out.println(TreeNode.createBinaryTree(new int[]{5, 4, 11, 7, 0, 0, 2, 0, 0, 8, 13, 0, 0, 4, 5, 0, 0, 1}));
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new LinkedList<>(), root, targetSum);
//        dfs(ans, new LinkedList<>(), root, targetSum);
        return ans;
    }

    public static void backtrack(List<List<Integer>> lists, Deque<Integer> list, TreeNode root, int targetSum) {
        if (root == null) return;
        list.add(root.val);
        targetSum -= root.val;
        if (targetSum == 0 && root.right == null && root.left == null) lists.add(new ArrayList<>(list));
        if (root.left != null) backtrack(lists, list, root.left, targetSum);
        if (root.right != null) backtrack(lists, list, root.right, targetSum);
        list.removeLast();
    }

    public static void dfs(List<List<Integer>> lists, Deque<Integer> list, TreeNode root, int targetSum) {
        if (root == null) return;
        list.add(root.val);
        targetSum -= root.val;
        if (targetSum == 0 && root.left == null && root.right == null) {
            lists.add(new ArrayList<>(list));
        }
        dfs(lists, list, root.left, targetSum);
        dfs(lists, list, root.right, targetSum);
        list.removeLast();
    }
}
