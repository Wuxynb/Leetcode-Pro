package ojs.xdoj;

import java.util.Scanner;

/**
 * 题目描述
 * <p>
 * 据Glory说，williamchen的音乐品味非常差。据了解，williamchen的netease cloud music中只有一个歌单，里面有n首歌。
 * williamchen每次听歌都按照歌单顺序从头开始听，一直听到某首歌后停止。
 * 现在给出m次williamchen的听歌记录，每个记录只包含这一次williamchen听到了哪一首歌后停止。
 * 请你计算出每一首歌的播放次数，按照歌单的顺序输出。
 * <p>
 * 输入
 * <p>
 * 第一行两个数n，m(0 < n,m  <= 10^5)，代表歌单里的歌曲数目以及记录的条目数。
 * 接下来m行，每行一个数k(0 < k <= n)，代表这次williamchen听到了第k首歌后停止。
 * <p>
 * 输出
 * 输出n行，每行一个数，表示按照歌单顺序，每首歌被播放的次数。
 * <p>
 * 样例输入
 * <p>
 * 5 4  <br>
 * 5    <br>
 * 1    <br>
 * 2    <br>
 * 3    <br>
 * 样例输出
 * <p>
 * 4    <br>
 * 3    <br>
 * 2    <br>
 * 1    <br>
 * 1    <br>
 */
public class P1276MusicList {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        int[] list = new int[n];
        // 初始化 差分 数组
        for (int i = 0; i < m; i++) {
            list[scan.nextInt() - 1]++;
        }
        // 求解原数组 - （差分数组的前缀和即为原数组）
        for (int i = n - 2; i >= 0; i--) {
            list[i] += list[i + 1];
        }
        for (int i = 0; i < n; i++) {
            System.out.println(list[i]);
        }
    }
}
