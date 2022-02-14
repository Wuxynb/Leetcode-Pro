package problems;

import util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class Problem106 {
    public static void main(String[] args) {
        System.out.println(buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3}));
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, map);
//        return buildTree(inorder, postorder, 0, postorder.length - 1, postorder.length);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder, int in, int post, int n) {
        if (n <= 0) return null;
        TreeNode root = new TreeNode(postorder[post]);
        int i = 0;
        for (i = 0; i < n; i++) {
            if (inorder[in + i] == root.val) break;
        }
        root.left = buildTree(inorder, postorder, in, post - n + i, i);
        root.right = buildTree(inorder, postorder, in + i + 1, post - 1, n - i - 1);
        return root;
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder, int ins, int ine, int pos, int poe, Map<Integer, Integer> map) {
        if (ins > ine || pos > poe) return null;
        TreeNode root = new TreeNode(postorder[poe]);
        int idx = map.get(root.val);

        root.left = buildTree(inorder, postorder, ins, idx - 1, pos, pos + idx - ins - 1, map);
        root.right = buildTree(inorder, postorder, idx + 1, ine, pos + idx - ins, poe - 1, map);
        return root;
    }
}
