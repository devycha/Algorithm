/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2638
 * 
 * 시간제한: 1초
 * 메모리제한: 128MB
 * 
 * 입력 & 파싱
 * 첫째 줄에는 모눈종이의 크기를 나타내는 두 개의 정수 N, M (5 ≤ N, M ≤ 100)이 주어진다. 그 다음 N개의 줄에는 모눈종이 위의 격자에 치즈가 있는 부분은 1로 표시되고, 치즈가 없는 부분은 0으로 표시된다. 또한, 각 0과 1은 하나의 공백으로 분리되어 있다.
 * 
    8 9                 n m
    0 0 0 0 0 0 0 0 0   arr
    0 0 0 1 1 0 0 0 0
    0 0 0 1 1 0 1 1 0
    0 0 1 1 1 1 1 1 0
    0 0 1 1 1 1 1 0 0
    0 0 1 1 0 1 1 0 0
    0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0
 * 
 * 출력
 * 출력으로는 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 정수로 첫 줄에 출력한다.
 * 
    4
 * 
 * 초기 설정
 * dx, dy: 상하좌우
 * visited: 방문리스트
 * count: 매 시간 치즈가 없어진 갯수
 * time: 시간
 */

// 파싱
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map(a => a.trim().split(" ").map(Number));

// 초기 설정
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let visited;
let count = 1;
let time = 0;

// 치즈가 녹아 없어진 갯수가 0이 될때 까지 반복
while (count > 0) {
  count = 0; // 현재 시간에 없어진 치즈 조각의 갯수를 0으로 초기화
  visited = Array.from({ length: n }, () => new Array(m).fill(0)); // 방문리스트 초기화
  visited[0][0] = 1; // 시작점(0, 0) 방문표시
  dfs(0, 0); // DFS수행
  time++; // 시간 증가
}

console.log(time - 1); // 없어진 치즈조각이 0일때의 시간(1)을 뺀 값 출력

function dfs(x, y) {
  for (let i = 0; i < 4; i++) {
    // 상하좌우
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (0 <= nx && nx < n && 0 <= ny && ny < m) {
      // 범위 체크
      if (arr[nx][ny] == 0 && visited[nx][ny] == 0) {
        // 빈공간이며 방문한 적이 없을 때
        visited[nx][ny]++; // 방문 표시
        dfs(nx, ny); // 해당 위치의 DFS 재귀 수행
      } else if (arr[nx][ny] == 1) {
        // 치즈조각이 있는 곳일 때
        if (visited[nx][ny] == 0) {
          // 방문하지 않았으면(공기중에 한번도 노출된 적이 없으면)
          visited[nx][ny]++; // 공기노출 + 1
        } else if (visited[nx][ny] == 1) {
          // 공기중에 한번 노출된 경우 이번 노출로 인해 치즈가 녹게 됨.
          visited[nx][ny]++; // 공기노출 + 1
          count++; // 녹은 치즈의 갯수 + 1
          arr[nx][ny] = "C"; // 해당 위치를 "C"로 표시하여 녹았다는 것을 표시
        }
      } else if (arr[nx][ny] == "C" && visited[nx][ny] == 0) {
        // 방문표시가 없고 치즈가 "C"(치즈가 녹아있는 경우)인 경우에는
        // 현재가 아니라 이전 타임에 이미 녹아있는 치즈이기 때문에 빈공간으로 인식하고 DFS를 재귀로 수행
        visited[nx][ny] = 1; // 방문표시
        dfs(nx, ny); // DFS 수행
      }
    }
  }
}
/**
 * 채점 결과
 * 메모리: 15768KB
 * 시간: 360ms
 * 언어: JS
 */
