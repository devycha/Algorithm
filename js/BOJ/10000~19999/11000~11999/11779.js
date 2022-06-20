/**
 * !문제 출처: 백준 온라인 져지
 * address: https://www.acmicpc.net/problem/11779
 * 
 * !시간제한: 1초
 * !메모리제한: 256MB
 * 
 * !문제
 * n(1≤n≤1,000)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1≤m≤100,000)개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. 그러면 A번째 도시에서 B번째 도시 까지 가는데 드는 최소비용과 경로를 출력하여라. 항상 시작점에서 도착점으로의 경로가 존재한다.
 * 
 * !입력 & 파싱
 * 첫째 줄에 도시의 개수 n(1≤n≤1,000)이 주어지고 둘째 줄에는 버스의 개수 m(1≤m≤100,000)이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
 * 그리고 m+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다.
 * 
    5     -> n
    8     -> m
    1 2 2 -> arr[0]
    1 3 3
    1 4 1
    1 5 10
    2 4 2
    3 4 1
    3 5 1
    4 5 3 -> arr[m-1]
    1 5   -> start end
 * 
 * !출력
 * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
 * 둘째 줄에는 그러한 최소 비용을 갖는 경로에 포함되어있는 도시의 개수를 출력한다. 출발 도시와 도착 도시도 포함한다.
 * 셋째 줄에는 최소 비용을 갖는 경로를 방문하는 도시 순서대로 출력한다.
 * 
    4
    3
    1 3 5
 * 
 * !초기 설정
 * MinHeap: 최소힙 클래스 구현
 * bus: 이동 가능한 버스 노선과 비용을 나타내는 2차원 배열
 * ? bus[i][j] = k (i도시에서 j도시로가는 비용 k, 이동 불가능일 때 값은 Infinity)
 * visited: 방문리스트
 * distance: start정점으로 시작해서 각 정점까지 가는 최단 비용 
 */
class MinHeap {
  constructor() {
    this.nodes = [];
  }

  add(node) {
    this.nodes.push(node);
    if (this.nodes.length > 1) this.bubbleUp();
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
    if (this.nodes.length == 1) return this.nodes.pop();
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
const fs = require("fs");
let [n, m, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
(n = +n), (m = +m);
arr = arr.map(a => a.trim().split(" ").map(Number));
const [start, end] = arr.pop();

let bus = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(Infinity));
let max = 0;
arr.forEach(e => {
  if (max < e[2]) max = e[2];
  if (bus[e[0]][e[1]]) {
    bus[e[0]][e[1]] = Math.min(bus[e[0]][e[1]], e[2]);
  } else {
    bus[e[0]][e[1]] = e[2];
  }
});

let minHeap = new MinHeap();
let visited = new Array(n + 1).fill(0);
let distance = Array.from({ length: n + 1 }, () => [null, max * (n + 1)]);
dijkstra(start, end);

function dijkstra(start, end) {
  for (let i = 1; i <= n; i++) {
    if (bus[start][i] != Infinity) {
      minHeap.add([i, bus[start][i]]);
      distance[i][0] = start;
      distance[i][1] = bus[start][i];
    }
  }

  while (minHeap.nodes.length) {
    let [cur, cost] = minHeap.extractMin();
    if (cur == end) break;

    for (let i = 1; i <= n; i++) {
      if (bus[cur][i] != Infinity) {
        if (!visited[cur] && distance[i][1] >= cost + bus[cur][i]) {
          minHeap.add([i, cost + bus[cur][i]]);
          distance[i][0] = cur;
          distance[i][1] = cost + bus[cur][i];
        }
      }
    }
    visited[cur] = 1;
  }

  const road = findRoad(start, end);
  console.log(distance[end][1] + "\n" + road.length + "\n" + road.join(" "));
}

function findRoad(start, end) {
  if (start == end) {
    return [start, end];
  }
  let result = [];
  let cur = end;

  while (cur != start) {
    result.push(cur);
    cur = distance[cur][0];
  }
  result.push(start);
  return result.reverse();
}
/**
 * ? 채점 결과
 * 메모리: 48288KB
 * 시간: 396ms
 * 언어: JS
 */
