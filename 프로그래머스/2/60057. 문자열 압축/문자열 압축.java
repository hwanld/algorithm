import java.util.*;

class Solution {
    
    public int solution(String s) {
        int answer = s.length();
        
        for (int i=1; i<=s.length()/2; i++) {
            answer = Math.min(getZipStringLen(s, i), answer);
        }
        return answer;
    }
    
    public int getZipStringLen(String str, int len) {
        StringBuilder sb = new StringBuilder();
        
        int result = 0;
        String first = str.substring(0, len);

        int index = 1;
        int stp = 0;
        
        for (int i=len; i<str.length(); i+=len) {
            String temp = "";
            if (i+len>str.length()) temp = str.substring(i, str.length());
            else temp = str.substring(i, i+len);
            
            if (first.equals(temp)) {
                index++;    
            }
            
            else {
                if (index != 1) sb.append(String.valueOf(index));
                sb.append(first);
                first = temp;
                index = 1;
            }
        }
        
        if (index != 1) sb.append(String.valueOf(index));
        sb.append(first);
        
        return sb.length();
    }
}