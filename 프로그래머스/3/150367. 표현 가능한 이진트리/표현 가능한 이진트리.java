import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i=0; i<numbers.length; i++) {
            String eachBinaryString = Long.toBinaryString(numbers[i]);
            String fullBinaryString = getFullBinaryString(eachBinaryString);
            if (isRepresentiveString(fullBinaryString)) answer[i] = 1;
            else answer[i] = 0;
        }
    
        return answer;
    }
    
    private String getFullBinaryString(String eachBinaryString) {
        int len = eachBinaryString.length();
        int depth = 1;
        int node = 0;
        while (len > node) {
            node += depth;
            depth *= 2;
        }
        return "0".repeat(node-len) + eachBinaryString;
    }
    
    private boolean isRepresentiveString (String fullBinaryString) {
        int len = fullBinaryString.length();
        if (len==1) return true;
        
        int root = len/2;
        
        String left = fullBinaryString.substring(0, root);
        String right = fullBinaryString.substring(root+1, len);
        
        if (fullBinaryString.charAt(root) == '0') {
            if (left.contains("1") || right.contains("1"))
                return false;
            return true;
        }
        
        return isRepresentiveString(left) && isRepresentiveString(right);
    }
}