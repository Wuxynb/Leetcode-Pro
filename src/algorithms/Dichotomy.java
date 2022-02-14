package algorithms;

/**
 * 二分查找
 *
 * while循环的条件中left<=right和left<right的确定由问题本身决定，
 * 如果查询元素一定存在搜索范围内，选择left<right可以夹出唯一的位置；
 * 而left<=right能将搜索区间范围一步步缩小，一直到区间为空才会退出。
 * 二分初始区间应该覆盖到所有可能返回的结果。
 *
 *
 * 情况一
 * 如果搜索区间[left, right]中一定有目标值索引，那么循环截止条件是while(left < right)，因为当left == right时目标索引就是left或者right，
 * 也就是说1中讨论的情况一和情况二循环终止条件都是while(left < right)，甚至情况三时，如果我们将搜索区间扩充为[-1, nums.length]，循环终止条件
 * 也是while(left < right)；
 *
 * 情况二
 * 如果搜索区间[left, right]中不一定有目标值索引，那么循环截止条件是while(left <= right)；（一般用于搜索区间内是否有某个值）
 *
 */
public class Dichotomy { // [daɪˈkɑːtəmi]
    public static void main(String[] args) {
        System.out.println(halfSearch(new int[]{1, 2, 3, 4, 6, 8}, 3));
        System.out.println(halfSearch_(new int[]{1, 2, 3, 4, 6, 8}, 3));
    }

    /*
        1. 为什么 while 循环的条件中是 <=，而不是 < ？
        答：因为初始化 right 的赋值是 nums.length - 1，即最后一个元素的索引，而不是 nums.length。

        这二者可能出现在不同功能的二分查找中，区别是：前者相当于两端都闭区间 [left, right]，
        后者相当于左闭右开区间 [left, right)，因为索引大小为 nums.length 是越界的。

        我们这个算法中使用的是 [left, right] 两端都闭的区间。这个区间就是每次进行搜索的区间，我们不妨称为「搜索区间」(search space)。
     */
    public static boolean halfSearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) { // 终止条件：low > high (low = high + 1)
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) low  = mid + 1;
            else if (nums[mid] == target) return true;
            else high = mid - 1;
        }

        return false;
    }

    public static boolean halfSearch_(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) { // 终止条件：low == high
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) low  = mid + 1;
            else if (nums[mid] == target) return true;
            else high = mid - 1;
        }

        return nums[low] == target;
    }
}
