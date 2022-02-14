package problems;

import util.Node;

import java.util.*;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 *
 *
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 *
 * 示例：
 *
 *
 *
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 *
 *
 * 提示：
 *
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 *
 * 连接；https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
 */
public class Problem117 {
    public static void main(String[] args) {
//        Node root = new Node(1,
//                new Node(2, null, new Node(5), null),
//                new Node(3, null, new Node(7), null), null);

        Node root = new Node(3,
                new Node(9),
                new Node(20, new Node(15), new Node(7, new Node(6), new Node(10), null), null), null);
        root = connectNone(root);
        Node.level(root);

        Node root2 = new Node(3,
                new Node(9),
                new Node(20, new Node(15), new Node(7, new Node(6), new Node(10), null), null), null);
        root2 = connect(root2);
        Node.level(root2);
    }

    // 按层将节点串连起来，访问一层时，将下一层的节点连起来，然后跳到下一层重复
    public static Node connect(Node root) {
        if (root == null) return null;
        Node cur = root, dummy = new Node(0), pre;
        while (cur != null) {
            // 遍历当前层的时候，为了方便操作在下一层前面添加一个哑结点
            // （访问当前层的节点，然后把下一层的节点串起来）
            dummy.next = null;
            pre = dummy; // pre 表示访下一层节点的前一个节点
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                cur = cur.next; // 继续访问到当前层的下一个节点
            }
            cur = dummy.next; // 转移到下一层的第一个节点
        }
        return root;
    }

    public static Node connectNone(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        Node p = root;
        queue.offer(p);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                p = queue.poll();
                p.next = i == size - 1 ? null : queue.peek();
                if (p.left != null) queue.offer(p.left);
                if (p.right != null) queue.offer(p.right);
            }
        }
        return root;
    }
}
