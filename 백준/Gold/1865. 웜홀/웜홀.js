const fs = require("fs");
let [t, ...arr] = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
arr = arr.map((a) => a.split(" ").map(Number));

let result = [];
let cnt = 0;
let n, m, w, adj;

while (cnt < arr.length) {
  [n, m, w] = arr[cnt++];
  let r = m + w;
  adj = Array.from({ length: n + 1 }, () => new Array());

  for (let i = 0; i < r; i++) {
    const [s, e, t] = arr[cnt++];
    if (i >= m) {
      adj[s].push([e, -t]);
    } else {
      adj[s].push([e, t]);
      adj[e].push([s, t]);
    }
  }
  result.push(bellmanFord() ? "YES" : "NO");
}
console.log(result.join("\n"));

function bellmanFord() {
  let distance = new Array(n + 1).fill(5000000);
  let cycle = false;
  distance[1] = 0;

  for (let i = 1; i <= n; i++) {
    for (let cur = 1; cur <= n; cur++) {
      if (adj[cur]) {
        adj[cur].forEach(([next, cost]) => {
          if (distance[next] > distance[cur] + cost) {
            distance[next] = distance[cur] + cost;
            if (i == n) cycle = true;
          }
        });
      }
    }
  }
  return cycle;
}