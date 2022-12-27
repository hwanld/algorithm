#include <string>
#include <vector>
#include <cmath>

using namespace std;

long long solution(int k, int d) {
    long long answer = 0;

    for (unsigned long long i=0; i<=d; i+=k) {
        long long d2 = d*d;
        long long i2 = i*i;
        long long distance = sqrtl ((long double)d*d-i*i);
        long long max = distance / k + 1;
        answer += max;
    }

    return answer;
}