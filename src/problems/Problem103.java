package problems;

import util.TreeNode;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class Problem103 {
    public static void main(String[] args) {
        System.out.println(zigzagLevelOrder(TreeNode.createBinaryTree(Arrays.asList(3, 9, null, null, 20, 15, null, null, 7))));
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        int c = 0;
        while (!queue.isEmpty()) {
            c++;
            int size = queue.size();
            Deque<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                if (c % 2 == 1) list.add(root.val);
                else list.addFirst(root.val);
                if (root.left != null) queue.offer(root.left);
                if (root.right != null) queue.offer(root.right);
            }
            ans.add(new LinkedList<>(list));
        }

        return ans;
    }
}
