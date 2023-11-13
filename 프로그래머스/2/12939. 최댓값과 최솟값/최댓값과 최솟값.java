import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] arr = s.split(" ");
        int max = Integer.parseInt(arr[0]);
        int min = Integer.parseInt(arr[0]);
        
        for (int i=1; i<arr.length; i++) {
            int temp = Integer.parseInt(arr[i]);
            max = Math.max(temp, max);
            min = Math.min(temp, min);
        }
        
        System.out.println(max);
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(min));
        sb.append(" ");
        sb.append(String.valueOf(max));
        return sb.toString();
    }
}