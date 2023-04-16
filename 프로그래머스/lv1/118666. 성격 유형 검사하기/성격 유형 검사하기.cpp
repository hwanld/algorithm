#include <string>
#include <vector>
#include <map>

using namespace std;

// survey[i][0]은 i+1번 질문의 비동의 관련 선택지
// survey[i][1]은 i+1번 질문의 동의 관련 선택지
// 1 : 매우 비동의 ~ 7 : 매우 동의

string solution(vector<string> survey, vector<int> choices) {
    
    map<char, int> m;
    
    m['R'] = 0;
    m['T'] = 0;
    m['C'] = 0;
    m['F'] = 0;
    m['J'] = 0;
    m['M'] = 0;
    m['A'] = 0;
    m['N'] = 0;
    
    for (int i=0; i<survey.size(); i++) {
        string eachSurvey = survey[i];
        int eachChoice = choices[i];
        
        if (eachChoice == 4) continue;
        if (eachChoice < 4) {
            int score = 4 - eachChoice;
            char ch = eachSurvey[0];
            m[ch] += score;
            continue;
        }
        if (eachChoice > 4) {
            int score = eachChoice - 4;
            char ch = eachSurvey[1];
            m[ch] += score;
            continue;
        }
    }
    
    string answer = "";
    
    if (m['R'] >= m['T']) answer += 'R';
    else answer += 'T';
    
    if (m['C'] >= m['F']) answer += 'C';
    else answer += 'F';
    
    if (m['J'] >= m['M']) answer += 'J';
    else answer += 'M';
    
    if (m['A'] >= m['N']) answer += 'A';
    else answer += 'N';
    
    return answer;
}