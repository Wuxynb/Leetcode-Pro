package problems;

import util.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem116 {
    public static void main(String[] args) {
        Node root = new Node(1,
                new Node(2, new Node(4), new Node(5), null),
                new Node(3, new Node(6), new Node(7), null), null);
        root = connect(root);
        Node.level(root);

        Node root2 = new Node(1,
                new Node(2, new Node(4), new Node(5), null),
                new Node(3, new Node(6), new Node(7), null), null);
        root2 = connectNone(root2);
        Node.level(root2);

        Node root3 = new Node(1,
                new Node(2, new Node(4), new Node(5), null),
                new Node(3, new Node(6), new Node(7), null), null);
        root3 = connectNone_(root3);
        Node.level(root3);
    }

    /**
     * 填充每个节点的下一个右侧节点的指针
     * tips：在进行递归填充时，首先将下一层的孩子节点的next进行连接，等到了下一层，由于已经连接好了next，所以可以得到这一层的右孩子节点的next为
     *    当前节点的next节点的左孩子，即： root.right.next = root.next.left
     *
     * @param root 完美（满）二叉树
     * @return 连接下一个节点后的二叉树
     */
    public static Node connect(Node root) {
        if (root == null || root.left == null) return root;
        // 连接左孩子节点的next
        root.left.next = root.right;
        // 如果当前节点存在next，则将其右孩子节点的next连接到当前节点的next节点的左孩子节点
        if (root.next != null) root.right.next = root.next.left;
        root.left = connect(root.left);
        root.right = connect(root.right);
        return root;
    }

    public static Node connectNone(Node root) {
        if (root == null) return null;
        Node leftMost = root; // 从根节点开始
        while (leftMost.left != null) {
            // 遍历这一层节点组成的链表，更新其下一层的next
            Node head = leftMost;
            while (head != null) {
                // 连接左孩子
                head.left.next = head.right;
                // 连接右孩子
                if (head.next != null) head.right.next = head.next.left;
                // 移向这一层的下一个节点
                head = head.next;
            }
            // 移向下一层的最左边节点
            leftMost = leftMost.left;
        }
        return root;
    }

    public static Node connectNone_(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        Node p = root;
        queue.offer(p);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                p = queue.poll();
                assert p != null;
                p.next = i == size - 1 ? null : queue.peek();
                if (p.left != null) queue.offer(p.left);
                if (p.right != null) queue.offer(p.right);
            }
        }

        return root;
    }
}
