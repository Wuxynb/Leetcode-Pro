package ojs.poj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NOI0105_40_ {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(dp(scan.nextInt()));
    }

    static int dp(int n) {
        if (n == 0) return 0;
        List<Integer> nums = new ArrayList<>();
        while (n != 0) {
            nums.add(n % 10);
            n /= 10;
        }
        int res = 0;
        for (int i = nums.size() - 1; i >= 0; i--) {
            int cur = nums.get(i);
            int left = 0, right = 0, power = 1;
            for (int j = nums.size() - 1; j > i; j--) left = left * 10 + nums.get(j);
            for (int j = i - 1; j >= 0; j--) {
                right = right * 10 + nums.get(j);
                power *= 10;
            }
            // 计算【当前位】取1的合法个数
            if (cur == 0) res += left * power;
            else if (cur == 1) res += left * power + right + 1;
            else res += (left + 1) * power;
        }
        return res;
    }
}
