#include <vector>
#include <iostream>
using namespace std;

bool checkPrime (int k) {
    for (int i=2;i<k;i++)
        if (k % i == 0) return false;
    return true;
}

int solution(vector<int> nums) {
    int answer = 0;
    int size = nums.size();

    for (int i=0;i<size-2;i++) {
        for (int j=i+1;j<size-1;j++) {
            for (int k=j+1;k<size;k++) {
                int sum = nums[i] + nums[j] + nums[k];
                if (checkPrime(sum)) answer++;
            }
        }
    }
    return answer;
}
