def dfs(pre, node, adj, done):
    global ans
    for neighbor in adj[node]:
        if neighbor != pre:
            dfs(node, neighbor, adj, done)
            if not done[node] and not done[neighbor]:
                done[neighbor] = done[node] = True
                ans += 1

def independent_edges(n, adj):
    global ans
    done = [False] * (n + 6)
    dfs(0, 1, adj, done)
    return ans

if __name__ == "__main__":
    import sys
    input = sys.stdin.read
    data = input().split()
    
    n = int(data[0])
    adj = [[] for _ in range(n + 6)]

    index = 1
    for _ in range(1, n):
        a = int(data[index])
        b = int(data[index + 1])
        adj[a].append(b)
        adj[b].append(a)
        index += 2

    ans = 0
    result = independent_edges(n, adj)
    print(result)
