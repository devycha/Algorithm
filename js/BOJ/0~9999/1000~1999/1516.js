/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1516
 * 
 * 시간제한: 2초
 * 메모리제한: 128MB
 * 
 * 입력 & 파싱
 * 첫째 줄에 건물의 종류 수 N(1 ≤ N ≤ 500)이 주어진다. 다음 N개의 줄에는 각 건물을 짓는데 걸리는 시간과 그 건물을 짓기 위해 먼저 지어져야 하는 건물들의 번호가 주어진다. 건물의 번호는 1부터 N까지로 하고, 각 줄은 -1로 끝난다고 하자. 각 건물을 짓는데 걸리는 시간은 100,000보다 작거나 같은 자연수이다. 모든 건물을 짓는 것이 가능한 입력만 주어진다.
 * 
    5         -> n
    10 -1     -> arr[0]
    10 1 -1
    4 1 -1
    4 3 1 -1
    3 3 -1    -> arr[n-1]
 * 
 * 출력
 * N개의 각 건물이 완성되기까지 걸리는 최소 시간을 출력한다.
 * 
    10
    20
    14
    18
    17
 * 
 * 초기 설정
 * cost: 각 건물을 설치하는데 걸리는 시간
 * obj: 선행작업: [후행작업리스트]
 * degree: 해당 인덱스 번호의 건물을 짓기 위해 먼저 지어야 하는 건물의 갯수(위상)
 * time: 각 건물이 설치가 완료되는 최소 시간(초기값: cost 배열 복제)
 * queue: BFS구현용 스택
 * pointer: BFS구현용 포인터
 */
const fs = require("fs");
let [n, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
(n = +n), (arr = arr.map((a) => a.trim().split(" ").map(Number)));

let cost = new Array(n + 1).fill(0);
let obj = Array.from({ length: n + 1 }, () => new Array());
let degree = new Array(n + 1).fill(0);

arr.forEach((e, k) => {
  cost[k + 1] = e[0];
  for (let i = 1; i < e.length - 1; i++) {
    obj[e[i]].push(k + 1);
    degree[k + 1]++;
  }
});
let time = cost.slice();
let queue = [];
let pointer = 0;

for (let i = 1; i <= n; i++) {
  if (degree[i] == 0) {
    queue.push([i, cost[i]]);
  }
}

while (pointer < queue.length) {
  let [b, t] = queue[pointer++];

  if (obj[b].length) {
    obj[b].forEach((nb) => {
      time[nb] = Math.max(time[nb], t + cost[nb]);

      if (--degree[nb] == 0) {
        queue.push([nb, time[nb]]);
      }
    });
  }
}

console.log(time.slice(1).join("\n"));
/**
 * 채점 결과
 * 메모리: 13004KB
 * 시간: 200ms
 * 언어: JS
 */
