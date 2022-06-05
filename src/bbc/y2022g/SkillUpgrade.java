package bbc.y2022g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 题目描述
 * <p>
 * 小蓝最近正在玩一款RPG 游戏。他的角色一共有N个可以加攻击力的技能。
 * <p>
 * 其中第i个技能首次升级可以提升Ai点攻击力，以后每次升级增加的点数都会减少Bi。
 * <p>
 * ⌈Ai/Bi⌉ (向上取整) 次之后，再升级该技能将不会改变攻击力。
 * <p>
 * 现在小蓝可以总计升级M次技能，他可以任意选择升级的技能和次数。
 * <p>
 * 请你计算小蓝最多可以提高多少点攻击力？
 * <p>
 * 输入格式
 * <p>
 * 输入第一行包含两个整数N和M。
 * <p>
 * 以下N行每行包含两个整数Ai和Bi。
 * <p>
 * 对于40% 的评测用例，1 ≤ N, M ≤ 1000；
 * <p>
 * 对于60% 的评测用例，1 ≤ N ≤ 10^4; 1 ≤ M ≤ 10^7；
 * <p>
 * 对于所有评测用例，1 ≤ N ≤ 10^5，1 ≤ M ≤ 2 × 10^9，1 ≤ Ai; Bi ≤ 10^6。
 * <p>
 * 输出格式
 * <p>
 * 输出一行包含一个整数表示答案。
 * <p>
 * 输入样例 复制
 * <p>
 * 3 6
 * <p>
 * 10 5
 * <p>
 * 9 2
 * <p>
 * 8 1
 * <p>
 * 输出样例 复制
 * <p>
 * 47
 * <p>
 * 分类标签
 * <p>
 * 进阶题 二分
 */
public class SkillUpgrade {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 65536);
    static StringTokenizer tokenizer = new StringTokenizer("");
    static int maxn = (int) (1e5 + 5);
    static int[] array = new int[maxn];
    static int[] facts = new int[maxn];
    static int n, m;

    // 二分枚举最后一次攻击力最高能加多少，并且要注意最后的计算不能把等于这个值的直接加在答案里
    public static void main(String[] args) {
        n = nextInt(); m = nextInt();
        for (int i = 0; i < n; i++) {
            array[i] = nextInt();
            facts[i] = nextInt();
        }
        long ans = 0L;
        // 二分最后一次技能最多提升了多少攻击力
        int l = 0, r = (int) 1e6;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) l = mid;  // 如果mid可以
            else r = mid - 1;
        }
        int x = l; // 6
        for (int i = 0; i < n; i++) {
            if (array[i] >= x) {
                int cnt = (array[i] - x) / facts[i] + 1;
                int last = array[i] - (cnt - 1) * facts[i];
                if (last == x) { // 不能把等于这个值x的直接加在答案里
                    cnt--;
                    last += facts[i];
                }
                ans += (long) (array[i] + last) * cnt >> 1;
//                System.out.println("arr[i]: " + array[i] + ", cnt: " + cnt + ", last: " + last + ", ans: " + ans);
                m -= cnt;
            }
        }
        ans += (long) m * x;
        System.out.println(ans);
    }

    // 最后一次加攻击能不能到 x
    public static boolean check(int x) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] < x) continue;
            cnt += (array[i] - x) / facts[i] + 1;
            if (cnt >= m) return true;
        }
        return false;
    }

    private static void solve() { // 优先队列求解，可以通过 60%
        long ans = 0L;
        int n = nextInt(), m = nextInt();
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> (y[0] - x[0]));
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{nextInt(), nextInt()});
        }
        while (m-- > 0) {
            int[] arr = pq.poll();
            ans += arr[0];
            pq.offer(new int[]{Math.max(arr[0] - arr[1], 0), arr[1]});
        }
        System.out.println(ans);
    }

    public static String next() {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }

    public static int nextInt() { return Integer.parseInt(next()); }

    public static long nextLong() { return Long.parseLong(next()); }
}
