package problems;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 173. 二叉搜索树迭代器
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 * <p>
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * 输入
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * 输出
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 * <p>
 * 解释
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // 返回 3
 * bSTIterator.next();    // 返回 7
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 9
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 15
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 20
 * bSTIterator.hasNext(); // 返回 False
 * <p>
 * 链接：https://leetcode-cn.com/problems/binary-search-tree-iterator/
 */
public class Problem173 {
    public static void main(String[] args) {

    }

    /**
     * Your BSTIterator object will be instantiated and called as such:
     * BSTIterator obj = new BSTIterator(root);
     * int param_1 = obj.next();
     * boolean param_2 = obj.hasNext();
     */
    static class BSTIteratorRecursion {
        TreeNode curr;
        Deque<TreeNode> stack;

        public BSTIteratorRecursion(TreeNode root) {
            curr = root;
            stack = new ArrayDeque<>();
        }

        public int next() {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            int res = curr.val;
            curr = curr.right;
            return res;
        }

        public boolean hasNext() {
            return curr != null || !stack.isEmpty();
        }
    }

    static class BSTIterator {
        int idx;
        List<Integer> list;

        public BSTIterator(TreeNode root) {
            idx = 0;
            list = new ArrayList<>();
            inorder(root);
        }

        public int next() {
            return list.get(idx++);
        }

        public boolean hasNext() {
            return idx < list.size();
        }

        private void inorder(TreeNode root) {
            if (root != null) {
                inorder(root.left);
                list.add(root.val);
                inorder(root.right);
            }
        }
    }
}
