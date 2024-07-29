import java.util.*;

public class Main {
    static int MAXN = 200011;
    static int M = 20;
    static int[][] up = new int[MAXN][M];
    static int[] level = new int[MAXN];

    public static void dfs(int node, int par, List<Integer>[] adj, int lvl) {
        up[node][0] = par;
        level[node] = lvl;
        for (int child : adj[node]) {
            if (child != par) {
                dfs(child, node, adj, lvl + 1);
            }
        }
    }

    public static void preprocess() {
        for (int i = 1; i < MAXN; i++) {
            for (int j = 1; j < M; j++) {
                if (up[i][j - 1] != -1) {
                    int par = up[i][j - 1];
                    up[i][j] = up[par][j - 1];
                }
            }
        }
    }

    public static void findTheBossAtKthLevel(int n, List<Integer>[] adj, List<int[]> queries) {
        for (int[] query : queries) {
            int x = query[0];
            int k = query[1];

            if (level[x] < k) {
                System.out.println(-1);
                continue;
            }

            while (k > 0) {
                int i = (int) (Math.log(k) / Math.log(2));
                x = up[x][i];
                if (x == -1) break;
                k -= (1 << i);
            }

            System.out.println(x);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 2; i <= n; i++) {
            int a = sc.nextInt();
            adj[i].add(a);
            adj[a].add(i);
        }

        dfs(1, -1, adj, 0);
        preprocess();

        List<int[]> queries = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            queries.add(new int[]{u, v});
        }

        findTheBossAtKthLevel(n, adj, queries);
        sc.close();
    }
}
