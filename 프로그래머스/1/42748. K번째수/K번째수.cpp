#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    vector<int> temp;
    for(int i=0;i<commands.size();i++){
        
        for(int j=commands[i][0]-1;j<=commands[i][1]-1;j++){
            cout << array[j];
            temp.push_back(array[j]);    
        }
        cout << "\n";
        sort(temp.begin(),temp.end());
        answer.push_back(temp[commands[i][2]-1]);
        temp.clear();
    }
    return answer;
}