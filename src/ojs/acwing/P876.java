package ojs.acwing;

import java.util.Scanner;

/**
 * 给定n组ai,pi，其中pi是质数，求ai模pi的乘法逆元，若逆元不存在则输出 impossible。
 * <p>
 * 注意：请返回在0~p-1之间的逆元。
 * <p>
 * 乘法逆元的定义：
 * <p>
 * 若整数 b,m 互质，并且对于任意的整数 a，如果满足 b|a，则存在一个整数 x，使得 a/b ≡ a * x （mode m），则
 * 称 x 为 b 的模 m 乘法逆元，记为 b^-1 (mod m)。
 * b存在乘法逆元的充要条件是 b 与模数 m 互质，当模数 m 为质数时，b^(m-2) 即为 b 的乘法逆元。
 * <p>
 * 输入格式
 * <p>
 * 第一行包含整数 n。
 * <p>
 * 接下来n行，每行包含一个数组ai,pi，数据保证pi是质数。
 * <p>
 * 输出格式
 * <p>
 * 输出共n行，每组数据书橱一个结果，每个结果占一行。
 * <p>
 * 若ai模pi的乘法逆元存在，则输出一个整数，表示逆元，否则输出 impossible。
 * <p>
 * 数据范围
 * <p>
 * 1 <= n <= 10^5
 * 1 <= ai,pi <= 2*10^9
 * <p>
 * 输入样例：
 * <p>
 * 3
 * <p>
 * 4 3
 * <p>
 * 8 5
 * <p>
 * 6 3
 * <p>
 * <p>
 * 输出样例
 * <p>
 * 1
 * <p>
 * 2
 * <p>
 * impossible
 */
public class P876 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n-- > 0) {
            int a = scan.nextInt(), p = scan.nextInt();
            if (a % p > 0) { // p已经是质数了，那么如果a还不能整除p，就说明它们互质
                System.out.println(quickMi(a, p - 2, p));
            } else System.out.println("impossible");
        }
    }

    public static long quickMi(long a, long b, long c) {
        long res = 1;
        while (b != 0) {
            if ((b & 1) == 1) res = res * a % c;
            a = a * a % c;
            b >>= 1;
        }
        return res;
    }
}
