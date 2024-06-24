#include <string>
#include <vector>
#include <algorithm>
using namespace std;

vector<string> solution(vector<string> strings, int n) {
    vector<string> answer;
    sort(strings.begin(),strings.end(),[n](const string &a, const string &b){
        // c++ sort 함수는 기본적으로 벡터의 시작과 끝 포인터를 지정해주고 3번째 포인터에서는
        // 추가적인 조건 설정 가능 그래서 string 포인터를 지정하고 조건에서 일단 return을
        // 내가 원하는(여기서는 포인터의 n번째 문자들끼리를 비교해 큰 사전순으로 정렬하고)
        // 추가적인 조건이 필요하면 if문을 써서 같을때는 사전순으로 정렬한다.
        if(a[n] == b[n]){
            return a < b;
        }
        return a[n] < b[n];
    });
    
        
    return strings;
}