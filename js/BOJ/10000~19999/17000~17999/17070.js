/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/17070
 * 
 * 시간제한: 1초
 * 메모리제한: 512MB
 * 
 * 입력
 * 첫째 줄에 집의 크기 N(3 ≤ N ≤ 16)이 주어진다. 둘째 줄부터 N개의 줄에는 집의 상태가 주어진다. 빈 칸은 0, 벽은 1로 주어진다. (1, 1)과 (1, 2)는 항상 빈 칸이다.
 * 
    3
    0 0 0
    0 0 0
    0 0 0
 * 
 * 출력
 * 첫째 줄에 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수를 출력한다. 이동시킬 수 없는 경우에는 0을 출력한다. 방법의 수는 항상 1,000,000보다 작거나 같다.
 * 
    1
 * 
 * 파싱
 * n = 3
 * arr = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
 * 
 * {{초기 설정}}
 * 경우의수 count = 0
 */
const fs = require("fs");
let [n, ...arr] = fs.readFileSync("input.txt").toString().split("\n");
n = +n;
arr = arr.map((a) => a.trim().split(" ").map(Number));

let count = 0;
dfs(1, 1, 1, 2);
console.log(count);

/**
 * →, ↓, ↘ 세가지 이기 때문에
 * 겹치는 경우가 절대 없으므로
 * 파이프가 가로일 때: 오른쪽, 오른쪽아래
 * 파이프가 세로일 때: 아래, 오른쪽아래
 * 파이프가 대각선일 때: 오른쪽, 오른쪽 아래, 아래
 *
 * 이렇게 경우를 나누어서 정확하게만 구현해주면 된다.
 *
 * 범위체크와, 벽인지 아닌지 체크하는 것으로 dfs실행의 조건을 붙여주면 된다.
 */
function dfs(left0, left1, right0, right1) {
  if (right0 == n && right1 == n) {
    count++;
    return;
  }

  let a = right0 - left0;
  let b = right1 - left1;

  if (a == 1 && b == 1) {
    // 대각선
    // 아래
    if (right0 < n && arr[right0][right1 - 1] == 0) {
      dfs(right0, right1, right0 + 1, right1);
    }
    // 오른쪽
    if (right1 < n && arr[right0 - 1][right1] == 0) {
      dfs(right0, right1, right0, right1 + 1);
    }
    // 오른쪽아래
    if (
      right0 < n &&
      right1 < n &&
      arr[right0][right1] == 0 &&
      arr[right0 - 1][right1] == 0 &&
      arr[right0][right1 - 1] == 0
    ) {
      dfs(right0, right1, right0 + 1, right1 + 1);
    }
  } else if (a == 1) {
    // 세로
    // 아래
    if (right0 < n && arr[right0][right1 - 1] == 0) {
      dfs(right0, right1, right0 + 1, right1);
    }
    // 오른쪽아래
    if (
      right0 < n &&
      right1 < n &&
      arr[right0][right1] == 0 &&
      arr[right0 - 1][right1] == 0 &&
      arr[right0][right1 - 1] == 0
    ) {
      dfs(right0, right1, right0 + 1, right1 + 1);
    }
  } else if (b == 1) {
    // 가로
    // 오른쪽
    if (right1 < n && arr[right0 - 1][right1] == 0) {
      dfs(right0, right1, right0, right1 + 1);
    }
    // 오른쪽아래
    if (
      right0 < n &&
      right1 < n &&
      arr[right0][right1] == 0 &&
      arr[right0 - 1][right1] == 0 &&
      arr[right0][right1 - 1] == 0
    ) {
      dfs(right0, right1, right0 + 1, right1 + 1);
    }
  }
}
/**
 * 채점 결과
 * 메모리: 10196KB
 * 시간: 340ms
 * 언어: JS
 */
