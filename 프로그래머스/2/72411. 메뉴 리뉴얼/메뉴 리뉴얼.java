import java.util.*;

class Solution {
    Map<String, Integer> map = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        
        for (int i=0; i<course.length; i++) {
            
            int combLen = course[i];
            List<String> temp = new ArrayList<>();
            int maxIndex = 0;
            
            for (int j=0; j<orders.length; j++) {
                String order = orders[j];
                char[] chars = order.toCharArray();
                Arrays.sort(chars);
                order = new String(chars);
                makeMap (combLen, order, 0, "");
            }
            
            for (String key : map.keySet()) {
                
                if (map.get(key) > maxIndex) {
                    maxIndex = map.get(key);
                    temp.clear();
                    temp.add(key);
                }
                else if (map.get(key) == maxIndex) {
                    temp.add(key);
                }
                else continue;
            }
            
            for (int j=0; j<temp.size(); j++) {
                if (map.get(temp.get(j)) == 1) continue;
                answer.add(temp.get(j));
            }
            
            map.clear();
        }

        Collections.sort(answer);
        return answer.toArray(new String[answer.size()]);
    }
    
    public void makeMap (int len, String order, int stp, String result) {
        if (result.length() == len) {
            if (!map.containsKey(result)) map.put(result, 0);
            map.put(result, map.get(result)+1);
            return;
        }
        
        if (stp == order.length()) return;
        
        String target = String.valueOf(order.charAt(stp));
        
        makeMap(len, order, stp+1, result + target);
        makeMap(len, order, stp+1, result);
    }
}