import java.util.*;

public class Main {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] coins = new int[n+1];
        int[] dp = new int[k+1];

        dp[0] = 1;

        for (int i=1; i<=n; i++) {
            coins[i] = sc.nextInt();
        }

        for (int i=1; i<=n; i++) {
            for (int j=coins[i]; j<=k; j++) {
                dp[j] += dp[j-coins[i]];
            }
        }

        System.out.println(dp[k]);
    }
    
    public static void main(String[] args) {
        new Main().solution();
    }
}
