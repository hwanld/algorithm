#include <string>
#include <vector>
#include <deque>
#include <iostream>
#include <algorithm>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    int index = 0;

    deque<int> dq;

    for (int i=0; i<priorities.size(); i++) {
        if (i == location) dq.push_back(priorities[i]+10);
        else dq.push_back(priorities[i]);
    }

    sort(priorities.begin(), priorities.end(), greater<int>());


    while(!dq.empty()) {
        int temp = dq.front();
        dq.pop_front();

        if (temp % 10 == priorities[index]) {
            if (temp / 10 == 1) break;
            index++;
            answer++;
        }
        else {
            dq.push_back(temp);
        }

    }

    return answer+1;
}