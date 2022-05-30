/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1167
 * 
 * * 시간제한: 2초
 * * 메모리제한: 256MB
 * 
 * * 입력
 * 트리가 입력으로 주어진다. 먼저 첫 번째 줄에서는 트리의 정점의 개수 V가 주어지고 (2 ≤ V ≤ 100,000)둘째 줄부터 V개의 줄에 걸쳐 간선의 정보가 다음과 같이 주어진다. 정점 번호는 1부터 V까지 매겨져 있다.
 * 먼저 정점 번호가 주어지고, 이어서 연결된 간선의 정보를 의미하는 정수가 두 개씩 주어지는데, 하나는 정점번호, 다른 하나는 그 정점까지의 거리이다. 예를 들어 네 번째 줄의 경우 정점 3은 정점 1과 거리가 2인 간선으로 연결되어 있고, 정점 4와는 거리가 3인 간선으로 연결되어 있는 것을 보여준다. 각 줄의 마지막에는 -1이 입력으로 주어진다. 주어지는 거리는 모두 10,000 이하의 자연수이다.
 * 
    5
    1 3 2 -1
    2 4 4 -1
    3 1 2 4 3 -1
    4 2 4 3 3 5 6 -1
    5 4 6 -1
 * 
 * * 출력
 * 첫째 줄에 트리의 지름을 출력한다.
 * 
    11
 * 
 * * 파싱
 * v = 5
 * arr = [
    [ 1, 3, 2 ],
    [ 2, 4, 4 ],
    [ 3, 1, 2, 4, 3 ],
    [ 4, 2, 4, 3, 3, 5, 6 ],
    [ 5, 4, 6 ]
  ]
 * 
 * * 초기 설정
 * 트리 지름의 최대값 
 * max = 0
 * 
 * 트리 지름이 가장 길 때 노드 
 * node
 * 
 * 방문리스트 
 * visited = [0 * v+1]
 * 
 * 인접 노드 리스트 객체 
 * obj = {}
 * 
 * * 풀이 방법
 * * DFS(재귀 & 스택)
 */
const fs = require("fs");
let [v, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
v = +v;
arr = arr.map((a) => {
  a = a.trim().split(" ").map(Number);
  a.pop();
  return a;
});

let max = 0;
let node;
let visited = new Array(v + 1).fill(0);
let obj = Array.from({ length: v + 1 }, () => new Array());
arr.forEach((e) => {
  for (let i = 1; i < e.length; i += 2) {
    obj[e[0]].push([e[i], e[i + 1]]);
  }
});

// 재귀DFS를 이용한 풀이
// 1
visited[1] = 1;
dfs_recurse(1, 0);
// 초기화
visited.fill(0);
visited[node] = 1;
max = 0;
// 2
dfs_recurse(node, 0);
// answer
console.log(max);

function dfs_recurse(n, d) {
  if (d > max) {
    max = d;
    node = n;
  }

  if (obj[n]) {
    obj[n].forEach((next) => {
      if (!visited[next[0]]) {
        visited[next[0]] = 1;
        dfs_recurse(next[0], d + next[1]);
      }
    });
  }
}
/**
 * 채점 결과
 * 메모리: 104708KB
 * 시간: 740ms
 * 언어: JS
 */

// Stack을 이용한 풀이
const n = dfs(1)[1];
const answer = dfs(n)[0];
console.log(answer);

function dfs(start) {
  let queue = [[start, 0]];
  let visited = new Array(v + 1).fill(0);
  visited[start] = 1;
  let max = 0;
  let node;

  while (queue.length) {
    let [current, distance] = queue.pop();

    if (max < distance) {
      max = distance;
      node = current;
    }

    if (obj[current]) {
      obj[current].forEach((next) => {
        if (!visited[next[0]]) {
          visited[next[0]] = 1;
          queue.push([next[0], distance + next[1]]);
        }
      });
    }
  }

  return [max, node];
}
/**
 * 채점 결과
 * 메모리: 115672KB
 * 시간: 792ms
 * 언어: JS
 */
