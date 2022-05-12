/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/18405
 * 
 * 시간제한: 1초
 * 메모리제한: 256MB
 * 
 * 입력
 * 첫째 줄에 자연수 N, K가 공백을 기준으로 구분되어 주어진다. (1 ≤ N ≤ 200, 1 ≤ K ≤ 1,000) 둘째 줄부터 N개의 줄에 걸쳐서 시험관의 정보가 주어진다. 각 행은 N개의 원소로 구성되며, 해당 위치에 존재하는 바이러스의 번호가 공백을 기준으로 구분되어 주어진다. 단, 해당 위치에 바이러스가 존재하지 않는 경우 0이 주어진다. 또한 모든 바이러스의 번호는 K이하의 자연수로만 주어진다. N+2번째 줄에는 S, X, Y가 공백을 기준으로 구분되어 주어진다. (0 ≤ S ≤ 10,000, 1 ≤ X, Y ≤ N)
 * 
    3 3
    1 0 2
    0 0 0
    3 0 0
    2 3 2
 * 
 * 출력
 * S초 뒤에 (X,Y)에 존재하는 바이러스의 종류를 출력한다. 만약 S초 뒤에 해당 위치에 바이러스가 존재하지 않는다면, 0을 출력한다.
 * 
    3
 * 
 * 
 * 
 * 
 */
const fs = require("fs");
let [nk, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, k] = nk.split(" ").map(Number);
arr = arr.map(a => a.split(" ").map(Number));
let [s, x, y] = arr.pop();
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let checkList = Array.from({ length: n }, () => new Array(n).fill(0));
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (arr[i][j] > 0) {
      checkList[i][j] = 1;
    }
  }
}

let time = 1;
while (time <= s + 1) {
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (arr[i][j] == 0) {
        let min = k + 1;
        for (let k = 0; k < 4; k++) {
          let ni = i + dx[k];
          let nj = j + dy[k];

          if (
            0 <= ni &&
            ni < n &&
            0 <= nj &&
            nj < n &&
            checkList[ni][nj] < time
          ) {
            let virus = arr[ni][nj];
            if (virus == 0) continue;
            if (virus < min) {
              min = virus;
            }
          }
        }
        if (min <= k) {
          arr[i][j] = min;
          checkList[i][j] = time;
        }
      }
    }
  }
  time++;
}

console.log(arr[x - 1][y - 1]);
/**
 * 채점 결과
 * 메모리: 12188KB
 * 시간: 1172ms
 * 언어: JS
 */
