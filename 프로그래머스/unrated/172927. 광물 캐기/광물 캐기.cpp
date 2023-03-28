#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int getAbsoluteWork (string minerals);
int calculateWorkWithPicks (string picks, string minerals);
bool compare(pair<int, int> p1, pair<int, int> p2);
vector<pair<int, int>> getPriority (vector<int> picks, vector<string> minerals);

int solution(vector<int> picks, vector<string> minerals) {
    int answer = 0;
    
    // 하나의 곡괭이는 무조건 다섯 광물을 부수기 때문에
    // i*5 + {0,1,2,3,4} 번째의 minerals를 부수게 된다.
    // i*5 + {0,1,2,3,4} 의 절대적 일의 크기를 전부 계산해서 우선순위가 높은 것 부터
    // 차례대로 diamond, iron, stone을 배정한다
    
    vector<pair<int, int>> priority = getPriority(picks, minerals);
    
    sort(priority.begin(), priority.end(), compare);
    
    int priorityIndex = 0;
    
    for (int i=0; i<picks[0]; i++) {
        string curPicks = "diamond";
        if (priorityIndex*5 >= minerals.size()) return answer;

        for (int j=0; j<5; j++) {
            int targetIndex = priority[priorityIndex].first*5 + j;
            if (targetIndex >= minerals.size()) break;
            string targetMinerals = minerals[targetIndex];
            answer += calculateWorkWithPicks(curPicks, targetMinerals);
        }
        priorityIndex++;
    }
    

    
    for (int i=0; i<picks[1]; i++) {
        string curPicks = "iron";
        if (priorityIndex*5 >= minerals.size()) return answer;
        
        for (int j=0; j<5; j++) {
            int targetIndex = priority[priorityIndex].first*5 + j;
            if (targetIndex >= minerals.size()) break;
            string targetMinerals = minerals[targetIndex];
            answer += calculateWorkWithPicks(curPicks, targetMinerals);
        }
        priorityIndex++;
    }
    
    for (int i=0; i<picks[2]; i++) {
        string curPicks = "stone";
        if (priorityIndex*5 >= minerals.size()) return answer;
        
        for (int j=0; j<5; j++) {
            int targetIndex = priority[priorityIndex].first*5 + j;
            if (targetIndex >= minerals.size()) break;
            string targetMinerals = minerals[targetIndex];
            answer += calculateWorkWithPicks(curPicks, targetMinerals);
        }
        priorityIndex++;
    }

    return answer;
}

int getAbsoluteWork (string minerals) {
    if (minerals == "diamond") return 25;
    if (minerals == "iron") return 5;
    if (minerals == "stone") return 1;
}

int calculateWorkWithPicks (string picks, string minerals) {
    if (picks == "diamond") return 1;
    if (picks == "iron") {
        if (minerals == "diamond") return 5;
        return 1;
    }    
    if (picks == "stone") {
        if (minerals == "diamond") return 25;
        if (minerals == "iron") return 5;
    }
    return 1;
}

bool compare(pair<int, int> p1, pair<int, int> p2) {
    return p1.second > p2.second;
}

vector<pair<int, int>> getPriority (vector<int> picks, vector<string> minerals) {
    int numOfPicks = 0;
    for (auto i : picks) numOfPicks += i;

    vector<pair<int, int>> priority;
    for (int i=0; i<=minerals.size()/5; i++) {
        pair<int, int> tempPair;
        int tempSum = 0;
        for (int j=0; j<5; j++) {
            int tempIndex = i*5 + j;
            
            // picks 이상의 minerals는 sort할 필요가 없음
            if (tempIndex >= numOfPicks*5) 
                return priority;
                
            // minerals 갯수를 초과하는 사태를 막기 위함
            if (tempIndex >= minerals.size()) {
                tempPair.first = i;
                tempPair.second = tempSum;
                priority.push_back(tempPair);
                return priority;
            }
            
            string tempMinerals = minerals[tempIndex];
            tempSum += getAbsoluteWork (tempMinerals);
        }
        tempPair.first = i;
        tempPair.second = tempSum;
        priority.push_back(tempPair);
    }
    return priority;
}