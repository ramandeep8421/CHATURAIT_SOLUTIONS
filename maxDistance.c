#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct {
    int first;
    int second;
} Pair;

typedef struct Node {
    Pair data;
    struct Node* next;
} Node;

typedef struct {
    Node* front;
    Node* rear;
} Queue;

void enqueue(Queue* q, Pair p) {
    Node* new_node = (Node*)malloc(sizeof(Node));
    new_node->data = p;
    new_node->next = NULL;
    if (q->rear) {
        q->rear->next = new_node;
    } else {
        q->front = new_node;
    }
    q->rear = new_node;
}

Pair dequeue(Queue* q) {
    if (!q->front) {
        Pair p = {-1, -1};
        return p;
    }
    Node* temp = q->front;
    Pair p = temp->data;
    q->front = q->front->next;
    if (!q->front) {
        q->rear = NULL;
    }
    free(temp);
    return p;
}

bool is_empty(Queue* q) {
    return q->front == NULL;
}

Pair bfs(int node, int n, int** adj, int* sizes) {
    Queue q = {NULL, NULL};
    enqueue(&q, (Pair){node, 0});
    bool* vis = (bool*)calloc(n + 1, sizeof(bool));
    vis[node] = true;
    Pair u;
    
    while (!is_empty(&q)) {
        u = dequeue(&q);
        for (int i = 0; i < sizes[u.first]; i++) {
            int child = adj[u.first][i];
            if (!vis[child]) {
                enqueue(&q, (Pair){child, u.second + 1});
                vis[child] = true;
            }
        }
    }
    
    free(vis);
    return u;
}

void dfs(int par, int node, int d, int c, int** adj, int* sizes, int** dp) {
    dp[node][c] = d;
    for (int i = 0; i < sizes[node]; i++) {
        int child = adj[node][i];
        if (child != par) {
            dfs(node, child, d + 1, c, adj, sizes, dp);
        }
    }
}

void maxDistances(int n, int** adj, int* sizes) {
    Pair p = bfs(1, n, adj, sizes);
    Pair pp = bfs(p.first, n, adj, sizes);
    
    int** dp = (int**)malloc((n + 1) * sizeof(int*));
    for (int i = 0; i <= n; i++) {
        dp[i] = (int*)calloc(2, sizeof(int));
    }
    
    dfs(0, p.first, 0, 0, adj, sizes, dp);
    dfs(0, pp.first, 0, 1, adj, sizes, dp);
    
    for (int i = 1; i <= n; i++) {
        printf("%d ", dp[i][0] > dp[i][1] ? dp[i][0] : dp[i][1]);
    }
    
    for (int i = 0; i <= n; i++) {
        free(dp[i]);
    }
    free(dp);
}

int main() {
    int n;
    scanf("%d", &n);
    
    int** adj = (int**)malloc((n + 1) * sizeof(int*));
    int* sizes = (int*)calloc((n + 1), sizeof(int));
    for (int i = 0; i <= n; i++) {
        adj[i] = (int*)malloc((n + 1) * sizeof(int));
    }
    
    for (int i = 1; i < n; i++) {
        int a, b;
        scanf("%d %d", &a, &b);
        adj[a][sizes[a]++] = b;
        adj[b][sizes[b]++] = a;
    }
    
    maxDistances(n, adj, sizes);
    
    for (int i = 0; i <= n; i++) {
        free(adj[i]);
    }
    free(adj);
    free(sizes);
    
    return 0;
}
