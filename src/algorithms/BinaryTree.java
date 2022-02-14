package algorithms;

import java.util.*;

public class BinaryTree {
    public static void main(String[] args) {
//        TreeNode tree = createBinaryTree(new LinkedList<>(Arrays.asList(1, 2, 3, null, null, 4, 5)));
        TreeNode tree = createBinaryTree(new LinkedList<>(Arrays.asList(1, 2, 3, null, null, 4, 5, null, 6, null, null, 7, null, null, null)));
//        TreeNode tree = createCompleteBinaryTree(new int[]{1, 2, 3, 4, 5});
//        TreeNode tree = createBinaryTreePreAndIn(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{3, 2, 5, 4, 6, 1, 7, 8}, 0, 0, 8);
        TreeNode tree2 = createBinaryTreeInAndPost(new int[]{3, 2, 5, 4, 6, 1, 7, 8}, new int[]{3, 5, 6, 4, 2, 8, 7, 1}, 0, 7, 8);

        System.out.println(isEquals(tree, tree2));

        System.out.print("先序遍历：");
        preOrderBinaryTree(tree);
        System.out.println();
        System.out.print("先序非递归：");
        preOrderBinaryTreeNone(tree);
        System.out.println();
        System.out.print("先序非递归2：");
        preOrderBinaryTreeNone2(tree);
        System.out.println();
        System.out.print("中序遍历：");
        inOrderBinaryTree(tree);
        System.out.println();
        System.out.print("中序非递归：");
        inOrderBinaryTreeNone(tree);
        System.out.println();
        System.out.print("后序遍历：");
        postOrderBinaryTree(tree);
        System.out.println();
        System.out.print("后序非递归：");
        postOrderBinaryTreeNone(tree);
        System.out.println();
        System.out.print("层次遍历：");
        levelOrderBinaryTree(tree);
    }

    public static TreeNode createCompleteBinaryTree(int[] nums) {
        if (nums.length == 0) return null;
        List<TreeNode> nodes = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            nodes.add(new TreeNode(nums[i]));
        }
        for (int i = 0; i <= nums.length / 2 - 1; i++) {
            nodes.get(i).left = i * 2 + 1 < nums.length ? nodes.get(i * 2 + 1) : null;
            nodes.get(i).right = i * 2 + 2 < nums.length ? nodes.get(i * 2 + 2) : null;
        }

        return nodes.get(0);
    }

    /*
        1, 2, null, 3, 4, 5
     */
    public static TreeNode createBinaryTree(Deque<Integer> list) {
        TreeNode root = null;
        if (list.size() > 0) {
            Integer data = list.removeFirst();
            if (data == null) return null;
            root = new TreeNode(data);
            root.left = createBinaryTree(list);
            root.right = createBinaryTree(list);
        }
        return root;
    }

    /**
     * 创建步骤：<br>
     * <li>1. 取先序遍历的第一个结点作为根结点，序列的结点数为n</li>
     * <li>2. 在中序遍历中查找根结点，其位置为i，那么在中序遍历中根结点之前的i个结点构成根结点左子树的中序遍历序列，根结点后n-i-1个结点构成根结点右子树的中序遍历序列</li>
     * <li>3. 在先序遍历中，根结点之后i个结点构成的序列为根结点左子树的先序遍历序列，先序遍历之后n-i-1个结点构成的序列为根结点右子树的先序遍历序列</li>
     * <li>4. 重复（1）（2）（3），确定左右子树的根结点和子树的左右字数</li>
     * <li>5. 用递归进行建立</li>
     *
     * @param preOrder 先序序列
     * @param inOrder  中序序列
     * @param pre      先序序列中根（开始）节点坐标
     * @param in       中序序列中开始节点坐标
     * @param n        节点个数
     * @return 二叉树 TreeNode
     */
    public static TreeNode createBinaryTreePreAndIn(int[] preOrder, int[] inOrder, int pre, int in, int n) {
        if (n <= 0) return null;
        int c = preOrder[pre]; // 在先序序列中找到根节点
        int i;
        for (i = 0; i < n; i++) {
            // 在中序序列中找到先序序列中对应根节点的位置 n
            if (inOrder[i + in] == c) break;
        }

        TreeNode root = new TreeNode(c);
        root.left = createBinaryTreePreAndIn(preOrder, inOrder, pre + 1, in, i);
        root.right = createBinaryTreePreAndIn(preOrder, inOrder, pre + i + 1, in + i + 1, n - i - 1);
        return root;
    }

    public static TreeNode createBinaryTreeInAndPost(int[] inOrder, int[] postOrder, int in, int post, int n) {
        if (n <= 0) return null;
        int c = postOrder[post];
        int i = 0;
        for (i = 0; i < n; i++) {
            if (inOrder[in + i] == c) break;
        }

        TreeNode root = new TreeNode(c);
        root.left = createBinaryTreeInAndPost(inOrder, postOrder, in, post - (n - i), i);
        root.right = createBinaryTreeInAndPost(inOrder, postOrder, in + i + 1, post - 1, n - i - 1);
        return root;
    }

    public static void preOrderBinaryTree(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preOrderBinaryTree(root.left);
            preOrderBinaryTree(root.right);
        }
    }

    public static void inOrderBinaryTree(TreeNode root) {
        if (root != null) {
            inOrderBinaryTree(root.left);
            System.out.print(root.val + " ");
            inOrderBinaryTree(root.right);
        }
    }

    public static void postOrderBinaryTree(TreeNode root) {
        if (root != null) {
            postOrderBinaryTree(root.left);
            postOrderBinaryTree(root.right);
            System.out.print(root.val + " ");
        }
    }

    public static void levelOrderBinaryTree(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            System.out.print(root.val + " ");
            if (root.left != null) queue.offer(root.left);
            if (root.right != null) queue.offer(root.right);
        }
    }

    public static void preOrderBinaryTreeNone(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                System.out.print(root.val + " ");
                stack.push(root);
                root = root.left;
            }
            root = stack.pop().right;
        }
    }

    public static void preOrderBinaryTreeNone2(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            System.out.print(root.val + " ");
            if (root.right != null) stack.push(root.right);
            if (root.left != null) stack.push(root.left);
        }
    }

    public static void inOrderBinaryTreeNone(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.print(root.val + " ");
            root = root.right;
        }
    }

    public static void postOrderBinaryTreeNone(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = root; // 记录上一个被访问的节点
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.peek().right;
            // 若栈顶节点的右节点为空或者已经visit过，则按顺序应该访问栈顶节点
            if (root == null || root == prev) {
                root = stack.pop();
                System.out.print(root.val + " ");
                prev = root;
                root = null;
            }
        }
    }

    public static boolean isEquals(TreeNode tree1, TreeNode tree2) {
        if (tree1 != null && tree2 != null) {
            if (tree1.val == tree2.val) {
                return isEquals(tree1.left, tree2.left) && isEquals(tree1.right, tree2.right);
            } else return false;
        } else return tree1 == tree2;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {

        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
