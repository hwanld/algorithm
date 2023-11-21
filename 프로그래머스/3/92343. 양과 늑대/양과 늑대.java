import java.util.*;

class Solution {
    int answer;
    int[] g_info;
    int[][] g_edges;
    
    public int solution(int[] info, int[][] edges) {
        answer = 0;
        g_info = info;
        g_edges = edges;
        
        List<Integer> list = new ArrayList<>();
        
        dfs (0, list, 0, 0);
        
        return answer;
    }
    
    private void dfs (int stp, List<Integer> list, int sheep, int wolf) {
        if (g_info[stp] == 0) sheep++;
        else wolf++;
        
        list.add(stp);
        
        if (sheep <= wolf) return;
        
        answer = Math.max(answer, sheep);
        
        for (int each : list) {
            for (int[] edges : g_edges) {
                if (edges[0] == each && !list.contains(edges[1])) {
                    List<Integer> newList = new ArrayList<>(list);
                    dfs (edges[1], newList, sheep, wolf);
                }
            }
        }
    }
}