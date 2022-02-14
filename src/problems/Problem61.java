package problems;

/**
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * <p>
 * 链接：https://leetcode-cn.com/problems/rotate-list/
 */
public class Problem61 {
    public static void main(String[] args) {
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        ListNode head = new ListNode(0, new ListNode(1, new ListNode(2)));
        ListNode head = new ListNode(1);

//        System.out.println(rotateRight_(head, 2000000000));
        System.out.println(rotateRight_(head, 99));
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        ListNode ans = head;
        for (int i = 0; i < k % len; i++) {
            ans = rotate(ans);
        }
        return ans;
    }

    public static ListNode rotate(ListNode head) {
        ListNode p = head, q = head.next;
        while (q != null && q.next != null) {
            p = p.next;
            q = q.next;
        }

        if (q != null) {
            p.next = null;
            q.next = head;
            return q;
        } else return head;
    }

    public static ListNode rotateRight_(ListNode head, int k) {
        if (head == null || k == 0 || head.next == null) return head;
        ListNode f = head, s = head, p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }
        if (k % len == 0) return head;
        for (int i = 0; i < k % len; i++) {
            f = f.next;
        }

        while (f.next != null) {
            f = f.next;
            s = s.next;
        }

        ListNode newHead = s.next;
        s.next = null;
        f.next = head;

        return newHead;
    }

    /*
        闭环
     */
    public ListNode rotateRight__(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
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
