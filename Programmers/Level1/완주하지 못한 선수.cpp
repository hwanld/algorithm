#include <string>
#include <vector>
#include <map>
#include <iostream>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    map<string, int> m;
    for (auto temp : completion) m[temp] += 1;
    for (auto temp : participant) {
        m[temp] -= 1;
        if(m[temp] <0 ) return temp;
    }
}
