package offers;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 剑指 Offer 59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *
 * 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/
 */
public class Offer59_II {
    public static void main(String[] args) {

    }

    class MaxQueue {
        PriorityQueue<Integer> pq;
        Queue<Integer> queue;
        public MaxQueue() {
            queue = new LinkedList<>();
            pq = new PriorityQueue<>((x, y) -> (y - x));
        }

        public int max_value() {
            return pq.peek();
        }

        public void push_back(int value) {
            queue.offer(value);
            pq.offer(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) return -1;
            int x = queue.poll();
            pq.remove(x);
            return x;
        }
    }
}
