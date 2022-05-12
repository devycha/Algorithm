const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

class MinHeap {
  constructor() {
    this.nodes = [];
  }

  insert(value) {
    this.nodes.push(value);
    this.bubbleUp();
  }

  bubbleUp(index = this.nodes.length - 1) {
    if (index < 1) return;

    const currentNode = this.nodes[index];
    const parentIndex = Math.floor((index - 1) / 2);
    const parentNode = this.nodes[parentIndex];
    if (parentNode.cost <= currentNode.cost) return;

    this.nodes[index] = parentNode;
    this.nodes[parentIndex] = currentNode;
    index = parentIndex;
    this.bubbleUp(index);
  }

  extract() {
    const min = this.nodes[0];
    if (this.nodes.length === 1) {
      this.nodes.pop();
      return min;
    }
    this.nodes[0] = this.nodes.pop();
    this.trickleDown();
    return min;
  }

  trickleDown(index = 0) {
    const leftChildIndex = 2 * index + 1;
    const rightChildIndex = 2 * index + 2;
    const length = this.nodes.length;
    let minimum = index;

    if (!this.nodes[rightChildIndex] && !this.nodes[leftChildIndex]) return;
    if (!this.nodes[rightChildIndex]) {
      if (this.nodes[leftChildIndex].cost < this.nodes[minimum].cost) {
        minimum = leftChildIndex;
      }
      return;
    }

    if (this.nodes[leftChildIndex].cost > this.nodes[rightChildIndex].cost) {
      if (
        rightChildIndex <= length &&
        this.nodes[rightChildIndex].cost < this.nodes[minimum].cost
      ) {
        minimum = rightChildIndex;
      }
    } else {
      if (
        leftChildIndex <= length &&
        this.nodes[leftChildIndex].cost < this.nodes[minimum].cost
      ) {
        minimum = leftChildIndex;
      }
    }

    if (minimum !== index) {
      let t = this.nodes[minimum];
      this.nodes[minimum] = this.nodes[index];
      this.nodes[index] = t;
      console.log(minimum);
      this.trickleDown(minimum);
    }
  }
}

let count = -2;
let v = 0,
  e = 0,
  k = 0;
const input = [];

function dijkstra(graph) {
  let distance = new Array(v + 1).fill(Infinity);
  const queue = [];
  distance[k] = 0;
  const minHeap = new MinHeap();
  minHeap.insert({
    vertex: k,
    cost: 0,
  });
  while (minHeap.nodes.length) {
    const now = minHeap.extract();
    const start = now.vertex;
    const cost = now.cost;

    if (graph[start] === undefined) continue;
    if (distance[start] < cost) continue;
    for (let i = 0; i < graph[start].length; i++) {
      const nt = graph[start][i];
      const next = nt.vertex;
      const nextCost = nt.cost;
      if (distance[next] > cost + nextCost) {
        distance[next] = cost + nextCost;
        minHeap.insert({
          vertex: next,
          cost: distance[next],
        });
      }
    }
  }

  return distance;
}

rl.on("line", function (line) {
  if (count === -2) {
    [v, e] = line.split(" ").map(v => parseInt(v));
    count = -1;
    return;
  }
  if (count === -1) {
    k = parseInt(line);
    count = e;
    return;
  }
  input.push(line.split(" ").map(v => parseInt(v)));
  count--;
  if (count === 0) rl.close();
}).on("close", function () {
  const graph = Array.from(Array(v + 1), () => new Array());

  input.forEach(value => {
    const [start, end, cst] = value;
    graph[start].push({
      vertex: end,
      cost: cst,
    });
  });

  const result = dijkstra(graph);
  const answer = [];

  for (let i = 1; i <= v; i++) {
    if (result[i] === Infinity) answer.push("INF");
    else answer.push(result[i]);
  }
  console.log(answer.join("\n"));
  process.exit();
});
