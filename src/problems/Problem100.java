package problems;

import util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 100. 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 * 示例 3：
 * <p>
 * <p>
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 两棵树上的节点数目都在范围 [0, 100] 内
 * -104 <= Node.val <= 104
 * <p>
 * 链接：https://leetcode-cn.com/problems/same-tree/
 */
public class Problem100 {
    public static void main(String[] args) {
        TreeNode p = TreeNode.createBinaryTree(Arrays.asList(1, 2, null, null, 3));
        TreeNode q = TreeNode.createBinaryTree(Arrays.asList(1, 2, null, null, 3));
        System.out.println(isSameTree(p, q));
        System.out.println(isSameTreeNone(p, q));
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        return (p != null && q != null) ?
                (p.val == q.val &&
                        (isSameTree(p.left, q.left) &&
                                isSameTree(p.right, q.right))) :
                p == q;
    }

    public static boolean isSameTreeNone(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null || q == null) return false;
        Queue<TreeNode> queue1 = new LinkedList<>(), queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll(), node2 = queue2.poll();
            if (node1.val != node2.val) return false;
            TreeNode left1 = node1.left, right1 = node1.right, left2 = node2.left, right2 = node2.right;
            if (left1 == null ^ left2 == null) return false; // 异或^，两个为不同结果时为1
            if (right1 == null ^ right2 == null) return false;
            if (left1 != null) queue1.offer(left1);
            if (right1 != null) queue1.offer(right1);
            if (left2 != null) queue2.offer(left2);
            if (right2 != null) queue2.offer(right2);
        }

        return true;
    }
}
