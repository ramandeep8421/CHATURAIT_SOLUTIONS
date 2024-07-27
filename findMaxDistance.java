import java.util.*;

public class Main {
    
    static class Pair {
        int first;
        int second;
        
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    
    static Pair bfs(int node, int n, ArrayList<Integer>[] adj) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(node, 0));
        
        boolean[] vis = new boolean[n + 1];
        vis[node] = true;
        
        Pair lastVis = null;
        
        while (!q.isEmpty()) {
            lastVis = q.poll();
            
            for (int child : adj[lastVis.first]) {
                if (!vis[child]) {
                    q.add(new Pair(child, lastVis.second + 1));
                    vis[child] = true;
                }
            }
        }
        
        return lastVis;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] adj = new ArrayList[n + 5];
        
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 1; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        
        Pair p = bfs(1, n, adj);
        Pair pp = bfs(p.first, n, adj);
        
        System.out.println(pp.second);
        
        scanner.close();
    }
}
