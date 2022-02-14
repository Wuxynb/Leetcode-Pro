package util;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A utility class Node (The combination of TreeNode and ListNode.)
 *
 * @author Wuxy
 * @version 1.0
 * @date 2021/10/09
 * @see TreeNode
 * @see ListNode
 * @see Node
 * @since 1.8
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        this.val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        this.val = _val;
        this.left = _left;
        this.right = _right;
        this.next = _next;
    }

    public static void level(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                System.out.print(node.val + " --> " + (node.next != null ? node.next.val : null) + ", ");
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            System.out.println();
        }
    }
}
