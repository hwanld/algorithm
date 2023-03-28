#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int n, int m, vector<int> section) {
    int answer = 0;

    // 지금 어디까지 칠해졌는지?
    int paintedUntil = 0;

    queue<int> q;

    for (auto i : section) q.push(i);

    while(!q.empty()) {
        int next = q.front();
        q.pop();
        if (next > paintedUntil) {
            paintedUntil = next + m - 1;
            answer++;
        }

    }

    return answer;
}