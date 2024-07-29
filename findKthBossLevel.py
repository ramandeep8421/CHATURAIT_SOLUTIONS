import math
from collections import defaultdict, deque

MAXN = 200011
M = 20

up = [[0] * M for _ in range(MAXN)]
level = [0] * MAXN

def dfs(node, par, adj, lvl):
    up[node][0] = par
    level[node] = lvl
    for child in adj[node]:
        if child != par:
            dfs(child, node, adj, lvl + 1)

def preprocess():
    for i in range(1, MAXN):
        for j in range(1, M):
            if up[i][j - 1] != -1:
                par = up[i][j - 1]
                up[i][j] = up[par][j - 1]

def findTheBossAtKthLevel(n, adj, queries):
    results = []
    for x, k in queries:
        if level[x] < k:
            results.append(-1)
            continue

        while k > 0:
            i = int(math.log2(k))
            x = up[x][i]
            if x == -1:
                break
            k -= (1 << i)

        results.append(x)
    
    for res in results:
        print(res)

def main():
    import sys
    input = sys.stdin.read
    data = input().split()
    
    idx = 0
    n = int(data[idx])
    idx += 1
    q = int(data[idx])
    idx += 1
    
    adj = defaultdict(list)
    
    for i in range(2, n + 1):
        a = int(data[idx])
        idx += 1
        adj[i].append(a)
        adj[a].append(i)
    
    dfs(1, -1, adj, 0)
    preprocess()
    
    queries = []
    for i in range(q):
        u = int(data[idx])
        idx += 1
        v = int(data[idx])
        idx += 1
        queries.append((u, v))
    
    findTheBossAtKthLevel(n, adj, queries)

if __name__ == "__main__":
    main()
