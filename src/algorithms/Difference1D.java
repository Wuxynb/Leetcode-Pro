package algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 一维差分 -- 差分数组的作用是可以利用 线性时间 来处理 区间修改问题.
 * <ol>
 *     <li>给定原始数组 a = {a[1], a[2], ..., a[n]}</li>
 *     <li>目的：构造出数组 b = {b[1], b[2], ..., b[n]}，使得满足：a[i] = b[1] + b[2] + ... + b[1]。
 *          因此，数组 b 称为 数组 a 的差分和，数组 a 称为数组 b 的前缀和。
 *     </li>
 *     <li>
 *         现在需求：将[l,r]区间中的数组元素a[i]均加上常数C，如果使用原始算法，则要扫描一下数组a，时间复杂度为O(n)；如果使用差分算法则时间复杂度仅为O(1)。具体步骤如下：
 *         <ul>
 *             <li>a.b[l] + C：当b[l] + C时，a[l]、a[l+1]、…、a[n]均会加C，原因是a[i] = b[1] + b[2] + … + b[i]</li>
 *             <li>b.b[r + 1] - C：由于需求是将数组a中[l,r]区间中的元素均加C，因此需要将b数组中a[r+1, n]区间中的元素都减去C。这样做才能够抵消b[l] + C步骤中将数组a中[r + 1, n]区间中的元素多加的C给抵消掉，实现真正将[l,r]区间中的数组元素a[i]均加上常数C。</li>
 *         </ul>
 *     </li>
 * </ol>
 */
public class Difference1D {
    static int[] a; // a 数组为 b 数组的前缀和 -- 原数组
    static int[] b; // b 数组为 a 数组的差分 -- 差分数组

    /**
     * 输入一个长度为n的整数序列。接下来输入m个操作，每个操作包含三个整数l, r, c，表示将序列中[l, r]之间的每个数加上c。
     * 请你输出进行完所有操作后的序列。
     * <p>
     * 输入格式
     * 第一行包含两个整数n和m。
     * 第二行包含n个整数，表示整数序列。
     * 接下来m行，每行包含三个整数l，r，c，表示一个操作。
     * <p>
     * 输出格式
     * 共一行，包含n个整数，表示最终序列。
     * <p>
     * 数据范围
     * 1≤n,m≤100000,
     * 1≤l≤r≤n,
     * −1000≤c≤1000,
     * −1000≤整数序列中元素的值≤1000
     * <p>
     * 输入样例：
     * <p>
     * 6 3          <br>
     * 1 2 2 1 2 1          <br>
     * 1 3 1            <br>
     * 3 5 1            <br>
     * 1 6 1            <br>
     * <p>
     * 输出样例：
     * <p>
     * 3 4 5 3 4 2
     **/
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        a = new int[n + 1];
        b = new int[n + 2]; // 比原数组多长度多一
        for (int i = 1; i <= n; i++) {
            int c = scan.nextInt();
            insert(i, i, c);
        }
        System.out.println(Arrays.toString(b));

        // 执行 m 次 区间操作
        while (m-- > 0) {
            int l = scan.nextInt(), r = scan.nextInt(), c = scan.nextInt();
            insert(l, r, c);
        }
        for (int i = 1; i <= n; i++) {
            // 写法一
//            b[i] += b[i - 1]; // 将差分数组改为原数组
//            a[i] = b[i];
//            System.out.print(a[i] + " ");

            // 写法二
            a[i] = a[i - 1] + b[i]; // 前缀和（原）数组初始化
            System.out.print(a[i] + " ");
        }
    }

    /**
     * 将区间 [l, r] 上的每个数加上 v
     *
     * @param l 左区间 l
     * @param r 右区间 r
     * @param v 数 v
     */
    public static void insert(int l, int r, int v) {
        b[l] += v;
        b[r + 1] -= v;
    }

    /**
     * 例如存在一个二维数组 req，对于每一个 r=req[i]， 需要将 arr 数组中下标在 [r[0],r[1]] 间的元素都加上 r[2],
     * 那么此时差分数组就具备很好的使用场景，具体的代码模板如下：
     *
     * @param req {@link List}<{@link int[]}>
     * @param arr {@link int[]}
     */
    public void template(List<int[]> req, int[] arr) {
        int m = req.size(), n = arr.length;
        int[] diff = new int[n + 1];
        // 进行 m 次 区间修改操作
        for (int[] r : req) {
            int x = r[2];
            diff[r[0]] += x;
            diff[r[1] + 1] -= x;
        }
        arr[0] += diff[0];
        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
            arr[i] += diff[i];
        }
    }
}
