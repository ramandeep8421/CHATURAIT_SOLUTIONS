MAXN = 200011

adj = [[] for _ in range(MAXN)]
sub = [1] * MAXN
start = [0] * MAXN
value = [0] * MAXN
arr = [0] * MAXN
t = [0] * (4 * MAXN)
timer = 0

def build(v, tl, tr):
    if tl == tr:
        t[v] = arr[tl]
    else:
        tm = (tl + tr) // 2
        build(v * 2, tl, tm)
        build(v * 2 + 1, tm + 1, tr)
        t[v] = t[v * 2] + t[v * 2 + 1]

def update(v, tl, tr, pos, new_val):
    if tl == tr:
        t[v] = new_val
    else:
        tm = (tl + tr) // 2
        if pos <= tm:
            update(v * 2, tl, tm, pos, new_val)
        else:
            update(v * 2 + 1, tm + 1, tr, pos, new_val)
        t[v] = t[v * 2] + t[v * 2 + 1]

def sum(v, tl, tr, l, r):
    if l > r:
        return 0
    if l == tl and r == tr:
        return t[v]
    tm = (tl + tr) // 2
    return sum(v * 2, tl, tm, l, min(r, tm)) + sum(v * 2 + 1, tm + 1, tr, max(l, tm + 1), r)

def dfs(node, par):
    global timer
    start[node] = timer = timer + 1
    arr[start[node]] = value[node]
    for child in adj[node]:
        if child != par:
            dfs(child, node)
            sub[node] += sub[child]

def solve():
    import sys
    input = sys.stdin.read
    data = input().split()
    
    idx = 0
    n = int(data[idx])
    q = int(data[idx + 1])
    idx += 2
    
    for i in range(1, n + 1):
        value[i] = int(data[idx])
        idx += 1
    
    for _ in range(1, n):
        a = int(data[idx])
        b = int(data[idx + 1])
        idx += 2
        adj[a].append(b)
        adj[b].append(a)
    
    dfs(1, -1)
    build(1, 1, n)
    
    results = []
    for _ in range(q):
        t = int(data[idx])
        if t == 1:
            s = int(data[idx + 1])
            x = int(data[idx + 2])
            idx += 3
            update(1, 1, n, start[s], x)
            arr[start[s]] = x
        else:
            s = int(data[idx + 1])
            idx += 2
            results.append(sum(1, 1, n, start[s], start[s] + sub[s] - 1))
    
    print("\n".join(map(str, results)))

if __name__ == "__main__":
    solve()
