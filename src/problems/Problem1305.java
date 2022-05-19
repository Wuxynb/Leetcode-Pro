package problems;

import util.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1305. 两棵二叉搜索树中的所有元素
 * 给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 * 示例 2：
 *
 *
 *
 * 输入：root1 = [1,null,8], root2 = [8,1]
 * 输出：[1,1,8,8]
 *
 *
 * 提示：
 *
 * 每棵树的节点数在 [0, 5000] 范围内
 * -105 <= Node.val <= 105
 *
 * link: https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/
 */
public class Problem1305 {
    public static void main(String[] args) {
        System.out.println(getAllElements(
                TreeNode.createBinaryTree(Arrays.asList(2, 1, null, null, 4)),
                TreeNode.createBinaryTree(Arrays.asList(1, 0, null, null, 3))));
    }

    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>(), ans = new ArrayList<>();
        inorder(root1, list1);
        inorder(root2, list2);
        int i = 0, j = 0;
        Integer[] arr1 = list1.toArray(new Integer[0]), arr2 = list2.toArray(new Integer[0]);
        while (i < arr1.length || j < arr2.length) {
            if (i < arr1.length && j < arr2.length) {
                if (arr1[i] < arr2[j]) ans.add(arr1[i++]);
                else ans.add(arr2[j++]);
            } else if (i < arr1.length) ans.add(arr1[i++]);
            else ans.add(arr2[j++]);
        }
        return ans;
    }

    public static void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}
