#include <string>
#include <vector>
#include <iostream>

using namespace std;

int from_to[1001][1001]={};

int get_index (vector<string> id_list, string id) {
    for (int i=0;i<id_list.size();i++) {
        if (id_list[i] == id) return i;
    }
    return -1;
}

vector<int> solution(vector<string> id_list, vector<string> report, int k) {
    vector<int> answer;
    int userNumber = id_list.size();
    
    for (string temp : report) {
        int space_index = temp.find(' ');
        string from = temp.substr(0, space_index);
        string to = temp.substr(space_index+1, temp.size()-space_index);
        
        int from_index = get_index(id_list, from);
        int to_index = get_index(id_list, to);

        if (from_to[from_index][to_index]==0) from_to[from_index][to_index]++;
    }

    vector<int> id_mail;
    for (int i=0; i<userNumber; i++) {
        int temp = 0;
        for (int j=0; j<userNumber; j++) {
            if (from_to[j][i]) temp++;
        }
        if (temp >= k) id_mail.push_back(i);
    }
    
    for (int i=0; i<userNumber;i++) {
        int each_answer = 0;
        for (int temp : id_mail) {
            if (from_to[i][temp]) each_answer++;
        }
        answer.push_back(each_answer);
    }
    return answer;
}
