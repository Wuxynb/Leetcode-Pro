package problems;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 *
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
public class Problem24 {
    public static void main(String[] args) {
        System.out.println(swapPairs(new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(4, new ListNode(5, new ListNode(6))))))));
        System.out.println(change(new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(4, new ListNode(5, new ListNode(6))))))));
    }

    /*
        Recursive method
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;

        return newHead;
    }

    /*
        Iterative method
     */
    public static ListNode change(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0, head), p = dummy;
        while (p.next != null) {
            ListNode q = p.next, r = q.next;
            if (r != null) {
                p.next = r;
                q.next = r.next;
                r.next = q;
            }
            p = q;
        }

        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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
