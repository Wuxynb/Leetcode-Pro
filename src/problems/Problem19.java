package problems;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class Problem19 {

    /*
                      head
        [0|next] -> [1|next] -> [2|next] -> [3|next] -> [4|next] -> [5|next] -> [null| ]
            |          |
         second      first

         Step:
         After the first pointer moves n node, the first and second pointer move together util
         the first pointer reached the next node of the last node(Null).
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head, second = dummy;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;

        return dummy.next;
    }

    public static ListNode removeNthFromEnd_(ListNode head, int n) {
        int i = 1;
        ListNode p = head, q = head, r = q;
        while (p.next != null) {
            if (i >= n) {
                r = q;
                q = q.next;
            }
            p = p.next;
            i++;
        }

        if (r != q) r.next = r.next.next;
        else head = head.next;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        System.out.println(removeNthFromEnd(head, 2));
        System.out.println(removeNthFromEnd_(head, 1));
    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode() {}

        public ListNode(int val, ListNode next) {this.val = val; this.next = next;}

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
