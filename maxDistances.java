import java.util.*;

public class Main {
    
    static class Pair {
        int first, second;
        
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    
    static Pair bfs(int node, int n, List<Integer>[] adj) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(node, 0));
        
        boolean[] vis = new boolean[n + 1];
        vis[node] = true;
        
        Pair u = null;
        
        while (!q.isEmpty()) {
            u = q.poll();
            
            for (int child : adj[u.first]) {
                if (!vis[child]) {
                    q.add(new Pair(child, u.second + 1));
                    vis[child] = true;
                }
            }
        }
        
        return u;
    }
    
    static void dfs(int par, int node, int d, int c, List<Integer>[] adj, int[][] dp) {
        dp[node][c] = d;
        for (int child : adj[node]) {
            if (child != par) {
                dfs(node, child, d + 1, c, adj, dp);
            }
        }
    }
    
    static void maxDistances(int n, List<Integer>[] adj) {
        Pair p = bfs(1, n, adj);
        Pair pp = bfs(p.first, n, adj);
        
        int[][] dp = new int[n + 1][2];
        
        dfs(0, p.first, 0, 0, adj, dp);
        dfs(0, pp.first, 0, 1, adj, dp);
        
        for (int i = 1; i <= n; i++) {
            System.out.print(Math.max(dp[i][0], dp[i][1]) + " ");
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 1; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        
        maxDistances(n, adj);
        
        scanner.close();
    }
}
