package problems;

import java.util.Iterator;

/**
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 * <p>
 * 链接：https://leetcode-cn.com/problems/partition-list/
 */
public class Problem86 {
    public static void main(String[] args) {
        System.out.println(partition(new ListNode(new int[]{1, 4, 3, 2, 5, 2}), 3));
//        System.out.println(partition(new ListNode(new int[]{1, 6, 4, 3, 2, 5, 2}), 3));
//        System.out.println(partition(new ListNode(new int[]{1, 6, 4, 3, 2, 5, 2}), 4));
//        System.out.println(partition(new ListNode(new int[]{1, 2}), 1));

        // [1,0,1,1,0,1,0,2,3,2,2,2,2,2]
//        System.out.println(partition(new ListNode(new int[]{1, 2, 0, 3, 1, 2, 1, 0, 2, 3, 2, 1, 0, 2}), 2));
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(0), largeHead = new ListNode(0);
        ListNode small = smallHead, large = largeHead;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;

        return smallHead.next;
    }

    public static ListNode partition_(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode left = new ListNode(0), p = head, right = new ListNode(0), left2 = new ListNode(0);
        ListNode low = left, low2 = left2, high = right, xNode = new ListNode(0), xIdx = xNode;
        boolean f = false;
        while (p != null) {
            if (p.val != x && !f) {
                if (p.val < x) {
                    low.next = new ListNode(p.val);
                    low = low.next;
                } else {
                    low2.next = new ListNode(p.val);
                    low2 = low2.next;
                }
            } else if (p.val == x && !f) {
                f = true;
                xIdx.next = new ListNode(x);
                xIdx = xIdx.next;
            } else {
                if (p.val < x) {
                    low.next = new ListNode(p.val);
                    low = low.next;
                } else {
                    high.next = new ListNode(p.val);
                    high = high.next;
                }
            }
            p = p.next;
        }

        if (right.next != null) xIdx.next = right.next;
        if (xNode.next != null) low2.next = xNode.next;
        if (left2.next != null) low.next = left2.next;

        return left.next;
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
