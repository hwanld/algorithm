import java.util.*;

class Solution {
    
    Map<String, List<Integer>> map = new HashMap<>();
    int[] answer;
    
    public int[] solution(String[] info, String[] query) {
        answer = new int[query.length];
        
        for (int i=0; i<info.length; i++) {
            String[] eachInfoList = info[i].split(" ");
            makeMap(eachInfoList, "", 0);
        }
        
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        
        for (int i=0; i<query.length; i++) {
            int temp = 0;
            
            String eachQuery = query[i].replaceAll(" and ", "");
            String[] eachQueryList = eachQuery.split(" ");
            
            if (map.containsKey(eachQueryList[0])) 
                temp = binarySearch(eachQueryList[0], Integer.parseInt(eachQueryList[1]));
            
            answer[i]  = temp;
        }
        
        return answer;
    }
    
    public void makeMap (String[] str, String info, int n) {
        if (n == 4) {
            if (!map.containsKey(info)) map.put(info, new ArrayList<Integer>());
            map.get(info).add(Integer.parseInt(str[4]));
            return;
        }
        makeMap(str, info + "-", n+1);
        makeMap(str, info + str[n], n+1);
    }
    
    public int binarySearch(String key, int target) {
        List<Integer> scoreList = map.get(key);
        int stp = 0;
        int end = scoreList.size()-1;
        
        while (stp <= end) {
            int mid = (stp + end) / 2;
            
            if (target > scoreList.get(mid)) {
                stp = mid+1;
            }
            
            else {
                end = mid-1;
            }
        }
        
        return scoreList.size()-stp;
    }
}