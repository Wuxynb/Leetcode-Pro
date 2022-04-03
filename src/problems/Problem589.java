package problems;

import util.NNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 589. N 叉树的前序遍历
 * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
 * <p>
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 节点总数在范围 [0, 104]内
 * 0 <= Node.val <= 104
 * n 叉树的高度小于或等于 1000
 * <p>
 * <p>
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 * <p>
 * link: https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 */
public class Problem589 {
    public static void main(String[] args) {

    }

    public List<Integer> preorderIter(NNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Deque<NNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            NNode node = stack.poll();
            ans.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--) stack.push(node.children.get(i));
        }
        return ans;
    }

    public List<Integer> preorder(NNode root) {
        List<Integer> ans = new ArrayList<>();
        preorder(root, ans);
        return ans;
    }

    public void preorder(NNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        for (NNode node : root.children) preorder(node, list);
    }
}
