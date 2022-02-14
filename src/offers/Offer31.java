package offers;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 31. 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *
 * 链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 */
public class Offer31 {
    public static void main(String[] args) {

    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        int j = 0, top = -1;
        int[] stack = new int[pushed.length];
        for (int num : pushed) {
            stack[++top] = num;
            while (j < popped.length && top != -1 && stack[top] == popped[j]) {
                j++;
                top--;
            }
        }
        return j == popped.length;
    }

    public static boolean validateStackSequencesStack(int[] pushed, int[] popped) {
        int j = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num : pushed) {
            stack.push(num);
            while (j < popped.length && !stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return j == popped.length;
    }
}
