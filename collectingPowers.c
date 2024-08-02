#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>

#define maxN 2501
#define maxM 5001
#define INF LLONG_MAX / 3

typedef struct {
    int a, b;
    long long c;
} Edge;

Edge edges[maxM];
long long dp[maxN];
bool vis[maxN], visR[maxN];
int G[maxN][maxN], GR[maxN][maxN]; // Adjacency matrix for simplicity
int G_size[maxN], GR_size[maxN];   // Size of adjacency list for each node

void dfs(int u) {
    vis[u] = true;
    for (int i = 0; i < G_size[u]; i++) {
        int v = G[u][i];
        if (!vis[v])
            dfs(v);
    }
}

void dfsR(int u) {
    visR[u] = true;
    for (int i = 0; i < GR_size[u]; i++) {
        int v = GR[u][i];
        if (!visR[v])
            dfsR(v);
    }
}

int main() {
    int N, M;
    scanf("%d %d", &N, &M);

    for (int i = 0; i < M; i++) {
        int a, b;
        long long c;
        scanf("%d %d %lld", &a, &b, &c);
        edges[i].a = a;
        edges[i].b = b;
        edges[i].c = -c;
        G[a][G_size[a]++] = b;
        GR[b][GR_size[b]++] = a;
    }

    dfs(1);
    dfsR(N);

    for (int i = 2; i <= N; i++) {
        dp[i] = INF;
    }

    bool improvement = true;
    for (int iter = 0; iter < N && improvement; iter++) {
        improvement = false;
        for (int i = 0; i < M; i++) {
            int u = edges[i].a;
            int v = edges[i].b;
            long long w = edges[i].c;

            if (dp[v] > dp[u] + w) {
                dp[v] = dp[u] + w;
                improvement = true;

                if (iter == N - 1 && vis[v] && visR[v]) {
                    printf("-1\n");
                    return 0;
                }
            }
        }
    }

    printf("%lld\n", -dp[N]);
    return 0;
}
