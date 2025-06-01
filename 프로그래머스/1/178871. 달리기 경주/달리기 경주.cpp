#include <string>
#include <vector>
#include<map>
using namespace std;

vector<string> solution(vector<string> players, vector<string> callings) {
    vector<string> answer;
    map<string,int> ranks;
    for(int i=0;i<players.size();i++){
        ranks.insert({players[i],i});
    }
    for(auto it:callings){
        int rank = ranks[it];
        
        swap(players[rank],players[rank-1]);
        ranks[it]--;
        ranks[players[rank]]++;
    }
    return players;
}