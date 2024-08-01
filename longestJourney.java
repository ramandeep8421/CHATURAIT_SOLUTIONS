import java.util.*;
import java.io.*;

public class Main {
    static List<Integer>[] adj = new ArrayList[100005];
    static boolean[] vis = new boolean[100005];
    static List<Integer> v = new ArrayList<>();
    
    public static void dfs(int s) {
        if (vis[s]) return;
        vis[s] = true;
        for (int i : adj[s]) dfs(i);
        v.add(s);
    }
    
    public static void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        for (int i = 0; i < 100005; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[b].add(a);
        }
        
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) dfs(i);
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
        
        int[] len = new int[100005];
        int[] par = new int[100005];
        len[1] = 1;
        par[1] = 0;
        
        for (int x : t) {
            for (int j : adj[x]) {
                if (len[x] < len[j] + 1 && len[j] > 0) {
                    len[x] = len[j] + 1;
                    par[x] = j;
                }
            }
        }
        
        i = n;
        v = new ArrayList<>();
        while (i != 0) {
            v.add(i);
            i = par[i];
        }
        Collections.reverse(v);
        
        if (v.size() < 2) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        System.out.println(v.size());
        for (int x : v) {
            System.out.print(x + " ");
        }
    }
    
    public static void main(String[] args) {
        solve();
    }
}
