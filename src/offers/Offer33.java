package offers;

import java.util.Stack;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 *
 *
 * 参考以下这颗二叉搜索树：
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 *
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 *
 * 输入: [1,3,2,6,5]
 * 输出: true
 *
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 */
public class Offer33 {
    public static void main(String[] args) {
        System.out.println(verifyPostorder(new int[]{1, 3, 2, 6, 5}));
    }

    public static boolean verifyPostorder(int[] postorder) {
        return verify(postorder, 0, postorder.length - 1);
    }

    public static boolean verify(int[] postorder, int left, int right) {
        if (left >= right) return true;
        int i = left;
        while (postorder[i] < postorder[right]) i++;
        int m = i;
        while (i < right) if (postorder[i++] < postorder[right]) return false;
        return verify(postorder, left, m - 1) && verify(postorder, m, right - 1);
    }

    /**
       Example:
            5
           / \
          2   7
         / \  / \
        1   3 6  8
       postorder = [1, 3, 2, 6, 8, 7, 5]。
     */
    public static boolean verifyPostorderStack(int[] postorder) {
        Stack<Integer> stack = new Stack<>(); // 单调递增栈
        int prev = Integer.MAX_VALUE; //存储当前遍历子树的根节点的值，初始为max
        // 后序遍历 从后往前 为二叉树的 反转先序遍历
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > prev) return false; // 左子树元素必须小于栈顶元素
            while (!stack.isEmpty() && postorder[i] < stack.peek()) { // 小于栈顶元素，即往左子树走
                prev = stack.pop(); // 找到左子树的根节点
            }
            stack.push(postorder[i]);
        }
        return true;
    }

    public static boolean verifyPostorderAStack(int[] postorder) {
        int top = -1, prev = Integer.MAX_VALUE;
        int[] stack = new int[postorder.length];
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > prev) return false;
            while (top != -1 && postorder[i] < stack[top]) prev = stack[top--];
            stack[++top] = postorder[i];
        }
        return true;
    }
}
