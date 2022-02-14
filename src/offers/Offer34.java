package offers;

import util.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 *
 *
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 */
public class Offer34 {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(root, target, ans, new LinkedList<>(), 0);
        return ans;
    }

    public static void backtrack(TreeNode root, int target, List<List<Integer>> ans, Deque<Integer> deque, int sum) {
        if (root == null) return;
        sum += root.val;
        deque.add(root.val);
        if (sum == target && root.left == null && root.right == null) ans.add(new ArrayList<>(deque));
        backtrack(root.left, target, ans, deque, sum);
        backtrack(root.right, target, ans, deque, sum);
        deque.removeLast();
    }
}
