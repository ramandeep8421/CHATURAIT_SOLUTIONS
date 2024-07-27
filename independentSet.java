import java.util.*;

public class Main {
    static int ans = 0;
    static final int MAX = 200005;
    static boolean[] done = new boolean[MAX];

    public static void dfs(int pre, int node, ArrayList<Integer>[] adj) {
        for (int neighbor : adj[node]) {
            if (neighbor != pre) {
                dfs(node, neighbor, adj);
                if (!done[node] && !done[neighbor]) {
                    done[neighbor] = done[node] = true;
                    ans++;
                }
            }
        }
    }

    public static int independentEdges(int n, ArrayList<Integer>[] adj) {
        Arrays.fill(done, false);
        dfs(0, 1, adj);
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayList<Integer>[] adj = new ArrayList[n + 6];
        for (int i = 0; i < n + 6; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        int result = independentEdges(n, adj);
        System.out.println(result);

        scanner.close();
    }
}
