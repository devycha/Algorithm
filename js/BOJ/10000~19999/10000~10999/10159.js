/**
 * !문제 출처: 백준 온라인 져지
 * address: https://www.acmicpc.net/problem/10159
 * 
 * !시간제한: 1초
 * !메모리제한: 256MB
 * 
 * !입력 & 파싱
 * 첫 줄에는 물건의 개수 N 이 주어지고, 둘째 줄에는 미리 측정된 물건 쌍의 개수 M이 주어진다. 단, 5 ≤ N ≤ 100 이고, 0 ≤ M ≤ 2,000이다. 다음 M개의 줄에 미리 측정된 비교 결과가 한 줄에 하나씩 주어진다. 각 줄에는 측정된 물건 번호를 나타내는 두 개의 정수가 공백을 사이에 두고 주어지며, 앞의 물건이 뒤의 물건보다 더 무겁다.
 * 
    6   -> n
    5   -> m
    1 2 -> arr[0]
    2 3
    3 4
    5 4
    6 5 -> arr[m-1]
 * 
 * !출력
 * 여러분은 N개의 줄에 결과를 출력해야 한다. i 번째 줄에는 물건 i 와 비교 결과를 알 수 없는 물건의 개수를 출력한다.
 * 
 * !초기 설정
 * result: 결과출력용 배열
 * obj: 플로이드와샬(작은몸무게)
 * obj2: 플로이드와샬(큰몸무게)
 */
const fs = require("fs");
let [n, m, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
(n = +n), (m = +m);
arr = arr.map((a) => a.split(" ").map(Number));

let result = [];
let obj = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(0));
let obj2 = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(0));

arr.forEach((e) => {
  obj[e[0]][e[1]] = 1;
  obj2[e[1]][e[0]] = 1;
});

for (let k = 1; k <= n; k++) {
  for (let i = 1; i <= n; i++) {
    for (let j = 1; j <= n; j++) {
      if (obj[i][k] && obj[k][j]) {
        obj[i][j] = 1;
      }

      if (obj2[i][k] && obj2[k][j]) {
        obj2[i][j] = 1;
      }
    }
  }
}

for (let i = 1; i <= n; i++) {
  let count = 0;
  for (let j = 1; j <= n; j++) {
    if (i == j) continue;
    if (obj[i][j] == 0 && obj2[i][j] == 0) count++;
  }

  result.push(count);
}

console.log(result.join("\n"));
/**
 * 채점 결과
 * 메모리: 11504KB
 * 시간: 228ms
 * 언어: JS
 */
