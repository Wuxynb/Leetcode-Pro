package bbc.y2022g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 题目描述
 * <p>
 * 对于一个长度为N 的整数数列A[1], A[2], ... , A[N]，小蓝想知道下标 l 到 r 的部分和 A[l] + A[l+1] + ... + A[r] 是多少？
 * <p>
 * 然而，小蓝并不知道数列中每个数的值是多少，他只知道它的 M 个部分和的值。
 * <p>
 * 其中第i 个部分和是下标 li 到 ri 的部分和：A[li] + A[li+1] + ... + A[ri]，值是Si。
 * <p>
 * 输入格式
 * <p>
 * 第一行包含3个整数N、M和Q，分别代表数组长度、已知的部分和数量和询问的部分和数量。
 * <p>
 * 接下来M行，每行包含3个整数 li, ri, Si。
 * <p>
 * 接下来Q行，每行包含2个整数 l 和 r，代表小蓝想知道的部分和。
 * <p>
 * 10%的测试数据：1≤N,M,Q≤10, -100≤Si≤100;
 * <p>
 * 20%的测试数据：1≤N,M,Q≤20, -1000≤Si≤1000;
 * <p>
 * 30%的测试数据：1≤N,M,Q≤50, -10000≤Si≤10000;
 * <p>
 * 40%的测试数据：1≤N,M,Q≤1000, -10^6≤Si≤10^6;
 * <p>
 * 60%的测试数据：1≤N,M,Q≤10000, -10^9≤Si≤10^9;
 * <p>
 * 100%的测试数据：1≤N,M,Q≤10^5, -10^12≤Si≤10^12, 1≤li≤ri≤N，1≤l≤r≤N。
 * <p>
 * 数据保证没有矛盾。
 * <p>
 * 输出格式
 * <p>
 * 对于每个询问，输出一行包含一个整数表示答案。如果答案无法确定，输出UNKNOWN。
 * <p>
 * 输入样例 复制
 * <p>
 * 5 3 3
 * <p>
 * 1 5 15
 * <p>
 * 4 5 9
 * <p>
 * 2 3 5
 * <p>
 * 1 5
 * <p>
 * 1 3
 * <p>
 * 1 2
 * <p>
 * <p>
 * 输出样例 复制
 * <p>
 * 15
 * <p>
 * 6
 * <p>
 * UNKNOWN
 * <p>
 * 分类标签
 * <p>
 * 进阶题 并查集 搜索
 */
public class DerivationPartSum {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 65536);
    static StringTokenizer tokenizer = new StringTokenizer("");
    static int N = (int) 1e5 + 5, n;
    static int[] fa = new int[N];
    static long[] sum = new long[N];
    static boolean[] vis = new boolean[N];
    static List<long[]>[] edges = new ArrayList[N];

    public static void main(String[] args) {
        n = nextInt();
        int m = nextInt(), q = nextInt();
        for (int i = 0; i <= n; i++) {
            fa[i] = i;
            edges[i] = new ArrayList<>();
        }
        while (m-- > 0) { // 建有权图 [l-1~r]，其中 w = s和 w = -s
            int l = nextInt(), r = nextInt();
            long s = nextLong();
            edges[l - 1].add(new long[]{r, s});
            edges[r].add(new long[]{l - 1, -s});
            merge(l - 1, r); // 合并操作
        }
        for (int i = 0; i <= n; i++) if (!vis[i]) bfs(i); // bfs遍历每一个节点 构造前缀和
        while (q-- > 0) {
            int l = nextInt(), r = nextInt();
            if (find(l - 1) == find(r)) System.out.println((sum[r] - sum[l - 1]));
            else System.out.println("UNKNOWN");
        }
    }

    static void bfs(int x) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(x);
        sum[x] = 0;
        vis[x] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (long[] vw : edges[u]) {
                int v = (int) vw[0];
                long w = vw[1];
                if (vis[v]) continue;
                vis[v] = true;
                sum[v] = sum[u] + w;
                queue.offer(v);
            }
        }
    }

    static int find(int x) {
        if (fa[x] == x) return x;
        return fa[x] = find(fa[x]);
    }

    static void merge(int i, int j) {
        fa[find(i)] = find(j);
    }

    static String next() {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }

    static long nextLong() {
        return Long.parseLong(next());
    }
}
