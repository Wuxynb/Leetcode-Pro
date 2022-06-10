package bbc.y2014b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 内存限制：256.0MB   C/C++时间限制：1.0s   Java时间限制：3.0s   Python时间限制：5.0s
 * <p>
 * 　　n 个小朋友站成一排。现在要把他们按身高从低到高的顺序排列，但是每次只能交换位置相邻的两个小朋友。
 * <p>
 * 　　每个小朋友都有一个不高兴的程度。开始的时候，所有小朋友的不高兴程度都是0。
 * <p>
 * 　　如果某个小朋友第一次被要求交换，则他的不高兴程度增加1，如果第二次要求他交换，则他的不高兴程度增加2（即不高兴程度为3），依次类推。当要求某个小朋友第k次交换时，他的不高兴程度增加k。
 * <p>
 * 　　请问，要让所有小朋友按从低到高排队，他们的不高兴程度之和最小是多少。
 * <p>
 * 　　如果有两个小朋友身高一样，则他们谁站在谁前面是没有关系的。
 * <p>
 * 数据格式
 * <p>
 * 　　输入的第一行包含一个整数n，表示小朋友的个数。
 * <p>
 * 　　第二行包含 n 个整数 H1 H2 … Hn，分别表示每个小朋友的身高。
 * <p>
 * 　　输出一行，包含一个整数，表示小朋友的不高兴程度和的最小值。
 * <p>
 * 　　例如，输入：
 * <p>
 * 3
 * <p>
 * 3 2 1
 * <p>
 * 　　程序应该输出：
 * <p>
 * 9
 * <p>
 * 样例说明
 * <p>
 * 　　首先交换身高为3和2的小朋友，再交换身高为3和1的小朋友，再交换身高为2和1的小朋友，每个小朋友的不高兴程度都是3，总和为9。
 * <p>
 * 数据规模和约定
 * <p>
 * 　　对于10%的数据， 1<=n<=10；
 * <p>
 * 　　对于30%的数据， 1<=n<=1000；
 * <p>
 * 　　对于50%的数据， 1<=n<=10000；
 * <p>
 * 　　对于100%的数据，1<=n<=100000，0<=Hi<=1000000。
 */
public class ChildrenLineUp {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer = new StringTokenizer("");
    static int N = (int) 1e6 + 5;
    static int[] C = new int[N + 1];
    static int[] cnt, arr;

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        cnt = new int[n]; arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
            update(arr[i] + 1, 1);
            cnt[i] = (query(N) - query(arr[i] + 1)); // todo: 注意 [数 + 1]，第 0 号元素不用
        }
        long ans = 0;
        C = new int[N + 1];
        for (int i = n - 1; i >= 0; i--) {
            update(arr[i] + 1, 1);
            cnt[i] += (query(arr[i]));
            ans += ((long) (cnt[i] + 1) * cnt[i] / 2);
        }
        System.out.println(ans);
    }

    static int lowbit(int x) {
        return x & (-x);
    }

    static void update(int x, int v) {
        for (int i = x; i <= N; i += lowbit(i)) {
            C[i] += v;
        }
    }

    static int query(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            res += C[i];
        }
        return res;
    }

    private static String next() {
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
}
