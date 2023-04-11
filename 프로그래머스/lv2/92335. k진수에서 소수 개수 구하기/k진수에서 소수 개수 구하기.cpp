#include <string>
#include <vector>
#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

bool isPrime (long long int n);
string getDiv (int n, int k);

int solution(int n, int k) {
    int answer = 0;
    
    string div = getDiv (n,k);
    
    if (div.size() == 1) {
        if (isPrime(div[0]-'0')) return 1;
        return 0;
    }
    
    int len = 1;
    int start = 0;
    for (int i=0; i<div.size(); i++) {
        if (div[i]-'0'== 0) {
            string temp = div.substr(start, max(1,len-1));
            if (isPrime(stoll (temp))) answer++;
            start = i+1;
            len = 0;
        }
        
        if (i==div.size()-1 && div[i]-'0' != 0) {
            string temp = div.substr(start, len);
            start = i+1;
            len = 1;
            if (isPrime(stoll (temp))) answer++;
            
        }
        len++;
    }
    
    return answer;
}

bool isPrime (long long int n) {
    if (n==0) return false;
    if (n==1) return false;
    for (int i=2; i<=sqrt(n); i++) {
        if (n%i==0) return false;
    }
    return true;
}

string getDiv (int n, int k) {
    string temp;
    while (n!=0) {
        int div = n%k;
        n = n/k;
        temp.push_back( (char) div + '0' );
    }
    
    string div;
    for (int i=temp.size()-1; i>=0; i--) {
        div.push_back(temp[i]);
    }
    return div;
}
