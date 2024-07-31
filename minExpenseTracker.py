import heapq

INF = 10**18

def dijkstra(src, N, adj):
    distance = [INF] * (N + 1)
    distance[src] = 0

    pq = [(0, src)]

    while pq:
        cost, node = heapq.heappop(pq)

        if distance[node] < cost:
            continue

        for to, c in adj[node]:
            if distance[to] > c + distance[node]:
                distance[to] = c + distance[node]
                heapq.heappush(pq, (distance[to], to))

    return distance

def main():
    import sys
    input = sys.stdin.read
    data = input().split()

    idx = 0
    n = int(data[idx])
    idx += 1
    m = int(data[idx])
    idx += 1

    adj = [[] for _ in range(n + 1)]
    adj_rev = [[] for _ in range(n + 1)]

    for _ in range(m):
        a = int(data[idx])
        idx += 1
        b = int(data[idx])
        idx += 1
        c = int(data[idx])
        idx += 1
        adj[a].append((b, c))
        adj_rev[b].append((a, c))

    minCosts = dijkstra(1, n, adj)
    minCostsRev = dijkstra(n, n, adj_rev)

    ans = INF

    for i in range(1, n + 1):
        for to, c in adj[i]:
            if minCosts[i] == INF or minCostsRev[to] == INF:
                continue
            ans = min(ans, minCosts[i] + c // 2 + minCostsRev[to])

    print(ans)

if __name__ == "__main__":
    main()
