package problems;

public class Problem2024 {
    public static void main(String[] args) {
        System.out.println(maxConsecutiveAnswers("TTFF", 2));
        System.out.println(maxConsecutiveAnswers("TTFTTFTT", 1));
    }

    /**
     * 输入：answerKey = "TTFF", k = 2
     * 输出：4
     * 解释：我们可以将两个 'F' 都变为 'T' ，得到 answerKey = "TTTT" 。 总共有四个连续的 'T' 。
     **/
    public static int maxConsecutiveAnswers(String answerKey, int k) {
        char[] str = answerKey.toCharArray();
        return Math.max(helper(str, k, 'T'), helper(str, k, 'F'));
    }

    public static int helper(char[] str, int k, char t) {
        int ans = 0, n = str.length, l = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (str[i] == t) cnt++;
            while (cnt > k) {
                if (str[l] == t) cnt--;
                l++;
            }
            ans = Math.max(ans, i - l + 1);
        }
        return ans;
    }
}
