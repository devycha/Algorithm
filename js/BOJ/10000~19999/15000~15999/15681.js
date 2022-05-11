/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/15681
 * 
 * 시간제한: 1초
 * 메모리제한: 128MB
 * 
 * 문제
 * 간선에 가중치와 방향성이 없는 임의의 루트 있는 트리가 주어졌을 때, 아래의 쿼리에 답해보도록 하자.
 * 정점 U를 루트로 하는 서브트리에 속한 정점의 수를 출력한다.
 * 만약 이 문제를 해결하는 데에 어려움이 있다면, 하단의 힌트에 첨부한 문서를 참고하자.
 * 
 * 입력
 * 트리의 정점의 수 N과 루트의 번호 R, 쿼리의 수 Q가 주어진다. (2 ≤ N ≤ 105, 1 ≤ R ≤ N, 1 ≤ Q ≤ 105)
 * 이어 N-1줄에 걸쳐, U V의 형태로 트리에 속한 간선의 정보가 주어진다. (1 ≤ U, V ≤ N, U ≠ V)
 * 이는 U와 V를 양 끝점으로 하는 간선이 트리에 속함을 의미한다.
 * 이어 Q줄에 걸쳐, 문제에 설명한 U가 하나씩 주어진다. (1 ≤ U ≤ N)
 * 입력으로 주어지는 트리는 항상 올바른 트리임이 보장된다.
 * 
    9 5 3
    1 3
    4 3
    5 4
    5 6
    6 7
    2 3
    9 6
    6 8
    5
    4
    8
 * 
 * 출력
 * Q줄에 걸쳐 각 쿼리의 답을 정수 하나로 출력한다.
 * 
    9
    4
    1
 * 
 * 파싱
 * n = 9, r = 5, q = 3
 * arr = [[1, 3], [4, 3], [5, 4], [5, 6], [6, 7], [2, 3], [9, 6], [6, 8]]
 * example = [8, 4, 5] => 거꾸로되어있음
 * 
 * {{초기 설정}}
 * 인접 리스트 객체 obj = {}
 * 서브 트리의 갯수 subTree = [0 * n+1]
 * 체크리스트 checkList = [0 * n+1]
 * 
 */
const fs = require("fs");
let [nrq, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, r, q] = nrq.split(" ").map(Number);
arr = arr.map((a) => a.trim().split(" ").map(Number));

let example = [];
for (let i = 0; i < q; i++) {
  example.push(arr.pop()[0]);
}

let obj = {};
arr.forEach((a) => {
  if (!obj[a[0]]) obj[a[0]] = [];
  if (!obj[a[1]]) obj[a[1]] = [];
  obj[a[0]].push(a[1]);
  obj[a[1]].push(a[0]);
});

let subTree = new Array(n + 1).fill(1);
let checkList = new Array(n + 1).fill(0);

dfs(r);

console.log(
  example
    .map((a) => subTree[a])
    .reverse()
    .join("\n")
);

function dfs(current) {
  checkList[current] = 1;
  if (obj[current]) {
    obj[current].forEach((child) => {
      if (!checkList[child]) {
        subTree[current] += dfs(child);
      }
    });
  }
  return subTree[current];
}
/**
 * 채점 결과
 * 메모리: 140428KB
 * 시간: 816ms
 * 언어: JS
 */
