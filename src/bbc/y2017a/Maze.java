package bbc.y2017a;

public class Maze {
    static String[] strings = {
            "UDDLUULRUL",
            "UURLLLRRRU",
            "RRUURLDLRD",
            "RUDDDDUUUU",
            "URUDLLRRUU",
            "DURLRLDLRL",
            "ULLURLLRDU",
            "RDLULLRDDD",
            "UUDDUDUDLL",
            "ULRDLUURRR",
    };
    static char[][] maze;

    public static void main(String[] args) {
        maze = new char[10][10];
        for (int i = 0; i < 10; i++) {
            maze[i] = strings[i].toCharArray();
        }
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (check(i, j)) ans++;
            }
        }
        System.out.println(ans);
    }

    public static boolean check(int i, int j) {
        boolean[][] visited = new boolean[10][10];
        while (true) {
            if (i < 0 || i >= 10 || j < 0 || j >= 10) return true;
            if (visited[i][j]) return false;
            visited[i][j] = true;
            char c = maze[i][j];
            switch (c) {
                case 'U':
                    i--;
                    break;
                case 'D':
                    i++;
                    break;
                case 'L':
                    j--;
                    break;
                default:
                    j++;
                    break;
            }
        }
    }
}
