package offers;

import util.ListNode;

/**
 * 剑指 Offer 18. 删除链表的节点
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 *
 * 返回删除后的链表的头节点。
 *
 * 注意：此题对比原题有改动
 *
 * 示例 1:
 *
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 *
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 * 链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
 */
public class Offer18 {
    public static void main(String[] args) {
        System.out.println(deleteNode(new ListNode(new int[]{4, 5, 1, 9}), 5));
        System.out.println(deleteNodeRecursion(new ListNode(new int[]{4, 5, 1, 9}), 5));
    }

    public static ListNode deleteNodeRecursion(ListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) return head.next;
        head.next = deleteNodeRecursion(head.next, val);
        return head;
    }


    public static ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head), prev = dummy, q = head;
        while (q != null && q.val != val) {
            q = q.next;
            prev = prev.next;
        }
        if (q != null) prev.next = q.next;
        return dummy.next;
    }
}
