package offers;

import util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 37. 序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * <p>
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * <p>
 * 链接：https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/
 */
public class Offer37 {
    public static void main(String[] args) {
        Codec codec = new Codec();
        String serial = codec.serialize(TreeNode.createBinaryTree(Arrays.asList(1, 2, null, null, 3, 4, null, null, 5)));
        System.out.println(codec.deserialize(serial));
    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));
    static class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return null;
            StringBuilder str = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    root = queue.poll();
                    if (root != null) {
                        str.append(root.val).append(" ");
                        queue.offer(root.left);
                        queue.offer(root.right);
                    } else str.append(" ");
                }
            }
            return str.toString().trim();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null) return null;
            String[] strs = data.split(" ");
            int i = 0;
            TreeNode root = new TreeNode(Integer.parseInt(strs[i++])), p = root;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(p);
            while (!queue.isEmpty()) {
                p = queue.poll();
                if (p != null) {
                    if (i < strs.length) {
                        p.left = strs[i].equals("") ? null : new TreeNode(Integer.parseInt(strs[i]));
                        i++;
                    }
                    queue.offer(p.left);
                    if (i < strs.length) {
                        p.right = strs[i].equals("") ? null : new TreeNode(Integer.parseInt(strs[i]));
                        i++;
                    }
                    queue.offer(p.right);
                }
            }
            return root;
        }
    }
}
