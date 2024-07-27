def dfs(node, parent, adj, dp):
    for it in adj[node]:
        if it != parent:
            dfs(it, node, adj, dp)
            dp[node] += dp[it]

def solve():
    import sys
    input = sys.stdin.read
    data = input().split()
    
    n = int(data[0])
    adj = [[] for _ in range(n)]
    dp = [1] * n
    
    index = 1
    for i in range(1, n):
        a = int(data[index]) - 1
        index += 1
        adj[i].append(a)
        adj[a].append(i)
    
    dfs(0, -1, adj, dp)
    
    print(" ".join(map(lambda x: str(x - 1), dp)))

if __name__ == "__main__":
    solve()
