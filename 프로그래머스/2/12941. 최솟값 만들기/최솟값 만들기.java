import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        int first = 0;
        int second = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        
        for (int i=0; i<A.length; i++) {
            first += A[i] * B[A.length-i-1];
            second += B[i] * A[A.length-i-1];
        }
        
        return Math.min(first, second);
    }
}