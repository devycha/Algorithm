/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1766
 * 
 * * 시간제한: 2초
 * * 메모리제한: 128MB
 * 
 * * 입력
 * 첫째 줄에 문제의 수 N(1 ≤ N ≤ 32,000)과 먼저 푸는 것이 좋은 문제에 대한 정보의 개수 M(1 ≤ M ≤ 100,000)이 주어진다. 둘째 줄부터 M개의 줄에 걸쳐 두 정수의 순서쌍 A,B가 빈칸을 사이에 두고 주어진다. 이는 A번 문제는 B번 문제보다 먼저 푸는 것이 좋다는 의미이다.
 * 항상 문제를 모두 풀 수 있는 경우만 입력으로 주어진다.
 * 
    4 2
    4 2
    3 1
 * 
 * * 출력
 * 첫째 줄에 문제 번호를 나타내는 1 이상 N 이하의 정수들을 민오가 풀어야 하는 순서대로 빈칸을 사이에 두고 출력한다.
 * 
    3 1 4 2
 * 
 * * 파싱
 * n = 5, m = 2
 * arr = [[4, 2], [3, 1]]
 * 
 * * 초기 설정
 * 우선순위 큐(최소힙) 클래스 
 * PriorityQueue
 * 
 * 선행 - 후행문제 리스트 객체
 * obj[선행] = [..후행문제들]
 * 
 * 위상
 * degree = [0 * n+1]
 * 
 * 최소힙(우선순위큐)
 * pq = new PriorityQueue();
 * 
 * 문제 푸는 순서
 * sequence = []
 */

// 최소힙(우선순위 큐)
class PriorityQueue {
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

    if (parent > child) {
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
      if (leftChild < rightChild) {
        if (leftChild < parent) {
          this.nodes[index] = leftChild;
          this.nodes[leftChildIndex] = parent;
          this.sinkDown(leftChildIndex);
        }
      } else {
        if (rightChild < parent) {
          this.nodes[index] = rightChild;
          this.nodes[rightChildIndex] = parent;
          this.sinkDown(rightChildIndex);
        }
      }
    } else if (leftChild) {
      if (leftChild < parent) {
        this.nodes[index] = leftChild;
        this.nodes[leftChildIndex] = parent;
        this.sinkDown(leftChildIndex);
      }
    }
  }
}

// 파싱
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map((a) => a.trim().split(" ").map(Number));

// 초기 설정
let obj = Array.from({ length: n + 1 }, () => new Array());
let degree = new Array(n + 1).fill(0);
let pq = new PriorityQueue();
let sequence = [];

// 위상 업데이트
arr.forEach((e) => {
  obj[e[0]].push(e[1]);
  degree[e[1]]++;
});

// 위상이 0인 문제들(가장 먼저 풀 수 있는 문제들 최소힙에 추가)
for (let i = 1; i <= n; i++) {
  if (degree[i] == 0) {
    pq.add(i);
  }
}

// 가장 먼저 풀 수 있는 문제들 중 가장 쉬운 문제부터 풀기(문제 번호가 가장 낮은 문제부터)
while (pq.nodes.length) {
  let current = pq.extractMin();
  sequence.push(current);

  if (obj[current]) {
    obj[current].forEach((next) => {
      // 다음으로 풀 수 있는 문제 중에 위상이 0이 되는 문제들을
      if (--degree[next] == 0) {
        pq.add(next); // 최소힙에 추가
      }
    });
  }
}
console.log(sequence.join(" "));
/**
 * 채점 결과
 * 메모리: 49648KB
 * 시간: 448ms
 * 언어: JS
 */
