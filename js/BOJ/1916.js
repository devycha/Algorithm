/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1916
 *
 * 문제
 * N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.
 *
 * 입력
 * 첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다. 그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
 * 그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다. 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.
 *
 * 출력
 * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
 */
class MinHeap {
  constructor() {
    this.nodes = [];
  }

  addNode(node) {
    this.nodes.push(node);
    this.bubbleUp();
  }

  bubbleUp(index = this.nodes.length - 1) {
    if (index == 0) return;

    let parentIndex = Math.floor((index - 1) / 2);
    let parent = this.nodes[parentIndex];

    if (this.nodes[index].c < this.nodes[parentIndex].c) {
      this.nodes[parentIndex] = this.nodes[index];
      this.nodes[index] = parent;
      this.bubbleUp(parentIndex);
    }
  }

  extractMin() {
    if (this.nodes.length == 1) return this.nodes.pop();

    let min = this.nodes[0];
    this.nodes[0] = this.nodes.pop();

    this.sinkDown();

    return min;
  }

  sinkDown(index = 0) {
    let parent = this.nodes[index];
    let leftIndex = 2 * index + 1;
    let rightIndex = 2 * index + 2;

    if (!this.nodes[leftIndex] && !this.nodes[rightIndex]) return;
    if (!this.nodes[rightIndex]) {
      if (this.nodes[leftIndex].c < this.nodes[index].c) {
        this.nodes[index] = this.nodes[leftIndex];
        this.nodes[leftIndex] = parent;
        this.sinkDown(leftIndex);
      }
    } else {
      if (this.nodes[leftIndex].c < this.nodes[rightIndex].c) {
        if (this.nodes[leftIndex].c < this.nodes[index].c) {
          this.nodes[index] = this.nodes[leftIndex];
          this.nodes[leftIndex] = parent;
          this.sinkDown(leftIndex);
        }
      } else {
        if (this.nodes[rightIndex].c < this.nodes[index].c) {
          this.nodes[index] = this.nodes[rightIndex];
          this.nodes[rightIndex] = parent;
          this.sinkDown(rightIndex);
        }
      }
    }
  }
}

const fs = require("fs");
let [n, m, ...arr] = fs.readFileSync("input.txt").toString().split("\n");
arr = arr.map(a => a.split(" ").map(Number));
let dest = arr.pop();
let obj = {};
let self = 0;
arr.forEach(a => {
  if (a[0] === dest[0] && a[1] === dest[0]) {
    return (self = a[2]);
  }
  if (!obj[a[0]]) obj[a[0]] = [];

  obj[a[0]].push({ v: a[1], c: a[2] });
});

let distance = new Array(+n + 1).fill(-1);

let minHeap = new MinHeap();
minHeap.addNode({ v: dest[0], c: 0 });

while (minHeap.nodes.length) {
  let ex = minHeap.extractMin();
  if (distance[ex.v] == -1 || distance[ex.v] > ex.c) {
    distance[ex.v] = ex.c;
  }

  if (obj[ex.v]) {
    obj[ex.v].forEach(next => {
      if (distance[next.v] > 0 && distance[next.v] < ex.c + next.c) return;
      minHeap.addNode({ v: next.v, c: ex.c + next.c });
    });
  }
}

distance[dest[0]] = self;
console.log(distance[dest[1]]);
