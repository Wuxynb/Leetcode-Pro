package problems;

/**
 * 83. 删除排序链表中的重复元素
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 *
 * 返回同样按升序排列的结果链表。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * 示例 2：
 *
 *
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class Problem83 {
    public static void main(String[] args) {

    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        while (p != null && p.next != null) {
            if (p.val != p.next.val) p = p.next;
            else p.next = p.next.next;
        }

        return head;
    }

    public static ListNode deleteDuplicatesRecursion(ListNode head) {
        if (head == null || head.next == null) return head;
        if (head.val != head.next.val) head.next = deleteDuplicatesRecursion(head.next);
        else head = deleteDuplicatesRecursion(head.next);
        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
