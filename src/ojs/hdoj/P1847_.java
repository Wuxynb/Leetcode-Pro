package ojs.hdoj;

import java.util.Arrays;
import java.util.Scanner;

public class P1847_ {
    static int maxn = 1005;
    static int[] sg = new int[maxn];
    static int[] arr = new int[15];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;
        arr[0] = 1;
        for (int i = 1; i < 15; i++) arr[i] = arr[i - 1] * 2;
        while (input.hasNext() && (n = input.nextInt()) != 0) {
            Arrays.fill(sg, -1);
            if (getSg(n) == 0) System.out.println("Cici");
            else System.out.println("Kiki");

            // [[], [0], [1, 0], [2, 1], [0, 2, 0], [1, 0, 1], [2, 1, 2], [0, 2, 0], [1, 0, 1, 0], [2, 1, 2, 1]]
            // [[], [0], [1, 0], [2, 1], [0, 2, 0], [1, 0, 1], [2, 1, 2], [0, 2, 0], [1, 0, 1, 0]]
            // [[], [0]]
//            System.out.println(Arrays.toString(lists));
        }
    }

    public static int getSg(int x) { // 找x的sg函数，即求sg[x]
        if (sg[x] != -1) return sg[x];
        boolean[] visited = new boolean[maxn];
        for (int i = 0; i < 10 && arr[i] <= x; i++) { // 遍历sg[x]的所有后继sg[y]
            int y = x - arr[i];
            sg[y] = getSg(y); // 拿完 i 张牌后剩下的
            visited[sg[y]] = true;
        }
        for (int i = 0; ;i++) { // 找其不属于后继集合的最小数
            if (!visited[i]) return (sg[x] = i);
        }
    }
}
