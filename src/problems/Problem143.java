package problems;

import util.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 143. 重排链表 （字节、阿里）
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 *  L0 → L1 → … → Ln-1 → Ln
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 *
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 * 示例 2:
 *
 *
 *
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 *
 * 链接：https://leetcode-cn.com/problems/reorder-list/
 */
public class Problem143 {
    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4, 5});
        System.out.println(head);
        reorderList(head);
        System.out.println(head);
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        // 找中点
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 翻转后半段链表
        ListNode last = slow.next;
        if (slow.next != null) {
            ListNode pre = null, cur = slow.next;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            last = pre;
        }
        // 分离前后段链表
        slow.next = null;
        // 合并前后端链表
        while (head != null && last != null) {
            ListNode next = last.next;
            last.next = head.next;
            head.next = last;
            head = last.next;
            last = next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void reorderListList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            list.add(p);
            p = p.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) break;
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
}
