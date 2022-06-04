/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1937
 * 
 * * 시간제한: 2초
 * * 메모리제한: 256MB
 * 
 * * 입력
 * 첫째 줄에 대나무 숲의 크기 n(1 ≤ n ≤ 500)이 주어진다. 그리고 둘째 줄부터 n+1번째 줄까지 대나무 숲의 정보가 주어진다. 대나무 숲의 정보는 공백을 사이로 두고 각 지역의 대나무의 양이 정수 값으로 주어진다. 대나무의 양은 1,000,000보다 작거나 같은 자연수이다.
 * 
    4
    14 9 12 10
    1 11 5 4
    7 15 2 13
    6 3 16 8
 * 
 * * 출력
 * 첫째 줄에는 판다가 이동할 수 있는 칸의 수의 최댓값을 출력한다.
 * 
    4
 * 
 * * 파싱
 * n = 4
 * arr = [[14, 9, 12, 10], [1, 11, 5, 4], [7, 15, 2, 13], [6, 3, 16, 8]]
 * 
 * * 초기 설정
 * 상하좌우 dx, dy
 * memoization dp = [[1 * n] * n]
 * 최대값 max = 0
 */

// 파싱
const fs = require("fs");
let [n, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
arr = arr.map((a) => a.trim().split(" ").map(Number));

// 초기 설정
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let dp = Array.from({ length: n }, () => new Array(n).fill(0));
let max = 0;

// DFS & DP 시작(모든 인덱스에 대해)
for (let x = 0; x < n; x++) {
  for (let y = 0; y < n; y++) {
    max = Math.max(max, dfs(x, y));
  }
}

console.log(max);

function dfs(x, y) {
  // DFS가 모두 끝나서 판다가 몇년을 살 수 있을지 정해진 인덱스의 경우
  if (dp[x][y] >= 1) {
    return dp[x][y]; // 해당 연도를 리턴
  }

  dp[x][y] = 1; // 최소 1년은 살 수 있기 때문에 1로 초기화

  // 상하좌우 인덱스에 대해서
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];
    // 범위 체크
    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
    // 현재 인덱스의 대나무보다 더 많은 대나무를 가진 곳만
    if (arr[nx][ny] > arr[x][y]) {
      /**
       * 해당 인덱스에 재귀적으로 DFS를 수행하여 그 중 최댓값을 가져오면서
       * DFS가 끝난 인덱스는 dp배열에 저장한다.
       */
      dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
    }
  }
  // 상하좌우에 대해서 DFS가 모두 끝난 인덱스는 현재 인덱스의 dp값을 리턴한다.(판다가 살 수 있는 년도의 최댓값)
  return dp[x][y];
}
/**
 * 채점 결과
 * 메모리: 22248KB
 * 시간: 272ms
 * 언어: JS
 */
