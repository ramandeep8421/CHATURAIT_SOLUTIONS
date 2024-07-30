import heapq
import sys

INF = 1000000000000000

def dijkstra(N, adj):
    distance = [INF] * N
    distance[0] = 0

    pq = []
    heapq.heappush(pq, (0, 0))

    while pq:
        dist, node = heapq.heappop(pq)

        if dist > distance[node]:
            continue

        for weight, to in adj[node]:
            if distance[to] > dist + weight:
                distance[to] = dist + weight
                heapq.heappush(pq, (distance[to], to))

    print(" ".join(map(str, distance)))

def main():
    input = sys.stdin.read
    data = input().split()
    index = 0

    N = int(data[index])
    index += 1
    M = int(data[index])
    index += 1

    adj = [[] for _ in range(N)]

    for _ in range(M):
        a = int(data[index]) - 1
        index += 1
        b = int(data[index]) - 1
        index += 1
        c = int(data[index])
        index += 1
        adj[a].append((c, b))

    dijkstra(N, adj)

if __name__ == "__main__":
    main()
