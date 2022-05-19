package problems;

import util.TreeNode;

import java.util.*;

/**
 * 449. 序列化和反序列化二叉搜索树
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 *
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 *
 * 编码的字符串应尽可能紧凑。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [2,1,3]
 * 输出：[2,1,3]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点数范围是 [0, 104]
 * 0 <= Node.val <= 104
 * 题目数据 保证 输入的树是一棵二叉搜索树。
 *
 * link: https://leetcode.cn/problems/serialize-and-deserialize-bst/
 */
public class Problem449 {
    /*
         5
        /  \
       3    7
       /\  /
      1  4 6
     */
    public static void main(String[] args) {
        Codec ser = new Codec();
        Codec deser = new Codec();
        TreeNode root = TreeNode.createBinaryTree(Arrays.asList(5, 3, 1, null, null, 4, null, null, 7, 6));
        String tree = ser.serialize(root);
        TreeNode ans = deser.deserialize(tree);
        System.out.println(ans);
    }

    // Your Codec object will be instantiated and called as such:
    // Codec ser = new Codec();
    // Codec deser = new Codec();
    // String tree = ser.serialize(root);
    // TreeNode ans = deser.deserialize(tree);
    // return ans;
    static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<String> list = new ArrayList<>();
            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                list.add(String.valueOf(root.val));
                if (root.right != null) stack.push(root.right);
                if (root.left != null) stack.push(root.left);
            }
//            preorder(root, list);
            return String.join(",", list);
        }

        private void preorder(TreeNode root, List<String> list) {
            if (root == null) return;
            list.add(String.valueOf(root.val));
            preorder(root.left, list);
            preorder(root.right, list);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            TreeNode root = null;
            for (String s : data.split(",")) {
                root = insert(root, Integer.parseInt(s));
            }
            return root;
        }

        public TreeNode insert(TreeNode node, Integer value) {
            TreeNode p = new TreeNode(value);
            if (node == null) return p;
            if (node.val > value) {
                node.left = insert(node.left, value);
                return node;
            } else if (node.val < value){
                node.right = insert(node.right, value);
                return node;
            }
            return node;
        }
    }
}
