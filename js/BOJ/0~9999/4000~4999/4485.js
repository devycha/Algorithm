/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/4485
 * 
 * * 시간제한: 1초
 * * 메모리제한: 256MB
 * 
 * * 입력
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다.
 * 각 테스트 케이스의 첫째 줄에는 동굴의 크기를 나타내는 정수 N이 주어진다. (2 ≤ N ≤ 125) N = 0인 입력이 주어지면 전체 입력이 종료된다.
 * 이어서 N개의 줄에 걸쳐 동굴의 각 칸에 있는 도둑루피의 크기가 공백으로 구분되어 차례대로 주어진다. 도둑루피의 크기가 k면 이 칸을 지나면 k루피를 잃는다는 뜻이다. 여기서 주어지는 모든 정수는 0 이상 9 이하인 한 자리 수다.
 * 
    3
    5 5 4
    3 9 1
    3 2 7
    5
    3 7 2 0 1
    2 8 0 9 1
    1 2 1 8 1
    9 8 9 2 0
    3 6 5 1 5
    7
    9 0 5 1 1 5 3
    4 1 2 1 6 5 3
    0 7 6 1 6 8 5
    1 1 7 8 3 2 3
    9 4 0 7 6 4 1
    5 8 3 2 4 8 3
    7 4 8 4 8 3 4
    0
 * 
 * * 출력
 * 각 테스트 케이스마다 한 줄에 걸쳐 정답을 형식에 맞춰서 출력한다. 형식은 예제 출력을 참고하시오.
 * 
    Problem 1: 20
    Problem 2: 19
    Problem 3: 36
 * 
 * * 파싱
 * 입력값을 한줄씩 배열에 담아 하나의 배열에 담은 2차원 배열
 * arr
 * 
 * * 초기 설정
 * 상하좌우
 * dx, dy
 * 
 * n(동굴의 크기)
 * cave(도둑루피배열)
 * dp(루피를 최소한으로 잃는 값이 담긴 배열)
 * result(정답배열)
 */
const fs = require("fs");
let arr = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n")
  .map((a) => a.trim().split(" ").map(Number));

let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let n, cave, dp;
let result = [];

for (let i = 0; i < arr.length; i += arr[i][0] + 1) {
  if (arr[i][0] == 0) break;

  n = arr[i][0];
  cave = [];
  for (let j = i + 1; j < i + arr[i][0] + 1; j++) {
    cave.push(arr[j]);
  }
  dp = Array.from({ length: n }, () => new Array(n).fill(Infinity));
  dp[0][0] = cave[0][0];

  let queue = [[0, 0, dp[0][0]]];

  while (queue.length) {
    let [x, y, dist] = queue.shift();

    if (dp[x][y] < dist) continue;

    for (let j = 0; j < 4; j++) {
      let nx = x + dx[j];
      let ny = y + dy[j];

      if (0 <= nx && nx < n && 0 <= ny && ny < n) {
        if (dp[nx][ny] > dist + cave[nx][ny]) {
          dp[nx][ny] = dist + cave[nx][ny];
          queue.push([nx, ny, dp[nx][ny]]);
        }
      }
    }
  }

  result.push(dp[n - 1][n - 1]);
}

console.log(result.map((a, i) => `Problem ${i + 1}: ${a}`).join("\n"));
/**
 * 채점 결과
 * 메모리: 20920KB
 * 시간: 328ms
 * 언어: JS
 */
