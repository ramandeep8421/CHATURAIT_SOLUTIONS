import java.util.*;

public class Main {
    static final int maxN = 2501;
    static final int maxM = 5001;
    static final long INF = Long.MAX_VALUE / 3; // Adjusted to avoid overflow

    static class Edge {
        int a, b;
        long c;

        Edge(int a, int b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static int N, M;
    static long[] dp = new long[maxN];
    static boolean[] vis = new boolean[maxN];
    static boolean[] visR = new boolean[maxN];
    static List<Integer>[] G = new ArrayList[maxN];
    static List<Integer>[] GR = new ArrayList[maxN];
    static Edge[] edges = new Edge[maxM];

    public static void dfs(int u) {
        vis[u] = true;
        for (int v : G[u]) {
            if (!vis[v]) {
                dfs(v);
            }
        }
    }

    public static void dfsR(int u) {
        visR[u] = true;
        for (int v : GR[u]) {
            if (!visR[v]) {
                dfsR(v);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();

        for (int i = 0; i <= N; i++) {
            G[i] = new ArrayList<>();
            GR[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            long c = scanner.nextLong();
            edges[i] = new Edge(a, b, -c);
            G[a].add(b);
            GR[b].add(a);
        }

        dfs(1);
        dfsR(N);

        Arrays.fill(dp, 2, N + 1, INF);
        boolean improvement = true;

        for (int iter = 0; iter < N && improvement; iter++) {
            improvement = false;
            for (int i = 0; i < M; i++) {
                int u = edges[i].a;
                int v = edges[i].b;
                long w = edges[i].c;

                if (dp[v] > dp[u] + w) {
                    dp[v] = dp[u] + w;
                    improvement = true;

                    if (iter == N - 1 && vis[v] && visR[v]) {
                        System.out.println("-1");
                        scanner.close();
                        return;
                    }
                }
            }
        }

        System.out.println(-dp[N]);
        scanner.close();
    }
}
