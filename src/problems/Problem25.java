package problems;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * 示例 3：
 * <p>
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * 示例 4：
 * <p>
 * 输入：head = [1], k = 1
 * 输出：[1]
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 */
public class Problem25 {

    public static void main(String[] args) {
        System.out.println(reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2));
        System.out.println(reverseKLists(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2));
    }

    /*
        Recursive method
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        ListNode[] nodes = new ListNode[k];
        ListNode p = head;
        for (int i = 0; i < k; i++) {
            if (p != null) {
                nodes[i] = p;
                p = p.next;
            } else return head;
        }
        nodes[0].next = reverseKGroup(nodes[k - 1].next, k);
        for (int i = k - 1; i > 0; i--) {
            nodes[i].next = nodes[i - 1];
        }
        return nodes[k - 1];
    }

    /*
        Iterative method
     */
    public static ListNode reverseKLists(ListNode head, int k) {
        ListNode[] nodes = new ListNode[k];
        ListNode dummy = new ListNode(0, head), p = dummy, q;
        while (p.next != null) {
            q = p.next;
            for (int i = 0; i < k; i++) {
                if (q != null) {
                    nodes[i] = q;
                    q = q.next;
                } else return dummy.next;
            }

            nodes[0].next = nodes[k - 1].next;
            p.next = nodes[k - 1];
            for (int i = k - 1; i > 0; i--) {
                nodes[i].next = nodes[i - 1];
            }
            p = nodes[0];
        }

        return dummy.next;
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
