import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        
        int targetAlp = 0;
        int targetCop = 0;
        
        for (int i=0; i<problems.length; i++) {
            int[] eachProblem = problems[i];
            targetAlp = Math.max(targetAlp, eachProblem[0]);
            targetCop = Math.max(targetCop, eachProblem[1]);
        }
        
        if (alp >= targetAlp) targetAlp = alp;
        if (cop >= targetCop) targetCop = cop;
    
        int[][] dp = new int[targetAlp+1][targetCop+1];
        
        for (int i=alp; i<=targetAlp; i++) {
            for (int j=cop; j<=targetCop; j++) {
                dp[i][j] = 100000000;
            }
        }
        
        dp[alp][cop] = 0;
        
        for (int i=alp; i<=targetAlp; i++) {
            for (int j=cop; j<=targetCop; j++) {
                
                if (j!=targetCop) dp[i][j+1] = Math.min(dp[i][j]+1, dp[i][j+1]);
                if (i!=targetAlp) dp[i+1][j] = Math.min(dp[i][j]+1, dp[i+1][j]);
                
                for (int k=0; k<problems.length; k++) {
                    int[] eachProblem = problems[k];
                    
                    if (i>=eachProblem[0] && j>=eachProblem[1]) {
                        int dx = eachProblem[2];
                        int dy = eachProblem[3];
                        int executionTime = eachProblem[4];
                        
                        if (i+dx >= targetAlp) dx = targetAlp-i;
                        if (j+dy >= targetCop) dy = targetCop-j;
                        
                        dp[i+dx][j+dy] = Math.min(dp[i][j] + executionTime, dp[i+dx][j+dy]);
                    }
                }
                
            }
        }
        
        return dp[targetAlp][targetCop];
    }
}