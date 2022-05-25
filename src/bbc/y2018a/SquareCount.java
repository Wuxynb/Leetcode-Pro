package bbc.y2018a;

/**
 * 题目描述
 * 如图所示，在二维平面上有无数个1x1的小方格。
 * 我们以某个小方格的一个顶点为圆心画一个半径为 50000 的圆。
 * 你能计算出这个圆里有多少个完整的小方格吗？
 *
 *
 * 输出格式
 * 输出一个整数表示答案
 */
public class SquareCount {
    public static void main(String[] args) {
        long distance = 50000 * 50000L, cnt = 0;
        for (long i = 1; i <= 50000; i++) {
            for (long j = 1; j <= 50000; j++) {
                if (i * i + j * j > distance) break;
                cnt++;
            }
        }
        System.out.println(cnt * 4);
    }
}
