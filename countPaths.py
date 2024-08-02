MOD = 1_000_000_007

def dfs(s, adj, vis, v):
    if vis[s]:
        return
    vis[s] = True
    for i in adj[s]:
        dfs(i, adj, vis, v)
    v.append(s)

def solve():
    import sys
    input = sys.stdin.read
    data = input().split()
    
    index = 0
    n = int(data[index])
    index += 1
    m = int(data[index])
    index += 1

    adj = [[] for _ in range(n + 1)]  # Adjust size based on n
    vis = [False] * (n + 1)
    v = []

    for _ in range(m):
        a = int(data[index])
        index += 1
        b = int(data[index])
        index += 1
        adj[b].append(a)

    for i in range(1, n + 1):
        if not vis[i]:
            dfs(i, adj, vis, v)

    t = []
    i, f = 0, 0
    while i < len(v):
        if f == 0:
            if v[i] == 1:
                f = 1
                t.append(1)
        else:
            t.append(v[i])
        i += 1

    path = [0] * (n + 1)
    path[1] = 1
    for x in t:
        for j in adj[x]:
            path[x] = (path[x] + path[j]) % MOD

    print(path[n])

def main():
    t = 1
    # t = int(input())
    for _ in range(t):
        solve()
        # print()

if __name__ == "__main__":
    main()
