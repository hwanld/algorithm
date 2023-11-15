import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        List<As> asList = new ArrayList<>();
        
        for (String[] eachPlan : plans) {
            As as = new As(eachPlan);
            asList.add(as);
        }
        
        Collections.sort(asList);
        Stack<As> st = new Stack<>();
        
        int availTime = 0;
        
        for (int i=0; i<asList.size(); i++) {
            As each = asList.get(i);
            if (i==0) {
                availTime = calTime(each, asList.get(i+1));
                if (each.extime <= availTime) {
                    answer.add(each.name);
                }
                else {
                    each.extime -= availTime;
                    st.push(each);
                }
            }
            else if (i==asList.size()-1) {
                answer.add(each.name);
            }
            else {
                availTime = calTime(each, asList.get(i+1));
                if (each.extime <= availTime) {
                    answer.add(each.name);
                    availTime -= each.extime;
                    while(!st.isEmpty() && availTime > 0) {
                        As temp = st.pop();
                        if (temp.extime <= availTime) {
                            answer.add(temp.name);
                            availTime -= temp.extime;
                        }
                        else {
                            temp.extime -= availTime;
                            availTime = 0;
                            st.push(temp);
                        }
                    }
                }
                else {
                    each.extime -= availTime;
                    st.push(each);
                }
            }
        }
        
        while(!st.isEmpty()) {
            answer.add(st.pop().name);
        }
        
        String[] answerArray = new String[answer.size()];
        answer.toArray(answerArray);
        return answerArray;
    }
    
    public int calTime (As a1, As a2) {
        return Math.abs((a2.hour - a1.hour)*60 + (a2.min-a1.min));
    }
}

class As implements Comparable<As>{ 
    String name;
    int hour;
    int min;
    int extime;

    public As (String[] plans) {
        this.name = plans[0];
        this.extime = Integer.parseInt(plans[2]);
        this.hour = Integer.parseInt(plans[1].split(":")[0]);
        this.min = Integer.parseInt(plans[1].split(":")[1]);
    }
    
    @Override
    public int compareTo (As other) {
        if (this.hour != other.hour) 
            return this.hour - other.hour;
        return this.min - other.min;
    }
}