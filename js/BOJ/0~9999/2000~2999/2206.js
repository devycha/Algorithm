/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2206
 * 
 * 시간제한: 2초
 * 메모리제한: 192MB
 * 
 * 문제
 * N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
 * 만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
 * 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
 * 맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.
 * 
    6 4
    0100
    1110
    1000
    0000
    0111
    0000
 * 
 * 출력
 * 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
 * 
    15
 * 
 * 파싱 
 * n = 6, m = 4
 * arr = [
    [ 0, 1, 0, 0 ],
    [ 1, 1, 1, 0 ],
    [ 1, 0, 0, 0 ],
    [ 0, 0, 0, 0 ],
    [ 0, 1, 1, 1 ],
    [ 0, 0, 0, 0 ]
  ]
 * 
 * {{초기 설정}}
 * 체크리스트
 * checkList = n * m * 2 의 3차원 배열(모두 0으로 초기화)
 * 
 * 상하좌우
 * dx = [1, -1, 0, 0]
 * dy = [0, 0, 1, -1]
 * 
 * BFS용 queue
 * queue = [[0, 0, 0]] => 시작점과 벽을 부수지 않은 상태
 * 
 * 결과
 * result = -1
 */
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.trim().split(" ").map(Number);
arr = arr.map(a => a.trim().split("").map(Number));
let checkList = Array.from(new Array(n), () =>
  Array.from(new Array(m), () => new Array(2).fill(0))
);

let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let queue = [[0, 0, 0]];
checkList[0][0][0] = 1;
let result = -1;
let i = 0;

while (i < queue.length) {
  let [x, y, b] = queue[i++];

  if (x == n - 1 && y == m - 1) {
    result = checkList[x][y][b];
    break;
  }

  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (0 <= nx && nx < n && 0 <= ny && ny < m) {
      if (!arr[nx][ny] && !checkList[nx][ny][b]) {
        queue.push([nx, ny, b]);
        checkList[nx][ny][b] = checkList[x][y][b] + 1;
      }

      if (arr[nx][ny] && b == 0) {
        queue.push([nx, ny, 1]);
        checkList[nx][ny][1] = checkList[x][y][0] + 1;
      }
    }
  }
}

console.log(result);
/**
 * 채점 결과
 * 메모리: 323516KB
 * 시간: 1592ms
 * 언어: JS
 */
