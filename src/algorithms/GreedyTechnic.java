package algorithms;

/**
 * 算法特征
 *
 * ❶ 随着算法的进行，将积累起其它两个集合：一个包含已经被考虑过并被选出的候选对象，另一个包含已经被考虑过但被丢弃的候选对象
 *
 * ❷ 选择函数可以指出哪一个剩余的候选对象最有希望构成问题的解
 *
 * ❸ 有一个函数来检查一个候选对象的集合是否提供了问题的解答
 *
 * ❹ 目标函数给出解的值
 *
 * ❺ 另一个函数检查是否一个候选对象的集合是可行的，即是否可能往该集合上添加更多的候选对象以获得一个解
 *
 * ❻ 为了解决问题，需要寻找一个构成解的候选对象集合，它可以优化目标函数，贪心算法一步一步的进行。起初，算法选出的候选对象的集合为空。
 * 接下来的每一步中，根据选择函数，算法从剩余候选对象中选出最有希望构成解的对象。
 */
public class GreedyTechnic {

    public static void main(String[] args) {
        System.out.println(changeMarking(new int[]{1, 5, 10, 20, 50, 100}, 637));
    }

    public static int changeMarking(int[] coins, int n) {
        int count = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            count += n / coins[i];
            n = n % coins[i];
            if (n == 0) return count;
        }

        return count;
    }
}
