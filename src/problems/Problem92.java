package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 92. 反转链表 II （百度、美团）
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * 示例 2：
 *
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 *
 * 提示：
 *
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii/
 */
public class Problem92 {
    public static void main(String[] args) {
        System.out.println(reverseBetween(new ListNode(new int[]{1, 2, 3, 4, 5}), 2, 4));
        System.out.println(reverseBetween(new ListNode(new int[]{5}), 1, 1));
        System.out.println(reverseBetween(new ListNode(new int[]{3, 5}), 1, 2));
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head), pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next, next;
        for (int i = left; i < right; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }

        return dummy.next;
    }

    public static ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head), pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next, leftNode = pre.next;
        pre.next = null;
        for (int i = left; i <= right; i++) {
            ListNode next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        leftNode.next = cur;

        return dummy.next;
    }

    public static ListNode reverseBetween_(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head), pre = dummy;
        // 得到left的前一个节点pre
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 确定right右节点
        ListNode rightNode = pre.next;
        for (int i = 0; i < right - left; i++) {
            rightNode = rightNode.next;
        }

        // left左节点和 right的后一个节点successor
        ListNode leftNode = pre.next, successor = rightNode.next;

        // 切断需要翻转的链表
        pre.next = null;
        rightNode.next = null;

        // 翻转链表
        reverseList(leftNode);

        // 拼接翻转完的链表
        pre.next = rightNode;
        leftNode.next = successor;

        return dummy.next;
    }

    public static void reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    public static ListNode reverseBetween__(ListNode head, int left, int right) {
        ListNode p = head;
        List<Integer> list = new ArrayList<>();
        while (p != null) {
            int x = p.val;
            list.add(x);
            p = p.next;
        }
        left--; right--;
        while (left <= right) {
            int temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
            left++; right--;
        }

        ListNode newHead = new ListNode(list.get(0)), q = newHead;
        for (int i = 1; i < list.size(); i++) {
            q.next = new ListNode(list.get(i));
            q = q.next;
        }

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
