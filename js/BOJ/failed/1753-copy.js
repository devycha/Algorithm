class MinHeap {
  constructor() {
    this.nodes = [];
  }

  addNode(node) {
    this.nodes.push(node);
    this.bubbleUp();
  }

  extractMin() {
    if (this.nodes.length === 1) {
      return this.nodes.pop();
    }
    let minNode = this.nodes[0];
    this.nodes[0] = this.nodes.pop();
    this.sinkDown();
    return minNode;
  }

  bubbleUp(index = this.nodes.length - 1) {
    if (index <= 0) return;

    let parentNodeIndex = Math.floor((index - 1) / 2);
    let parentNode = this.nodes[parentNodeIndex];
    let childNode = this.nodes[index];

    if (parentNode.c > childNode.c) {
      this.nodes[parentNodeIndex] = childNode;
      this.nodes[index] = parentNode;
      index = parentNodeIndex;
      this.bubbleUp(index);
    }
  }

  sinkDown(index = 0) {
    let leftChildIndex = index * 2 + 1;
    let rightChildIndex = index * 2 + 2;
    let length = this.nodes.length;

    let originIndex = index;

    let parent = this.nodes[index];

    // 자식 노드가 둘다 없을 때
    if (!this.nodes[leftChildIndex]) return;
    // 왼쪽 자식 노드만 있을 때
    if (!this.nodes[rightChildIndex]) {
      if (this.nodes[leftChildIndex].c < parent.c) {
        index = leftChildIndex;
      }
    } else {
      // 자식 노드가 둘다 존재할 때
      if (this.nodes[leftChildIndex].c < this.nodes[rightChildIndex].c) {
        if (this.nodes[leftChildIndex].c < parent.c) {
          index = leftChildIndex;
        }
      } else if (
        this.nodes[rightChildIndex].c <= this.nodes[leftChildIndex].c // u -> v 간선 중 가중치가 같은 경우도 있기 때문
      ) {
        if (this.nodes[rightChildIndex].c < parent.c) {
          index = rightChildIndex;
        }
      }
    }
    if (index !== originIndex) {
      let t = this.nodes[index];
      this.nodes[index] = this.nodes[originIndex];
      this.nodes[originIndex] = t;
      this.sinkDown(index);
    }
  }
}

const fs = require("fs");
let [VE, S, ...arr] = fs.readFileSync("input.txt").toString().split("\n");
const [V, E] = VE.trim().split(" ").map(Number);
const start = +S.trim();
arr = arr.map(a => a.trim().split(" ").map(Number));

let adjacentList = Array.from(V + 1, () => new Array());
arr.forEach(a => {
  if (!adjacentList[a[0]]) adjacentList[a[0]] = [];
  adjacentList[a[0]].push({ v: a[1], c: a[2] });
});

let distance = new Array(V + 1).fill(Infinity);
distance[start] = 0;

let minHeap = new MinHeap();
minHeap.addNode({ v: start, c: 0 });

// console.log(adjacentList);
// console.log(minHeap);

while (minHeap.nodes.length) {
  let extract = minHeap.extractMin();
  if (!adjacentList[extract.v]) continue;
  adjacentList[extract.v].forEach(node => {
    if (distance[node.v] !== Infinity) return;
    distance[node.v] = extract.c + node.c;
    minHeap.addNode({ v: node.v, c: extract.c + node.c });
  });
}

let answer = [];
for (let i = 1; i < distance.length; i++) {
  answer.push(distance[i] == Infinity ? "INF" : distance[i]);
}
console.log(answer.join("\n"));
