def dfs(s):
    if vis[s]:
        return
    vis[s] = True
    for i in adj[s]:
        dfs(i)
    v.append(s)

def solve():
    n, m = map(int, input().split())
    global adj, vis, v
    adj = [[] for _ in range(100005)]
    vis = [False] * 100005
    v = []
    
    for _ in range(m):
        a, b = map(int, input().split())
        adj[b].append(a)
    
    for i in range(1, n + 1):
        if not vis[i]:
            dfs(i)
    
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
    
    len_arr = [0] * 100005
    par = [0] * 100005
    len_arr[1] = 1
    par[1] = 0
    
    for i in t:
        for j in adj[i]:
            if len_arr[i] < len_arr[j] + 1 and len_arr[j]:
                len_arr[i] = len_arr[j] + 1
                par[i] = j
    
    i = n
    v = []
    while i != 0:
        v.append(i)
        i = par[i]
    v.reverse()
    
    if len(v) < 2:
        print("IMPOSSIBLE")
        return
    print(len(v))
    print(" ".join(map(str, v)))

if __name__ == "__main__":
    solve()
