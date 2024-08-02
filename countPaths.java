import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final int MOD = 1_000_000_007;
    static List<Integer>[] adj = new ArrayList[100005];
    static boolean[] vis = new boolean[100005];
    static List<Integer> v = new ArrayList<>();

    static void dfs(int s) {
        if (vis[s]) return;
        vis[s] = true;
        for (int i : adj[s]) {
            dfs(i);
        }
        v.add(s);
    }

    static void solve(Scanner scanner) {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            adj[b].add(a);
        }

        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                dfs(i);
            }
        }

        List<Integer> t = new ArrayList<>();
        int i = 0, f = 0;
        while (i < v.size()) {
            if (f == 0) {
                if (v.get(i) == 1) {
                    f = 1;
                    t.add(1);
                }
            } else {
                t.add(v.get(i));
            }
            i++;
        }

        long[] path = new long[100005];
        path[1] = 1;
        for (int x : t) {
            for (int j : adj[x]) {
                path[x] = (path[x] + path[j]) % MOD;
            }
        }

        System.out.println(path[n]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = 1;
        // t = scanner.nextInt();
        while (t-- > 0) {
            solve(scanner);
            // System.out.println();
        }
    }
}
