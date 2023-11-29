import java.util.*;

class Solution {
    StringBuilder sb = new StringBuilder();
    
    public String solution(String s) {
        
        boolean pastIsBlank = true;
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            
            if (ch == ' ') {
                pastIsBlank = true;
            }
            else if (pastIsBlank) {
                if (!Character.isDigit(ch))
                    ch = Character.toUpperCase(ch);
                pastIsBlank = false;
            }
            else {
                ch = Character.toLowerCase(ch);
            }
            
            sb.append(String.valueOf(ch));
        }
        
        return sb.toString();
    }
}