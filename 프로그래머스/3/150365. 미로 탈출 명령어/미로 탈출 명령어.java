class Solution {
    String answer = "";
    
    int desX;
    int desY;
    
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int d = getDistance (x,y,r,c);
        int remain = k-d;
        if (remain%2 != 0 || remain < 0) return "impossible";
        desX = r;
        desY = c;
        
        dfs(k,x,y,"",n,m);
            
        return answer;
    }
    
    private int getDistance (int x, int y, int r, int c) {
        return Math.abs(x-r) + Math.abs(y-c);
    }
    
    char[] chArr = {'d','l','r','u'};
    int[] dx = {1,0,0,-1};
    int[] dy = {0,-1,1,0};
    
    private void dfs (int remain, int x, int y, String route, int n, int m) {
        if (answer.length() != 0) return;
        if (getDistance (x,y,desX,desY) > remain) return;
        if (remain == 0) {
            if (x==desX && y==desY) {
                answer = route;
            }
            return;
        }
        
        for (int i=0; i<4; i++) {
            int curX = x+dx[i];
            int curY = y+dy[i];
            if (curX <= n && curY <= m && curX>0 && curY>0) {
                StringBuilder sb = new StringBuilder(route);
                sb.append(String.valueOf(chArr[i]));
                String newRoute = sb.toString();
                dfs(remain-1, curX, curY, newRoute, n, m);
            }
        }
    }
}