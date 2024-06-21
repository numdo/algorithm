#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
#include <set>
using namespace std;

vector<int> solution(vector<int> numbers) {
    vector<int> answer;
    
    // 내 풀이
    
    // int temp;
    // int cnt = 1;
    // sort(numbers.begin(),numbers.end());
    // for(int i=0;i<numbers.size()-1;i++){
    //     for(int j=i+1;j<numbers.size();j++){
    //         temp = numbers[i] + numbers[j];
    //         if(find(answer.begin(),answer.end(),temp) == answer.end()){
    //             cout << temp << "\n";
    //             answer.push_back(temp);
    //         }        
    //     }
    // }
    // sort(answer.begin(),answer.end());
    
    
    
    // 좋은 풀이
    // set 을 사용한 풀이 set은 중복이 허용되지 않으니까 사용
    set<int> st;
    for(int i = 0;i<numbers.size();++i){
        for(int j = i+1 ; j< numbers.size();++j){
            st.insert(numbers[i] + numbers[j]);
        }
    }
    answer.assign(st.begin(), st.end());
    return answer;
}