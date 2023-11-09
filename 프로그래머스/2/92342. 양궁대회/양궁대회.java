class Solution {
    int[] imp = {-1};
    int[] answer = {};
    int[] apeach;
    int maxDiff = -1;
    
    public int[] solution(int n, int[] info) {
        apeach = info;
        solve (n, 0, new int[11]);
        
        if (maxDiff == -1) return imp;
        return answer;
    }
    
    public void solve (int arrow, int stp, int[] lion) {
        if (arrow == 0 || stp == 10) {
            lion[10] = arrow;
            calDiff(lion);
            return;
        }
        
        if (arrow > apeach[stp]) {
            int[] newLion = lion.clone();
            newLion[stp] = apeach[stp]+1;
            solve (arrow - newLion[stp], stp+1, newLion);
        }
        
        solve (arrow, stp+1, lion);
    }
    
    public void calDiff(int[] lion) {
        int apeachScore = 0;
        int lionScore = 0;
        
        for (int i=0; i<11; i++) {
            if (apeach[i] == 0 && lion[i] == 0) continue;
            if (apeach[i] >= lion[i]) apeachScore += (10-i);
            else lionScore += (10-i);
        }
        
        if (apeachScore >= lionScore) return;
        
        // diff는 무조건 1 이상
        int diff = lionScore - apeachScore;
        if (diff == maxDiff) {
            for (int i=10; i>=0; i--) {
                if (lion[i] > answer[i]) {
                    answer = lion.clone();
                    break;
                }
                else if (answer[i] == lion[i]) {
                    continue;
                }
                else {
                    break;
                }
            }
        }
        else if (diff > maxDiff) {
            maxDiff = diff;
            answer = lion.clone();
        }
    }
}