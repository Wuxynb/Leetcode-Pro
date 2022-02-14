package problems;

import java.util.ArrayList;
import java.util.List;

public class Problem89 {
    public static void main(String[] args) {
        System.out.println(grayCode(2));
    }

    /*

        n = 0    n = 1    n = 2    n = 3

          0        0        0|0     0|00
                   -        0|1     0|01
                   1        ---     0|11
                            1|1     0|10
                            1|0     ----
                                    1|10
                                    1|11
                                    1|01
                                    1|00

     */
    public static List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = ans.size() - 1; j >= 0; j--)
                ans.add(ans.get(j) + head);
            head <<= 1;
        }

        return ans;
    }
}
