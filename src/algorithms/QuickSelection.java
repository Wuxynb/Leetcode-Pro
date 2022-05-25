package algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

public class QuickSelection {
    public static void main(String[] args) {
        System.out.println(quickSelection(0, 8, 5, new Integer[]{8, 3, 2, 9, 7, 1, 5, 4, 6}));
        System.out.println(quickSelection(0, 2, 2, new Integer[]{1, 3, 2}));

        System.out.println(QuickSelect(new int[]{8, 3, 2, 9, 7, 1, 5, 4, 6}, 0, 8, 5));
        System.out.println(QuickSelect(new int[]{1, 3, 2}, 0, 2, 2));

        System.out.println(theKthNum(new Integer[]{8, 3, 2, 9, 7, 1, 5, 4, 6}, 5));
        System.out.println(theKthNum(new Integer[]{1, 3, 2}, 2));
    }

    public static <T extends Comparable<T>> T theKthNum(T[] arr, int k) {
        PriorityQueue<T> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (T a : arr) {
            pq.offer(a);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }

    // 8, 3, 2, 9, 7, 1, 5, 4, 6
    public static <T extends Comparable<T>> T quickSelection(int low, int high, int k, T[] array) {
        if (low == high) return array[low];
        int i = low - 1, j = high + 1;
        T mid = array[i + j >> 1];
        while (i < j) {
            while (i < j && array[--j].compareTo(mid) > 0) ;
            while (i < j && array[++i].compareTo(mid) < 0) ;
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        int cntl = j - low + 1;
        if (cntl >= k) return quickSelection(low, j, k, array); // 往左边找
        else return quickSelection(j + 1, high, k - cntl, array);
    }

    public static int QuickSelect(int[] array, int left, int right, int k) {
        int s = LomutoPartition(left, right, array);
        if (s == left + k - 1) return array[s];
        else if (s > left + k - 1) return QuickSelect(array, left, s - 1, k);
        else return QuickSelect(array, s + 1, right, k - (s - left + 1));
    }

    private static int LomutoPartition(int left, int right, int[] array) {
        int pivot = array[left], s = left;
        for (int i = left + 1; i <= right; i++) {
            if (array[i] < pivot) {
                s += 1;
                int temp = array[i];
                array[i] = array[s];
                array[s] = temp;
            }
        }
        int temp = array[left];
        array[left] = array[s];
        array[s] = temp;
        return s;
    }
}
