/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/13913
 * 
 * 시간제한: 2초
 * 메모리제한: 512MB
 * 
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 * 
    5 17
 * 
 * 출력
 * 첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 * 둘째 줄에 어떻게 이동해야 하는지 공백으로 구분해 출력한다.
 * 
    4
    5 4 8 16 17
 * 
 * 파싱
 * n = 5, k = 17
 * 
 * 초기 설정
 * visited = [Infinity * 100001]
 * queue = [n]
 * pointer = 0
 */
const fs = require("fs");
let [n, k] = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split(" ")
  .map(Number);

if (k < n) {
  console.log(n - k);
  console.log(
    Array.from({ length: n - k + 1 }, (v, i) => i + k)
      .reverse()
      .join(" ")
  );
} else {
  let visited = new Array(100001).fill(Infinity);
  visited[n] = -1;
  let queue = [n];
  let pointer = 0;

  while (pointer < queue.length) {
    let current = queue[pointer++];
    let dx = [1, -1, current];

    if (current == k) break;

    for (let i = 0; i < 3; i++) {
      let next = current + dx[i];
      if (next < 0 || next > 2 * k) continue;
      if (visited[next] == Infinity) {
        visited[next] = current;
        queue.push(next);
      }
    }
  }
  let answer = findCourse(visited);
  console.log(answer.length - 1 + "\n" + answer.join(" "));
}

function findCourse(visited) {
  let num = k;
  let result = [];
  while (num !== -1) {
    result.push(num);
    num = visited[num];
  }
  return result.reverse();
}
/**
 * 채점 결과
 * 메모리: 20628KB
 * 시간: 236ms
 * 언어: JS
 */
