package util;

/**
 * A utility class RandomNode.
 *
 * @author Wuxy
 * @version 1.0
 * @date 2021/10/17
 * @see ListNode
 * @see RandomNode
 * @since 1.8
 */
public class RandomNode {
    public int val;
    public RandomNode next;
    public RandomNode random;

    public RandomNode(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public String toString() {
        RandomNode p = this;
        String str = "";
        while (p != null) {
            str += p.val + " (" + (p.random == null ? null : p.random.val) + "), ";
            p = p.next;
        }
        return str;
    }
}
