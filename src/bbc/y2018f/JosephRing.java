package bbc.y2018f;

import java.util.*;

/**
 * 题目描述
 * <p>
 * n 个人的编号是 1 ~ n，如果他们依编号按顺时针排成一个圆圈，从编号是 1 的人开始顺时针报数。
 * <p>
 * （报数是从 1 报起）当报到 k 的时候，这个人就退出游戏圈。下一个人重新从 1 开始报数。
 * <p>
 * 求最后剩下的人的编号。这就是著名的约瑟夫环问题。
 * <p>
 * 本题目就是已知 n，k 的情况下，求最后剩下的人的编号。
 * <p>
 * 输入描述
 * <p>
 * 输入是一行，2 个空格分开的整数 n, k (0 < n,k < 10^7)。
 * <p>
 * 输出描述
 * <p>
 * 要求输出一个整数，表示最后剩下的人的编号。
 * <p>
 * 输入输出样例
 * <p>
 * 示例
 * <p>
 * 输入
 * <p>
 * 10 3
 * <p>
 * copy
 * <p>
 * 输出
 * <p>
 * 4
 * <p>
 * copy
 * <p>
 * 运行限制
 * <p>
 * 最大运行时间：1s
 * <p>
 * 最大运行内存: 256M
 */
public class JosephRing {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), k = scan.nextInt();
        System.out.println(solve(n, k));
        System.out.println(solveList(n, k));
        System.out.println(solveQueue(n, k));
    }

    private static int solve(int n, int k) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = (ans + k) % i;
        }
        return ans + 1;
    }

    private static int solveList(int n, int k) { // list 模拟
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) list.add(i);
        int last = 0;
        while (list.size() > 1) {
            last = (k - 1 + last) % list.size();
            list.remove(last);
        }
        return list.get(0);
    }

    private static int solveQueue(int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) queue.offer(i);
        while (queue.size() > 1) {
            for (int i = 1; i < k; i++) queue.offer(queue.poll());
            queue.poll();
        }
        return queue.poll();
    }
}
