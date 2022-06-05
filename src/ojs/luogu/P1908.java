package ojs.luogu;

import java.util.Scanner;

public class P1908 {
    static long ans = 0; // 答案会超出 int 范围

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = scan.nextInt();
        mergeSort(array, 0, n - 1);
        System.out.println(ans);
    }

    public static void mergeSort(int[] nums, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(nums, l, m);
            mergeSort(nums, m + 1, r);
            merge(nums, l, m, r);
        }
    }

    public static void merge(int[] nums, int l, int m, int r) {
        int i = l, j = m + 1, t = 0;
        int[] temp = new int[r - l + 1];
        while (i <= m && j <= r) {
            if (nums[i] <= nums[j]) {
                temp[t++] = nums[i++];
            } else {
                ans += (m - i + 1);
                temp[t++] = nums[j++];
            }
        }
        while (i <= m) temp[t++] = nums[i++];
        while (j <= r) temp[t++] = nums[j++];
        for (int k = 0; k < temp.length; k++) nums[l + k] = temp[k];
    }
}
