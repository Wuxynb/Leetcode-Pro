package problems;

import java.util.Arrays;

public class Problem88 {
    public static void main(String[] args) {
        merge2(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3);
        merge2(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m-- + n-- - 1;
        while (m >= 0 || n >= 0) {
            if (m >= 0 && n >= 0) nums1[k--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
            else nums1[k--] = m >= 0 ? nums1[m--] : nums2[n--];
        }
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        for (int i = --m + --n + 1; i >= 0; i--) {
            if (m >= 0 && n >= 0) nums1[i] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
            else nums1[i] = m >= 0 ? nums1[m--] : nums2[n--];
        }
    }

    public static void merge_(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0, k = 0;
        int[] temps = new int[m + n];
        while (i < m || j < n) {
            if (i < m && j < n) temps[k++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
            else if (i < m) temps[k++] = nums1[i++];
            else temps[k++] = nums2[j++];
        }
        System.arraycopy(temps, 0, nums1, 0, temps.length);
    }
}
