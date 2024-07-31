import java.util.*;

public class Main {
    static boolean isCycle(int node, boolean[] vis, boolean[] recStack, Stack<Integer> st, List<Integer>[] adj) {
        vis[node] = true;
        recStack[node] = true;
        st.push(node);

        for (int neighbor : adj[node]) {
            if (!vis[neighbor]) {
                if (isCycle(neighbor, vis, recStack, st, adj)) {
                    return true;
                }
            } else if (recStack[neighbor]) {
                st.push(neighbor);
                return true;
            }
        }

        recStack[node] = false;
        st.pop();
        return false;
    }

    static List<Integer> extract(Stack<Integer> st) {
        int node = st.peek();
        List<Integer> cycle = new ArrayList<>();
        cycle.add(node);
        st.pop();

        while (!st.isEmpty()) {
            int top = st.peek();
            cycle.add(top);
            if (top == node) break;
            st.pop();
        }
        return cycle;
    }

    static List<Integer> roundTrip(int n, List<Integer>[] adj) {
        boolean[] vis = new boolean[n + 1];
        boolean[] recStack = new boolean[n + 1];
        Stack<Integer> st = new Stack<>();

        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                if (isCycle(i, vis, recStack, st, adj)) {
                    return extract(st);
                }
            }
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[a].add(b);
        }

        List<Integer> cycle = roundTrip(n, adj);

        if (cycle.isEmpty()) {
            System.out.println("NOT POSSIBLE");
        } else {
            Collections.reverse(cycle);
            System.out.println(cycle.size());
            for (int node : cycle) {
                System.out.print(node + " ");
            }
        }
    }
}
