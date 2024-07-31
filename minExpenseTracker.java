import java.util.*;

public class Main {
    static final long INF = (long) 1e18;

    public static List<Long> dijkstra(int src, int N, List<List<Pair>> adj) {
        List<Long> distance = new ArrayList<>(Collections.nCopies(N + 1, INF));
        distance.set(src, 0L);

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(p -> p.cost));
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int node = p.node;
            long cost = p.cost;

            if (distance.get(node) < cost) continue;

            for (Pair neighbor : adj.get(node)) {
                int to = neighbor.node;
                long c = neighbor.cost;

                if (distance.get(to) > c + distance.get(node)) {
                    distance.set(to, c + distance.get(node));
                    pq.add(new Pair(to, distance.get(to)));
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 0, m = 0;
        if (sc.hasNextInt()) {
            n = sc.nextInt();
        } else {
            System.out.println("Invalid input for n");
            return;
        }

        if (sc.hasNextInt()) {
            m = sc.nextInt();
        } else {
            System.out.println("Invalid input for m");
            return;
        }

        List<List<Pair>> adj = new ArrayList<>(n + 1);
        List<List<Pair>> adjRev = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
            adjRev.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = 0, b = 0;
            long c = 0;
            if (sc.hasNextInt()) {
                a = sc.nextInt();
            } else {
                System.out.println("Invalid input for a");
                return;
            }

            if (sc.hasNextInt()) {
                b = sc.nextInt();
            } else {
                System.out.println("Invalid input for b");
                return;
            }

            if (sc.hasNextLong()) {
                c = sc.nextLong();
            } else {
                System.out.println("Invalid input for c");
                return;
            }

            adj.get(a).add(new Pair(b, c));
            adjRev.get(b).add(new Pair(a, c));
        }

        List<Long> minCosts = dijkstra(1, n, adj);
        List<Long> minCostsRev = dijkstra(n, n, adjRev);

        long ans = INF;

        for (int i = 1; i <= n; i++) {
            for (Pair neighbor : adj.get(i)) {
                int to = neighbor.node;
                long c = neighbor.cost;
                if (minCosts.get(i) == INF || minCostsRev.get(to) == INF) {
                    continue;
                }
                ans = Math.min(ans, minCosts.get(i) + c / 2 + minCostsRev.get(to));
            }
        }

        System.out.println(ans);
    }
}

class Pair {
    int node;
    long cost;

    Pair(int node, long cost) {
        this.node = node;
        this.cost = cost;
    }
}
