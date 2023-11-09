import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> st = new Stack<>();
        Queue<Integer> qu = new ArrayDeque<>();
        
        for (int i=1; i<=order.length; i++) 
            qu.add(i);
        
        int qtop = -1;
        int stop = -1;
        int curIndex = 0;
        
        while (true) {
            if (curIndex >= order.length) break;
            int target = order[curIndex];
            
            if (!qu.isEmpty()) qtop = qu.peek();
            if (!st.isEmpty()) stop = st.peek();
            
            if (qtop == target) {
                qu.poll();
                curIndex++;
            }
            else if (qtop != target && stop == target) {
                st.pop();
                curIndex++;
            }
            else if (qtop == -1 && stop != target) {
                break;
            }
            else {
                st.push(qtop);
                qu.poll();
            }
            
            qtop = -1;
            stop = -1;
        }
    
        return curIndex;
    }
}