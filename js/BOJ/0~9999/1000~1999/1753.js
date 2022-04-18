// https://www.acmicpc.net/problem/1753
/**
 * 입력값 예시
 * 5 6
 * 1
 * 5 1 1
 * 1 2 2
 * 1 3 3
 * 2 3 4
 * 2 4 5
 * 3 4 6
 *
 * 출력값 예시
 * 0
 * 2
 * 3
 * 7
 * INF
 *
 * 파싱
 * V = 5
 * E = 6
 * start = 1
 * arr = [[5, 1, 1], [1, 2, 2], [1, 3, 3], [2, 3, 4], [2, 4, 5], [3, 4, 6]]
 *
 * 초기 설정
 * queue = [start] = [1]
 * distance = ["INF", "INF", ...]
 * distance[start] = distance[1] = 0;
 * let i = 0 (pointer)
 * obj: adjacency object = {}
 */
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
    const leftChildIndex = 2 * index + 1;
    const rightChildIndex = 2 * index + 2;
    const length = this.nodes.length;
    let minimum = index;

    if (!this.nodes[rightChildIndex] && !this.nodes[leftChildIndex]) return;
    if (!this.nodes[rightChildIndex]) {
      if (this.nodes[leftChildIndex].c < this.nodes[minimum].c) {
        minimum = leftChildIndex;
      }
      return;
    }

    if (this.nodes[leftChildIndex].c > this.nodes[rightChildIndex].c) {
      if (
        rightChildIndex <= length &&
        this.nodes[rightChildIndex].c < this.nodes[minimum].c
      ) {
        minimum = rightChildIndex;
      }
    } else {
      if (
        leftChildIndex <= length &&
        this.nodes[leftChildIndex].c < this.nodes[minimum].c
      ) {
        minimum = leftChildIndex;
      }
    }

    if (minimum !== index) {
      let t = this.nodes[minimum];
      this.nodes[minimum] = this.nodes[index];
      this.nodes[index] = t;
      this.sinkDown(minimum);
    }
  }
}

const fs = require("fs");
let [VE, S, ...arr] = fs.readFileSync("/dev/stdin").toString().split("\n");
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

while (minHeap.nodes.length) {
  const now = minHeap.extractMin();
  const v = now.v;
  const c = now.c;

  if (adjacentList[v] === undefined) continue;
  if (distance[v] < c) continue;
  for (let i = 0; i < adjacentList[v].length; i++) {
    const nt = adjacentList[v][i];
    const next = nt.v;
    const nextCost = nt.c;
    if (distance[next] > c + nextCost) {
      distance[next] = c + nextCost;
      minHeap.addNode({
        v: next,
        c: distance[next],
      });
    }
  }
}

let answer = [];
for (let i = 1; i < distance.length; i++) {
  answer.push(distance[i] == Infinity ? "INF" : distance[i]);
}
console.log(answer.join("\n"));
/**
 * 채점 결과
 * 메모리: 131944KB
 * 시간: 952ms
 * 언어: JS
 */
