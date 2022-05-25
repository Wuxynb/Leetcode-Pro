package bbc.y2019a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * 题目描述
 * “饱了么”外卖系统中维护着N 家外卖店，编号1~N。
 * 每家外卖店都有一个优先级，初始时(0 时刻) 优先级都为0。
 * 每经过1 个时间单位，如果外卖店没有订单，则优先级会减少1，最低减到0；
 * 而如果外卖店有订单，则优先级不减反加，每有一单优先级加2。
 * 如果某家外卖店某时刻优先级大于5，则会被系统加入优先缓存中；
 * 如果优先级小于等于3，则会被清除出优先缓存。
 * 给定T 时刻以内的M 条订单信息，请你计算T 时刻时有多少外卖店在优先缓存中。
 * 输入格式
 * 第一行包含3 个整数N、M 和T。
 * 以下M 行每行包含两个整数ts 和id，表示ts 时刻编号id 的外卖店收到一个订单
 * 1<=N, M, T<=100000，1<=ts<=T，1<=id<=N。
 * <p>
 * 输出格式
 * 输出一个整数代表答案。
 * 输入样例 复制
 * 2 6 6
 * 1 1
 * 5 2
 * 3 1
 * 6 2
 * 2 1
 * 6 2
 * 输出样例 复制
 * 1
 */
public class TakeoutPriorityMap {
    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        int n = input.nextInt(), m = input.nextInt(), t = input.nextInt();
        Map<Integer, Integer>[] maps = new TreeMap[n + 1];
        for (int i = 1; i <= n; i++) maps[i] = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            int x = input.nextInt(), y = input.nextInt();
            maps[y].put(x, maps[y].getOrDefault(x, 0) + 1);
        }
        boolean[] results = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            int priority = 0, prev = 0;
            for (Map.Entry<Integer, Integer> entry : maps[i].entrySet()) {
                int time = entry.getKey(), cnt = entry.getValue();
                priority = Math.max(0, priority - (time - prev - 1));
                if (results[i] && priority <= 3) results[i] = false;
                priority += cnt * 2;
                prev = time;
                if (priority > 5) results[i] = true;
            }
            priority = Math.max(0, priority - (t - prev));
            if (results[i] && priority <= 3) results[i] = false;
        }
        int ans = 0;
        for (boolean r : results) if (r) ans++;
        System.out.println(ans);
    }

    static class InputReader {
        private final static int BUF_SZ = 65536;
        BufferedReader in;
        StringTokenizer tokenizer;

        public InputReader(InputStream in) {
            this.in = new BufferedReader(new InputStreamReader(in), BUF_SZ);
            tokenizer = new StringTokenizer("");
        }

        private String next() {
            while (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}