#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define MAX 200005

int ans = 0;
bool done[MAX];

void dfs(int pre, int node, int adj[][MAX], int adjSize[]) {
    for (int i = 0; i < adjSize[node]; i++) {
        int neighbor = adj[node][i];
        if (neighbor != pre) {
            dfs(node, neighbor, adj, adjSize);
            if (!done[node] && !done[neighbor]) {
                done[neighbor] = done[node] = true;
                ans++;
            }
        }
    }
}

int independentEdges(int n, int adj[][MAX], int adjSize[]) {
    memset(done, 0, sizeof(done));
    dfs(0, 1, adj, adjSize);
    return ans;
}

int main() {
    int n;
    scanf("%d", &n);

    int adj[MAX][MAX];
    int adjSize[MAX] = {0};

    for (int i = 1; i < n; i++) {
        int a, b;
        scanf("%d %d", &a, &b);
        adj[a][adjSize[a]++] = b;
        adj[b][adjSize[b]++] = a;
    }

    int result = independentEdges(n, adj, adjSize);
    printf("%d\n", result);

    return 0;
}
