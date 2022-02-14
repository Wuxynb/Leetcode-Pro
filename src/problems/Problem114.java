package problems;

import util.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [0]
 * 输出：[0]
 *
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 */
public class Problem114 {
    public static void main(String[] args) {
        TreeNode root = TreeNode.createBinaryTree(new int[]{1, 2, 3, 0, 0, 4, 0, 0, 5, 0, 6});
        flatten(root);
        System.out.println(root);

        TreeNode root2 = TreeNode.createBinaryTree(new int[]{1, 2, 3, 0, 0, 4, 0, 0, 5, 0, 6});
        flattenNone(root2);
        System.out.println(root2);

        TreeNode root3 = TreeNode.createBinaryTree(new int[]{1, 2, 3, 0, 0, 4, 0, 0, 5, 0, 6});
        flatten_(root3);
        System.out.println(root3);
    }

    /*
           对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，将当前节点的右子节点赋给前驱节点的右子节点，
        然后将当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点设为空。对当前节点处理结束后，继续处理链表中的下一个节点，直
        到所有节点都处理结束。。
     */
    public static void flattenNone(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode next = cur.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) predecessor = predecessor.right;
                predecessor.right = cur.right;
                cur.right = next;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    /*
        在还没操作节点右子树前，不能破坏该节点的右子树指向。所以采用后序遍历
     */
    public static void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode rgt = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) root = root.right;
        root.right = rgt;
    }

    public static void flatten_(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        dfs(root, list);
        root = list.get(0);
        list.remove(0);
        for (TreeNode node : list) {
            root.right = node;
            root.left = null;
            root = root.right;
        }
    }

    public static void dfs(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        list.add(root);
        dfs(root.left, list);
        dfs(root.right, list);
    }
}
