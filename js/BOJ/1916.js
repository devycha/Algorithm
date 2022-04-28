/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1916
 *
 * 문제
 * N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.
 *
 * 입력
 * 첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다. 그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
 * 그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다. 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.
 *
 * 출력
 * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
 */
const fs = require("fs");
let [n, m, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
arr = arr.map((a) => a.trim().split(" ").map(Number));
let dest = arr.pop();
let obj = {};

arr.forEach((a) => {
  if (!obj[a[0]]) obj[a[0]] = {};

  if (!obj[a[0]][a[1]]) {
    obj[a[0]][a[1]] = a[2];
  } else {
    obj[a[0]][a[1]] = Math.min(obj[a[0]][a[1]], a[2]);
  }
});

let distance = new Array(+n + 1).fill(Infinity);
distance[dest[0]] = 0;
let queue = [[dest[0], 0]];
while (queue.length) {
  let [current, cost] = queue.shift();
  if (queue.length >= 2) {
    sinkDown();
  }
  if (current == dest[1]) break;
  if (distance[current] < cost) continue;
  if (obj[current]) {
    for (let key in obj[current]) {
      let next = +key;
      let nextCost = cost + obj[current][key];

      if (distance[next] > nextCost) {
        distance[next] = nextCost;
        queue.push([next, nextCost]);
        bubbleUp();
      }
    }
  }
}

console.log(distance[dest[1]]);

function bubbleUp(index = queue.length - 1) {
  if (index <= 0) return;

  let parentNodeIndex = Math.floor((index - 1) / 2);
  let parentNode = queue[parentNodeIndex];
  let childNode = queue[index];

  if (parentNode[1] > childNode[1]) {
    queue[parentNodeIndex] = childNode;
    queue[index] = parentNode;
    index = parentNodeIndex;
    bubbleUp(index);
  }
}

function sinkDown(index = 0) {
  let leftChildIndex = index * 2 + 1;
  let rightChildIndex = index * 2 + 2;

  let originIndex = index;
  let parent = queue[index];

  if (!queue[leftChildIndex]) return;

  if (!queue[rightChildIndex]) {
    if (queue[leftChildIndex][1] < parent[1]) {
      index = leftChildIndex;
    }
  } else {
    // 자식 노드가 둘다 존재할 때
    if (queue[leftChildIndex][1] < queue[rightChildIndex][1]) {
      if (queue[leftChildIndex][1] < parent[1]) {
        index = leftChildIndex;
      }
    } else if (queue[rightChildIndex][1] <= queue[leftChildIndex][1]) {
      if (queue[rightChildIndex][1] < parent[1]) {
        index = rightChildIndex;
      }
    }
  }
  if (index !== originIndex) {
    let t = queue[index];
    queue[index] = queue[originIndex];
    queue[originIndex] = t;
    sinkDown(index);
  }
}
