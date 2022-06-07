/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1647
 * 
 * * 시간제한: 2초
 * * 메모리제한: 256MB
 * 
 * * 입력
 * 첫째 줄에 집의 개수 N, 길의 개수 M이 주어진다. N은 2이상 100,000이하인 정수이고, M은 1이상 1,000,000이하인 정수이다. 그 다음 줄부터 M줄에 걸쳐 길의 정보가 A B C 세 개의 정수로 주어지는데 A번 집과 B번 집을 연결하는 길의 유지비가 C (1 ≤ C ≤ 1,000)라는 뜻이다.
 * 
    7 12
    1 2 3
    1 3 2
    3 2 1
    2 5 2
    3 4 4
    7 3 6
    5 1 5
    1 6 2
    6 4 1
    6 5 3
    4 5 3
    6 7 4
 * 
 * * 출력
 * 첫째 줄에 없애고 남은 길 유지비의 합의 최솟값을 출력한다.
 * 
    8
 * 
 * * 파싱
 * n = 7, m = 12
 * arr = [
    [ 1, 2, 3 ], [ 1, 3, 2 ],
    [ 3, 2, 1 ], [ 2, 5, 2 ],
    [ 3, 4, 4 ], [ 7, 3, 6 ],
    [ 5, 1, 5 ], [ 1, 6, 2 ],
    [ 6, 4, 1 ], [ 6, 5, 3 ],
    [ 4, 5, 3 ], [ 6, 7, 4 ]
  ]
 * 
 * * 초기 설정
 * 최소힙 클래스 MinHeap
 * minHeap = new MinHeap()
 * 
 * 마을을 최종적으로 분할했을 때의 도로 중 가장 비싼 비용 
 * max = 0
 * 
 * 선택한 도로의 갯수
 * count = 0
 * 
 * 도시 - 연결할 수 있는 도시 리스트
 * obj = [[] * n+1]
 * 
 * 연결된 도시를 체크하는 리스트
 * visited = [0 * n+1]
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
// 파싱
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map(a => a.split(" ").map(Number));

// 초기 설정
let max = 0;
let count = 0;

// 도시 - 연결할 수 있는 도시 리스트(비용 포함)
let obj = Array.from({ length: n + 1 }, () => new Array());
arr.forEach(e => {
  obj[e[0]].push([e[1], e[2]]);
  obj[e[1]].push([e[0], e[2]]);
});

// 연결된 도시 리스트
let visited = new Array(n + 1).fill(0);
// 최소힙(가중치: 도로 건설 비용)
let minHeap = new MinHeap();
visited[1] = 1;
// 1번 도시부터 시작(임의의 한 도시를 1로 설정)
obj[1].forEach(e => {
  minHeap.add(e);
});
// 다익스트라 알고리즘
while (minHeap.nodes.length) {
  if (count == n - 1) break; // 최소 스패닝 트리를 달성하고 나면 break
  let [current, cost] = minHeap.extractMin(); // 비용이 최소인 값 선택

  if (!visited[current]) {
    // 이미 연결된 도시라면 추가적으로 연결하지 않음
    visited[current] = cost; // 연결이 안되어 있다면, 해당 도로 건설 비용을 방문리스트에 저장
    count++; // 카운트 증가
    if (max < cost) {
      // 현재 건설한 도로가 지금까지 건설한 어떤 도로보다 비용이 많이 들었다면
      max = cost; // 최고값 업데이트
    }
  }

  if (obj[current].length) {
    // 연결할 수 있는 도시 리스트 중
    obj[current].forEach(next => {
      if (!visited[next[0]]) {
        // 이미 연결이 안 된 리스트만
        minHeap.add(next); // 최소힙에 추가
      }
    });
  }
}

// visited에 저장된 모든 비용들을 더한 뒤 1을 빼준다(1번 도시의 비용을 1로 놓았기 때문 -> 원래는 0)
// max값을 최종적으로 빼주어 최소스패닝 트리에서 가장 비용이 비싼 도로 하나를 없애서 마을을 2개로 분할한다.
console.log(visited.reduce((a, b) => a + b, 0) - max - 1);
/**
 * 채점 결과
 * 메모리: 448828KB
 * 시간: 2440ms
 * 언어: JS
 */
