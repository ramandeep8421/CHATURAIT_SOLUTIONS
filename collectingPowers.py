import sys
from collections import defaultdict, deque

INF = float('inf')
maxN = 2501
maxM = 5001

class Edge:
    def __init__(self, a, b, c):
        self.a = a
        self.b = b
        self.c = c

edges = []
dp = [INF] * maxN
vis = [False] * maxN
visR = [False] * maxN
G = defaultdict(list)
GR = defaultdict(list)

def dfs(u):
    vis[u] = True
    for v in G[u]:
        if not vis[v]:
            dfs(v)

def dfsR(u):
    visR[u] = True
    for v in GR[u]:
        if not visR[v]:
            dfsR(v)

def main():
    input = sys.stdin.read
    data = input().split()
    index = 0
    N = int(data[index])
    index += 1
    M = int(data[index])
    index += 1

    for i in range(M):
        a = int(data[index])
        index += 1
        b = int(data[index])
        index += 1
        c = int(data[index])
        index += 1
        edges.append(Edge(a, b, -c))
        G[a].append(b)
        GR[b].append(a)

    dfs(1)
    dfsR(N)

    for i in range(2, N + 1):
        dp[i] = INF

    dp[1] = 0
    improvement = True

    for iter in range(N):
        if not improvement:
            break
        improvement = False
        for i in range(M):
            u = edges[i].a
            v = edges[i].b
            w = edges[i].c

            if dp[v] > dp[u] + w:
                dp[v] = dp[u] + w
                improvement = True

                if iter == N - 1 and vis[v] and visR[v]:
                    print("-1")
                    return

    print(-dp[N])

if __name__ == "__main__":
    main()
