/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2573
 * 
 * 시간제: 1초
 * 메모리제한: 256MB
 * 
 * 입력
 * 첫 줄에는 이차원 배열의 행의 개수와 열의 개수를 나타내는 두 정수 N과 M이 한 개의 빈칸을 사이에 두고 주어진다. N과 M은 3 이상 300 이하이다. 그 다음 N개의 줄에는 각 줄마다 배열의 각 행을 나타내는 M개의 정수가 한 개의 빈 칸을 사이에 두고 주어진다. 각 칸에 들어가는 값은 0 이상 10 이하이다. 배열에서 빙산이 차지하는 칸의 개수, 즉, 1 이상의 정수가 들어가는 칸의 개수는 10,000 개 이하이다. 배열의 첫 번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다.
 * 
    5 7
    0 0 0 0 0 0 0
    0 2 4 5 3 0 0
    0 3 0 2 5 2 0
    0 7 6 2 4 0 0
    0 0 0 0 0 0 0
 * 
 * 출력
 * 첫 줄에 빙산이 분리되는 최초의 시간(년)을 출력한다. 만일 빙산이 다 녹을 때까지 분리되지 않으면 0을 출력한다.
 * 
    2
 * 
 * 파싱
 * n = 5, m = 7
 * arr = [
 *  [0, 0, 0, 0, 0, 0, 0],
 *  [0, 2, 4, 5, 3, 0, 0],
 *  [0, 3, 0, 2, 5, 2, 0],
 *  [0, 7, 6, 2, 4, 0, 0],
 *  [0, 0, 0, 0, 0, 0, 0]
 * ]
 * 
 * {{초기 설정}}
 * 동서남북
 * dx = [1, -1, 0, 0]
 * dy = [0, 0, 1, -1]
 * 
 * 방문리스트 visited = [[0 * n] * m]
 * 동서남북에 바다가 접해있는 수의 갯수 리스트 [[0 * n] * m]
 */

// 파싱
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map(a => a.split(" ").map(Number));

// 초기 설정
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let time = 0;
let visited;
let sea;

// 출력
console.log(solve());

function solve() {
  while (true) {
    // 방문 리스트 초기화
    visited = Array.from({ length: n }, () => new Array(m).fill(0));
    // 인접 바다 리스트 초기화
    sea = Array.from({ length: n }, () => new Array(m).fill(0));
    let counts = 0; // 덩어리 갯수 초기화
    let finished = true; // 빙산이 다 녹았는지

    for (let i = 0; i < n; i++) {
      for (let j = 0; j < m; j++) {
        if (!visited[i][j] && arr[i][j]) {
          // 방문하지 않은 빙산이 있을 경우
          finished = false; // 빙산이 안 녹았다고 표시
          counts++; // 빙산덩어리 갯수 1 증가
          if (counts >= 2) {
            // 빙산의 덩어리가 2개 이상일 때
            return time; // 현재 시간을 리턴
          }
          visited[i][j] = 1; // 방문 표시
          sea[i][j] = checkSea(i, j); // 현재 시간에 현재 인덱스의 빙산이 바다와 인접한 면의 갯수 구하기
          melt(i, j); // 해당 빙산에 대해서 DFS 수행
        }
      }
    }

    if (finished) {
      // 빙산이 다 녹았다면
      return 0;
    }
    time++; // 1시간 증가
  }
}

function melt(x, y) {
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      0 <= nx &&
      nx < n &&
      0 <= ny &&
      ny < m &&
      !visited[nx][ny] &&
      arr[nx][ny] > 0
    ) {
      visited[nx][ny] = 1;
      sea[nx][ny] = checkSea(nx, ny);
      melt(nx, ny);
    }
  }
  // 바다와 인접한 면의 갯수를 가지고 빙산을 최종적으로 녹임.(DFS의 끝부터 처음으로 빼기)
  if (arr[x][y] - sea[x][y] >= 0) {
    arr[x][y] -= sea[x][y];
  } else {
    arr[x][y] = 0;
  }
}

// 바다와 인접한 면의 갯수 계산
function checkSea(x, y) {
  let count = 0;
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (0 <= nx && nx < n && 0 <= ny && ny < m && arr[nx][ny] == 0) {
      count++;
    }
  }
  return count;
}
/**
 * 채점 결과
 * 메모리: 49892KB
 * 시간: 748ms
 * 언어: JS
 */
