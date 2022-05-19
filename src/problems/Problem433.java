package problems;

import java.util.*;

public class Problem433 {
    public static void main(String[] args) {
        System.out.println(minMutationGraph("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));
        System.out.println(minMutationGraph("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"}));
        System.out.println(minMutationGraph("AAAAACCC", "AACCCCCC", new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"}));
    }

    public static int minMutationGraph(String start, String end, String[] bank) {
        if (start.equals(end)) return 0;
        List<Integer>[] adjacency = new ArrayList[bank.length]; // 邻接矩阵，建图
        boolean[] visited = new boolean[bank.length];
        for (int i = 0; i < bank.length; i++) adjacency[i] = new ArrayList<>();
        int endIdx = -1, step = 1;
        for (int i = 0; i < bank.length; i++) {
            if (start.equals(bank[i])) visited[i] = true;
            else {
                if (end.equals(bank[i])) endIdx = i;
                for (int j = i + 1, diff = 0; j < bank.length; j++, diff = 0) {
                    for (int k = 0; k < bank[i].length(); k++) {
                        if (bank[i].charAt(k) != bank[j].charAt(k)) diff++;
                    }
                    if (diff == 1) {
                        adjacency[i].add(j);
                        adjacency[j].add(i);
                    }
                }
            }
        }
        if (endIdx == -1) return -1;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0, diff = 0; i < bank.length; i++, diff = 0) {
            if (visited[i]) continue;
            for (int j = 0; j < start.length(); j++) {
                if (start.charAt(j) != bank[i].charAt(j)) diff++;
            }
            if (diff == 1) {
                if (i == endIdx) return 1;
                queue.offer(i);
                visited[i] = true;
            }
        }
        while (!queue.isEmpty()) {
            for (int i = 0, size = queue.size(); i < size; i++) {
                int curr = queue.poll();
                for (int next : adjacency[curr]) {
                    if (!visited[next]) {
                        if (next == endIdx) return step + 1;
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static int minMutationBfs(String start, String end, String[] bank) {
        char[] items = {'A', 'C', 'G', 'T'};
        Set<String> set = new HashSet<>(Arrays.asList(bank)), visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        int step = 0;
        while (!queue.isEmpty()) {
            for (int i = 0, size = queue.size(); i < size; i++) {
                String curr = queue.poll();
                for (int j = 0; j < curr.length(); j++) {
                    for (char item : items) {
                        if (item != curr.charAt(j)) {
                            StringBuilder sb = new StringBuilder(curr);
                            sb.setCharAt(j, item);
                            String next = sb.toString();
                            if (!visited.contains(next) && set.contains(next)) {
                                if (next.equals(end)) return step + 1;
                                queue.offer(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    int ans = Integer.MAX_VALUE;

    public int minMutation(String start, String end, String[] bank) {
        backtrack(start, end, bank, new boolean[bank.length], 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public void backtrack(String start, String end, String[] bank, boolean[] used, int t) {
        if (t >= ans) return;
        if (start.equals(end)) ans = t;
        else {
            for (int i = 0, diff = 0; i < bank.length; i++, diff = 0) {
                if (used[i]) continue;
                for (int j = 0; j < start.length(); j++)
                    diff += start.charAt(j) != bank[i].charAt(j) ? 1 : 0;
                if (diff == 1) {
                    used[i] = true;
                    backtrack(bank[i], end, bank, used, t + 1);
                    used[i] = false;
                }
            }
        }
    }
}
