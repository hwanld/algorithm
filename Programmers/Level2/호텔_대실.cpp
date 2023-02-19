#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int convertStringToInt(string str);
int checkOverflow (int n);

bool compare (vector<string> a, vector<string> b) {
    int aTime = convertStringToInt(a[0]);
    int bTime = convertStringToInt(b[0]);
    return aTime < bTime;
}

int solution(vector<vector<string>> book_time) {
    int answer = 0;

    vector<int> endingTimeVector;

    sort(book_time.begin(), book_time.end());

    for (auto i : book_time) {
        int startingTime = convertStringToInt(i[0]);
        int endingTime = convertStringToInt(i[1]);

        sort(endingTimeVector.begin(), endingTimeVector.end());

        if (endingTimeVector.size() == 0) {
            answer++;
            endingTimeVector.push_back(checkOverflow(endingTime+10));
        }

        else if (endingTimeVector[0] > startingTime) {
            answer++;
            endingTimeVector.push_back(checkOverflow(endingTime+10));
        }
        else {
            endingTimeVector[0] = checkOverflow(endingTime + 10);
        }

    }

    return answer;
}

int convertStringToInt (string str) {
    str.erase(str.find(":"), 1);
    return stoi(str);
}

int checkOverflow (int i) {
    if ((i % 100) > 59) {
        return i+40;
    }
    return i;
}

