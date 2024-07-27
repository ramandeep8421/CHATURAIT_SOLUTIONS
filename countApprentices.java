import java.util.*;

class Main {

    static void dfs(int node, int parent, ArrayList<Integer>[] adj, int[] dp) {
        for (int it : adj[node]) {
            if (it != parent) {
                dfs(it, node, adj, dp);
                dp[node] += dp[it];
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayList<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int a = scanner.nextInt();
            a--;
            adj[i].add(a);
            adj[a].add(i);
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        dfs(0, -1, adj, dp);

        for (int i = 0; i < n; i++) {
            System.out.print((dp[i] - 1) + " ");
        }
        System.out.println();
    }
}
