import sys
import heapq

INF = 10000000000000007
MAX = 1000000

def dijkstra(graph, start, size):
    d = [INF] * MAX
    d[start] = 0

    st = []
    heapq.heappush(st, (0, start))

    parent = [-1] * (size + 1)
    parent[1] = -1

    while st:
        dist, node = heapq.heappop(st)

        if node == size:
            break

        for to, wt in graph[node]:
            if d[to] > d[node] + wt:
                d[to] = d[node] + wt
                parent[to] = node
                heapq.heappush(st, (d[to], to))

    if d[size] == INF:
        print("IMPOSSIBLE")
        return

    path = []
    k = size
    while k != -1:
        path.append(k)
        k = parent[k]

    path.reverse()
    print(len(path))
   

def main():
    input = sys.stdin.read
    data = input().split()
    index = 0

    n = int(data[index])
    index += 1
    m = int(data[index])
    index += 1

    graph = [[] for _ in range(MAX)]

    for _ in range(m):
        u = int(data[index])
        index += 1
        v = int(data[index])
        index += 1
        graph[u].append((v, 1))
        graph[v].append((u, 1))

    dijkstra(graph, 1, n)

if __name__ == "__main__":
    main()
