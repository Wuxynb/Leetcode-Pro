package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 1996. 游戏中弱角色的数量
 * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
 *
 * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。
 *
 * 返回 弱角色 的数量。
 *
 *
 *
 * 示例 1：
 *
 * 输入：properties = [[5,5],[6,3],[3,6]]
 * 输出：0
 * 解释：不存在攻击和防御都严格高于其他角色的角色。
 * 示例 2：
 *
 * 输入：properties = [[2,2],[3,3]]
 * 输出：1
 * 解释：第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * 示例 3：
 *
 * 输入：properties = [[1,5],[10,4],[4,3]]
 * 输出：1
 * 解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 *
 *
 * 提示：
 *
 * 2 <= properties.length <= 105
 * properties[i].length == 2
 * 1 <= attacki, defensei <= 105
 *
 * link: https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game/
 *
 * @author Wuxinyue
 * @version 1.0
 * @date 2022/1/28 16:46
 */
public class Problem1996 {
    public static void main(String[] args) {
        System.out.println("\n" + numberOfWeakCharacters(new int[][]{{5, 5}, {6, 3}, {3, 6}}));
        System.out.println("\n" + numberOfWeakCharacters(new int[][]{{2, 2}, {3, 3}}));
        System.out.println("\n" + numberOfWeakCharacters(new int[][]{{1, 5}, {10, 4}, {4, 3}}));
        System.out.println("\n" + numberOfWeakCharacters(new int[][]{{1, 1}, {2, 1}, {2, 2}, {1, 2}}));
        System.out.println("\n" + numberOfWeakCharacters(new int[][]{{1, 1}, {3, 3}, {2, 2}, {1, 2}}));
        System.out.println("\n" + numberOfWeakCharacters(new int[][]{{7, 7}, {1, 2}, {9, 7}, {7, 3}, {3, 10}, {9, 8}, {8, 10}, {4, 3}, {1, 5}, {1, 5}}));
        // [[7,9],[10,7],[6,9],[10,4],[7,5],[7,10]]
        System.out.println("\n" + numberOfWeakCharacters(new int[][]{{7, 9}, {10, 7}, {6, 9}, {10, 4}, {7, 5}, {7, 10}}));
    }

    public static int numberOfWeakCharacters(int[][] properties) {
        int ans = 0, maxDef = 0;
        Arrays.sort(properties, (x, y) -> (x[0] == y[0] ? x[1] - y[1] : y[0] - x[0]));
        for (int[] property : properties) {
            System.out.print(Arrays.toString(property));
            if (property[1] < maxDef) ans++;
            else maxDef = property[1];
        }
        return ans;
    }

    public int numberOfWeakCharactersStack(int[][] properties) {
        int ans = 0, top = -1;
        int[] stack = new int[properties.length];
        Arrays.sort(properties, (x, y) -> (x[0] == y[0] ? y[1] - x[1] : x[0] - y[0]));
        for (int[] property : properties) {
            while (top != -1 && stack[top] < property[1]) {
                --top;
                ans++;
            }
            stack[++top] = property[1];
        }
        return ans;
    }

    public static int numberOfWeakCharactersStack_(int[][] properties) {
        int ans = 0;
        Arrays.sort(properties, (x, y) -> (x[0] == y[0] ? y[1] - x[1] : x[0] - y[0]));
        Stack<Integer> stack = new Stack<>();
        for (int[] property : properties) {
            while (!stack.isEmpty() && stack.peek() < property[1]) {
                stack.pop();
                ans++;
            }
            stack.push(property[1]);
        }
        return ans;
    }
}
