package problems;

import util.TreeNode;

/**
 * 700. 二叉搜索树中的搜索
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * <p>
 * 例如，
 * <p>
 * 给定二叉搜索树:
 * <p>
 * 4
 * / \
 * 2   7
 * / \
 * 1   3
 * <p>
 * 和值: 2
 * 你应该返回如下子树:
 * <p>
 * 2
 * / \
 * 1   3
 * <p>
 * 链接：https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 */
public class Problem700 {
    public static void main(String[] args) {

    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        else if (root.val > val) return searchBST(root.left, val);
        else return searchBST(root.right, val);
    }

    public TreeNode searchBSTNone(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) return root;
            root = root.val > val ? root.left : root.right;
        }
        return null;
    }
}
