package problems;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 */
public class Problem933 {
    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        System.out.println(recentCounter.ping(1)); // requests = [1]，范围是 [-2999,1]，返回 1
        System.out.println(recentCounter.ping(100)); // requests = [1, 100]，范围是 [-2900,100]，返回 2
        System.out.println(recentCounter.ping(3001)); // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
        System.out.println(recentCounter.ping(3002)); // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
    }

    /**
     * Your RecentCounter object will be instantiated and called as such:
     * RecentCounter obj = new RecentCounter();
     * int param_1 = obj.ping(t);
     */
    static class RecentCounter {
        Queue<Integer> queue;

        public RecentCounter() {
            queue = new ArrayDeque<>();
        }

        public int ping(int t) {
            while (!queue.isEmpty() && queue.peek() < t - 3000) {
                queue.poll();
            }
            queue.offer(t);
            return queue.size();
        }
    }
}
