function bfs(node, n, adj) {
    const q = [{ node, dist: 0 }];
    const vis = new Array(n + 1).fill(false);
    vis[node] = true;
    let u = null;
    
    while (q.length > 0) {
        u = q.shift();
        for (const child of adj[u.node]) {
            if (!vis[child]) {
                q.push({ node: child, dist: u.dist + 1 });
                vis[child] = true;
            }
        }
    }
    
    return u;
}

function dfs(par, node, d, c, adj, dp) {
    dp[node][c] = d;
    for (const child of adj[node]) {
        if (child !== par) {
            dfs(node, child, d + 1, c, adj, dp);
        }
    }
}

function maxDistances(n, adj) {
    const p = bfs(1, n, adj);
    const pp = bfs(p.node, n, adj);
    
    const dp = Array.from({ length: n + 1 }, () => [0, 0]);
    
    dfs(0, p.node, 0, 0, adj, dp);
    dfs(0, pp.node, 0, 1, adj, dp);
    
    const result = [];
    for (let i = 1; i <= n; i++) {
        result.push(Math.max(dp[i][0], dp[i][1]));
    }
    console.log(result.join(" "));
}

function main() {
    const readline = require('readline');
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });
    
    const input = [];
    
    rl.on('line', (line) => {
        input.push(line);
    });
    
    rl.on('close', () => {
        const n = parseInt(input[0]);
        const adj = Array.from({ length: n + 1 }, () => []);
        
        for (let i = 1; i < n; i++) {
            const [a, b] = input[i].split(' ').map(Number);
            adj[a].push(b);
            adj[b].push(a);
        }
        
        maxDistances(n, adj);
    });
}

main();
