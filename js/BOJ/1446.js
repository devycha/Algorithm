class MinHeap {
  constructor() {
    this.nodes = [];
  }

  add(node) {
    this.nodes.push(node);
    if (this.nodes.length > 1) {
      this.bubbleUp();
    }
  }

  bubbleUp(index = this.nodes.length - 1) {
    if (index == 0) return;
    let parentIndex = Math.floor((index - 1) / 2);
    let parent = this.nodes[parentIndex];
    let child = this.nodes[index];

    if (parent[1] > child[1]) {
      this.nodes[parentIndex] = child;
      this.nodes[index] = parent;
      this.bubbleUp(parentIndex);
    }
  }

  extractMin() {
    if (this.nodes.length == 1) {
      return this.nodes.pop();
    }

    let min = this.nodes[0];
    this.nodes[0] = this.nodes.pop();
    this.sinkDown();
    return min;
  }

  sinkDown(index = 0) {
    let leftChildIndex = index * 2 + 1;
    let rightChildIndex = index * 2 + 2;
    let leftChild = null,
      rightChild = null;
    let parent = this.nodes[index];

    if (this.nodes[leftChildIndex]) {
      leftChild = this.nodes[leftChildIndex];
    }

    if (this.nodes[rightChildIndex]) {
      rightChild = this.nodes[rightChildIndex];
    }

    if (leftChild && rightChild) {
      if (leftChild[1] < rightChild[1]) {
        if (leftChild[1] < parent[1]) {
          this.nodes[leftChildIndex] = parent;
          this.nodes[index] = leftChild;
          this.sinkDown(leftChildIndex);
        }
      } else {
        if (rightChild[1] < parent[1]) {
          this.nodes[rightChildIndex] = parent;
          this.nodes[index] = rightChild;
          this.sinkDown(rightChildIndex);
        }
      }
    } else if (leftChild) {
      if (leftChild[1] < parent[1]) {
        this.nodes[leftChildIndex] = parent;
        this.nodes[index] = leftChild;
        this.sinkDown(leftChildIndex);
      }
    }
  }
}
const fs = require("fs");
let [nd, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, d] = nd.split(" ").map(Number);
arr = arr.map(a => a.trim().split(" ").map(Number));

let obj = {};
arr.forEach(e => {
  if (!obj[e[0]]) obj[e[0]] = [];
  obj[e[0]].push([e[1], e[2]]);
});

let minHeap = new MinHeap();
let answer = 0;

for (let key in obj) {
  minHeap.add([+key, +key]);
}

while (minHeap.nodes.length) {
  console.log(minHeap);
  let [current, distance] = minHeap.extractMin();

  if (current > d) continue;
  if (current == d) {
    answer = distance;
    break;
  }

  if (obj[current]) {
    obj[current].forEach(e => {
      minHeap.add([e[0], e[1] + distance]);
    });
  }
  minHeap.add([d, distance + d - current]);
}
console.log(answer);
