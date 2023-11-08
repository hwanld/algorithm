import java.util.*;

// 실패율 = 도달했으나 클리어하지 못한 수 / 스테이지에 도달한 플레이어수

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        int maxStage = 0;
        int[] arrive = new int[200001];
        
        for (int i=0; i<stages.length; i++) {
            int each = stages[i];
            for (int j=1; j<=each; j++) {
                arrive[j]++;
            }
        }
        
        List<Pair> pairList = new ArrayList<>();
        
        for (int i=1; i<=N; i++) {
            int curArrive = arrive[i];
            int success = arrive[i+1];        
            
            double fail = 0;
            if (curArrive != 0) fail = (double)(curArrive-success) / (double)curArrive;
            
            Pair pair = new Pair(i, fail);
            pairList.add(pair);
        }
        
        
        Collections.sort(pairList);
        for (int i=0; i<pairList.size(); i++) {
            answer[i] = pairList.get(i).n;
        }
        
        return answer;
    }
}

class Pair implements Comparable<Pair> {
    int n;
    double fail;
    
    public Pair(int n, double fail) {
        this.n = n;
        this.fail = fail;
    }
    
    @Override
    public int compareTo (Pair other) {
        if (fail < other.fail) return 1;
        else if (fail == other.fail) {
            if (n > other.n) return 1;
            else return -1;
        }
        else return -1;
    }
}