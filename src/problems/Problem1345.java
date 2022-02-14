package problems;

import java.util.*;

/**
 * 1345. 跳跃游戏 IV
 * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
 *
 * 每一步，你可以从下标 i 跳到下标：
 *
 * i + 1 满足：i + 1 < arr.length
 * i - 1 满足：i - 1 >= 0
 * j 满足：arr[i] == arr[j] 且 i != j
 * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
 *
 * 注意：任何时候你都不能跳到数组外面。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
 * 输出：3
 * 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
 * 示例 2：
 *
 * 输入：arr = [7]
 * 输出：0
 * 解释：一开始就在最后一个元素处，所以你不需要跳跃。
 * 示例 3：
 *
 * 输入：arr = [7,6,9,6,9,6,9,7]
 * 输出：1
 * 解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
 * 示例 4：
 *
 * 输入：arr = [6,1,9]
 * 输出：2
 * 示例 5：
 *
 * 输入：arr = [11,22,7,7,7,7,7,7,7,22,13]
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 *
 * link: https://leetcode-cn.com/problems/jump-game-iv/
 */
public class Problem1345 {
    public static void main(String[] args) {
//        System.out.println(minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}));
//        System.out.println(minJumps(new int[]{100,15,25,23,100,45,23,23,23}));
        System.out.println(minJumps(new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2}));
    }

    public int minJumpsAnswer(int[] arr) {
        Map<Integer, List<Integer>> idxSameValue = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            idxSameValue.putIfAbsent(arr[i], new ArrayList<>());
            idxSameValue.get(arr[i]).add(i);
        }
        Set<Integer> visitedIndex = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>(); // {node, step}
        queue.offer(new int[]{0, 0});
        visitedIndex.add(0);
        while (!queue.isEmpty()) {
            // todo:
            //  队列元素为 {node, step}，故不需要在此遍历完此时队列中的所有元素，因为此时有step记录第几步:
            //  否则，需要在此遍历完队列中所有元素并记录其属于第几步，即step。
            int[] idxStep = queue.poll();
            int idx = idxStep[0], step = idxStep[1];
            if (idx == arr.length - 1) {
                return step;
            }
            int v = arr[idx];
            step++;
            if (idxSameValue.containsKey(v)) {
                for (int i : idxSameValue.get(v)) {
                    if (visitedIndex.add(i)) {
                        queue.offer(new int[]{i, step});
                    }
                }
                idxSameValue.remove(v);
            }
            if (idx + 1 < arr.length && visitedIndex.add(idx + 1)) {
                queue.offer(new int[]{idx + 1, step});
            }
            if (idx - 1 >= 0 && visitedIndex.add(idx - 1)) {
                queue.offer(new int[]{idx - 1, step});
            }
        }
        return -1;
    }

    public static int minJumps(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && i < arr.length - 1 && arr[i - 1] == arr[i] && arr[i + 1] == arr[i]) continue;
            if (i > 1 && i < arr.length - 2 && arr[i - 2] == arr[i] && arr[i + 2] == arr[i] && arr[i - 1] == arr[i + 1]) continue;
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }
        boolean[] visited = new boolean[arr.length];
        int step = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int idx = queue.poll();
                if (idx == arr.length - 1) return step;
                // 向后走
                if (idx + 1 < arr.length && !visited[idx + 1]) {
                    if (idx + 1 == arr.length - 1) return step + 1;
                    queue.offer(idx + 1);
                    visited[idx + 1] = true;
                }
                // 向前走
                if (idx - 1 >= 0 && !visited[idx - 1]) {
                    queue.offer(idx - 1);
                    visited[idx - 1] = true;
                }
                // 向相同的元素走
                if (map.containsKey(arr[idx])) {
                    for (int j : map.get(arr[idx])) {
                        if (!visited[j]) {
                            if (j == arr.length - 1) return step + 1;
                            queue.offer(j);
                            visited[j] = true;
                        }
                    }
                    map.remove(arr[idx]);
                }
            }
            step++;
        }
        return step;
    }

    public static int minJumpsSlow(int[] arr) {
        if (arr.length < 3) return arr.length - 1;
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && i < arr.length - 1) {
                if (arr[i - 1] == arr[i] && arr[i + 1] == arr[i]) continue;
            }
            if (i > 1 && i < arr.length - 2) {
                if (arr[i - 2] == arr[i] && arr[i + 2] == arr[i] && arr[i - 1] == arr[i + 1]) continue;
            }
            array.add(arr[i]);
        }

        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer>[] adjacency = new List[array.size()];
        boolean[] visited = new boolean[array.size()];
        for (int i = 0; i < array.size(); i++) {
            List<Integer> list = map.getOrDefault(array.get(i), new ArrayList<>());
            list.add(i);
            map.put(array.get(i), list);
        }
        for (int i = 0; i < array.size(); i++) {
            Set<Integer> set = new HashSet<>();
            if (i - 1 >= 0) set.add(i - 1);
            if (i + 1 < array.size()) set.add(i + 1);
            for (int j : map.get(array.get(i))) {
                if (i != j) set.add(j);
            }
            adjacency[i] = new ArrayList<>(set);
        }
        queue.offer(0);
        visited[0] = true;
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size && !visited[array.size() - 1]; i++) {
                int idx = queue.poll();
                for (int node : adjacency[idx]) {
                    if (!visited[node]) {
                        visited[node] = true;
                        if (node == array.size() - 1) break;
                        queue.offer(node);
                    }
                }
            }
            ans++;
            if (visited[array.size() - 1]) break;
        }
        return ans;
    }
}
