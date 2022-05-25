package ojs.luogu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), k = input.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = input.nextInt();
        System.out.println(quickSelect(0, n - 1, k, array));

        input.close();
    }

    public static int quickSelect(int low, int high, int k, int[] array) {
        if (low == high) return array[low];
        int i = low - 1, j = high + 1, m = array[i + j >> 1];
        while (i < j) {
            while (i < j && array[--j] > m);
            while (i < j && array[++i] < m);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        int cntl = j - low + 1;
        if (cntl >= k) return quickSelect(low, j, k, array);
        else return quickSelect(j + 1, high, k - cntl, array);
    }
}