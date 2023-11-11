import java.util.*;

class Solution {
    
    List<List<Integer>> sList = new ArrayList<>();
    
    public int[] solution(String s) {
        List<Integer> answer = new ArrayList<>();
        
        String newS = s.substring(1, s.length()-1);
        
        for (int i=0; i<newS.length(); i++) {
            if (newS.charAt(i) == '{') {
                String temp = "";
                List<Integer> list = new ArrayList<>();
                i++;
                
                while (newS.charAt(i) != '}') {
                    if (newS.charAt(i) == ',') {
                        list.add(Integer.parseInt(temp));
                        temp = "";
                        i++;
                        continue;
                    }
                    temp += newS.charAt(i);
                    i++;
                }
                list.add(Integer.parseInt(temp));
                sList.add(list);
            }
        }
        
        sList.sort((List<Integer> l1, List<Integer> l2) -> {
            return l1.size()-l2.size();
        });
        
        for (List<Integer> eachList : sList) {
            for (int each : eachList) {
                if (!answer.contains(each)) answer.add(each);
            }
        }
        
        return answer.stream()
            .mapToInt(i->i)
            .toArray();
    }
    
}