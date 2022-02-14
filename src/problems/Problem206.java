package problems;

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class Problem206 {
    public static void main(String[] args) {
        System.out.println(reverseList(new ListNode(new int[]{1, 2, 3, 4, 5})));
        System.out.println(reverseListRecursion(new ListNode(new int[]{1, 2, 3, 4, 5})));
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public static ListNode reverseListRecursion(ListNode head) {
        if (head == null ||  head.next == null) return head;
        ListNode newHead = reverseListRecursion(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
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

        ListNode(int[] vals) {
            ListNode p = this;
            for (int i = 0; i < vals.length; i++) {
                p.val = vals[i];
                p.next = i == vals.length - 1 ? null : new ListNode();
                p = p.next;
            }
        }

        public String toString() {
            ListNode p = this;
            StringBuilder str = new StringBuilder("[");
            while (p != null) {
                str.append(p.val);
                if (p.next != null) str.append(", ");
                p = p.next;
            }
            str.append("]");
            return str.toString();
        }
    }
}
