#include <bits/stdc++.h>
using namespace std;

void dfs(int node, int parent, vector<int>adj[], vector<int>&dp){
     for(auto it: adj[node]){
         if(it != parent){
              dfs(it,node,adj,dp);
              dp[node] += dp[it];
         }
     }
}

void countApprentices(int n,vector<int>adj[]){
   
    vector<int>dp(n,1);
       dfs(0,-1,adj,dp);

       for(int i=0;i<n;i++)
          cout << dp[i]-1 << " ";
}


int main() {
    
     int n;

       cin >> n;

       vector<int>adj[n];

       for(int i=1;i<n;i++){
            int a;
            cin >> a;
            a--;
            adj[i].push_back(a);
            adj[a].push_back(i);
       }


    return 0;
}



