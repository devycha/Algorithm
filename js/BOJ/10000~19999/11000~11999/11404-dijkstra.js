/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/11404
 * 
 * 시간제한: 1초
 * 메모리제한: 256MB
 * 
 * 입력
 * 첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다. 시작 도시와 도착 도시가 같은 경우는 없다. 비용은 100,000보다 작거나 같은 자연수이다.
 * 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
 * 
    5
    14
    1 2 2
    1 3 3
    1 4 1
    1 5 10
    2 4 2
    3 4 1
    3 5 1
    4 5 3
    3 5 10
    3 1 8
    1 4 2
    5 1 7
    3 4 2
    5 2 4
 * 
 * 출력
 * n개의 줄을 출력해야 한다. i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다. 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.
 * 
    0 2 3 1 4
    12 0 15 2 5
    8 5 0 1 1
    10 7 13 0 3
    7 4 10 6 0
 * 
 * 파싱
 * n = 5, m = 14
 * arr = [
  [ 1, 2, 2 ],  [ 1, 3, 3 ],
  [ 1, 4, 1 ],  [ 1, 5, 10 ],
  [ 2, 4, 2 ],  [ 3, 4, 1 ],
  [ 3, 5, 1 ],  [ 4, 5, 3 ],
  [ 3, 5, 10 ], [ 3, 1, 8 ],
  [ 1, 4, 2 ],  [ 5, 1, 7 ],
  [ 3, 4, 2 ],  [ 5, 2, 4 ]
  ]
 *
 * {{초기 설정}}
 * 최소힙 MinHeap
 * 인접 도시 리스트 객체 obj
 * 최단 거리 2차원 배열 distance
 */

// 최소힙
class MinHeap {
  constructor() {
    this.nodes = [];
  }

  add(bus) {
    this.nodes.push(bus);
    if (this.nodes.length > 1) {
      this.bubbleUp();
    }
  }

  bubbleUp(index = this.nodes.length - 1) {
    if (index == 0) return;

    let parentIndex = Math.floor((index - 1) / 2);
    let parent = this.nodes[parentIndex];
    let child = this.nodes[index];

    if (parent[2] > child[2]) {
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
      if (leftChild[2] < rightChild[2]) {
        if (leftChild[2] < parent[2]) {
          this.nodes[leftChildIndex] = parent;
          this.nodes[index] = leftChild;
          this.sinkDown(leftChildIndex);
        }
      } else {
        if (rightChild[2] < parent[2]) {
          this.nodes[rightChildIndex] = parent;
          this.nodes[index] = rightChild;
          this.sinkDown(rightChildIndex);
        }
      }
    } else if (leftChild) {
      if (leftChild[2] < parent[2]) {
        this.nodes[leftChildIndex] = parent;
        this.nodes[index] = leftChild;
        this.sinkDown(leftChildIndex);
      }
    }
  }
}

// 파싱
const fs = require("fs");
let [n, m, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
m = +m;
arr = arr.map((a) => a.split(" ").map(Number));

// 초기 설정
let obj = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(0));
let distance = Array.from({ length: n }, () => new Array(n).fill(0));

arr.forEach((bus) => {
  if (obj[bus[0]][bus[1]] == 0) {
    obj[bus[0]][bus[1]] = bus[2];
  } else {
    obj[bus[0]][bus[1]] = Math.min(obj[bus[0]][bus[1]], bus[2]);
  }
});

// 최소힙
let minHeap = new MinHeap();
obj[1].forEach((bus, i) => {
  if (bus == 0) return;
  minHeap.add([1, i, bus]);
});

// 다익스트라
while (minHeap.nodes.length) {
  let min = minHeap.extractMin();

  if (min[0] == min[1]) continue;

  if (distance[min[0] - 1][min[1] - 1]) continue;
  distance[min[0] - 1][min[1] - 1] = min[2];

  if (obj[min[1]]) {
    obj[min[1]].forEach((bus, i) => {
      if (bus == 0) return;
      if (!distance[min[0] - 1][i - 1]) {
        minHeap.add([min[0], i, min[2] + bus]);
      }

      if (!distance[min[1] - 1][i - 1]) {
        minHeap.add([min[1], i, bus]);
      }
    });
  }
}

// 출력
console.log(distance.map((a) => a.join(" ")).join("\n"));
/**
 * 채점 결과
 * 메모리: 167420KB
 * 시간: 4248ms
 * 언어: JS
 */
