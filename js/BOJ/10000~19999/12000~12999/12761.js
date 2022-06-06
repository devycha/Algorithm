/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/12761
 * 
 * * 시간제한: 1초
 * * 메모리제한: 128MB
 * 
 * * 입력
 * 입력의 첫 줄에 스카이 콩콩의 힘 A와 B, 그리고 동규의 현재위치 N, 주미의 현재 위치 M이 주어진다.
 * 2 <= A, B <= 30, 0 <= N, M <= 100000
 * 
    2 3 1 20
 * 
 * * 출력
 * 동규가 주미에게 도달하기 위한 최소한의 이동 횟수를 출력하라.
 * 
    4
 * 
 * * 파싱
 * a = 2, b = 3, n = 1, m = 20
 * 
 * * 초기 설정
 * 6가지 방향 dx = [1, -1, a, -a, b, -b]
 * a배 b배 위치로 이동하는 경우는 따로 구현
 * 
 * BFS용
 * queue = [n]
 * pointer = 0
 * 
 * 방문리스트
 * visited = [0 * 100001]
 */
const fs = require("fs");
let [a, b, n, m] = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split(" ")
  .map(Number);

let dx = [1, -1, a, -a, b, -b];
let queue = [n];
let pointer = 0;
let visited = new Array(100001).fill(0);
visited[n] = 1;

while (pointer < queue.length) {
  let current = queue[pointer++];
  if (current == m) {
    break;
  }

  for (let i = 0; i < 6; i++) {
    let next = current + dx[i];

    if (0 <= next && next <= 100000 && !visited[next]) {
      visited[next] = visited[current] + 1;
      queue.push(next);
    }
  }

  if (current * a <= 100000 && !visited[current * a]) {
    visited[current * a] = visited[current] + 1;
    queue.push(current * a);
  }

  if (current * b <= 100000 && !visited[current * b]) {
    visited[current * b] = visited[current] + 1;
    queue.push(current * b);
  }
}
console.log(visited[m] - 1);
/**
 * 채점 결과
 * 메모리: 22560KB
 * 시간: 212ms
 * 언어: JS
 */
