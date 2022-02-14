package util;

/**
 * A utility class ListNode
 *
 * @author Wuxy
 * @version 1.0
 * @date 2021/10/04
 * @see ListNode
 * @since 1.8
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int[] vals) {
        if (vals.length == 0) return;
        ListNode p = this;
        for (int i = 0; i < vals.length; i++) {
            assert p != null;
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
