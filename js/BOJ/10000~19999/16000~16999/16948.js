/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/16948
 * 
 * 입력
 * 첫째 줄에 체스판의 크기 N(5 ≤ N ≤ 200)이 주어진다. 둘째 줄에 r1, c1, r2, c2가 주어진다.
 * 
    7
    6 6 0 1
 * 
 * 출력
 * 첫째 줄에 데스 나이트가 (r1, c1)에서 (r2, c2)로 이동하는 최소 이동 횟수를 출력한다. 이동할 수 없는 경우에는 -1을 출력한다.
 * 
    4
 * 
 * 파싱
 * n = 7
 * r1 = 6, c1 = 6
 * r2 = 0, c2 = 1
 * 
 * {{초기 설정}}
 * 나이트의 이동경로 6가지
 * dr = [-2, -2, 0, 0, 2, 2]
 * dc = [-1, 1, -2, 2, -1, 1]
 * 
 * 체크리스트
 * checkList = [[0 * n] * n] -> checkList[r1][c1] = 1
 * 
 * BFS용 큐와 포인터
 * queue = [[r1, c1]]
 * i = 0;
 */

// 파싱
const fs = require("fs");
let [n, arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
let [r1, c1, r2, c2] = arr.trim().split(" ").map(Number);

// 초기 설정
// 6가지 이동 경로
let dr = [-2, -2, 0, 0, 2, 2];
let dc = [-1, 1, -2, 2, -1, 1];
// 체크리스트
let checkList = Array.from({ length: n }, () => new Array(n).fill(0));
checkList[r1][c1] = 1;
// BFS용 큐와 포인터
let queue = [[r1, c1]];
let i = 0;
// BFS
while (i < queue.length) {
  let [cr, cc] = queue[i++];

  if (cr == r2 && cc == c2) {
    break;
  }

  for (let i = 0; i < 6; i++) {
    let nr = cr + dr[i];
    let nc = cc + dc[i];
    // 범위 체크
    if (0 <= nr && nr < n && 0 <= nc && nc < n && !checkList[nr][nc]) {
      checkList[nr][nc] = checkList[cr][cc] + 1;
      queue.push([nr, nc]);
    }
  }
}

console.log(checkList[r2][c2] - 1);
/**
 * 채점 결과
 * 메모리: 12644KB
 * 시간: 204ms
 * 언어: JS
 */
