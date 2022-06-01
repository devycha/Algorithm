/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2056
 * 
 * 시간제한: 2초
 * 메모리제한: 256MB
 * 
 * 입력
 * 첫째 줄에 N이 주어진다.
 * 두 번째 줄부터 N+1번째 줄까지 N개의 줄이 주어진다. 2번째 줄은 1번 작업, 3번째 줄은 2번 작업, ..., N+1번째 줄은 N번 작업을 각각 나타낸다. 각 줄의 처음에는, 해당 작업에 걸리는 시간이 먼저 주어지고, 그 다음에 그 작업에 대해 선행 관계에 있는 작업들의 개수(0 ≤ 개수 ≤ 100)가 주어진다. 그리고 선행 관계에 있는 작업들의 번호가 주어진다.
 * 
    7
    5 0
    1 1 1
    3 1 2
    6 1 1
    1 2 2 4
    8 2 2 4
    4 3 3 5 6
 * 
 * 출력
 * 첫째 줄에 모든 작업을 완료하기 위한 최소 시간을 출력한다.
 * 
    23
 * 
 * 파싱
 * n = 7
 * arr = [
    [ 5, 0 ],
    [ 1, 1, 1 ],
    [ 3, 1, 2 ],
    [ 6, 1, 1 ],
    [ 1, 2, 2, 4 ],
    [ 8, 2, 2, 4 ],
    [ 4, 3, 3, 5, 6 ]
  ]
 * 
 * 초기 설정
 * 위상
 * degree = [0 * n+1] 
 * 
 * 선행-후행 리스트 객체
 * obj = {}
 * 
 * 작업별 걸리는 시간
 * time = [0 * n+1]
 * 
 * BFS queue
 * queue = []
 */
const fs = require("fs");
let [n, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
arr = arr.map((a) => a.split(" ").map(Number));

let degree = new Array(n + 1).fill(0);
let obj = Array.from({ length: n + 1 }, () => new Array());
let time = new Array(n + 1).fill(0);
let queue = [];

for (let i = 1; i <= n; i++) {
  degree[i] = arr[i - 1][1];
  time[i] = arr[i - 1][0];
  if (degree[i] == 0) {
    queue.push(i);
  }
  if (degree[i]) {
    for (let j = 2; j < 2 + arr[i - 1][1]; j++) {
      obj[arr[i - 1][j]].push(i);
    }
  }
}

let pointer = 0;
while (pointer < queue.length) {
  let current = queue[pointer++];

  if (obj[current]) {
    obj[current].forEach((next) => {
      time[next] = Math.max(time[next], time[current] + arr[next - 1][0]);

      if (--degree[next] == 0) {
        queue.push(next);
      }
    });
  }
}

console.log(Math.max(...time));
/**
 * 채점 결과
 * 메모리: 50980KB
 * 시간: 428ms
 * 언어: JS
 */
