// 문제 링크: https://www.acmicpc.net/problem/11403
/**
 * 문제 설명
 * 가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서, i에서 j로 가는 경로가 있는지 없는지 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 정점의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄부터 N개 줄에는 그래프의 인접 행렬이 주어진다. i번째 줄의 j번째 숫자가 1인 경우에는 i에서 j로 가는 간선이 존재한다는 뜻이고, 0인 경우는 없다는 뜻이다. i번째 줄의 i번째 숫자는 항상 0이다.
 * 
 * 입력값 예시
    3
    0 1 0
    0 0 1
    1 0 0
 * 
 * 출력
 * 총 N개의 줄에 걸쳐서 문제의 정답을 인접행렬 형식으로 출력한다. 정점 i에서 j로 가는 경로가 있으면 i번째 줄의 j번째 숫자를 1로, 없으면 0으로 출력해야 한다.
 * 
 * 출력값 예시
    1 1 1
    1 1 1
    1 1 1
 * 
 * 파싱
 * N = 3
 * arr = [ [ 0, 1, 0 ], [ 0, 0, 1 ], [ 1, 0, 0 ] ]
 * 
 * {{초기 설정}}
 * 인접 노드 객체
 * obj = {};
 * 
 * 결과(2차원배열 N * N)
 * result = [[0 * N] * N]
 * 
 * 체크리스트
 * checkList = [0 * N]
 * 
 */
const fs = require("fs");
let [N, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
arr = arr.map((a) => a.trim().split(" ").map(Number));
let obj = {};

for (let i = 0; i < arr.length; i++) {
  for (let j = 0; j < arr[0].length; j++) {
    if (arr[i][j] === 1) {
      adj(obj, i, j);
    }
  }
}
let result = [];
let checkList;
for (let i = 0; i < +N; i++) {
  result.push(new Array(+N).fill(0));
}

for (let i = 0; i < +N; i++) {
  checkList = new Array(+N).fill(0);
  dfs(i, i, checkList);
}
console.log(result.map((a) => a.join(" ")).join("\n"));

/**
 * 시작점을 파라미터로 계속 넘겨주어 result에 표시
 */
function dfs(base, current, checkList) {
  if (obj[current]) {
    obj[current].forEach((next) => {
      if (!checkList[next]) {
        checkList[next] = 1;
        result[base][next] = 1; // 시작점부터 도착할 수 있다고 표시
        dfs(base, next, checkList); // 인접 노드에 대하여 반복
      }
    });
  }
}

function adj(obj, i, j) {
  if (!obj[i]) obj[i] = [];
  obj[i].push(j);
}
/**
 * 채점 결과
 * 메모리: 11708KB
 * 시간: 200ms
 * 언어: JS
 */
