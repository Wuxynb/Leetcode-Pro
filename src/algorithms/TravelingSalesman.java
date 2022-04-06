package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 旅行商问题
 * <p>
 *     <ul> https://www.cnblogs.com/youmuchen/p/6879579.html </ul>
 *     <ul> https://blog.csdn.net/qq_39559641/article/details/101209534 </ul>
 * </p>
 */
public class TravelingSalesman {

    public static void main(String[] args) {
        int cityCount = 4;
        /*int[][] roadInfo = new int[][]{
                {0, 1, 10},
                {1, 0, 10},
                {1, 3, 25},
                {3, 1, 25},
                {3, 2, 30},
                {2, 3, 30},
                {0, 2, 15},
                {2, 0, 15},
                {1, 2, 35},
                {2, 1, 35}
        };*/

        int[][] roadInfo = new int[][]{
                {0, 1, 1},
                {1, 0, 1},
                {1, 3, 2},
                {3, 1, 2},
                {3, 2, 10},
                {2, 3, 10},
                {0, 2, 10},
                {2, 0, 10},
                {1, 2, 1},
                {2, 1, 1},
                {0, 3, 10},
                {3, 0, 10}
        };

        int[][] roadmap = new int[cityCount][cityCount];        //转成邻接矩阵方便取数
        int[][] dp = new int[cityCount][1 << (cityCount - 1)];
        for (int i = 0; i < cityCount; i++) {
            for (int j = 0; j < cityCount; j++) {
                roadmap[i][j] = 0x7ffff;                        //用0x7ffff表示无穷大
            }
        }
        for (int[] ints : roadInfo) {                 //邻接矩阵
            roadmap[ints[0]][ints[1]] = ints[2];
        }

        for (int i = 0; i < cityCount; i++) {                          //先求dp表第一列
            dp[i][0] = roadmap[i][0];                            //求出了每个城市回到起点的距离了。
        }

        for (int j = 1; j < 1 << (cityCount - 1); j++) {             //再求其他列
            for (int i = 0; i < cityCount; i++) {                    //从i出发，要去包含j = {010101}的    城市
                dp[i][j] = 0x7ffff;
                if (((j >> (i - 1)) & 1) == 1) {                   //如果已经到过j了，就continue
                    continue;
                }
                for (int k = 1; k < cityCount; k++) {                 //看能不能先到k城市
                    if (((j >> (k - 1)) & 1) == 0) {
                        continue;                                 //不能先到k城市，continue;
                    }
                    if (dp[i][j] > roadmap[i][k] + dp[k][j ^ (1 << (k - 1))]) {
                        dp[i][j] = roadmap[i][k] + dp[k][j ^ (1 << (k - 1))];
                    }
                }
            }
        }
        System.out.println(dp[0][(1 << (cityCount - 1)) - 1]);

//        printPath(roadmap, dp, cityCount, 1 << (cityCount - 1));
    }

    // 判断节点是否都已经被访问
    public static boolean allVisited(boolean[] visited) {
        for (boolean b : visited) {
            if (!b) return false;
        }
        return true;
    }

    public static void printPath(int[][] graph, int[][] dp, int n, int m) {
        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int pioneer = 0, min = 0x7ffff, s = m - 1, temp = 0;
        list.add(0);

        while (!allVisited(visited)) {
            for (int i = 1; i < n; i++) {
                if (!visited[i] && (s & (1 << (i - 1))) != 0) {
                    if (min > graph[i][pioneer] + dp[i][(s ^ (1 << (i - 1)))]) {
                        min = graph[i][pioneer] + dp[i][(s ^ (1 << (i - 1)))];
                        temp = i;
                    }
                }
            }
            pioneer = temp;
            list.add(pioneer);
            visited[pioneer] = true;
            s = s ^ (1 << (pioneer - 1));
            min = 0x7ffff;
        }

        list.forEach((x) -> System.out.print(x + "->"));
    }
}
