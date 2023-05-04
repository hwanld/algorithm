import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        TreeMap<String, Integer> m = new TreeMap<>();
        
        for (int i=0; i<name.length; i++) {
            m.put(name[i], yearning[i]);
        }
        
        for (int i=0; i<photo.length; i++) {
            int tempSum = 0;
            for (int j=0; j<photo[i].length; j++) {
                String eachPhoto = photo[i][j];
                if (m.containsKey(eachPhoto)) {
                    tempSum += m.get(eachPhoto);    
                }
            }
            System.out.println(tempSum);
            answer[i] = tempSum;   
        }
        
        
        return answer;
    }
}