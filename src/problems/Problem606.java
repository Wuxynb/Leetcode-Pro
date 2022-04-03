package problems;

import util.TreeNode;

public class Problem606 {
    public static void main(String[] args) {
        System.out.println(tree2str(TreeNode.createBinaryTree(new int[]{1, 2, 4, 0, 0, 0, 3})));
        System.out.println(tree2str(TreeNode.createBinaryTree(new int[]{1, 2, 0, 4, 0, 0, 3})));
    }

    public static String tree2str2(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, false, sb);
        return sb.substring(1, sb.length() - 1);
    }

    public static String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    public static void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.val);
        if (node.left != null || node.right != null) {
            sb.append('(');
            preorder(node.left, sb);
            sb.append(')');
        }
        if (node.right != null) {
            sb.append('(');
            preorder(node.right, sb);
            sb.append(')');
        }
    }

    public static void preorder(TreeNode node, boolean flag, StringBuilder sb) {
        if (node == null) {
            if (flag) sb.append("()");
            return;
        }
        sb.append('(').append(node.val);
        preorder(node.left, node.right != null,sb);
        preorder(node.right, false, sb);
        sb.append(')');
    }
}
