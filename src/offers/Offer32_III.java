package offers;

import util.TreeNode;

import java.util.*;

public class Offer32_III {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int line = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Deque<Integer> ans = new LinkedList<>();
            line++;
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                if ((line & 1) == 1) ans.add(root.val);
                else ans.addFirst(root.val);
                if (root.left != null) queue.offer(root.left);
                if (root.right != null) queue.offer(root.right);
            }
            res.add(new ArrayList<>(ans));
        }
        return res;
    }
}
