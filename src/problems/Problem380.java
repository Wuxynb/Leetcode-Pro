package problems;

import java.util.*;

/**
 * 380. O(1) 时间插入、删除和获取随机元素
 * 实现RandomizedSet 类：
 *
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 *
 *
 *
 * 示例：
 *
 * 输入
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * 输出
 * [null, true, false, true, 2, true, false, 2]
 *
 * 解释
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
 * randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
 * randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
 * randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 *
 *
 * 提示：
 *
 * -231 <= val <= 231 - 1
 * 最多调用 insert、remove 和 getRandom 函数 2 * 10^5 次
 * 在调用 getRandom 方法时，数据结构中 至少存在一个 元素。
 *
 * link: https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 */
public class Problem380 {
    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1)); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
        System.out.println(randomizedSet.remove(2)); // 返回 false ，表示集合中不存在 2 。
        System.out.println(randomizedSet.insert(2)); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
        System.out.println(randomizedSet.getRandom()); // getRandom 应随机返回 1 或 2 。
        System.out.println(randomizedSet.remove(1)); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
        System.out.println(randomizedSet.insert(2)); // 2 已在集合中，所以返回 false 。
        System.out.println(randomizedSet.getRandom()); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
    }

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
    static class RandomizedSet {
        List<Integer> nums;
        Map<Integer, Integer> indexes;
        Random random;

        public RandomizedSet() {
            nums = new ArrayList<>();
            indexes = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (indexes.containsKey(val)) return false;
            nums.add(val);
            indexes.put(val, nums.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            if (!indexes.containsKey(val)) return false;
            int index = indexes.remove(val);
            nums.set(index, nums.get(nums.size() - 1));
            indexes.put(nums.get(nums.size() - 1), index);
            nums.set(nums.size() - 1, val);
            nums.remove(nums.size() - 1);
            indexes.remove(val);
            return true;
        }

        public int getRandom() {
            return nums.get(random.nextInt(nums.size()));
        }
    }
}
