#include<bits/stdc++.h>
using namespace std;
 
int ans = 0;
int MAX = 200005;
 
bool done[200005];
 
void dfs(int pre, int node, vector<int>adj[]){
       
      for(auto neighbor: adj[node]){
            if(neighbor  != pre){
               dfs(node, neighbor, adj);
               if(!done[node] and !done[neighbor])done[neighbor] = done[node] = 1, ans++;
            }
      }
 }

 int independentEdges(int n, vector<int>adj[]){
       

      memset(done, 0, sizeof(done));
 
      dfs(0,1,adj);
 

      return ans;
 }
 
 
int main(){
     
    int n;
    cin >> n;
 
    vector<int>adj[n+6];
 
    for(int i=1;i<n;i++){
       int a,b;
       cin >> a >> b;
       adj[a].push_back(b);
       adj[b].push_back(a);
    }
     
     int ans = independentEdges(n,adj);

     cout << ans;
     
     return 0;
     
}
