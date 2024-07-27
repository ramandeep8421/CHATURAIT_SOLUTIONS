const fs = require('fs');

let ans = 0;
const MAX = 200005;
const done = Array(MAX).fill(false);

function dfs(pre, node, adj) {
    for (const neighbor of adj[node]) {
        if (neighbor !== pre) {
            dfs(node, neighbor, adj);
            if (!done[node] && !done[neighbor]) {
                done[neighbor] = done[node] = true;
                ans++;
            }
        }
    }
}

function independentEdges(n, adj) {
    done.fill(false);
    dfs(0, 1, adj);
    return ans;
}

function main() {
    const input = fs.readFileSync(0, 'utf-8').trim().split('\n').map(line => line.split(' ').map(Number));
    
    const n = input[0][0];
    const adj = Array.from({ length: n + 6 }, () => []);

    for (let i = 1; i < n; i++) {
        const [a, b] = input[i];
        adj[a].push(b);
        adj[b].push(a);
    }

    const result = independentEdges(n, adj);
    console.log(result);
}

main();
