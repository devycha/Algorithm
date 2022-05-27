/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1967
 * 
 * * 입력
 * 파일의 첫 번째 줄은 노드의 개수 n(1 ≤ n ≤ 10,000)이다. 둘째 줄부터 n-1개의 줄에 각 간선에 대한 정보가 들어온다. 간선에 대한 정보는 세 개의 정수로 이루어져 있다. 첫 번째 정수는 간선이 연결하는 두 노드 중 부모 노드의 번호를 나타내고, 두 번째 정수는 자식 노드를, 세 번째 정수는 간선의 가중치를 나타낸다. 간선에 대한 정보는 부모 노드의 번호가 작은 것이 먼저 입력되고, 부모 노드의 번호가 같으면 자식 노드의 번호가 작은 것이 먼저 입력된다. 루트 노드의 번호는 항상 1이라고 가정하며, 간선의 가중치는 100보다 크지 않은 양의 정수이다.
 * 
    12
    1 2 3
    1 3 2
    2 4 5
    3 5 11
    3 6 9
    4 7 1
    4 8 7
    5 9 15
    5 10 4
    6 11 6
    6 12 10
 * 
 * * 출력
 * 첫째 줄에 트리의 지름을 출력한다.
 * 
 * * 파싱
 * n = 12
 * arr = [[1, 2, 3], [1, 3, 2], [2, 4, 5], [3, 5, 11], [3, 6, 9], [4, 7, 1], [4, 8, 7], [5, 9, 15], [5, 10, 4], [6, 11, 6], [6, 12, 10]]
 * 
 * * 초기 설정
 * 부모-자식리스트 객체 obj = {}
 * 한 노드를 중심으로 바로 아래 차수의 자식방향으로 leafNode까지의 거리의 최댓값 result = []
 * 트리 지름 max = 0
 */
const fs = require("fs");
let [n, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
arr = arr.map((a) => a.trim().split(" ").map(Number));
let obj = {};

arr.forEach((e) => {
  if (!obj[e[0]]) obj[e[0]] = [];
  obj[e[0]].push([e[1], e[2]]);
});

let result = [];
let max = 0;

for (let key in obj) {
  result = [];
  let distance = 0;

  obj[key].forEach((child) => {
    result.push([]);
    dfs(child[0], child[1]);
  });

  result = result.map((dists) => {
    return dists.sort((a, b) => b - a)[0];
  });

  result = result.sort((a, b) => b - a);

  for (let i = 0; i < 2; i++) {
    if (result[i]) {
      distance += result[i];
    }
  }

  if (max < distance) {
    max = distance;
  }
}

console.log(max);

function dfs(node, distance) {
  if (!obj[node]) {
    result[result.length - 1].push(distance);
    return;
  }
  obj[node].forEach((child) => {
    dfs(child[0], distance + child[1]);
  });
}
/**
 * 채점 결과
 * 메모리: 40680KB
 * 시간: 1480ms
 * 언어: JS
 */
