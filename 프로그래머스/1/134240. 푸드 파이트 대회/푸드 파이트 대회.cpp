#include <string>
#include <vector>
#include <algorithm>
using namespace std;

string solution(vector<int> food) {
    string answer = "";
    string temp;
    for(int i= 1;i<food.size();i++){
        for(int j=0;j<food[i]/2;j++) temp.push_back(i + '0');
    }
    answer+=temp;
    answer.push_back('0');
    reverse(temp.begin(),temp.end());
    answer+=temp;
    return answer;
}