package problems;

import java.util.ArrayList;
import java.util.List;

public class Problem728 {
    public static void main(String[] args) {
        System.out.println(selfDividingNumbers(1, 22));
        System.out.println(selfDividingNumbers(47, 85));
    }

    /**
     * 输入：left = 1, right = 22
     * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
     **/
    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int num = i, f = 1;
            while (num != 0) {
                int r = num % 10;
                if (r == 0 || i % r != 0) {
                    f = 0;
                    break;
                }
                num /= 10;
            }
            if (f == 1) ans.add(i);
        }
        return ans;
    }
}
