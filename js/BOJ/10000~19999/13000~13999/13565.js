/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/13565
 * 
 * 시간제한: 1초
 * 메모리제한: 512MB
 * 
 * 입력
 * 첫째 줄에는 격자의 크기를 나타내는  M (2 ≤ M ≤ 1,000) 과 N (2 ≤ N ≤ 1,000) 이 주어진다. M줄에 걸쳐서, N개의 0 또는 1 이 공백 없이 주어진다. 0은 전류가 잘 통하는 흰색, 1은 전류가 통하지 않는 검은색 격자임을 뜻한다.
 * 
    5 6
    010101
    010000
    011101
    100011
    001011
 * 
 * 출력
 * 바깥에서 흘려준 전류가 안쪽까지 잘 전달되면 YES를 출력한다.
 * 그렇지 않으면 NO를 출력한다.
 * 
    NO
 * 
 * 파싱
 * m = 5, n = 6
 * arr = [[0, 1, 0, 1, 0, 1], [0, 1, 0, 0, 0, 0], [0, 1, 1, 1, 0, 1], [1, 0, 0, 0, 1, 1], [0, 0, 1, 0, 1, 1]]
 * 
 * {{초기 설정}}
 * 방문리스트 visited = [[0 * n] * m]
 * 
 * 상하좌우
 * dx = [1, -1, 0, 0]
 * dy = [0, 0, 1, -1]
 */
const fs = require("fs");
let [mn, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [m, n] = mn.split(" ").map(Number);
arr = arr.map(a => a.trim().split("").map(Number));

let visited = Array.from({ length: m }, () => new Array(n).fill(0));
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];

for (let i = 0; i < n; i++) {
  if (arr[0][i] == 0 && !visited[0][i]) {
    visited[0][i] = 1;
    dfs(0, i);
  }
}

let answer = "NO";

for (let i = 0; i < n; i++) {
  if (visited[m - 1][i] == 1 && arr[m - 1][i] == 0) {
    answer = "YES";
    break;
  }
}

console.log(answer);

function dfs(x, y) {
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      0 <= nx &&
      nx < m &&
      0 <= ny &&
      ny < n &&
      !visited[nx][ny] &&
      !arr[nx][ny]
    ) {
      visited[nx][ny] = 1;
      dfs(nx, ny);
    }
  }
}
/**
 * 채점 결과
 * 메모리: 72392KB
 * 시간: 324ms
 * 언어: JS
 */
