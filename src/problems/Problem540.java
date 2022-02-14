package problems;

/**
 * @author Wuxinyue
 * @version 1.0
 * @date 2022/2/14 12:13
 */
public class Problem540 {
    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 2, 3, 3, 4, 8, 8}));
        System.out.println(singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));

        System.out.println(singleNonDuplicateAns(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(singleNonDuplicateAns(new int[]{1, 1, 2, 2, 3, 3, 4, 8, 8}));
        System.out.println(singleNonDuplicateAns(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }

    public static int singleNonDuplicateAns(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = ((right - left) >> 1) + left;
            if ((mid & 1) == 1) mid--;
            if (nums[mid] == nums[mid + 1]) left = mid + 2;
            else right = mid;
        }
        return nums[left];
    }

    // 1 1 2 3 3 4 4 8 8
    // 1 1 2 2 3 3 4 8 8
    public static int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid - 1] == nums[mid]) {
                if (((mid - 1) & 1) == 1) right = mid - 2;
                else left = mid + 1;
            } else if (nums[mid] == nums[mid + 1]) {
                if ((mid & 1) == 1) right = mid - 1;
                else left = mid + 2;
            } else return nums[mid];
        }
        return nums[left];
    }
}
