package algorithms.ddp;

import java.util.ArrayList;
import java.util.List;

public class CountOnes {
    public static void main(String[] args) {

    }

    /**
     * 假设当前要讨论的数是 abcdefg，我们当前对 d 这一位的取值进行讨论，看在 d 的不同取值下，这一位（d位）是1的数有多少个。
     * <p>
     *   -若 d=0，则前三位的取值范围为 000∼abc−1，后三位的取值范围为 000∼999，这样合法的数就有 abc⋅1000 个
     *   <p>
     *   -若 d=1
     *   <p>
     *     -前三位取 000∼abc−1，后三位取 000∼999，这样合法的数就有 abc⋅1000 个
     *     <p>
     *     -前三位取 abc，后三位取 000∼efg，这样合法的数就有 efg+1 个
     *     <p>
     *   -若 d>1，则前三位取 000∼abc，后三位取 000∼999，这样合法的数有 (abc+1)⋅1000
     *   <p>
     * 每一位都像这样讨论，就可以算出来所有合法的数的个数。
     * <p>
     * 原文链接：https://blog.csdn.net/geraltofrivia123/article/details/123180845
     */
    static int countDigitOne(int n) {
        List<Integer> nums = new ArrayList<>();
        while (n > 0) { // 截取n的每一位
            nums.add(n % 10);
            n /= 10;
        }
        int res = 0;
        for (int i = nums.size() - 1; i >= 0; i--) {
            int cur = nums.get(i);
            int left = 0, right = 0, power = 1;
            for (int j = nums.size() - 1; j > i; j--) left = left * 10 + nums.get(j); // 统计左边
            for (int j = i - 1; j >= 0; j--) { // 统计右边
                right = right * 10 + nums.get(j);
                power *= 10;
            }
            if (cur == 0) res += left * power;
            else if (cur == 1) res += left * power + right + 1;
            else res += (left + 1) * power;
        }
        return res;
    }
}
