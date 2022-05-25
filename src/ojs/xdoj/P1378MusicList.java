package ojs.xdoj;

import java.util.Scanner;

/**
 * 有一个n*m的歌单,每一轮听歌,某聚聚选择一个节点(i, j),这个节点前的所有歌(u, v), (u <= i ,v <= j)都会听一次(其实就是左上角那个点和选择那个点
 * 组成对角线所在的矩形内的所有歌).给出每轮的节点,求H轮之后,每首歌听了几次。——抽象自xdoj1378
 * <p>
 * 输入
 * <p>
 * 3 3 9        <br>
 * 1 1      <br>
 * 1 2      <br>
 * 1 3      <br>
 * 2 1      <br>
 * 2 2      <br>
 * 2 3      <br>
 * 3 1      <br>
 * 3 2      <br>
 * 3 3      <br>
 * <p>
 * 输出
 * <p>
 * 9        <br>
 * 6        <br>
 * 3        <br>
 * 6        <br>
 * 4        <br>
 * 2        <br>
 * 3        <br>
 * 2        <br>
 * 1        <br>
 */
public class P1378MusicList {
    /*
     *      (x,y)  (x,y+1)
     *           --------------------
     *          |   |                |
     * (x+1,y)  |--------------------|
     *          |   | (x+1,y+1)      |
     *          |   |                |
     *          |   |                |
     *          |   |                |
     *           --------------------
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt(), H = scan.nextInt();
        int[][] a = new int[n + 1][m + 1]; // 多开一位
        // 初始化差分数组
        for (int i = 0; i < H; i++) {
            int x = scan.nextInt(), y = scan.nextInt();
            a[x - 1][y - 1]++;
        }
        // 求原始数组 - 差分数组前缀和
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                a[i][j] += a[i + 1][j] + a[i][j + 1] - a[i + 1][j + 1];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.println(a[i][j]);
            }
        }
    }
}
