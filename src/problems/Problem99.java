package problems;

import java.util.*;

/**
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 * 示例 2：
 *
 *
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 *
 *
 * 提示：
 *
 * 树上节点的数目在范围 [2, 1000] 内
 * -231 <= Node.val <= 231 - 1
 *
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree/
 */
public class Problem99 {
    public static void main(String[] args) {
        TreeNode root = TreeNode.createBinaryTree(new LinkedList<>(Arrays.asList(3, 1, null, null, 4, 2, null, null, null)));
        System.out.println(root);
        recoverTree(root);
//        recoverTree_(root);
        System.out.println(root);
    }

    public static void recoverTree(TreeNode root) {
        TreeNode x = null, y = null, pred = null, predecessor = null;
        while (root != null) {
            if (root.left != null) {
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) predecessor = predecessor.right;

                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    if (pred != null && pred.val > root.val) {
                        y = root;
                        if (x == null) x = pred;
                    }
                    pred = root;
                    predecessor.right = null;
                    root = root.right;
                }
            } else {
                if (pred != null && pred.val > root.val) {
                    y = root;
                    if (x == null) x = pred;
                }
                pred = root;
                root = root.right;
            }
        }

        assert x != null;
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    public static void recoverTree_(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = new TreeNode(Integer.MIN_VALUE), node1 = null,  node2 = null, p = root;
        while (!stack.isEmpty() || p != null) {
            while (p !=  null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            if (node1 == null && pre.val > p.val) node1 = pre;
            if (node1 != null && pre.val > p.val) node2 = p;
            pre = p;
            p = p.right;
        }
        assert node1 != null;
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }

    /*
        1 2 3 6 5 4 7
     */
    public static void recoverTree__(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        inorderTraversal(root, nodes);
        TreeNode x = null, y = null;
        for (int i = 0; i < nodes.size() - 1; i++) {
            if (nodes.get(i).val > nodes.get(i + 1).val) {
                y = nodes.get(i + 1);
                if (x == null) x = nodes.get(i);
            }
        }
        assert x != null;
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    public static void inorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            inorderTraversal(root.left, list);
            list.add(root);
            inorderTraversal(root.right, list);
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            TreeNode root = this;
            StringBuilder str = new StringBuilder();
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    str.append(root.val).append(" ");
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop().right;
            }

            return str.toString();
        }

        static TreeNode createBinaryTree(Deque<Integer> list) {
            if (list.size() == 0) return null;
            Integer data = list.removeFirst();
            if (data == null) return null;
            TreeNode root = new TreeNode(data);
            root.left = createBinaryTree(list);
            root.right = createBinaryTree(list);

            return root;
        }
    }
}
