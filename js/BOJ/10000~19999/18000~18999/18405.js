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
 * 파싱
 * n = 3, k = 3
 * arr = [[1, 0, 2], [0, 0, 0], [3, 0, 0]]
 * s = 2, x = 3, y = 2
 * 
 * {{초기 설정}}
 * 상하좌우
 * dx = [1, -1, 0, 0]
 * dy = [0, 0, 1, -1]
 * 
 * 체크리스트
 * checkList = [[0 * n] * n] => 바이러스가 있는 인덱스의 값을 1로 설정
 * time = 1 => 1초부터 시작한다고 가정
 */

// 파싱
const fs = require("fs");
let [nk, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, k] = nk.split(" ").map(Number);
arr = arr.map(a => a.split(" ").map(Number));
let [s, x, y] = arr.pop();

// 초기 설정
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

// 1초부터 시작한다고 가정했으므로 0초부터 s초까지가 아닌 1초부터 s+1초까지
while (time <= s + 1) {
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (arr[i][j] == 0) {
        // 아직 바이러스가 전염되지 않은 인덱스에 대해
        let min = k + 1; // 바이러스의 번호 최댓값
        for (let k = 0; k < 4; k++) {
          // 상하좌우에 있는 바이러스를 탐색
          let ni = i + dx[k];
          let nj = j + dy[k];

          if (
            // 범위 체크
            0 <= ni &&
            ni < n &&
            0 <= nj &&
            nj < n &&
            // 현재 시간 이전에 전염된 바이러스에 대해서만(현재 시간에 바이러스가 전염되고있는 인덱스는 제외)
            checkList[ni][nj] < time
          ) {
            let virus = arr[ni][nj];
            if (virus == 0) continue; // 바이러스가 없는 경우
            if (virus < min) {
              // 바이러스의 번호가 인접한 바이러스의 최솟값보다 작은 경우
              min = virus; // 최솟값을 업데이트
            }
          }
        }
        if (min <= k) {
          // 인접한 인덱스의 바이러스 번호의 최솟값이 k보다 작거나 같을 때(바이러스가 있을 때)
          arr[i][j] = min; // 현재 인덱스에 가장 작은 번호의 바이러스를 전염시키고
          checkList[i][j] = time; // 바이러스가 전염된 시각을 체크리스트에 저장
        }
      }
    }
  }
  time++; // 시간 증가
}

console.log(arr[x - 1][y - 1]);
/**
 * 채점 결과
 * 메모리: 12188KB
 * 시간: 1172ms
 * 언어: JS
 */
