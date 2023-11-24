class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int stp = 1;
        int end = 200000000;
        
        int mid;
        
        while (stp <= end) {
            mid = (stp + end) / 2;
            if (isPossible (stones, k, mid)) {
                stp = mid+1;
                answer = Math.max(mid, answer);
            }
            else {
                end = mid-1;
            }
        }
        return answer;
    }
    
    private boolean isPossible (int[] stones, int k, int n) {
        int zeroCnt = 0;
        for (int i=0; i<stones.length; i++) {
            if (stones[i] - n < 0) zeroCnt++;
            else zeroCnt = 0;
            
            if (zeroCnt >= k) return false;
        }
        return true;
    }
}