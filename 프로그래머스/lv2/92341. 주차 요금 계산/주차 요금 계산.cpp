#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <map>

using namespace std;

// IN인 경우에는 str size 13
// OUT인 경우에는 str size 14

int getMin(string str);
string getCar(string str);

vector<int> solution(vector<int> fees, vector<string> records) {
    
    int minTime = fees[0];
    int minFee = fees[1];
    int unitTime = fees[2];
    int unitFee = fees[3];
    
    map<string, int> m;
    vector<string> cars;
    vector<int> answer;
    
    
    for (auto str : records) {
        // When IN
        if (str.size() == 13) {
            if (m[getCar(str)] == 0) 
                cars.push_back(getCar(str));
            
            m[getCar(str)] -= getMin(str);
        }
    
        // When OUT
        if (str.size() == 14) 
            m[getCar(str)] += getMin(str);
    }
    
    sort(cars.begin(), cars.end());
    
    for (auto car : cars) {
        int time = m[car];
        if (time <= 0) time += 1439;
        cout<<"time : "<<time<<endl;
        if (time <= minTime) answer.push_back(minFee);
        else {
            time -= minTime;
            if (time % unitTime == 0) {
                answer.push_back(minFee + unitFee * (time / unitTime));
            }
            else {
                answer.push_back(minFee + unitFee * ((time / unitTime)+1));
            }
            
        }
    }
    
    return answer;
}

int getMin(string str) {
    int hour = stoi(str.substr(0,2));
    int min = stoi(str.substr(3,2));
    return hour * 60 + min;
}

string getCar(string str) {
    return str.substr(6,4);
}