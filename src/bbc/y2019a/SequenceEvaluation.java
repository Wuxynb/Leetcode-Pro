package bbc.y2019a;

import java.util.Arrays;

/**
 * 给定数列1, 1, 1, 3, 5, 9, 17, …，从第4 项开始，每项都是前3 项的和。求
 * 第20190324 项的最后4 位数字。
 */
public class SequenceEvaluation {
    public static void main(String[] args) {
        /*
         * 20190324 这个数过于庞大 无论是递归还是迭代 程序都无法正常运行， 而且int、long、BigDecimal等都无法、承载这么大的数
         * 题目要求我们只取最后四位，那我们可以对每次运算出来的结果 %10000 这样每次结果就都保留最后四位数字
         */
        int[] sequence = new int[20190324];
        Arrays.fill(sequence, 0, 3, 1);
        for (int i = 3; i < sequence.length; i++) {
            sequence[i] = (sequence[i - 1] + sequence[i - 2] + sequence[i - 3]) % 10000;
        }
        String str = String.valueOf(sequence[20190323]);
        System.out.println(str.substring(str.length() - 4));
    }
}
