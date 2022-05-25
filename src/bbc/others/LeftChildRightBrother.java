package bbc.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 * 对于一棵多叉树，我们可以通过“左孩子右兄弟” 表示法，将其转化成一棵二叉树。
 * 如果我们认为每个结点的子结点是无序的，那么得到的二叉树可能不唯一。
 * 换句话说，每个结点可以选任意子结点作为左孩子，并按任意顺序连接右兄弟。
 * 给定一棵包含N 个结点的多叉树，结点从1 至N 编号，其中1 号结点是根，每个结点的父结点的编号比自己的编号小。
 * 请你计算其通过“左孩子右兄弟” 表示法转化成的二叉树，高度最高是多少。
 * 注：只有根结点这一个结点的树高度为0 。
 * 例如如下的多叉树：
 * <p>
 * 可能有以下3 种(这里只列出3 种，并不是全部) 不同的“左孩子右兄弟”表示：
 * <p>
 * 其中最后一种高度最高，为4。
 * 输入格式
 * 输入的第一行包含一个整数N。
 * 以下N-1 行，每行包含一个整数，依次表示2 至N 号结点的父结点编号。
 * 对于30% 的评测用例，1 ≤ N ≤ 20；
 * 对于所有评测用例，1 ≤ N ≤ 100000。
 * 输出格式
 * 输出一个整数表示答案。
 * 输入样例 复制
 * 5
 * 1
 * 1
 * 1
 * 2
 * 输出样例 复制
 * 4
 */
public class LeftChildRightBrother {
    static int answer = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        List<Integer>[] tree = new List[n + 1];
        int[] arrays = new int[n + 1];
        for (int i = 0; i < n; i++) tree[i + 1] = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            int t = input.nextInt();
            tree[t].add(i);
        }
        dfs(tree, 1, arrays);
        System.out.println(answer);
        System.out.println(dfs(tree, 1));
    }

    public static int dfs(List<Integer>[] tree, int node) {
        if (tree[node].size() == 0) return 0;
        int cnt = 0;
        for (int n : tree[node]) cnt = Math.max(cnt, dfs(tree, n));
        return cnt + tree[node].size();
    }

    public static void dfs(List<Integer>[] tree, int node, int[] arrays) {
        for (int i : tree[node]) {
            arrays[i] = arrays[node] + tree[node].size(); // 求节点孩子的最深层数
            answer = Math.max(answer, arrays[i]);
            dfs(tree, i, arrays);
        }
    }
}
