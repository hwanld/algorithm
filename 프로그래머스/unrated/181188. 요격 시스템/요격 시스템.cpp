#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

bool compare (vector<int> a, vector<int> b);
int solution(vector<vector<int>> targets) {
    int answer = 0;
    sort(targets.begin(), targets.end(), compare);
    
    for (int index=0; index<targets.size(); ) {
        answer++;
        int end = targets[index][1];
    
        while(targets[index][0] < end) {
            if (targets[index][1] < end) {
                end = targets[index][1];
            }
            index++;
            if (index == targets.size()) {
                break;
            }
        }
    }
    
    return answer;
}

// 두 개의 벡터를 오름차순으로 정렬
bool compare (vector<int> a, vector<int> b) {
    if (a[0]==b[0]) return (a[1]<b[1]);
    return a[0]<b[0];
}