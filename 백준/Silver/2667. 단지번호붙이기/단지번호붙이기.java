import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int[][] map, visited;
    static List<Integer> answer = new ArrayList<>();
    final int[] dx = {-1,0,1,0};
    final int[] dy = {0,-1,0,1};

    public void solution() {
        n = sc.nextInt();
        map = new int[n][n];
        visited = new int[n][n];

        for (int i=0; i<n; i++) {
            String str = sc.next();
            for (int j=0; j<n; j++) {
                char ch = str.charAt(j);
                map[i][j] = ch - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isValid(i, j)) {
                    int result = bfs(i,j);
                    if (result != 0)
                        answer.add(result);
                }
            }
        }

        System.out.println(answer.size());
        answer.sort(Comparator.naturalOrder());
        answer.stream()
                .forEach(each -> System.out.println(each));
    }

    boolean isValid(int x, int y) {
        if (x < 0 || y < 0) return false;
        if (x >= n || y >= n) return false;
        if (map[x][y] == 0) return false;
        if (visited[x][y] == 1) return false;
        return true;
    }

    int bfs(int x, int y) {
        visited[x][y] = 1;
        int answer = 0;
        for (int i=0; i<4; i++) {
            int curX = x + dx[i];
            int curY = y + dy[i];
            if (!isValid(curX, curY)) continue;
            answer += bfs(curX, curY);
        }
        return answer+1;
    }

    public static void main(String[] args) {
        new Main().solution();
    }
}