#include <stdio.h>
#include <stdlib.h>

#define MAX_NODES 100000

// Structure to represent adjacency list node
typedef struct ListNode {
    int val;
    struct ListNode* next;
} ListNode;

// Function to create a new adjacency list node
ListNode* createListNode(int val) {
    ListNode* newNode = (ListNode*)malloc(sizeof(ListNode));
    if (newNode == NULL) {
        fprintf(stderr, "Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }
    newNode->val = val;
    newNode->next = NULL;
    return newNode;
}

// Function to perform DFS
void dfs(int node, int parent, ListNode** adj, int* dp) {
    ListNode* current = adj[node];
    while (current != NULL) {
        int neighbor = current->val;
        if (neighbor != parent) {
            dfs(neighbor, node, adj, dp);
            dp[node] += dp[neighbor];
        }
        current = current->next;
    }
}

// Main function
int main() {
    int n;
    scanf("%d", &n); // Read number of nodes
    
    // Allocate memory for adjacency list
    ListNode* adj[MAX_NODES];
    for (int i = 0; i < n; i++) {
        adj[i] = NULL;
    }
    
    // Read edges and construct adjacency list
    for (int i = 1; i < n; i++) {
        int a;
        scanf("%d", &a);
        a--; // Convert to 0-based index
        ListNode* newNodeA = createListNode(a);
        ListNode* newNodeI = createListNode(i);
        
        // Add edges
        newNodeA->next = adj[i];
        adj[i] = newNodeA;
        
        newNodeI->next = adj[a];
        adj[a] = newNodeI;
    }
    
    // Initialize dp array
    int dp[MAX_NODES];
    for (int i = 0; i < n; i++) {
        dp[i] = 1;
    }
    
    // Perform DFS from node 0
    dfs(0, -1, adj, dp);
    
    // Print dp array values
    for (int i = 0; i < n; i++) {
        printf("%d ", dp[i] - 1);
    }
    printf("\n");
    
    // Free allocated memory
    for (int i = 0; i < n; i++) {
        ListNode* current = adj[i];
        while (current != NULL) {
            ListNode* temp = current;
            current = current->next;
            free(temp);
        }
    }
    
    return 0;
}
