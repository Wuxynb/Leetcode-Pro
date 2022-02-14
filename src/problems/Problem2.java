package problems;

/**
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
//        l2.next = new ListNode(9);
//        l2.next.next = new ListNode(9);
//        l2.next.next.next = new ListNode(9);

        ListNode l3 = addTwoNumbers(l1, l2);
        while (l3 != null) {
            System.out.print(l3.val + " ");
            l3 = l3.next;
        }


    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode();
        ListNode p = l3;

        int carry = 0;
        while (l1 != null || l2 != null) {
            int val = -1;
            if (l1 != null && l2 != null) {
                val = l1.val + l2.val + carry;
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1 != null) {
                val = l1.val + carry;
                l1 = l1.next;
            } else {
                val = l2.val + carry;
                l2 = l2.next;
            }
            carry = 0;
            if (val != -1) {
                ListNode node = new ListNode();
                if (val < 10) {
                    carry = 0;
                    node.val = val;
                } else {
                    carry = 1;
                    node.val = val - 10;
                }
                p.next = node;
                p = p.next;
            }

            if (l1 == null && l2 == null && carry != 0) {
                p.next = new ListNode(carry);
            }
        }

        return l3.next;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode l3 = null;
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();
        while (l1 != null) {
            str1.append(String.valueOf(l1.val));
            l1 = l1.next;
        }
        while (l2 != null) {
            str2.append(String.valueOf(l2.val));
            l2 = l2.next;
        }
        long num3 = Long.parseLong(str1.reverse().toString()) + Long.parseLong(str2.reverse().toString());
        str3.append(num3 + "");
        String str = str3.reverse().toString();

        ListNode p = l3;
        for (int i = 0; i < str.length(); i++) {
            int val = Integer.parseInt(str.charAt(i) + "");
            if (i == 0) {
                l3 = new ListNode(val);
                p = l3;
            } else {
                ListNode node = new ListNode(val);
                p.next = node;
                p = p.next;
            }
        }

        return l3;
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


