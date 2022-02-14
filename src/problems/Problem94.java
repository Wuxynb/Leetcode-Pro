package problems;

import java.util.*;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 *
 *
 * 输入：root = [1,2]
 * 输出：[2,1]
 * 示例 5：
 *
 *
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class Problem94 {
    public static void main(String[] args) {
        System.out.println(inorderTraversal(createBinaryTree(new LinkedList<>(Arrays.asList(1, null, 2, 3)))));
        System.out.println(inorderTraversalMorris(createBinaryTree(new LinkedList<>(Arrays.asList(1, null, 2, 3)))));
        System.out.println(inorderTraversalNone(createBinaryTree(new LinkedList<>(Arrays.asList(1, null, 2, 3)))));
    }

    public static List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode predecessor = null;
        while (root != null) {
            if (root.left != null) {
                predecessor = root.left;
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                while (predecessor.right != null && predecessor.right != root) predecessor = predecessor.right;

                if (predecessor.right == null) {// 让 predecessor 的右指针指向 root，继续遍历左子树
                    predecessor.right = root;
                    root = root.left;
                } else { // 说明左子树已经访问完了，我们需要断开链接
                    ans.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            } else {// 如果没有左孩子，则直接访问右孩子
                ans.add(root.val);
                root = root.right;
            }
        }

        return ans;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inorder(root, ans);
        return ans;
    }

    public static void inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }

    public static List<Integer> inorderTraversalNone(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            ans.add(root.val);
            root = root.right;
        }

        return ans;
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

    private static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
