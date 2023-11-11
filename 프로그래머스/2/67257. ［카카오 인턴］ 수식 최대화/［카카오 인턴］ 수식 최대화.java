import java.util.*;

// 모든 참가자들에게는 숫자와 3가지 연산문자 (+, -, *)로 이루어진 연산 수식 전달
// 연산자의 우선 수누이를 자유롭게 재정의하여 만들 수 있는 가장 큰 숫자 재출
// 동일 순위의 연산자는 만들 수 없음, 즉 3가지 연산 문자는 3!=6가지
// 음수는 절대값으로 변환하여 제출
// 최종 결과값은 절대값이 2^63-1 이하

class Solution {
    List<Long> numList = new ArrayList<>();
    List<Character> opList = new ArrayList<>();
    Set<Character> opSet = new HashSet<>(); 
    List<String> opComb = new ArrayList<>();
    
    long answer = 0;
    
    public long solution(String expression) {
        initialize(expression);
        makeComb("");
        
        for (String each : opComb) 
            calculate (each);
        
        return answer;
    }
    
    public void calculate (String comb) {
        List<Character> op = new ArrayList<>();
        for (char ch : opList) op.add(ch);
        List<Long> num = new ArrayList<>();
        for (long n : numList) num.add(n);
        
        for (int i=0; i<comb.length(); i++) {
            char each = comb.charAt(i);

            for (int j=0; j<op.size(); ) {
                
                if (op.get(j) == each) {
                    Long first = num.get(j);
                    Long second = num.get(j+1);
                    num.remove(j);
                    num.remove(j);
                    
                    if (each == '-') num.add(j, first - second);
                    else if (each == '+') num.add(j, first + second);
                    else num.add(j, first * second);  
                    
                    op.remove(j);
                
                }

                else j++;
            }
            
        }
        
        answer = Math.max (Math.abs(num.get(0)), answer);
    }
    
    public void initialize(String expression) {
        int stp = 0;
        for (int i=0; i<expression.length(); i++) {
            char each = expression.charAt(i);
            if (each == '*' || each == '-' || each == '+') {
                long number = Long.parseLong(expression.substring(stp, i));
                numList.add(number);
                opList.add(each);
                opSet.add(each);
                stp = i+1;
            }
        }
        numList.add(Long.parseLong(expression.substring(stp)));
    }
    
    public void makeComb (String result) {
        if (result.length() == opSet.size()) {
            opComb.add(result);
            return;
        }
        
        for (char each : opSet) {
            if (result.contains(String.valueOf(each))) 
                continue;
            
            String newResult = result + String.valueOf(each);
            makeComb (newResult);
        }
    }
        
}