package offers;

/**
 * 剑指 Offer 66. 构建乘积数组
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 *
 *
 * 示例:
 *
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *
 * 链接：
 */
public class Offer66 {
    public static void main(String[] args) {

    }

    public static int[] constructArr(int[] a) {
        int[] b = new int[a.length];
        for (int i = 0, cur = 1; i < a.length; i++) {
            b[i] = cur;
            cur *= a[i];
        }
        for (int i = a.length - 1, cur = 1; i >= 0; i--) {
            b[i] *= cur;
            cur *= a[i];
        }
        return b;
    }

    public static int[] constructArrNo(int[] a) {
        int[] b = new int[a.length];
        int total = 1, zero = 0;
        for (int num : a)
            if (num != 0) total *= num;
            else zero++;
        if (zero > 1) return b;
        for (int i = 0; i < b.length; i++) {
            b[i] = zero > 0 ? (a[i] != 0 ? 0 : total) : total / a[i];
        }
        return b;
    }
}
