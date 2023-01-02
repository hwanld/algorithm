#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int gcd (int a, int b){
    while (b != 0) {
        int temp = a%b;
        a = b;
        b = temp;
    }
    return a;
}

int solution(vector<int> arrayA, vector<int> arrayB) {
    int answer = 0;

    int gcdA, gcdB;
    for (auto i : arrayA) {
        if (i == arrayA[0]) gcdA = i;
        else gcdA = gcd(gcdA, i);
    }
    for (auto i : arrayB) {
        if (i == arrayB[0]) gcdB = i;
        else gcdB = gcd(gcdB, i);
    }


    for (int i=1; i*i <= gcdA; i++) {
        if (gcdA % i != 0) continue;

        int temp = gcdA / i;

        bool able = true;
        for (auto j : arrayB) {
            if (j % temp == 0) {
                able = false;
                break;
            }
        }

        if (able) {
            answer = max(answer, temp);
            break;
        }
    }

    for (int i=1; i*i <= gcdB; i++) {
        if (gcdB % i != 0) continue;

        int temp = gcdB / i;

        bool able = true;
        for (auto j : arrayA) {
            if (j % temp == 0) {
                able = false;
                break;
            }
        }

        if (able) {
            answer = max(answer, temp);
            break;
        }
    }

    return answer;
}