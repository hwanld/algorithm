#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(vector<int> queue1, vector<int> queue2) {
    long long sum1 = 0;
    long long sum2 = 0;
    int cnt = 0;

    for (auto i : queue1) sum1 += i;
    for (auto i : queue2) sum2 += i;
    long long sum = sum1 + sum2;
    if (!sum%2) return -1;

    int index1 = 0;
    int index2 = 0;
    int qSize = queue1.size();

    while(cnt <= qSize*4) {
        if (sum1 == sum2) return cnt;
        else if (sum1 > sum2) {
            int target = index1 >= qSize ? queue2[index1-qSize] : queue1[index1];
            sum1-=target;
            sum2+=target;
            index1++;
            cnt++;

        }
        else {
            int target = index2 >= qSize ? queue1[index2-qSize] : queue2[index2];
            sum1+=target;
            sum2-=target;
            index2++;
            cnt++;
        }
    }
    return -1;
}