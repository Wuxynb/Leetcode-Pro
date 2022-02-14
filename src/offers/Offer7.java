package offers;

import util.TreeNode;

/**
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 *
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *
 *
 * 示例 1:
 *
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 示例 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 */
public class Offer7 {
    public static void main(String[] args) {
        System.out.println(buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, inorder, 0, preorder.length);
    }

    public static TreeNode buildTree(int[] preorder, int pre, int[] inorder, int in, int n) {
        if (n == 0) return null;
        int i = 0;
        for (i = 0; i < n; i++) {
            if (inorder[in + i] == preorder[pre]) break;
        }
        TreeNode root = new TreeNode(preorder[pre]);
        root.left = buildTree(preorder, pre + 1, inorder, in, i);
        root.right = buildTree(preorder, pre + i + 1, inorder, in + i + 1, n - i - 1);
        return root;
    }
}
