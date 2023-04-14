#include <string>
#include <vector>
#include <iostream>
#include <map>

using namespace std;

bool isOver (string today, string target);
string addTerms (string target, int month);

vector<int> solution(string today, vector<string> terms, vector<string> privacies) {
    vector<int> answer;
    map<char, int> m;
    
    for (auto t : terms) {
        char ch = t[0];
        int month;
        if (t.size() == 3) month = stoi(t.substr(2,1));
        if (t.size() == 4) month = stoi(t.substr(2,2));
        if (t.size() == 5) month = stoi(t.substr(2,3));
        m[ch] = month;
    }
    
    for (int i=0; i<privacies.size(); i++) {
        string eachprivacies = privacies[i];
        char eachTerms = eachprivacies[11];
        int termMonth = m[eachTerms];
        
        string target = eachprivacies.substr(0,10);
        string addPriv = addTerms (target, termMonth);
        cout<<addPriv<<endl;
        
        if (isOver(today, addPriv)) answer.push_back(i+1);
    }
    
    return answer;
}

string addTerms (string target, int month) {
    int tyear = stoi(target.substr(0,4));
    int tmonth = stoi(target.substr(5,2));
    int tdate = stoi(target.substr(8,2));
    tmonth += month;
    
    if (tmonth > 12) {
        int addYear = tmonth / 12;
        tmonth %= 12;
        if (tmonth == 0) {
            addYear -= 1;
            tmonth = 12;
        }
        tyear += addYear;
    }
    
    string temp;
    temp+=to_string(tyear);
    temp.push_back('.');
    if (tmonth < 10) temp.push_back('0');
    temp+=to_string(tmonth);
    temp.push_back('.');
    if (tdate < 10) temp.push_back('0');
    temp+=to_string(tdate);
    
    return temp;
}

bool isOver (string today, string target) {
    int nyear = stoi(today.substr(0,4));
    int nmonth = stoi(today.substr(5,2));
    int ndate = stoi(today.substr(8,2));
    
    int tyear = stoi(target.substr(0,4));
    int tmonth = stoi(target.substr(5,2));
    int tdate = stoi(target.substr(8,2));
    
    if (nyear > tyear) return true;
    if (nyear == tyear) {
        if (nmonth > tmonth) return true;
        if (nmonth == tmonth) {
            if (ndate >= tdate) return true;        
        }
    }
    return false;
}