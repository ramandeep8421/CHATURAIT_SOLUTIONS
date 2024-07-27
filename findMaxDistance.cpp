#include<bits/stdc++.h>
using namespace std;

pair<int,int>bfs(int node, int n, vector<int>adj[]){
        queue<pair<int,int>>q;
        q.push({node, 0});
 
        vector<bool>vis(n+1, false);
 
        vis[node] = true;
 
        pair<int,int>lastVis;
 
        while(!q.empty()){
            
              lastVis = q.front();
              q.pop();
 
              for(auto child: adj[lastVis.first]){
                   if(!vis[child]){
                       q.push({child, lastVis.second + 1});
                       vis[child] = true;
                   }
              }
        }
 
 
       return lastVis;
 
 
}
int main(){
    
    int n;
    cin >> n;
 
    vector<int>adj[n+5];
 
    for(int i=1;i<n;i++){
       int a,b;
       cin >> a >> b;
       adj[a].push_back(b);
       adj[b].push_back(a);
        
    }
    
     pair<int,int>p = bfs(1, n,adj);
      
      pair<int,int>pp = bfs(p.first, n, adj);
 
   
 
     cout<< pp.second;
 
    return 0;
}
