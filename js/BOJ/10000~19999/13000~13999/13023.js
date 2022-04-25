/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/13023
 * 
 * 시간제한: 2초
 * 메모리제한: 512MB
 * 
 * 문제
 * BOJ 알고리즘 캠프에는 총 N명이 참가하고 있다. 사람들은 0번부터 N-1번으로 번호가 매겨져 있고, 일부 사람들은 친구이다.
 * 오늘은 다음과 같은 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 구해보려고 한다.
 * A는 B와 친구다.
 * B는 C와 친구다.
 * C는 D와 친구다.
 * D는 E와 친구다.
 * 위와 같은 친구 관계가 존재하는지 안하는지 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 사람의 수 N (5 ≤ N ≤ 2000)과 친구 관계의 수 M (1 ≤ M ≤ 2000)이 주어진다.
 * 둘째 줄부터 M개의 줄에는 정수 a와 b가 주어지며, a와 b가 친구라는 뜻이다. 
 * (0 ≤ a, b ≤ N-1, a ≠ b) 같은 친구 관계가 두 번 이상 주어지는 경우는 없다.
 * 
    5 4
    0 1
    1 2
    2 3
    3 4
 * 
 * 출력
 * 문제의 조건에 맞는 A, B, C, D, E가 존재하면 1을 없으면 0을 출력한다.
 * 
    1
 * 
 * 파싱
 * n = 5, m = 4
 * arr = [ [ 0, 1 ], [ 1, 2 ], [ 2, 3 ], [ 3, 0 ] ]
 * 
 * {{초기 설정}}
 * 친구 리스트 객체(쌍방향 간선)
 * obj = {}
 * 
 * 체크리스트
 * checkList = [0 * n]
 * 
 * 결과 출력용
 * result = 0 (조건에 맞는 친구가 있으면 1 없으면 0)
 * 
 * 
 */
// 파싱
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n"); // trim() 필수, 안하면 56%에서 오답처리
const [n, m] = nm.split(" ").map(Number);
arr = arr.map((a) => a.trim().split(" ").map(Number));

// 초기 설정
let obj = {};
arr.forEach((f) => {
  if (!obj[f[0]]) obj[f[0]] = [];
  if (!obj[f[1]]) obj[f[1]] = [];
  obj[f[0]].push(f[1]);
  obj[f[1]].push(f[0]);
});
let checkList = new Array(n).fill(0);
let result = 0;

// 문제 풀이
for (let i = 0; i < n; i++) {
  // 0번부터 시작해서 N-1번 친구까지 모두 수행
  if (result) {
    // 조건에 맞는 친구가 있다는 것을 이미 찾을 경우 더이상 실행하지 않고 종료
    break;
  }
  // // let checkList = new Array(n).fill(0); // 이 부분의 scope를 위로 올림
  checkList[i] = 1; // 시작하는 친구의 방문했다고 체크
  dfs(i, 0); // DFS 수행
  checkList[i] = 0; // DFS가 다 끝나면 체크리스트를 초기화
}

console.log(result); // 결과 출력

function dfs(current, count) {
  if (result) return; // (이 부분 있고 없고에서 시간 차이가 많이 생김) 1516 <-> 300

  if (count == 4) {
    // 조건에 맞는 친구가 있는 경우
    return (result = 1); // result(결과)에 1을 넣고 리턴
  }

  if (obj[current]) {
    obj[current].forEach((friend) => {
      if (!checkList[friend]) {
        // 친구들을 돌면서 방문하지 않은 친구들에 대해서만
        checkList[friend] = 1; // 방문했다고 표시
        count++; // 카운트 증가
        dfs(friend, count); // 해당 친구에 대해서 재귀탐색
        count--; // 카운트 초기화
        checkList[friend] = 0; // 체크리스트 초기화
      }
    });
  }
}
/**
 * 채점 결과
 * 메모리: 16116KB -> 14928KB
 * 시간: 1516ms -> 300ms 단축
 * 언어: JS
 */
