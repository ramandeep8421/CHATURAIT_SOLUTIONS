#include<bits/stdc++.h>
using namespace std;


void findTheBossAtKthLevel(int n, vector<int>adj[], vector<pair<int,int>>queries){
       

}

 
 
int32_t main(){
    
     int n,q;
     cin >> n >> q;
     
     vector<int>adj[n+1];
 
     for(int i=2;i<=n;i++){
     	  int a;
     	  cin >> a;
          adj[i].push_back(a);
          adj[a].push_back(i);
     }
 
     dfs(1,-1,adj,0);
     preprocess();

     vector<pair<int,int>>queries;

     for(int i=0;i<q;i++){
         int u,v;
         cin >> u >> v;
         queries.push_back({u,v});
     }

     findTheBossAtKthLevel(n, adj, queries);

}
