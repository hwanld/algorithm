import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public void solution() {
        int t = sc.nextInt();
        for (int i=0; i<t; i++) {
            eachSolution();
        }
    }

    public void eachSolution() {
        int n = sc.nextInt();
        int stpX = sc.nextInt();
        int stpY = sc.nextInt();
        int[] shopX = new int[n+1];
        int[] shopY = new int[n+1];
        for (int i=0; i<n; i++) {
            shopX[i] = sc.nextInt();
            shopY[i] = sc.nextInt();
        }
        int desX = sc.nextInt();
        int desY = sc.nextInt();

        boolean[] visited = new boolean[n+1];
        for (int i=0; i<n+1; i++)
            visited[i] = false;

        Queue<Integer> curX = new ArrayDeque<>();
        Queue<Integer> curY = new ArrayDeque<>();
        curX.add(stpX);
        curY.add(stpY);

        if (n==0) {
            if (calculate(desX, desY, stpX, stpY) <= 1000) {
                System.out.println("happy");
                return;
            }
            System.out.println("sad");
            return;
        }

        while (!curX.isEmpty()){
            int x = curX.poll();
            int y = curY.poll();

            for (int i=0; i<n; i++) {
                if (calculate(desX, desY, x, y) <= 1000) {
                    System.out.println("happy");
                    return;
                }
                if (!visited[i] && calculate(shopX[i], shopY[i], x, y) <= 1000) {
                    visited[i] = true;
                    curX.add(shopX[i]);
                    curY.add(shopY[i]);
                }
            }

        }
        System.out.println("sad");
    }

    public int calculate (int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    public static void main(String[] args) {
        new Main().solution();
    }
}