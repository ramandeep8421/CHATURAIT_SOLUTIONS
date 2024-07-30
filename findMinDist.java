import java.util.*;

public class Main {
    static final long INF = 1000000000000000L;

    static void dijkstra(int N, List<List<Pair>> adj) {
        long[] distance = new long[N];
        Arrays.fill(distance, INF);
        distance[0] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(pair -> pair.first));
        pq.add(new Pair(0, 0));

        while (!pq.isEmpty()) {
            long dist = pq.peek().first;
            int node = pq.peek().second;
            pq.poll();

            if (dist > distance[node]) continue;

            for (Pair neighbor : adj.get(node)) {
                int to = neighbor.second;
                long weight = neighbor.first;

                if (distance[to] > dist + weight) {
                    distance[to] = dist + weight;
                    pq.add(new Pair(distance[to], to));
                }
            }
        }

        for (long dist : distance) {
            System.out.print(dist + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = sc.nextInt();
            adj.get(a).add(new Pair(c, b));
        }

        dijkstra(N, adj);
    }
}

class Pair {
    long first;
    int second;

    Pair(long first, int second) {
        this.first = first;
        this.second = second;
    }
}
