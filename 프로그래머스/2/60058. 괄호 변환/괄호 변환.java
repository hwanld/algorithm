import java.util.*;

// (와 )의 개수가 같다면 -> 균형 잡힌 괄호 문자열
// (와 )의 짝도 모두 맞다면 -> 올바른 괄호 문자열

// 빈 문자열 -> 빈 문자열 리턴
// 
class Solution {
    public String solution(String p) {
        StringBuilder sb = new StringBuilder();
        // 1. 입력이 빈 문자열인 경우 빈 문자열 반환
        if (p.length() == 0) return "";
        
        String[] uvArray = parseToStringArray(p);
        String u = uvArray[0];
        String v = uvArray[1];
        
        if (isCorrectString(u)){
            sb.append(u);
            sb.append(solution(v));
        }
        else {
            sb.append("(");
            sb.append(solution(v));
            sb.append(")");
            sb.append(createNewU(u));
        }
        return sb.toString();
    }
    
    String createNewU (String str) {
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<str.length()-1; i++){
            if (str.charAt(i) == '(') 
                sb.append(")");
            else 
                sb.append("(");
        }
        return sb.toString();
    }
    
    // 올바른 괄호 문자열인지 판단
    Boolean isCorrectString (String str) {
        Stack<Character> st = new Stack<>();
        for (int i=0; i<str.length(); i++) {
            char each = str.charAt(i);
            if (each == '(') {
                st.push(each);
            }
            else {
                if (st.isEmpty()) return false;
                else st.pop();
            }
        }
        return true;
    }
    
    // 문자열 w를 문자열 u, v로 분리
    String[] parseToStringArray(String str) {
        String[] stringArr = new String[2];
        StringBuilder sb = new StringBuilder();
        int openCnt = 0;
        int closeCnt = 0;
        
        for (int i=0; i<str.length(); i++) {
            String each = String.valueOf(str.charAt(i));
            
            if (each.equals("(")) {
                openCnt++;
                sb.append(each);
            }
            else {
                closeCnt++;
                sb.append(each);
            }
            
            if (openCnt == closeCnt) {
                stringArr[0] = sb.toString();
                stringArr[1] = str.substring(i+1, str.length());
                break;
            }
        }
        
        return stringArr;
    }
}