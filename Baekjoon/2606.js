const fs = require("fs");
const [computer, pairs, ...edges] = fs
  .readFileSync("input.txt")
  .toString()
  .split("\n");
let graph = {};
edges.forEach((edge) => {
  const virus = edge.split(" ").map(Number);
  if (!graph[virus[0]]) graph[virus[0]] = [];
  if (!graph[virus[1]]) graph[virus[1]] = [];
  graph[virus[0]].push(virus[1]);
  graph[virus[1]].push(virus[0]);
});

console.log(wormVirus(graph, 1).length - 1);

function wormVirus(graph, virus) {
  let check = new Array(+computer).fill(false);
  let virusStack = [virus];
  let virusList = [];

  while (virusStack.length) {
    let virusComputer = virusStack.shift();

    if (check[virusComputer]) continue;
    check[virusComputer] = true;
    virusList.push(virusComputer);

    if (graph[virusComputer]) {
      graph[virusComputer].forEach((computer) => {
        virusStack.push(computer);
      });
    }
  }

  return virusList;
}
