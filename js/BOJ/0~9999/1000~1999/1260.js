const fs = require("fs");
const [info, ...edgesInfo] = fs
  .readFileSync("input.txt")
  .toString()
  .split("\n");

const [node, edge, init] = info.split(" ");
let graph = {};

edgesInfo.forEach(edge => {
  const [a, b] = edge.split(" ");
  if (!graph[a]) graph[a] = [];
  if (!graph[b]) graph[b] = [];
  graph[a].push(b);
  graph[b].push(a);
});

for (let key in graph) {
  graph[key].sort((a, b) => a - b);
}

console.log(dfs(graph, +node, init).join(" "));
console.log(bfs(graph, +node, init).join(" "));

function dfs(graph, node, start) {
  let check = new Array(node + 1).fill(false);
  let dfsList = [];
  dfsRecurse(start);
  function dfsRecurse(start) {
    if (check[start]) return;
    check[start] = true;
    dfsList.push(start);
    if (graph[start]) {
      graph[start].forEach(adjacentNode => {
        dfsRecurse(adjacentNode);
      });
    }
  }
  return dfsList;
}

function bfs(graph, node, start) {
  let check = new Array(node + 1).fill(false);
  let stack = [start];
  let bfsList = [];

  while (stack.length) {
    let shift = stack.shift();
    if (check[shift]) {
      continue;
    }
    check[shift] = true;
    bfsList.push(shift);

    if (graph[start]) {
      graph[shift].forEach(adjacentNode => {
        if (!check[adjacentNode]) {
          stack.push(adjacentNode);
        }
      });
    }
  }
  return bfsList;
}
// const bfs = vStart => {
//   const willVisit = [vStart];
//   let v;
//   while (willVisit.length !== 0) {
//     v = willVisit.shift();
//     if (visited[v]) {
//       continue;
//     }

//     visited[v] = true;
//     result.push(v);
//     graph[v].forEach(vertex => {
//       if (!visited[vertex]) {
//         willVisit.push(vertex);
//       }
//     });
//   }
// };
