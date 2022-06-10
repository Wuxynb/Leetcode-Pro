package ojs.poj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 描述
 * <p>
 * 亚瑟和他的妹妹卡罗尔玩了一段时间的游戏叫尼姆。尼姆的演奏方式如下：
 * <p>
 * 起始位置有许多堆，都包含一些珠子，但不一定相等。
 * <p>
 * 玩家轮流选择一堆珠子，并从中取出正数的珠子。
 * <p>
 * 第一个不能移动的玩家输了。
 * <p>
 * <p>
 * 亚瑟和卡罗尔真的很喜欢玩这个简单的游戏，直到他们
 * <p>
 * 最近学习了一种始终能够找到最佳动作的简单方法：
 * <p>
 * Xor当前位置堆中的珠子数（即，如果我们有2、4和7，则Xor和将为1，因为2 Xor 4 Xor 7=1）。
 * <p>
 * 如果异或和为0，太糟糕了，您将失败。
 * <p>
 * 否则，移动以使异或和变为0。这总是可能的。
 * <p>
 * <p>
 * 很容易让自己相信这是可行的。考虑以下事实：
 * <p>
 * 拿最后一颗珠子的玩家获胜。
 * <p>
 * 获胜玩家最后一步后，异或和将为0。
 * <p>
 * 每次移动后，异或和都会发生变化。
 * <p>
 * <p>
 * 这意味着，如果你确保在你采取行动时，异或和始终为0，你的对手将永远无法获胜，因此，你将获胜。
 * <p>
 * 可以理解的是，当两个玩家都知道如何完美地玩游戏时，玩游戏是没有乐趣的（无知就是幸福）。四是，亚瑟和卡罗尔很快想出了一个类似的游戏，S-Nim，似乎解决
 * 了这个问题。每个玩家现在只允许移除一些预定义集合S中的一些珠子，例如，如果我们有S={2，5}，则每个玩家只允许移除2或5个珠子。现在不可能总是将异或和
 * 设为0，因此，上述策略是无用的。还是这样？
 * <p>
 * 你的工作是编写一个程序，确定S-Nim的一个职位是输家还是赢家。如果至少有一次移动到一个失败的位置，那么一个位置就是一个胜利的位置。如果没有移动到一个
 * 失败的位置，那么一个位置就是一个失败的位置。这意味着，正如预期的那样，没有合法变动的头寸是亏损头寸。
 * <p>
 * Input
 * <p>
 * Input consists of a number of test cases.
 * <p>
 * For each test case: The first line contains a number k (0 < k ≤ 100) describing the size of S, followed by k numbers
 * si (0 < si ≤ 10000) describing S. The second line contains a number m (0 < m ≤ 100) describing the number of positions
 * to evaluate. The next m lines each contain a number l (0 < l ≤ 100) describing the number of heaps and l numbers
 * hi (0 ≤ hi ≤ 10000) describing the number of beads in the heaps.
 * <p>
 * The last test case is followed by a 0 on a line of its own.
 * <p>
 * Output
 * <p>
 * For each position: If the described position is a winning position print a 'W'.If the described position is a losing
 * position print an 'L'.
 * <p>
 * Print a newline after each test case.
 * <p>
 * Sample Input
 * <p>
 * 2 2 5
 * <p>
 * 3
 * <p>
 * 2 5 12
 * <p>
 * 3 2 4 7
 * <p>
 * 4 2 3 7 12
 * <p>
 * 5 1 2 3 4 5
 * <p>
 * 3
 * <p>
 * 2 5 12
 * <p>
 * 3 2 4 7
 * <p>
 * 4 2 3 7 12
 * <p>
 * 0
 * <p>
 * Sample Output
 * <p>
 * LWW
 * <p>
 * WWL
 */
public class P2960 {
    static int maxn = 10005;
    static int[] sg = new int[maxn];
    static int[] array = new int[105];
    static int t;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext() && (t = scan.nextInt()) != 0) {
            Arrays.fill(sg, -1); // 方案数不一样，sg值不一样，故在内层进行初始化，否则在外层进行
            for (int i = 0; i < t; i++) array[i] = scan.nextInt();
            int m = scan.nextInt();
            while (m-- > 0) {
                int h = scan.nextInt(), ans = 0;
                while (h-- > 0) {
                    ans ^= getSg(scan.nextInt());
                }
                if (ans == 0) System.out.print("L");
                else System.out.print("W");
            }
            System.out.println();
        }
    }

    public static int getSg(int x) {
        if (sg[x] != -1) return sg[x];
        boolean[] vis = new boolean[maxn];
        for (int i = 0; i < t; i++) {
            if (array[i] <= x) vis[sg[x - array[i]] = getSg(x - array[i])] = true;
        }
        for (int i = 0; ; i++) if (!vis[i]) return sg[x] = i;
    }
}
