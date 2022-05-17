/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1197
 * 
 * 입력
 * 첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다. 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다. 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.
 * 그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다. 최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.
 * 
    3 3
    1 2 1
    2 3 2
    1 3 3
 * 
 * 출력
 * 첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.
 * 
    3
 * 
 * 파싱
 * v = 3, e = 3
 * arr = [[1, 2, 1], [2, 3, 2], [1, 3, 3]]
 * 
 * {{초기 설정}}
 * 연결 가능한 인접 정접 리스트 객체
 * obj = {}
 * 
 * 체크리스트
 * checkList = [0 * v+1]
 * 
 * 최소힙 
 * minHeap
 */
class MinHeap {
  constructor() {
    this.nodes = [];
  }

  add(vertex) {
    this.nodes.push(vertex);
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

    if (this.nodes[leftChildIndex]) leftChild = this.nodes[leftChildIndex];
    if (this.nodes[rightChildIndex]) rightChild = this.nodes[rightChildIndex];

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

// 파싱
const fs = require("fs");
let [ve, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [v, e] = ve.split(" ").map(Number);
arr = arr.map((a) => a.trim().split(" ").map(Number));

// 초기 설정
let obj = Array.from({ length: v + 1 }, () => new Array());
arr.forEach((vertex) => {
  obj[vertex[0]].push([vertex[1], vertex[2]]);
  obj[vertex[1]].push([vertex[0], vertex[2]]);
});

let checkList = new Array(v + 1).fill(0);
checkList[1] = 1;

// 최소힙
let minHeap = new MinHeap();
obj[1].forEach((vertex) => {
  minHeap.add(vertex);
});

while (minHeap.nodes.length) {
  let min = minHeap.extractMin();
  if (checkList[min[0]]) continue;
  checkList[min[0]] = min[1];

  if (obj[min[0]]) {
    obj[min[0]].forEach((vertex) => {
      if (!checkList[vertex[0]]) {
        minHeap.add(vertex);
      }
    });
  }
}

let result = 0;
for (let i = 2; i < checkList.length; i++) {
  result += checkList[i];
}

console.log(result);
/**
 * 채점 결과
 * 메모리: 80832KB
 * 시간: 824ms
 * 언어: JS
 */
