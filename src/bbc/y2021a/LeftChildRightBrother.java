package bbc.y2021a;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeftChildRightBrother {
    static int N = (int) 1e6 + 5;
    static List<Integer>[] trees = new ArrayList[N];
    static {
        for (int i = 0; i < N; i++) trees[i] = new ArrayList<>();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 2; i <= n; i++) {
            trees[scan.nextInt()].add(i);
        }
        System.out.println(dfs(1));
    }

    public static int dfs(int node) {
        if (trees[node].size() == 0) return 0;
        int cnt = 0; // 每个孩子能贡献的最大深度
        for (int next : trees[node]) {
            cnt = Math.max(cnt, dfs(next));
        }
        return cnt + trees[node].size();
    }
}
