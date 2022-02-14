package offers;

import util.ListNode;

import java.util.Arrays;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 */
public class Offer6 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(reversePrint(new ListNode(new int[]{1, 3, 2}))));
    }

    public static int[] reversePrint(ListNode head) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        int[] ans = new int[len];
        int idx = len - 1;
        while (head != null) {
            ans[idx--] = head.val;
            head = head.next;
        }
        return ans;
    }
}
