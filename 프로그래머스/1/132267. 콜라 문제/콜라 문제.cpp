#include <string>
#include <vector>
#include <iostream>
using namespace std;

int solution(int a, int b, int n) {
    int answer = 0;
    int mod=0;
    // n / a = 10 / a = 5(odd)
    while(n >= a){
        mod = n % a;
        n = (n / a)*b;
        answer += n;
        n+=mod;
        cout << n << "\n";
    }
    return answer;
}