#include <string>
#include <vector>

using namespace std;

string solution(string s) {
    string answer = "";
    int cnt = 0;
    for(int i=0;i<s.size();i++){
        if(s[i] == ' '){
            cnt = 0;
            answer.push_back(s[i]);
            continue;            
        }
        if(cnt == 0 || cnt%2 == 0){
            if('a'<= s[i] && s[i] <= 'z'){
                answer.push_back(s[i]-32);            
            }
            else{
                answer.push_back(s[i]);            
            }
        }
        else{
            if('A'<= s[i] && s[i] <= 'Z'){
                answer.push_back(s[i]+32);            
            }
            else{
                answer.push_back(s[i]);            
            }
        }
        cnt++;
    }
    return answer;
}