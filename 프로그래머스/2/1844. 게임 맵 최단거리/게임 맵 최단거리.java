import java.util.*;

class Solution {
    int[] dx = {-1,0,1,0};
    int[] dy = {0,-1,0,1};
    
    int[][] map;
    boolean[][] visited;
    int n;
    int m;
    int answer = -1;
    
    Queue<Integer> qx = new ArrayDeque<>();
    Queue<Integer> qy = new ArrayDeque<>();
    Queue<Integer> qa = new ArrayDeque<>();
    
    public int solution(int[][] maps) {
        map = maps;
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        
        qx.add(0);
        qy.add(0);
        qa.add(0);
        
        go();
        
        return answer;
    }
    
    public void go () {
        while (!qx.isEmpty()) {
            int x = qx.poll();
            int y = qy.poll();
            int temp = qa.poll();
            
            temp++;
            
            if (x==n-1 && y==m-1) {
                answer = temp;
                return;
            }
                       
            for (int i=0; i<4; i++) {
                int curX = x+dx[i];
                int curY = y+dy[i];
                
                if (isValid (curX, curY)) {
                    visited[curX][curY] = true;
                    qx.add(curX);
                    qy.add(curY);
                    qa.add(temp);
                }
            }
            
        }
    }
    
    public boolean isValid (int x, int y) {
        if (x<0 || y<0 || x>=n|| y>=m) return false;
        if (map[x][y] == 0) return false;
        if (visited[x][y]) return false;
        return true;
    }
}