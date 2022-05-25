package bbc.y2017a;

public class SquareSplit {
    static boolean[][] visited = new boolean[7][7];
    static int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int count;

    public static void main(String[] args) {
        visited[3][3] = true;
        backtrack(3, 3);
        System.out.println((count / 4));
    }

    public static void backtrack(int i, int j) {
        if (i <= 0 || j <= 0 || i >= 6 || j >= 6) {
            count++;
            return;
        }
        for (int[] direction : directions) {
            int x = i + direction[0], y = j + direction[1];
            if (!visited[x][y] && !visited[6 - x][6 - y]) {
                visited[x][y] = visited[6 - x][6 - y] = true;
                backtrack(x, y);
                visited[x][y] = visited[6 - x][6 - y] = false;
            }
        }
    }
}
