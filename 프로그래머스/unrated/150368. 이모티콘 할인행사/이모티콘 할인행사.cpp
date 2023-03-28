#include <string>
#include <vector>
#include <stack>
#include <algorithm>
#include <iostream>

using namespace std;

int maxPlus, maxSell;
stack<vector<int>> st;

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {
    vector<int> answer;
    vector<int> discount = {10, 20, 30, 40};
    
    for (auto i : discount) {
        vector<int> tempVector;
        tempVector.push_back(i);
        st.push(tempVector);
    }
    
    while(!st.empty()) {
        vector<int> temp = st.top();
        st.pop();
        
        if (temp.size() != emoticons.size()) {
            for (int i=10; i<=40; i+=10) {
                vector<int> forTemp = temp;
                forTemp.push_back(i);
                st.push(forTemp);
            }
        }
        
        else { 
            int plus = 0;
            int sell = 0;
            
            for (auto user : users) {
                int tempSum = 0;
                
                int userSales = user[0];
                int userLimit = user[1];
                
                for (int i=0; i<temp.size(); i++) {
                    
                    if (temp[i] >= userSales) {
                        tempSum += emoticons[i]*(100-temp[i])*0.01;
                    }
                }
                
                if (tempSum >= userLimit) plus++;
                else sell += tempSum;
            }
            if (plus > maxPlus) {
                maxPlus = plus;
                maxSell = sell;
            }
            else if (plus == maxPlus) {
                if (sell > maxSell)
                    maxSell = sell;
            }
        }
    }
    
    answer.push_back(maxPlus);
    answer.push_back(maxSell);
    return answer;
}