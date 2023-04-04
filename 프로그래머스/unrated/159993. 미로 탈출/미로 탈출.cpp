#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

vector<string> m;
int visited[100][100] = {0,};
int howMuch[100][100] = {0,};
queue<pair<int, int>> q;

int dx[4] = {-1,0,1,0};
int dy[4] = {0,-1,0,1};

bool isValid (int x, int y) {
    if (x < 0 || y < 0) return false;
    if (x >= m.size() || y >= m[0].size()) return false;
    if (m[x][y] == 'X') return false;
    if (visited[x][y] == 1) return false;
    return true;
}

int solution(vector<string> maps) {
    m = maps;
    
    int answer = 0;
    
    int stpX, stpY;
    for (int i=0; i<maps.size(); i++) {
        string tempStr = maps[i];
        for (int j=0; j<tempStr.size(); j++) {
            char tempCh = tempStr[j];
            if (tempCh == 'S') {
                stpX = i;
                stpY = j;
                break;
            }
        }
    }
    
    q.push({stpX, stpY});
    visited[stpX][stpY] = 1;

    int levX = -1;
    int levY = -1;
    bool isSolved = false;
    while(!q.empty()) {
        pair<int, int> p = q.front();
        q.pop();
        for (int i=0; i<4; i++) {
            int tempX = p.first + dx[i];
            int tempY = p.second + dy[i];
            if (!isValid(tempX, tempY)) continue;
            if (isSolved) break;
            if (m[tempX][tempY] == 'L') {
                levX = tempX;
                levY = tempY;
                answer += (howMuch[p.first][p.second] + 1);
                isSolved = true;

                break;
            }
            if (howMuch[tempX][tempY] == 0) {
                howMuch[tempX][tempY] = howMuch[p.first][p.second] + 1;
            }
            q.push ({tempX, tempY});
            visited[tempX][tempY] = 1;
        }
    }
    
    if (levX == -1 || levY == -1) return -1;
    
    while(!q.empty()) q.pop();
    for (int i=0; i<maps.size(); i++) {
        for (int j=0; j<maps[0].size(); j++) {
            visited[i][j] = 0;
            howMuch[i][j] = 0;
        }
    }

    q.push({levX, levY});
    visited[levX][levY] = 1;
    isSolved = false;
    while(!q.empty()) {
        pair<int, int> p = q.front();
        q.pop();
        for (int i=0; i<4; i++) {
            int tempX = p.first + dx[i];
            int tempY = p.second + dy[i];
            if (!isValid(tempX, tempY)) continue;
            if (isSolved) break;
            if (m[tempX][tempY] == 'E') {
                answer += (howMuch[p.first][p.second] + 1);
                isSolved = true;
                break;
            }
            if (howMuch[tempX][tempY] == 0) {
                howMuch[tempX][tempY] = howMuch[p.first][p.second] + 1;
            }
            q.push ({tempX, tempY});
            visited[tempX][tempY] = 1;
        }
    }
    
    
    if (!isSolved) return -1;

    return answer;
}