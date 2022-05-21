/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1261
 * 
 * 시간제한: 1초
 * 메모리제한: 128MB
 * 
 * 입력
 * 첫째 줄에 미로의 크기를 나타내는 가로 크기 M, 세로 크기 N (1 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 미로의 상태를 나타내는 숫자 0과 1이 주어진다. 0은 빈 방을 의미하고, 1은 벽을 의미한다.
 * (1, 1)과 (N, M)은 항상 뚫려있다.
 * 
    3 3
    011
    111
    110
 * 
 * 출력
 * 첫째 줄에 알고스팟 운영진이 (N, M)으로 이동하기 위해 벽을 최소 몇 개 부수어야 하는지 출력한다.
 * 
    3
 * 
 * 파싱
 * n = 3, m = 3 m이 행의 갯수, n이 열의 갯수
 * arr = [[0, 1, 1], [1, 1, 1], [1, 1, 0]]
 * 
 * {{초기 설정}}
 * 상하좌우
 * dx = [1, -1, 0, 0]
 * dy = [0, 0, 1, -1]
 * 
 * 방문리스트
 * visited = [[10000 * n] * m] => visited[0][0] = 1
 * 
 * BFS queue = [[0, 0]]
 * pointer = 0
 */
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map(a => a.trim().split(""));

let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let visited = Array.from({ length: m }, () => new Array(n).fill(0));
visited[0][0] = 1;

let queue = [[0, 0]];

while (queue.length) {
  const [x, y] = queue.shift();

  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];
    if (0 <= nx && nx < m && 0 <= ny && ny < n && !visited[nx][ny]) {
      if (arr[nx][ny] == 1) {
        visited[nx][ny] = visited[x][y] + 1;
        queue.push([nx, ny]);
      } else {
        visited[nx][ny] = visited[x][y];
        queue.unshift([nx, ny]);
      }
    }
  }
}

console.log(visited[m - 1][n - 1] - 1);
/**
 * 채점 결과
 * 메모리: 12308KB
 * 시간: 212ms
 * 언어: JS
 */
