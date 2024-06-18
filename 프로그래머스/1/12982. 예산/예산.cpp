#include <iostream>
#include <stdio.h>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> d, int budget) {
    int answer = 0;
    sort(d.begin(),d.end());
    for(auto it : d){
        if(budget - it >= 0){
            budget = budget - it;
            answer++;
        }
        else{
            break;
        }
    }
    return answer;
}