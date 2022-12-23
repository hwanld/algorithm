#include <string>
#include <vector>
#include <algorithm>

using namespace std;
bool cmp (vector<int> &a, vector<int> &b);
int sort_index = 0;

int solution(vector<vector<int>> data, int col, int row_begin, int row_end) {
    int answer = 0;
    sort_index = col-1;
    sort(data.begin(), data.end(), cmp);

    vector<int> S_i;
    int start_index = row_begin-1;
    int end_index = row_end-1;

    for (int i=start_index; i<=end_index; i++) {
        int temp = 0;
        for (auto j : data[i])
            temp += j%(i+1);
        S_i.push_back(temp);
    }

    for (int i=0; i<S_i.size(); i++) {
        if (i==0) answer = S_i[0];
        else answer ^= S_i[i];
    }

    return answer;
}

bool cmp (vector<int> &a, vector<int> &b) {
    if (a[sort_index] > b[sort_index]) return false;
    else if (a[sort_index] < b[sort_index]) return true;
    else {
        if(a[0] < b[0]) return false;
        return true;
    }
}