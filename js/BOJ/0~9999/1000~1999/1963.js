/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1963
 * 
 * 입력
 * 첫 줄에 test case의 수 T가 주어진다. 다음 T줄에 걸쳐 각 줄에 1쌍씩 네 자리 소수가 주어진다.
 * 
    3
    1033 8179
    1373 8017
    1033 1033
 * 
 * 출력
 * 각 test case에 대해 두 소수 사이의 변환에 필요한 최소 회수를 출력한다. 불가능한 경우 Impossible을 출력한다.
 * 
    6
    7
    0
 * 
 * 파싱
 * n = 3
 * arr = [[1033, 8179], [1373, 8017], [1033, 1033]]
 * 
 * {{초기 설정}}
 * maxPrime = 10000
 * 결과 출력용 배열 result = []
 * 소수인지 아닌지 적은 배열 primeNums = [true * 10000]
 */
const fs = require("fs");
let [n, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
arr = arr.map(a => a.trim().split(" ").map(Number));
const maxPrime = 10000;
let result = [];
let primeNums = new Array(maxPrime).fill(true);

for (let i = 2; i ** 2 < maxPrime; i++) {
  for (let j = 2 * i; j < maxPrime; j += i) {
    primeNums[j] = false;
  }
}
let visited;
arr.forEach(([start, end]) => {
  visited = new Array(maxPrime).fill(0);
  result.push(bfs(start, end));
});

console.log(result.join("\n"));

function bfs(start, end) {
  let queue = [[start, 0]];
  let pointer = 0;

  while (pointer < queue.length) {
    let [current, count] = queue[pointer++];

    if (current == end) {
      return count;
    }

    current = current + "";

    for (let i = 0; i < 10; i++) {
      let change =
        i * 1000 + +current[1] * 100 + +current[2] * 10 + +current[3];
      if (primeNums[change] && i != 0 && !visited[change]) {
        visited[change] = 1;
        queue.push([change, count + 1]);
      }

      change = +current[0] * 1000 + i * 100 + +current[2] * 10 + +current[3];
      if (primeNums[change] && !visited[change]) {
        visited[change] = 1;
        queue.push([change, count + 1]);
      }

      change = +current[0] * 1000 + +current[1] * 100 + i * 10 + +current[3];
      if (primeNums[+change] && !visited[change]) {
        visited[change] = 1;
        queue.push([+change, count + 1]);
      }

      change = +current[0] * 1000 + +current[1] * 100 + +current[2] * 10 + i;
      if (primeNums[+change] && !visited[change]) {
        visited[change] = 1;
        queue.push([+change, count + 1]);
      }
    }
  }
}
/**
 * 채점 결과
 * 메모리: 20952KB
 * 시간: 272ms
 * 언어: JS
 */
