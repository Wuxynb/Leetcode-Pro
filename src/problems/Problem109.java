package problems;

import util.ListNode;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 *  链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
 */
public class Problem109 {
    static ListNode node;
    public static void main(String[] args) {
        System.out.println(sortedListToBST(new ListNode(new int[]{-10, -3, 0, 5, 9})));
        System.out.println(sortedListToBST2(new ListNode(new int[]{-10, -3, 0, 5, 9})));
        System.out.println(sortedListToBST_(new ListNode(new int[]{-10, -3, 0, 5, 9})));
    }

    public static TreeNode sortedListToBST(ListNode head) {
        return helper(head, null);
    }

    public static TreeNode helper(ListNode left, ListNode right) {
        if (left == right) return null;
        ListNode fast = left, slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(left, slow);
        root.right = helper(slow.next, right);

        return root;
    }

    public static TreeNode sortedListToBST_(ListNode head) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            ++len;
            p = p.next;
        }
        node = head;
        return helper_(0, len - 1);
    }

    public static TreeNode helper_(int left, int right) {
        if (left > right) return null;
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = helper_(left, mid - 1);
        root.val = node.val;
        node = node.next;
        root.right = helper_(mid + 1, right);
        return root;
    }

    public static TreeNode sortedListToBST2(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return helper(list, 0, list.size() - 1);
    }

    public static TreeNode helper(List<Integer> list, int left, int right) {
        if (left > right) return null;
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = helper(list, left, mid - 1);
        root.right = helper(list, mid + 1, right);
        return root;
    }
}
