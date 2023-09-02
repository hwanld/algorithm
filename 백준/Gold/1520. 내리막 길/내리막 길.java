import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    int answer = 0;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int m;
    static int n;
    int[][] map;
    int[][] dp;

    public void solution() {
        m = sc.nextInt();
        n = sc.nextInt();

        map = new int[m + 1][n + 1];
        dp = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0, 0));
    }

    public int dfs(int x, int y) {
        if (x == m - 1 && y == n - 1)
            return 1;
        if (dp[x][y] != -1)
            return dp[x][y];

        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int curX = x + dx[i];
            int curY = y + dy[i];
            if (curX < 0 || curY < 0 || curX > m-1 || curY > n-1) continue;
            if (map[curX][curY] >= map[x][y] ) continue;
            dp[x][y] += dfs(curX, curY);
        }
        return dp[x][y];
    }

    public static void main(String[] args) {
        new Main().solution();
    }
}