/**
 * * 문제 출처: 백준 온라인 져지
 *
 * * 시간제한: 2초
 * * 메모리제한: 256MB
 * 
 * * 입력 & 파싱
 * 첫째 줄에 정수 K가 주어진다. 둘째 줄에 격자판의 가로길이 W, 세로길이 H가 주어진다. 그 다음 H줄에 걸쳐 W개의 숫자가 주어지는데, 0은 아무것도 없는 평지, 1은 장애물을 뜻한다. 장애물이 있는 곳으로는 이동할 수 없다. 시작점과 도착점은 항상 평지이다. W와 H는 1이상 200이하의 자연수이고, K는 0이상 30이하의 정수이다.
 * 
    1         - k
    4 4       - n, m
    0 0 0 0   - arr
    1 0 0 0
    0 0 1 0
    0 1 0 0
 * 
 * * 출력
 * 첫째 줄에 원숭이의 동작수의 최솟값을 출력한다. 시작점에서 도착점까지 갈 수 없는 경우엔 -1을 출력한다.
 * 
    4        - answer
 * 
 * * 초기 설정
 * dx, dy: 상하좌우(인접)
 * hx, hy: 말의 이동경로
 * pointer: 스택포인터
 * queue: 스택(포인터를 이용하여 큐처럼 작동)
 * visited: 방문리스트 3차원배열 n * m * k+1 (k번까지 말처럼 이동할 수 있기 때문에 k+1)
 */

// 파싱
const fs = require("fs");
let [k, wh, ...arr] = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n");
k = +k;
const [m, n] = wh.split(" ").map(Number);
arr = arr.map(a => a.trim().split(" ").map(Number));

// 초기 설정
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];

let hx = [2, 2, -2, -2, 1, 1, -1, -1];
let hy = [1, -1, 1, -1, 2, -2, 2, -2];

let pointer = 0;
let queue = [[0, 0, 0]];
let visited = Array.from({ length: n }, () =>
  Array.from({ length: m }, () => new Array(k + 1).fill(-1))
);
visited[0][0][0] = 0;
let answer = -1;

// BFS
while (pointer < queue.length) {
  let [x, y, h] = queue[pointer++];

  if (x == n - 1 && y == m - 1) {
    answer = visited[x][y][h];
    break;
  }

  if (h < k) {
    for (let i = 0; i < 8; i++) {
      let nx = x + hx[i];
      let ny = y + hy[i];

      if (
        0 <= nx &&
        nx < n &&
        0 <= ny &&
        ny < m &&
        visited[nx][ny][h + 1] == -1 &&
        arr[nx][ny] == 0
      ) {
        visited[nx][ny][h + 1] = visited[x][y][h] + 1;
        queue.push([nx, ny, h + 1]);
      }
    }
  }
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      0 <= nx &&
      nx < n &&
      0 <= ny &&
      ny < m &&
      visited[nx][ny][h] == -1 &&
      arr[nx][ny] == 0
    ) {
      visited[nx][ny][h] = visited[x][y][h] + 1;
      queue.push([nx, ny, h]);
    }
  }
}

console.log(answer);
/**
 * 채점 결과
 * 메모리: 186604KB
 * 시간: 1008ms
 * 언어: JS
 */
