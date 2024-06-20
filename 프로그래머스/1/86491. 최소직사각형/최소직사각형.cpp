#include <string>
#include <vector>
#include <iostream>

using namespace std;


// 가로 max 세로 min
int solution(vector<vector<int>> sizes) {
    int answer = 0;
    int temp;
    int max_width = 0;
    int max_height = 0;
    for(int i=0;i<sizes.size();i++){
        if(sizes[i][0] < sizes[i][1]){
            temp = sizes[i][0];
            sizes[i][0] = sizes[i][1];
            sizes[i][1] = temp;
        }
        if(max_width < sizes[i][0]) max_width = sizes[i][0];
        if(max_height < sizes[i][1]) max_height = sizes[i][1];
        // cout << sizes[i][0] << "\n";
        // cout << sizes[i][1] << "\n";
        
    }
    return answer = max_width * max_height;
}