const fs = require("fs");
let [t, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
t = +t;
arr = arr.map((a) => a.split(" ").map(Number));
let visited, st, n, count;
let result = [];

for (let i = 0; i < arr.length; i += 2) {
  n = arr[i][0];
  st = arr[i + 1];
  count = 0;

  for (let j = 1; j <= n; j++) {
    visited = new Array(n + 1).fill(0);
    if (j == dfs(j)) {
      count += groups;
    }
  }
  result.push(group.filter((e) => e == 0).length - 1);
}

console.log(result.join("\n"));

function dfs(start) {
  let next = st[start - 1];

  if (!visited[next]) {
    visited[next] = 1;
    return dfs(next);
  } else {
    return start;
  }
}
