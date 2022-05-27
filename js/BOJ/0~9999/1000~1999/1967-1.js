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
 * 노드의 인접 연결 노드 리스트 obj = {}
 */
const fs = require("fs");
let [n, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
arr = arr.map((a) => a.trim().split(" ").map(Number));
let obj = {};
arr.forEach((e) => {
  if (!obj[e[0]]) obj[e[0]] = [];
  if (!obj[e[1]]) obj[e[1]] = [];

  obj[e[0]].push([e[1], e[2]]);
  obj[e[1]].push([e[0], e[2]]);
});

const leafNode = findMaxDistanceNode();
const maxDistance = findMaxDistanceNode(leafNode[0])[1];
console.log(maxDistance);

function findMaxDistanceNode(start = 1) {
  let stack = [[start, 0]];
  let visited = new Array(n + 1).fill(0);
  visited[start] = 1;
  let leafNode = 1;
  let max = 0;

  while (stack.length) {
    let [node, distance] = stack.pop();
    if (distance > max) {
      max = distance;
      leafNode = node;
    }

    if (obj[node]) {
      obj[node].forEach(([nextNode, nextDistance]) => {
        if (!visited[nextNode]) {
          visited[nextNode] = 1;
          stack.push([nextNode, nextDistance + distance]);
        }
      });
    }
  }
  return [leafNode, max];
}
/**
 * 채점 결과
 * 메모리: 27740KB
 * 시간: 312ms
 * 언어: JS
 */
