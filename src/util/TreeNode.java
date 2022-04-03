package util;

import java.util.*;

/**
 * A utility class TreeNode
 *
 * @author Wuxy
 * @version 1.0
 * @date 2021/10/04
 * @see TreeNode
 * @since 1.8
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
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

    private static TreeNode createBinaryTree(Deque<Integer> deque) {
        if (deque.size() == 0) return null;
        Integer data = deque.removeFirst();
        if (data == null) return null;
        TreeNode root = new TreeNode(data);
        root.left = createBinaryTree(deque);
        root.right = createBinaryTree(deque);

        return root;
    }

    public static TreeNode createBinaryTree(List<Integer> list) {
        return createBinaryTree((Deque<Integer>) new LinkedList<>(list));
    }

    /**
     * 通过传入的数组先序递归创建二叉树
     *
     * @see TreeNode
     * @see List<Integer>
     * @deprecated replace by {@link #createBinaryTree(List)}
     * @param nums 二叉树节点值（若值小于或等于0，表明该节点为null）
     * @return 返回创建好的二叉树 TreeNode
     */
    @Deprecated
    public static TreeNode createBinaryTree(int[] nums) {
        Deque<Integer> list = new LinkedList<>();
        for (int i : nums) {
            list.add(i <= 0 ? null : i);
        }
        return createBinaryTree(list);
    }
}
