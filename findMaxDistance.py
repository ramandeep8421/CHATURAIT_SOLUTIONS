from collections import deque

def bfs(node, n, adj):
    q = deque([(node, 0)])
    vis = [False] * (n + 1)
    vis[node] = True
    last_vis = None
    
    while q:
        last_vis = q.popleft()
        
        for child in adj[last_vis[0]]:
            if not vis[child]:
                q.append((child, last_vis[1] + 1))
                vis[child] = True
    
    return last_vis

def main():
    n = int(input().strip())
    
    adj = [[] for _ in range(n + 5)]
    
    for _ in range(1, n):
        a, b = map(int, input().strip().split())
        adj[a].append(b)
        adj[b].append(a)
    
    p = bfs(1, n, adj)
    pp = bfs(p[0], n, adj)
    
    print(pp[1])

if __name__ == "__main__":
    main()
