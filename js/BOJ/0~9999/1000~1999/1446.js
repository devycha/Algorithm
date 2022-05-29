/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1446
 * 
 * * 시간제한: 2초
 * * 메모리제한: 128MB
 * 
 * * 입력
 * 첫째 줄에 지름길의 개수 N과 고속도로의 길이 D가 주어진다. N은 12 이하인 양의 정수이고, D는 10,000보다 작거나 같은 자연수이다. 다음 N개의 줄에 지름길의 시작 위치, 도착 위치, 지름길의 길이가 주어진다. 모든 위치와 길이는 10,000보다 작거나 같은 음이 아닌 정수이다. 지름길의 시작 위치는 도착 위치보다 작다.
 * 
    5 150
    0 50 10
    0 50 20
    50 100 10
    100 151 10
    110 140 90
 * 
 * * 출력
 * 세준이가 운전해야하는 거리의 최솟값을 출력하시오.
 * 
    70
 * 
 * * 파싱
 * n = 5, d = 150
 * arr = [[0, 50, 10], [0, 50, 20], [50, 100, 10], [100, 151, 10], [110, 140, 90]]
 * 
 * * 초기 설정
 * 다익스트라를 위한 최소힙 MinHeap Class
 * 지름길시작점 - 지름길도착점, 거리 객체 obj = {}
 * 최소 거리 answer = 0
 */
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
arr = arr.map((a) => a.trim().split(" ").map(Number));

let obj = {};
arr.forEach((e) => {
  if (!obj[e[0]]) obj[e[0]] = [];
  obj[e[0]].push([e[1], e[2]]);
});

let minHeap = new MinHeap();
let answer = 0;

minHeap.add([0, 0]);

while (minHeap.nodes.length) {
  let [current, distance] = minHeap.extractMin();
  if (current > d) continue;
  if (current == d) {
    answer = distance;
    break;
  }

  if (obj[current]) {
    obj[current].forEach((e) => {
      minHeap.add([e[0], distance + e[1]]);
    });
  }

  for (let key in obj) {
    if (current < +key) {
      minHeap.add([+key, distance + (+key - current)]);
    }
  }
  minHeap.add([d, distance + (d - current)]);
}
console.log(answer);
/**
 * 채점 결과
 * 메모리: 107068KB
 * 시간: 3612ms
 * 언어: JS
 */
