#include <string>
#include <vector>
#include <iostream>

using namespace std;

long long solution(int cap, int n, vector<int> deliveries, vector<int> pickups) {
    
    long long answer = 0;
    
    int d_last = n-1;
    int p_last = n-1;
    bool d_complete = false;
    bool p_complete = false;
    
    for (int i=n-1; i>=0; i--) {
        if (deliveries[i] != 0) {
            d_last = i;
            break;
        }
        
        if (deliveries[i] == 0 && i==0) 
            d_last = 0;
    }
    
    for (int i=n-1; i>=0; i--) {
        if (pickups[i] != 0) {
            p_last = i;
            break;
        }
        
        if (pickups[i] == 0 && i==0)
            p_last = 0;
    }
    
    if (d_last == 0 && p_last == 0) {
        if (deliveries[0] == 0 && pickups[0] == 0) {
            return 0;
        }
    }
    
    while ( 1 ) {
        if (d_last >= p_last) answer += ((d_last+1) * 2);
        else answer += ((p_last+1) * 2);
        
        int cur = cap;
        while (1) {
            if (cur == 0 && deliveries[d_last] != 0) break;
            if (d_complete) break;
            if (deliveries[d_last] > cur) {
                deliveries[d_last] -= cur;
                cur = 0;
            }
            else {
                cur -= deliveries[d_last];
                deliveries[d_last] = 0;
                
                if (d_last == 0) {
                    d_complete = true;
                    cur = 0;
                }
                d_last--;
            }
        }

        cur = cap;
        while (1) {
            if (cur == 0 && pickups[p_last] != 0) break;
            if (p_complete) break;
            if (pickups[p_last] > cur) {
                pickups[p_last] -= cur;
                cur = 0;
            }
            else {
                cur -= pickups[p_last];
                pickups[p_last] = 0;
                
                if (p_last == 0) {
                    p_complete = true;
                    cur = 0;
                }
                p_last--;
            }
        }
        
        if (d_complete == true && p_complete == true) break;
    }
    
    return answer;
}