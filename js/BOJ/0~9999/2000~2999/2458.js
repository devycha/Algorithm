/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2458
 * 
 * * 시간제한: 1초
 * * 메모리제한: 128MB
 * 
 * * 입력
 * 첫째 줄에 학생들의 수 N (2 ≤ N ≤ 500)과 두 학생 키를 비교한 횟수 M (0 ≤ M ≤ N(N-1)/2)이 주어진다. 다음 M개의 각 줄에는 두 학생의 키를 비교한 결과를 나타내는 두 양의 정수 a와 b가 주어진다. 이는 번호가 a인 학생이 번호가 b인 학생보다 키가 작은 것을 의미한다. 
 * 
    6 6
    1 5
    3 4
    5 4
    4 2
    4 6
    5 2
 * 
 * * 출력
 * 자신이 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지를 출력한다. 
 * 
    1
 * 
 * * 파싱
 * n = 6, m = 6
 * arr = [[1, 5], [3, 4], [5, 4], [4, 2], [4, 6], [5, 2]]
 * 
 * * 초기 설정
 * 플로이드와샬 경로배열 floyd1 = [[Infinity * n+1], * n+1]
 */
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map(a => a.split(" ").map(Number));
let answer = 0;

let floyd1 = Array.from({ length: n + 1 }, () =>
  new Array(n + 1).fill(Infinity)
);

arr.forEach(e => {
  floyd1[e[0]][e[1]] = 1;
});

for (let k = 1; k <= n; k++) {
  for (let i = 1; i <= n; i++) {
    for (let j = 1; j <= n; j++) {
      floyd1[i][j] = Math.min(floyd1[i][j], floyd1[i][k] + floyd1[k][j]);
    }
  }
}

for (let i = 1; i <= n; i++) {
  let count = 1;
  for (let j = 1; j <= n; j++) {
    if (i == j) continue;

    if (floyd1[i][j] == Infinity && floyd1[j][i] == Infinity) {
      count = 0;
      break;
    }
  }

  answer += count;
}

console.log(answer);
/**
 * 채점 결과
 * 메모리: 44900KB
 * 시간: 1208ms
 * 언어: JS
 */
