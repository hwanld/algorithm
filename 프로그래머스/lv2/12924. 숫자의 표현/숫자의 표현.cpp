#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(int n) {
    int answer = 0;
    int index = 1;
    int tempSum = 0;
    
    for (index; ; index++) {
        tempSum += index;
        if (tempSum >= n) break;
    }
    
    for (int i=1; i<=index; i++) {
        if (i%2==0) {
            if (n % i == i/2) answer++;
        }
        else {
            if (n % i == 0) answer++;    
        }
    }
    return answer;
}