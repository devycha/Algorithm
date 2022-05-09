/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2636
 *
 * 입력
 * 첫째 줄에는 사각형 모양 판의 세로와 가로의 길이가 양의 정수로 주어진다. 세로와 가로의 길이는 최대 100이다. 판의 각 가로줄의 모양이 윗 줄부터 차례로 둘째 줄부터 마지막 줄까지 주어진다. 치즈가 없는 칸은 0, 치즈가 있는 칸은 1로 주어지며 각 숫자 사이에는 빈칸이 하나씩 있다.
 * 
    13 12
    0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 1 1 0 0 0
    0 1 1 1 0 0 0 1 1 0 0 0
    0 1 1 1 1 1 1 0 0 0 0 0
    0 1 1 1 1 1 0 1 1 0 0 0
    0 1 1 1 1 0 0 1 1 0 0 0
    0 0 1 1 0 0 0 1 1 0 0 0
    0 0 1 1 1 1 1 1 1 0 0 0
    0 0 1 1 1 1 1 1 1 0 0 0
    0 0 1 1 1 1 1 1 1 0 0 0
    0 0 1 1 1 1 1 1 1 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0
 * 
 * 출력
 * 첫째 줄에는 치즈가 모두 녹아서 없어지는 데 걸리는 시간을 출력하고, 둘째 줄에는 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 출력한다.
 * 
    3
    5
 */
// 파싱
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map(a => a.trim().split(" ").map(Number));

// 상하좌우
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];

// 매시간 녹는 치즈의 갯수 배열
let count = [];

// 매시간 체크리스트
let checkList = Array.from({ length: n }, () => new Array(m).fill(0));

while (true) {
  // 1시간 뒤의 녹을 예정인 치즈의 갯수 push
  count.push(0);

  // 체크리스트 초기화
  checkList = Array.from({ length: n }, () => new Array(m).fill(0));

  // 네 모서리에서 DFS를 수행
  checkList[0][0] = 1;
  dfs(0, 0);
  checkList[0][m - 1] = 1;
  dfs(0, m - 1);
  checkList[n - 1][0] = 1;
  dfs(n - 1, 0);
  checkList[n - 1][m - 1];
  dfs(n - 1, m - 1);

  // 다음에 녹을 치즈의 개수가 0이라면 종료
  if (count[count.length - 1] == 0) {
    break;
  }
}

console.log(count.length - 1 + "\n" + count[count.length - 2]);

function dfs(x, y) {
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

    if (arr[nx][ny] == 1 && !checkList[nx][ny]) {
      arr[nx][ny] = 0;
      checkList[nx][ny] = 1;
      count[count.length - 1]++;
    }

    if (arr[nx][ny] == 0 && checkList[nx][ny] == 0) {
      checkList[nx][ny] = 1;
      dfs(nx, ny);
    }
  }
}
/**
 * 채점 결과
 * 메모리: 11360KB
 * 시간: 188ms
 * 언어: JS
 */
