/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1976
 * 
 * * 시간제한: 2초
 * * 메모리제한: 128MB
 * 
 * * 입력
 * 첫 줄에 도시의 수 N이 주어진다. N은 200이하이다. 둘째 줄에 여행 계획에 속한 도시들의 수 M이 주어진다. M은 1000이하이다. 다음 N개의 줄에는 N개의 정수가 주어진다. i번째 줄의 j번째 수는 i번 도시와 j번 도시의 연결 정보를 의미한다. 1이면 연결된 것이고 0이면 연결이 되지 않은 것이다. A와 B가 연결되었으면 B와 A도 연결되어 있다. 마지막 줄에는 여행 계획이 주어진다. 도시의 번호는 1부터 N까지 차례대로 매겨져 있다.
 * 
    3
    3
    0 1 0
    1 0 1
    0 1 0
    1 2 3
 * 
 * * 출력
 * 첫 줄에 가능하면 YES 불가능하면 NO를 출력한다.
 * 
    YES
 * 
 * * 파싱
 * n = 3, m = 3
 * arr = [[0, 1, 0], [1, 0, 1], [0, 1, 0]]
 * course = [1, 2, 3]
 * 
 * * 초기 설정
 * DisJoint Set(분리집합)
 * parent = [0, 1, 2, 3, ..., n-1]
 */
const fs = require("fs");
let [n, m, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
(n = +n), (m = +m);
arr = arr.map(a => a.trim().split(" ").map(Number));
let course = arr.pop();
let parent = Array.from({ length: n }, (v, i) => i);

// 부모 노드 찾기(재귀)
function find(p, node) {
  if (p[node] !== node) {
    p[node] = find(p, p[node]);
  }
  return p[node];
}

// a < b
// 더 작은 수의 부모로 업데이트
function union(p, a, b) {
  p[find(p, b)] = find(p, a);
}

for (let i = 0; i < arr.length; i++) {
  for (let j = 0; j < i; j++) {
    if (arr[i][j] == 1) union(parent, j, i);
  }
}

const roots = course.map(v => v - 1).map(v => find(parent, v));
console.log(roots.every(v => v === roots[0]) ? "YES" : "NO");
/**
 * 채점 결과
 * 메모리: 10684KB
 * 시간: 144ms
 * 언어: JS
 */
