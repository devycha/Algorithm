/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1939
 * 
 * 시간제한: 1초
 * 메모리제한: 128MB
 * 
 * 입력
 * 첫째 줄에 N, M(1 ≤ M ≤ 100,000)이 주어진다. 
 * 다음 M개의 줄에는 다리에 대한 정보를 나타내는 세 정수 A, B(1 ≤ A, B ≤ N), C(1 ≤ C ≤ 1,000,000,000)가 주어진다. 
 * 이는 A번 섬과 B번 섬 사이에 중량제한이 C인 다리가 존재한다는 의미이다. 
 * 서로 같은 두 섬 사이에 여러 개의 다리가 있을 수도 있으며, 모든 다리는 양방향이다. 
 * 마지막 줄에는 공장이 위치해 있는 섬의 번호를 나타내는 서로 다른 두 정수가 주어진다. 
 * 공장이 있는 두 섬을 연결하는 경로는 항상 존재하는 데이터만 입력으로 주어진다.
 * 
    3 3
    1 2 2
    3 1 3
    2 3 2
    1 3
 * 
 * 출력
 * 첫째 줄에 답을 출력한다.
 * 
    3
 * 
 * 파싱
 * n = 3, m = 3
 * arr = [[1, 2, 2], [3, 1 ,3], [2, 3, 2]]
 * s = 1, e = 3
 * 
 * {{초기 설정}}
 * 체크리스트 checkList = [0 * n+1] => checkList[s] = 1
 * 인접 섬 리스트 객체 obj = [[] * n+1]
 * 최대힙 클래스 MaxHeap
 */

// 최대힙
class MaxHeap {
  constructor() {
    this.nodes = [];
  }

  add(island) {
    this.nodes.push(island);
    if (this.nodes.length > 1) {
      this.bubbleUp();
    }
  }

  bubbleUp(index = this.nodes.length - 1) {
    if (index == 0) return;
    let parentIndex = Math.floor((index - 1) / 2);
    let parent = this.nodes[parentIndex];
    let child = this.nodes[index];

    if (parent[1] < child[1]) {
      this.nodes[parentIndex] = child;
      this.nodes[index] = parent;
      this.bubbleUp(parentIndex);
    }
  }

  extractMax() {
    if (this.nodes.length == 1) {
      return this.nodes.pop();
    }
    let max = this.nodes[0];
    this.nodes[0] = this.nodes.pop();
    this.sinkDown();
    return max;
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
        if (rightChild[1] > parent[1]) {
          this.nodes[rightChildIndex] = parent;
          this.nodes[index] = rightChild;
          this.sinkDown(rightChildIndex);
        }
      } else {
        if (leftChild[1] > parent[1]) {
          this.nodes[leftChildIndex] = parent;
          this.nodes[index] = leftChild;
          this.sinkDown(leftChildIndex);
        }
      }
    } else if (leftChild) {
      if (leftChild[1] > parent[1]) {
        this.nodes[leftChildIndex] = parent;
        this.nodes[index] = leftChild;
        this.sinkDown(leftChildIndex);
      }
    }
  }
}

// 파싱
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map((a) => a.split(" ").map(Number));
const [s, e] = arr.pop();

// 초기 설정
let checkList = new Array(n + 1).fill(0);
checkList[s] = 1;

let obj = Array.from({ length: n + 1 }, () => new Array());
arr.forEach((island) => {
  obj[island[0]].push([island[1], island[2]]);
  obj[island[1]].push([island[0], island[2]]);
});

let maxHeap = new MaxHeap();
obj[s].forEach((island) => {
  maxHeap.add(island);
});

// 다익스트라
while (maxHeap.nodes.length) {
  let island = maxHeap.extractMax();
  if (checkList[island[0]]) continue;
  checkList[island[0]] = island[1];

  if (obj[island[0]]) {
    obj[island[0]].forEach((nextIsland) => {
      if (!checkList[nextIsland[0]]) {
        maxHeap.add([nextIsland[0], Math.min(island[1], nextIsland[1])]);
      }
    });
  }
}

console.log(checkList[e]);
/**
 * 채점 결과
 * 메모리: 88792KB
 * 시간: 744ms
 * 언어: JS
 */
