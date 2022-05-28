/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1238
 * 
 * * 시간제한: 1초
 * * 메모리제한: 128MB
 * 
 * * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 10,000), X가 공백으로 구분되어 입력된다. 두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 Ti가 들어온다. 시작점과 끝점이 같은 도로는 없으며, 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.
 * 모든 학생들은 집에서 X에 갈수 있고, X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.
 * 
    4 8 2
    1 2 4
    1 3 2
    1 4 7
    2 1 1
    2 3 5
    3 1 2
    3 4 4
    4 2 3
 * 
 * * 출력
 * 첫 번째 줄에 N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.
 * 
    10
 * 
 * * 파싱
 * n = 4, m = 8, x = 2
 * arr = [[1, 2, 4], [1, 3, 2], [1, 4, 7], [2, 1, 1], [2, 3, 5], [3, 1, 2], [3, 4, 4], [4, 2, 3]]
 * 
 * * 초기 설정
 * 최소힙 MinHeap
 * 인접 마을 리스트 객체 obj = {}
 * x마을에서 각 마을까지의 최단거리 dist = [0 * n+1]
 */
// 최소힙
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
    let child = this.nodes[index];
    let parentIndex = Math.floor((index - 1) / 2);
    let parent = this.nodes[parentIndex];

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
// 파싱
const fs = require("fs");
let [nmx, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m, x] = nmx.split(" ").map(Number);
arr = arr.map(a => a.trim().split(" ").map(Number));

// 초기 설정
let obj = Array.from({ length: n + 1 }, () => new Array());
let dist = new Array(n + 1).fill(0);
let max = 0;
arr.forEach(e => {
  obj[e[0]].push([e[1], e[2]]);
});

let minHeap = new MinHeap();
dist[x] = 1;
obj[x].forEach(e => {
  minHeap.add(e);
});

// 다익스트라: x마을에서 각 마을까지의 최단거리
while (minHeap.nodes.length) {
  let [vil, time] = minHeap.extractMin();
  if (!dist[vil]) dist[vil] = time;

  if (obj[vil]) {
    obj[vil].forEach(nextVil => {
      if (!dist[nextVil[0]]) {
        minHeap.add([nextVil[0], time + nextVil[1]]);
      }
    });
  }
}

for (let i = 1; i <= n; i++) {
  if (i == x) continue;
  let distance = dijkstra(i, x) + dist[i];
  if (max < distance) max = distance;
}

console.log(max);

// 다익스트라2: start마을에서 end마일까지의 최단거리 리턴
function dijkstra(start, end) {
  let minHeap = new MinHeap();
  let visited = new Array(n + 1).fill(0);

  visited[start] = 1;
  obj[start].forEach(e => {
    minHeap.add(e);
  });

  while (minHeap.nodes.length) {
    let [vil, time] = minHeap.extractMin();
    if (!visited[vil]) visited[vil] = time;
    if (vil == end) {
      return time;
    }

    if (obj[vil]) {
      obj[vil].forEach(nextVil => {
        if (!visited[nextVil[0]]) {
          minHeap.add([nextVil[0], time + nextVil[1]]);
        }
      });
    }
  }
}
/**
 * 채점 결과
 * 메모리: 22416KB
 * 시간: 584ms
 * 언어: JS
 */
