import java.util.*;

// 맨해튼 거리 : x차이 절대값 + y차이 절대값
// 대기실 5개, 각 대기실은 5*5 크기
// 맨해튼 거리가 2 이하로 앉지 말 것
// 단 응시자 사이가 파티션은 허용

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int i=0; i<5; i++) {
            String[] eachPlace = places[i];
            answer[i] = solve(places[i]);
            System.out.println("-----------");
        }
        
        return answer;
    }

    public int solve (String[] place) {
        int answer = 1;
        int[][] map = initMap(place);
        boolean [][] visited = initVisited(map);
        
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (visited[i][j] == false) {
                    if (!isKept(i,j, map)) return 0;
                }
                else continue;
            }
        }
        return 1;
    }
    
    public boolean isKept (int x, int y, int[][] map) {
        // 맨해튼 거리 범위 확인
        for (int i=-2; i<=2; i++) {
            for (int j=-2; j<=2; j++) {
                // 맨해튼 거리 2 초과는 continue
                if (Math.abs(i) + Math.abs(j) > 2) continue;
                
                // 자기 자신인 경우도 continue
                if (i==0 && j==0) continue;
                
                int curX = x+i;
                int curY = y+j;
                
                // 범위가 유효하지 않으면 continue
                if (curX < 0 || curX > 4 || curY < 0 || curY > 4) continue;
                
                // 맨해튼 거리 안에 사람이 있는 경우
                else if (map[curX][curY] == 1) {
                    // 파티션으로 막혀있는지 확인하기
                    // x가 같다면 y로 한 칸 막힌지 확인
                    if (x == curX) {
                        if (map[curX][Math.min(y,curY)+1]==-1) {
                            System.out.println("x :" + x +", y : " + y + " is kept! curX : " + curX + ", curY : " + curY);
                            continue;   
                        }
                        else return false;
                    }
                    // y가 같다면 x로 한 칸 막힌지 확인
                    else if (y == curY) {
                        if (map[Math.min(x,curX)+1][y]==-1) {
                            System.out.println("x :" + x +", y : " + y + " is kept! curX : " + curX + ", curY : " + curY);
                            continue;   
                        }
                        else return false;
                    }
                    // x, y가 둘 다 다르다면
                    // x>curX, y>curY인 경우
                    // x>curX, y<curY인 경우
                    // x<curX, y>curY인 경우
                    // x<curX, y<curY인 경우
                    else if (x>curX && y>curY){
                        if (map[x-1][y]==-1 && map[x][y-1]==-1) {
                            System.out.println("x :" + x +", y : " + y + " is kept! curX : " + curX + ", curY : " + curY);
                            continue;   
                        }
                        else return false;
                    }
                    else if (x>curX && y<curY){
                        if (map[x-1][y]==-1 && map[x][y+1]==-1) {
                            System.out.println("x :" + x +", y : " + y + " is kept! curX : " + curX + ", curY : " + curY);
                            continue;   
                        }
                        return false;
                    }
                    else if (x<curX && y>curY){
                        if (map[x+1][y]==-1 && map[x][y-1]==-1) {
                            System.out.println("x :" + x +", y : " + y + " is kept! curX : " + curX + ", curY : " + curY);
                            continue;   
                        };  
                        return false;
                    }
                    else if (x<curX && y<curY){
                        if (map[x+1][y]==-1 && map[x][y+1]==-1) {
                            System.out.println("HERE! x :" + x +", y : " + y + " is kept! curX : " + curX + ", curY : " + curY);
                            continue;   
                        }   
                        return false;
                    }
                    else {
                        System.out.println("x :" + x +", y : " + y + " is not kept! curX : " + curX + ", curY : " + curY);
                        return false;
                    }
                }
                else continue;
            }
        }
        return true;
    }
    
    public int[][] initMap (String[] place) {
        int[][] map = new int[5][5];
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (place[i].charAt(j) == 'P') map[i][j] = 1;
                if (place[i].charAt(j) == 'O') map[i][j] = 0;
                if (place[i].charAt(j) == 'X') map[i][j] = -1;
            }
        }
        return map;
    }
    
    public boolean[][] initVisited (int[][] map) {
        boolean [][] visited = new boolean[5][5];
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (map[i][j] == 1) visited[i][j] = false;
                else visited[i][j] = true;
            }
        }
        return visited;
    }
}