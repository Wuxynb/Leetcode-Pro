package problems;

import util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 129. 求根节点到叶节点数字之和
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * 示例 2：
 *
 *
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 *
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 9
 * 树的深度不超过 10
 *
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 */
public class Problem129 {
    public static void main(String[] args) {
        System.out.println(sumNumbers(TreeNode.createBinaryTree(Arrays.asList(4, 9, 5, null, null, 1, null, null, 0))));
        System.out.println(sumNumbersNone(TreeNode.createBinaryTree(Arrays.asList(4, 9, 5, null, null, 1, null, null, 0))));
    }

    public static int sumNumbersNone(TreeNode root) {
        if (root == null) return 0;
        int res = 0, num = 0;
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> nums = new LinkedList<>();
        nodes.offer(root);
        nums.offer(root.val);
        while (!nodes.isEmpty()) {
            root = nodes.poll();
            num = nums.poll();
            if (root.left == null && root.right == null) res += num;
            else {
                if (root.left != null) {
                    nodes.offer(root.left);
                    nums.offer(num * 10 + root.left.val);
                }
                if (root.right != null) {
                    nodes.offer(root.right);
                    nums.offer(num * 10 + root.right.val);
                }
            }
        }

        return res;
    }

    public static int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public static int dfs(TreeNode root, int sum) {
        if (root == null) return 0;
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else return dfs(root.left, sum) + dfs(root.right, sum);
    }
}
