/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1303
 * 
 * 시간제한: 2초
 * 메모리제한: 128MB
 * 
 * 입력 & 파싱
 * 첫째 줄에는 전쟁터의 가로 크기 N, 세로 크기 M(1 ≤ N, M ≤ 100)이 주어진다. 그 다음 두 번째 줄에서 M+1번째 줄에는 각각 (X, Y)에 있는 병사들의 옷색이 띄어쓰기 없이 주어진다. 모든 자리에는 병사가 한 명 있다. B는 파란색, W는 흰색이다. 당신의 병사와 적국의 병사는 한 명 이상 존재한다.
 * 
    5 5     n(가로), m(세로)
    WBWWW   arr
    WWWWW
    BBBBB
    BBBWW
    WWWWW
 * 
 * 출력
 * 첫 번째 줄에 당신의 병사의 위력의 합과 적국의 병사의 위력의 합을 출력한다.
 * 
    130 65
 * 
 * 초기 설정
 * dx, dy: 상하좌우
 * visited: 방문리스트
 * white: 인접한 아군들의 수 집합
 * blue: 인접한 적군들의 수 집합
 */
// 파싱
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map((a) => a.trim().split(""));

// 초기 설정
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let visited = Array.from({ length: m }, () => new Array(n).fill(0));
let white = [];
let blue = [];

// 방문하지 않은 곳에 대해 DFS 수행
for (let i = 0; i < m; i++) {
  for (let j = 0; j < n; j++) {
    if (!visited[i][j]) {
      visited[i][j] = 1;

      if (arr[i][j] == "W") {
        white.push(1);
        dfs(i, j, "W");
      } else if (arr[i][j] == "B") {
        blue.push(1);
        dfs(i, j, "B");
      }
    }
  }
}

white = white.reduce((a, b) => a + b ** 2, 0);
blue = blue.reduce((a, b) => a + b ** 2, 0);
console.log(white + " " + blue);

// 인접한 아군 혹은 적군에 대해 방문하며 그 수를 증가시키는 DFS 함수
function dfs(x, y, c) {
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      0 <= nx &&
      nx < m &&
      0 <= ny &&
      ny < n &&
      !visited[nx][ny] &&
      arr[nx][ny] == c
    ) {
      visited[nx][ny] = 1;
      if (c == "W") {
        white[white.length - 1]++;
      } else if (c == "B") {
        blue[blue.length - 1]++;
      }
      dfs(nx, ny, c);
    }
  }
}
/**
 * 채점 결과
 * 메모리: 10420KB
 * 시간: 184ms
 * 언어: JS
 */
