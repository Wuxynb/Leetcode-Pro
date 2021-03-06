package bbc.y2019a;

import java.util.ArrayDeque;
import java.util.Queue;

public class Maze {
    private static final String[] strings = {
            "01010101001011001001010110010110100100001000101010",
            "00001000100000101010010000100000001001100110100101",
            "01111011010010001000001101001011100011000000010000",
            "01000000001010100011010000101000001010101011001011",
            "00011111000000101000010010100010100000101100000000",
            "11001000110101000010101100011010011010101011110111",
            "00011011010101001001001010000001000101001110000000",
            "10100000101000100110101010111110011000010000111010",
            "00111000001010100001100010000001000101001100001001",
            "11000110100001110010001001010101010101010001101000",
            "00010000100100000101001010101110100010101010000101",
            "11100100101001001000010000010101010100100100010100",
            "00000010000000101011001111010001100000101010100011",
            "10101010011100001000011000010110011110110100001000",
            "10101010100001101010100101000010100000111011101001",
            "10000000101100010000101100101101001011100000000100",
            "10101001000000010100100001000100000100011110101001",
            "00101001010101101001010100011010101101110000110101",
            "11001010000100001100000010100101000001000111000010",
            "00001000110000110101101000000100101001001000011101",
            "10100101000101000000001110110010110101101010100001",
            "00101000010000110101010000100010001001000100010101",
            "10100001000110010001000010101001010101011111010010",
            "00000100101000000110010100101001000001000000000010",
            "11010000001001110111001001000011101001011011101000",
            "00000110100010001000100000001000011101000000110011",
            "10101000101000100010001111100010101001010000001000",
            "10000010100101001010110000000100101010001011101000",
            "00111100001000010000000110111000000001000000001011",
            "10000001100111010111010001000110111010101101111000"};

    /*static String[] strings = {
            "010000",
            "000100",
            "001001",
            "110000"};*/

    public static void main(String[] args) {
        int m = strings.length, n = strings[0].length(), base = 131;
        char[][] maze = new char[m][n];
        for (int i = 0; i < m; i++) {
            maze[i] = strings[i].toCharArray();
        }
        char[][] cache = new char[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] directions = new int[][]{{1, 0, 'D'}, {0, -1, 'L'}, {0, 1, 'R'}, {-1, 0, 'U'}};
        visited[0][0] = true;
        queue.offer(new int[]{0, 0}); // "x->y"
        int i = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            i++;
            for (int j = 0; j < size; j++) {
                int[] curr = queue.poll();
                for (int[] direction : directions) {
                    int x = curr[0] + direction[0], y = curr[1] + direction[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == '0' && !visited[x][y]) {
                        queue.offer(new int[]{x, y});
                        cache[x][y] = (char) direction[2];
                        visited[x][y] = true;
                        if (x == m - 1 && y == n - 1) break;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int x = m - 1, y = n - 1;
        while (x != 0 || y != 0) {
            char c = cache[x][y];
            switch (c) {
                case 'D':
                    x--;
                    break;
                case 'L':
                    y++;
                    break;
                case 'R':
                    y--;
                    break;
                default:
                    x++;
                    break;
            }
            sb.append(c);
        }
        System.out.println(sb.reverse());
    }
}
