package problems;

/**
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 *
 *
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix/
 */
public class Problem74 {
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1, 3, 5,  7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
        System.out.println(searchMatrix(new int[][]{{1, 3, 5,  7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
        System.out.println(searchMatrix(new int[][]{{1, 3, 5,  7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 11));
        System.out.println(searchMatrix(new int[][]{{0}, {1}}, 2));
        System.out.println(searchMatrix(new int[][]{{1}, {3}}, 3));
    }

    /*
        1. 为什么 while 循环的条件中是 <=，而不是 < ？
        答：因为初始化 right 的赋值是 nums.length - 1，即最后一个元素的索引，而不是 nums.length。

        这二者可能出现在不同功能的二分查找中，区别是：前者相当于两端都闭区间 [left, right] （一般用于搜索区间内是否有某个值），


        后者相当于左闭右开区间 [left, right)，因为索引大小为 nums.length 是越界的。
        我们这个算法中使用的是 [left, right] 两端都闭的区间。这个区间就是每次进行搜索的区间，我们不妨称为「搜索区间」(search space)。
     */
    public static boolean searchMatrix_(int[][] matrix, int target) { // 时间复杂度：O(log(m) + log(n)) = O(log(m*n))
        int l1 = 0, r1 = matrix.length - 1, row = 0;
        while (l1 <= r1) {
            int mid = l1 + (r1 - l1) / 2;
            if (matrix[mid][0] < target) { // mid对应值比target小
                row = mid; // 记录mid为row，始终更新为最新的小于target的行数
                l1 = mid + 1;
            } else if (matrix[mid][0] == target) return true;
            else r1 = mid - 1;
        }

        int l2 = 0, r2 = matrix[0].length - 1;
        while (l2 <= r2) {
            int mid = l2 + (r2 - l2) / 2;
            if (matrix[row][mid] < target)  l2 = mid + 1;
            else if (matrix[row][mid] == target) return true;
            else r2 = mid - 1;
        }

        return false;
    }

    public static  boolean searchMatrix(int[][] matrix, int target) {
        int low = 0, high = matrix.length * matrix[0].length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int row = mid / matrix[0].length, col = mid % matrix[0].length;
            if (matrix[row][col] < target) low = mid + 1;
            else if (matrix[row][col] == target) return true;
            else high = mid - 1;
        }

        return false;
    }
}
