package problems;

import util.ListNode;

public class Problem148 {
    public static void main(String[] args) {
        System.out.println(sortList(new ListNode(new int[]{-1, 5, 3, 4, 0})));
        System.out.println(sortListPlus(new ListNode(new int[]{-1, 5, 3, 4, 0})));
        System.out.println(sortList_(new ListNode(new int[]{-1, 5, 3, 4, 0})));
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode b = slow.next;
        slow.next = null;
        return merge(sortList(head), sortList(b));
    }

    public static ListNode sortListPlus(ListNode head) {
        if (head == null) return null;
        // 1. 首先从头向后遍历,统计链表长度
        int length = 0; // 用于统计链表长度
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        // 2. 初始化 引入dummy node
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 3. 每次将链表拆分成若干个长度为subLen的子链表 , 并按照每两个子链表一组进行合并
        for (int subLen = 1; subLen < length; subLen <<= 1) { // subLen每次左移一位（即sublen = sublen*2）
            ListNode prev = dummyHead;
            ListNode curr = dummyHead.next;     // curr用于记录拆分链表的位置
            while (curr != null) {               // 如果链表没有被拆完
                // 3.1 拆分subLen长度的链表1
                ListNode head_1 = curr;        // 第一个链表的头 即 curr初始的位置
                for (int i = 1; i < subLen && curr.next != null; i++) {     // 拆分出长度为subLen的链表1
                    curr = curr.next;
                }
                // 3.2 拆分subLen长度的链表2
                ListNode head_2 = curr.next;  // 第二个链表的头  即 链表1尾部的下一个位置
                curr.next = null;             // 断开第一个链表和第二个链表的链接
                curr = head_2;                // 第二个链表头 重新赋值给curr
                for (int i = 1; i < subLen && curr != null && curr.next != null; i++) {      // 再拆分出长度为subLen的链表2
                    curr = curr.next;
                }
                // 3.3 再次断开 第二个链表最后的next的链接
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;   // next用于记录 拆分完两个链表的结束位置
                    curr.next = null;   // 断开连接
                }
                // 3.4 合并两个subLen长度的有序链表
                ListNode merged = merge(head_1, head_2);
                prev.next = merged;        // prev.next 指向排好序链表的头
                while (prev.next != null) {  // while循环 将prev移动到 subLen*2 的位置后去
                    prev = prev.next;
                }
                curr = next;              // next用于记录 拆分完两个链表的结束位置
            }
        }
        // 返回新排好序的链表
        return dummyHead.next;
    }

    public static ListNode sortList_(ListNode head) {
        return sortList(head, null);
    }

    public static ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) return null;
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) fast = fast.next;
        }

        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);

        return merge(list1, list2);
    }

    public static ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0), p = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                p.next = a;
                a = a.next;
            } else {
                p.next = b;
                b = b.next;
            }
            p = p.next;
        }
        if (a != null) p.next = a;
        else if (b != null) p.next = b;
        return dummy.next;
    }
}
