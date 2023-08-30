import java.util.*;

public class Main {

    public void solution() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        final int impossible = 100000;
        
        int[] dp = new int[k+1];
        Set<Integer> coinSet = new HashSet<>();

        for (int i=1; i<=k; i++)
            dp[i] = impossible;

        for (int i=1; i<=n; i++) {
            int temp = sc.nextInt();
            coinSet.add(temp);
        }

        // i : 코인 갯수
        // k : 목표 금액
        for (int i=1; i<=k; i++) {
            for (int each : coinSet) {
                if (i-each >= 0)
                    dp[i] = Math.min(dp[i-each] + 1, dp[i]);
            }
        }

        if (dp[k] != impossible) System.out.println(dp[k]);
        else System.out.println(-1);
    }

    public static void main(String[] args) {
        new Main().solution();
    }
}