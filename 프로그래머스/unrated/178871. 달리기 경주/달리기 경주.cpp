#include <string>
#include <vector>
#include <map>

using namespace std;

vector<string> solution(vector<string> players, vector<string> callings) {
    
    map<string, int> m;
    
    int a = 0;
    for (auto p : players) {
        m[p] = a;
        a++;
    }

    for (auto c : callings) {
        int i = m[c];
        
        string temp = players[i-1];
        players[i-1] = c;
        players[i] = temp;
        
        m[c]--;
        m[temp]++;
    }
    
    return players;
}