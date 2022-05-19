package problems;

import java.util.*;

public class Problem691 {
    public static void main(String[] args) {
        System.out.println(minStickers(new String[]{"with", "example", "science"}, "thehat"));
        System.out.println(minStickers(new String[]{"notice", "possible"}, "basicbasic"));
        System.out.println(minStickers(new String[]{"these", "guess", "about", "garden", "him"}, "atomher"));

        System.out.println(solve(new String[]{"with", "example", "science"}, "thehat"));
        System.out.println(solve(new String[]{"notice", "possible"}, "basicbasic"));
        System.out.println(solve(new String[]{"these", "guess", "about", "garden", "him"}, "atomher"));
    }

    static int ans = Integer.MAX_VALUE;

    /**
     * 输入： stickers = ["with","example","science"], target = "thehat"
     * <p>
     * 输出：3
     * <p>
     * 解释：我们可以使用 2 个 "with" 贴纸，和 1 个 "example" 贴纸。 把贴纸上的字母剪下来并重新排列后，就可以形成目标 “thehat“ 了。
     * 此外，这是形成目标字符串所需的最小贴纸数量。
     */
    public static int minStickers(String[] stickers, String target) {
        int n = target.length(), m = 1 << n;
        char[] ts = target.toCharArray();
        int[] dp = new int[m];
        Arrays.fill(dp, 16);
        dp[0] = 0;
        for (String sticker : stickers) { // 遍历每一个单词
            for (int i = 0; i < m; i++) { // 遍历每一种状态
                if (dp[i] == 16) continue; // 如果当前状态i没有解时，跳过
                int curState = i;
                for (char c : sticker.toCharArray()) { // 遍历sticker的每一个字符
                    for (int j = 0; j < n; j++) { // 遍历target的每一位
                        if (c == ts[j] && (curState & (1 << j)) == 0) { // 当前第j位没有字符时，选择c字符然后结束
                            curState |= (1 << j);
                            break;
                        }
                    }
                }
                dp[curState] = Math.min(dp[curState], dp[i] + 1);
            }
        }
        return dp[m - 1] == 16 ? -1 : dp[m - 1];
    }

    public static int minStickersBacktrack(String[] stickers, String target) {
        ans = Integer.MAX_VALUE;
        List<Character> list = new LinkedList<>();
        for (char c : target.toCharArray()) list.add(c);
        backtrack(stickers, list, 0, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void backtrack(String[] stickers, List<Character> target, int t, int idx) {
        if (t >= ans) return;
        if (target.size() == 0) ans = t;
        else {
            for (int i = idx; i < stickers.length; i++) {
                List<Character> list = new ArrayList<>();
                for (char c : stickers[i].toCharArray()) {
                    if (target.remove(new Character(c))) list.add(c);
                }
                if (list.size() > 0) {
                    backtrack(stickers, target, t + 1, i);
                    target.addAll(list);
                } else backtrack(stickers, target, t, i + 1);
            }
        }
    }

    public static int solve(String[] stickers, String target) {
        int n = target.length();
        int[] memo = new int[1 << n];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        int res = dfs(stickers, target.toCharArray(), memo, (1 << n) - 1);
        return res <= n ? res : -1;
    }

    public static int dfs(String[] stickers, char[] target, int[] memo, int state) {
        if (memo[state] < 0) {
            int res = target.length + 1;
            for (String sticker : stickers) {
                int curState = state;
                for (char c : sticker.toCharArray()) {
                    for (int i = 0; i < target.length; i++) {
                        if (c == target[i] && ((curState >> i) & 1) == 1) {
                            curState ^= (1 << i);
                            break;
                        }
                    }
                }
                if (curState < state)
                    res = Math.min(res, dfs(stickers, target, memo, curState) + 1);
            }
            memo[state] = res;
        }
        return memo[state];
    }

    public static int minStickersOps(String[] stickers, String target) {
        int n = target.length(), m = 1 << n;
        char[] ts = target.toCharArray();
        int[] dp = new int[m];
        Arrays.fill(dp, 16);
        dp[0] = 0;
        for (String sticker : stickers) { // 遍历每一个单词
            for (int i = 0; i < m; i++) { // 遍历每一种状态
                if (dp[i] == 16) continue; // 如果当前状态i没有解时，跳过
                int curState = i;
                int[] cnts = new int[26];
                for (char c : sticker.toCharArray()) cnts[c - 'a']++;
                for (int j = 0; j < n; j++) { // 遍历target的每一位
                    if (cnts[ts[j] - 'a'] > 0 && (curState & (1 << j)) == 0) { // 当前第j位没有字符时，选择c字符然后结束
                        cnts[ts[j] - 'a']--;
                        curState |= (1 << j);
                    }
                }
                dp[curState] = Math.min(dp[curState], dp[i] + 1);
            }
        }
        return dp[m - 1] == 16 ? -1 : dp[m - 1];
    }
}
