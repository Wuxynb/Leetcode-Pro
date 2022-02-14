package problems;

import util.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Problem147 {
    public static void main(String[] args) {
        System.out.println(insertionSortList(new ListNode(new int[]{4, 2, 1, 3})));
        System.out.println(insertionSortList(new ListNode(new int[]{1, 5, 3, 4, 0})));
        System.out.println(insertionSortList(new ListNode(new int[]{-1, 5, 3, 4, 0})));
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0, head);
        ListNode lastSorted = head, cur = head.next; // 指向排好序的最后一个元素，下一个需要排序的元素
        while (cur != null) {
            if (lastSorted.val <= cur.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummy;
                while (prev.next.val <= cur.val) {
                    prev = prev.next;
                }
                lastSorted.next = cur.next;
                cur.next = prev.next;
                prev.next = cur;
            }
            cur = lastSorted.next;
        }
        return dummy.next;
    }

    public static ListNode insertionSortList_(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(Integer.MIN_VALUE), cur = head.next;
        head.next = null;
        dummy.next = head;
        while (cur != null) {
            ListNode next = cur.next, prev = dummy, p = dummy.next;
            while (p != null && p.val <= cur.val) {
                prev = p;
                p = p.next;
            }
            cur.next = prev.next;
            prev.next = cur;
            cur = next;
        }
        return dummy.next;
    }

    public static ListNode insertionSortListList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            list.add(p.val);
            p = p.next;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (list.get(j) < list.get(j - 1)) {
                    int tmp = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, tmp);
                } else break;
            }
        }
        ListNode dummy = new ListNode();
        p = dummy;
        for (int i : list) {
            p.next = new ListNode(i);
            p = p.next;
        }
        return dummy.next;
    }
}
