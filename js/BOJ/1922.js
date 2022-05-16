/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1922
 * 
 * 입력
 * 첫째 줄에 컴퓨터의 수 N (1 ≤ N ≤ 1000)가 주어진다.
 * 둘째 줄에는 연결할 수 있는 선의 수 M (1 ≤ M ≤ 100,000)가 주어진다.
 * 셋째 줄부터 M+2번째 줄까지 총 M개의 줄에 각 컴퓨터를 연결하는데 드는 비용이 주어진다. 이 비용의 정보는 세 개의 정수로 주어지는데, 만약에 a b c 가 주어져 있다고 하면 a컴퓨터와 b컴퓨터를 연결하는데 비용이 c (1 ≤ c ≤ 10,000) 만큼 든다는 것을 의미한다. a와 b는 같을 수도 있다.
 * 
    6
    9
    1 2 5
    1 3 4
    2 3 2
    2 4 7
    3 4 6
    3 5 11
    4 5 3
    4 6 8
    5 6 8
 * 
 * 출력
 * 모든 컴퓨터를 연결하는데 필요한 최소비용을 첫째 줄에 출력한다.
 * 
    23
 * 
 * 파싱
 * n = 6, m = 9
 * arr = [[1, 2, 5], [1, 3, 4], [2, 3, 2], [2, 4, 7], [3, 4, 6], [3, 5, 11], [4, 5, 3], [4, 6, 8], [5, 6, 8]]
 * 
 * {{초기 설정}}
 * 최소힙 MinHeap Class
 * 연결가능한 인접 컴퓨터 리스트 객체 obj = {}
 * 체크리스트 checkList = [0 * n+1] => checkList[1] = 1 (1번 컴퓨터부터 항상 시작)
 */
class MinHeap {
  constructor() {
    this.nodes = [];
  }

  add(com) {
    this.nodes.push(com);
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
let [n, m, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
(n = +n), (m = +m);
arr = arr.map((a) => a.trim().split(" ").map(Number));

let totalPrice = 0;
let obj = Array.from({ length: n + 1 }, () => new Array());
arr.forEach((com) => {
  obj[com[0]].push([com[1], com[2]]);
  obj[com[1]].push([com[0], com[2]]);
});
let checkList = new Array(n + 1).fill(0);
let minHeap = new MinHeap();
checkList[1] = 1;
obj[1].forEach((com) => {
  minHeap.add(com);
});

while (minHeap.nodes.length) {
  let min = minHeap.extractMin();

  if (checkList[min[0]]) continue;
  checkList[min[0]] = min[1];

  if (obj[min[0]]) {
    obj[min[0]].forEach((com) => {
      if (!checkList[com[0]]) {
        minHeap.add(com);
      }
    });
  }
}

for (let i = 2; i < checkList.length; i++) {
  totalPrice += checkList[i];
}

console.log(totalPrice);
/**
 * 채점 결과
 * 메모리: 78740KB
 * 시간: 644ms
 * 언어: JS
 */
