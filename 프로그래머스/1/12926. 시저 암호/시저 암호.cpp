#include <string>
#include <vector>

using namespace std;

string solution(string s, int n) {
    string answer = "";
    // shift 연산이다 이거지
    
    for(int i=0;i<s.size();i++){
        if(s[i] == ' '){
            answer.push_back(s[i]);
            continue;
        }
        if((s[i] >= 'a' && s[i] < 'z')){
            if(s[i] + n > 'z'){
                answer.push_back((s[i] - 25) + n-1);
            }
            else{
                answer.push_back(s[i] + n);            
            }
        }
        else {
            if(s[i] + n > 'Z'){
                answer.push_back((s[i] - 25) + n-1);
            }
            else{
                answer.push_back(s[i] + n);            
            }
            
        }
    }
    return answer;
}