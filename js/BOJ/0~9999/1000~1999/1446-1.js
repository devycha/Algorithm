/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1446
 * 
 * * 시간제한: 2초
 * * 메모리제한: 128MB
 * 
 * * 입력
 * 첫째 줄에 지름길의 개수 N과 고속도로의 길이 D가 주어진다. N은 12 이하인 양의 정수이고, D는 10,000보다 작거나 같은 자연수이다. 다음 N개의 줄에 지름길의 시작 위치, 도착 위치, 지름길의 길이가 주어진다. 모든 위치와 길이는 10,000보다 작거나 같은 음이 아닌 정수이다. 지름길의 시작 위치는 도착 위치보다 작다.
 * 
    5 150
    0 50 10
    0 50 20
    50 100 10
    100 151 10
    110 140 90
 * 
 * * 출력
 * 세준이가 운전해야하는 거리의 최솟값을 출력하시오.
 * 
    70
 * 
 * * 파싱
 * n = 5, d = 150
 * arr = [[0, 50, 10], [0, 50, 20], [50, 100, 10], [100, 151, 10], [110, 140, 90]]
 * 
 * * 초기 설정
 * 지름길시작점 - 지름길도착점, 거리 객체 obj = {}
 * 각 위치까지 가는데 걸리는 최소 시간 distance = [0, 1, 2, 3, 4, ... , d]
 */
const fs = require("fs");
let [nd, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, d] = nd.split(" ").map(Number);
arr = arr.map((a) => a.trim().split(" ").map(Number));

let obj = {};
arr.forEach((e) => {
  if (!obj[e[0]]) obj[e[0]] = [];
  obj[e[0]].push([e[1], e[2]]);
});

let distance = Array.from({ length: d + 1 }, (v, i) => i);

for (let i = 0; i <= d; i++) {
  if (distance[i - 1] + 1 < distance[i]) {
    distance[i] = distance[i - 1] + 1;
  }

  if (obj[i]) {
    obj[i].forEach((e) => {
      distance[e[0]] = distance[e[0]]
        ? Math.min(distance[e[0]], distance[i] + e[1])
        : distance[i] + e[1];
    });
  }
}

console.log(distance[d]);
/**
 * 채점 결과
 * 메모리: 9404KB
 * 시간: 128ms
 * 언어: JS
 */
