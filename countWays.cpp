#include<bits/stdc++.h>
using namespace std;
const long long  md = 1000000007;
 
vector<int> adj[100005];
bool vis[100005];
vector<int>v;
void dfs(int s){
    if (vis[s]) return;
    vis[s]=1;
    for (auto i: adj[s]) dfs(i);
    v.push_back(s);
}
void solve(){
    int n,m; cin >> n >> m;
    for(int i=0;i<m;i++){
        int a,b; cin >> a >> b;
        adj[b].push_back(a);
    }
    for(int i=1;i < n+1; i++){
        if (!vis[i]) dfs(i);
    }
    // for (auto i:v) put(i);
    vector<int> t; 
    int i=0,f=0;
    while(i<v.size()){
        if (f==0){
            if (v[i]==1) {f=1; t.push_back(1);}
        }
        else{
            t.push_back(v[i]);
        }
        i++;
    }
    int path[100005]={0};
    path[1]=1;
    for (auto i: t) {
        for (auto j: adj[i]){
            (path[i]+=path[j])%=md;
        }
    }
   cout << path[n];
}
int main(){
 
    int t=1;
    //cin>>t;
    while(t--){
        solve();
        //cout<<'\n';
    }
}
