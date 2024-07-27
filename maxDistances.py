from collections import deque

def bfs(node, n, adj):
    q = deque([(node, 0)])
    vis = [False] * (n + 1)
    vis[node] = True
    u = None
    
    while q:
        u = q.popleft()
        for child in adj[u[0]]:
            if not vis[child]:
                q.append((child, u[1] + 1))
                vis[child] = True
    
    return u

def dfs(par, node, d, c, adj, dp):
    dp[node][c] = d
    for child in adj[node]:
        if child != par:
            dfs(node, child, d + 1, c, adj, dp)

def maxDistances(n, adj):
    p = bfs(1, n, adj)
    pp = bfs(p[0], n, adj)
    
    dp = [[0, 0] for _ in range(n + 1)]
    
    dfs(0, p[0], 0, 0, adj, dp)
    dfs(0, pp[0], 0, 1, adj, dp)
    
    for i in range(1, n + 1):
        print(max(dp[i][0], dp[i][1]), end=" ")

def main():
    import sys
    input = sys.stdin.read
    data = input().split()
    
    n = int(data[0])
    adj = [[] for _ in range(n + 1)]
    
    index = 1
    for _ in range(1, n):
        a = int(data[index])
        b = int(data[index + 1])
        index += 2
        adj[a].append(b)
        adj[b].append(a)
    
    maxDistances(n, adj)

if __name__ == "__main__":
    main()
