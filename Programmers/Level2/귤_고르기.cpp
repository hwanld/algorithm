#include <string>
#include <vector>
#include <map>
#include <algorithm>


using namespace std;

bool cmp (pair<int, int> a, pair<int, int> b) {
    return a.second > b.second;
}

int solution(int k, vector<int> tangerine) {
    int answer = 0;
    map<int, int> tmap;
    for (auto i : tangerine) {
        auto it = tmap.find(i);
        if (it == tmap.end()) tmap.insert({i, 1});
        else it->second++;
    }
    vector<pair<int, int>> keyValueVector;
    for (auto i : tmap)
        keyValueVector.push_back(i);
    sort(keyValueVector.begin(), keyValueVector.end(), cmp);

    for (auto i : keyValueVector) {
        if (k <= 0) break;
        k -= i.second;
        answer++;
    }
    return answer;
}