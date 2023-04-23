#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> people, int limit) {
    int answer = 0;
    int maxP = people.size()-1;
    int minP = 0;
    sort(people.begin(), people.end());
    
    while (maxP >= minP) {
        if (people[maxP] + people[minP] <= limit) {
            answer++;
            maxP--;
            minP++;
        }
        else {
            answer++;
            maxP--;
        }
    }
    
    return answer;
}