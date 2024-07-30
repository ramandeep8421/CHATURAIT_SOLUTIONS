#include <bits/stdc++.h>
using namespace std;

#define MOD 1000000007
#define INF 10000000000000007
#define MAX 1000000
 
void dijkstra(vector<pair<int,int>>v[] , int start , int size)
{
	vector<int>d(MAX , INF);
 
	d[start] = 0;
	set<pair<int,int>>st;
	st.insert({0 , start});
	
	vector<int>parent(size + 1);
	parent[1] = -1;
 
	while(!st.empty())
	{
        int node = st.begin()->second;
        st.erase(st.begin());
        
        if(node == size)
        break;
 
        //go to adjacent elements of v.
        for(auto edge: v[node])
        {
        	int to = edge.first;
        	int wt = edge.second;
        	if(d[to] > d[node] + wt)
        	{
        		st.erase({d[to] , to});
        		d[to] = d[node] + wt;
        		parent[to] = node;
        		st.insert({d[to] , to});
 
        	}
        }
 
	}
 
  if(d[size] == INF)
  {
   cout << "IMPOSSIBLE";
    return;
  }
 
  vector<int>path;
 
  
  int k = size;
  while(k != -1){
      path.push_back(k);
      k = parent[k];
  }
 reverse(path.begin() , path.end());
 cout << path.size();
 cout << endl;
  
}
 
int main()
{
   
 
	int n , m;
	cin >> n >> m;
	vector<pair<int,int>>graph[MAX];
	int u , v;
	for(int i=0;i<m;i++)
	{
      cin >> u >> v;
       graph[u].push_back({v,1});
       graph[v].push_back({u,1});
	}
 
	//call dijkstra from 1 ..
 
	dijkstra(graph , 1 , n);
 
}
