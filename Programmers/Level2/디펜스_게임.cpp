#include <string>
#include <vector>
#include <queue>
#include <functional>
#include <iostream>


using namespace std;

int solution(int n, int k, vector<int> enemy) {
    int answer = k;
    priority_queue<int, vector<int>, greater<int>> pq;

    int sum = 0;
    for (int i=0; i<enemy.size(); i++) {
        pq.push(enemy[i]);

        if (pq.size() > k) {
            sum += pq.top();
            pq.pop();
        }

        if (sum > n) return i;

    }

    return enemy.size();
}