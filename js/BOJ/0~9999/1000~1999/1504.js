/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1504
 * 
 * 시간제한: 1초
 * 메모리제한: 256MB
 * 
 * 입력
 * 첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000) 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1 ≤ c ≤ 1,000) 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2가 주어진다. (v1 ≠ v2, v1 ≠ N, v2 ≠ 1) 임의의 두 정점 u와 v사이에는 간선이 최대 1개 존재한다.
 * 
    4 6
    1 2 3
    2 3 3
    3 4 1
    1 3 5
    2 4 5
    1 4 4
    2 3  
 * 
 * 출력
 * 첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.
 * 
    7
 * 
 * 파싱
 * n = 4, e = 6
 * arr = [[1, 2, 3], [2, 3, 3], [3, 4, 1], [1, 3, 5], [2, 4, 5], [1, 4, 4]]
 * v1 = 2, v2 = 3
 * 
 * {{초기 설정}}
 * 연결할 수 있는 인접 정점과의 간선 리스트(가중치있음) obj = [[] * n+1]
 * 최소힙 MinHeap
 */
class MinHeap {
  constructor() {
    this.nodes = [];
  }

  add(edge) {
    this.nodes.push(edge);
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

// 파싱
const fs = require("fs");
let [ne, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, e] = ne.split(" ").map(Number);
arr = arr.map(a => a.trim().split(" ").map(Number));
const [v1, v2] = arr.pop();

// 초기 설정
let obj = Array.from({ length: n + 1 }, () => new Array());
arr.forEach(edge => {
  obj[edge[0]].push([edge[1], edge[2]]);
  obj[edge[1]].push([edge[0], edge[2]]);
});

if (e == 0) {
  // 간선의 갯수가 하나도 없는 경우
  console.log(-1);
} else if (v1 == 1 && v2 == n) {
  // 1부터 n까지 가는데 1과 n을 지나야 하는 경우
  console.log(dijkstra(1, n));
} else {
  // 그 외 일반적인 경우
  let d1 = dijkstra(1, v1); // 1부터 v1까지의 최단거리
  let d2 = dijkstra(1, v2); // 1부터 v2까지의 최단거리
  let d3 = dijkstra(v1, v2); // v1(v2)부터 v2(v1)까지의 최단거리
  let d4 = dijkstra(v1, n); // v1부터 n까지의 최단거리
  let d5 = dijkstra(v2, n); // v2부터 n까지의 최단거리

  let minDistance1 = checkDistance(d1, d3, d5); // 1 -> v1 -> v2 -> n
  let minDistance2 = checkDistance(d2, d3, d4); // 1 -> v2 -> v1 -> n

  if (minDistance1 && minDistance2) {
    // 둘다 존재하면 둘 중 최솟값
    console.log(Math.min(minDistance1, minDistance2));
  } else if (minDistance1) {
    // 둘 중 하나만 존재하면 그 값
    console.log(minDistance1);
  } else if (minDistance2) {
    // 둘 중 하나만 존재하면 그 값
    console.log(minDistance2);
  } else {
    // 둘 다 존재하지 않으면 -1
    console.log(-1);
  }
}

// 종합 거리 구하기
function checkDistance(s1, s2, s3) {
  // 세 거리 중 하나라도 -1인 경우가 있으면(갈 수 있는 경로가 없으면)
  if (s1 == -1 || s2 == -1 || s3 == -1) {
    return null;
  }

  return s1 + s2 + s3;
}

// 다익스트라
function dijkstra(start, end) {
  let minHeap = new MinHeap();
  let checkList = new Array(n + 1).fill(0);

  obj[start].forEach(edge => {
    minHeap.add(edge);
  });

  while (minHeap.nodes.length) {
    let min = minHeap.extractMin();

    if (checkList[min[0]]) continue;
    checkList[min[0]] = min[1];
    if (min[0] == end) {
      return min[1];
    }

    if (obj[min[0]]) {
      obj[min[0]].forEach(edge => {
        if (!checkList[edge[0]]) {
          minHeap.add([edge[0], edge[1] + min[1]]);
        }
      });
    }
  }
  if (!checkList[end]) {
    return -1;
  }
}
/**
 * 채점 결과
 * 메모리: 110272KB
 * 시간: 744ms
 * 언어: JS
 */
