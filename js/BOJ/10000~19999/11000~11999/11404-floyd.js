/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/11404
 * 
 * 시간제한: 1초
 * 메모리제한: 256MB
 * 
 * 입력
 * 첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다. 시작 도시와 도착 도시가 같은 경우는 없다. 비용은 100,000보다 작거나 같은 자연수이다.
 * 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
 * 
    5
    14
    1 2 2
    1 3 3
    1 4 1
    1 5 10
    2 4 2
    3 4 1
    3 5 1
    4 5 3
    3 5 10
    3 1 8
    1 4 2
    5 1 7
    3 4 2
    5 2 4
 * 
 * 출력
 * n개의 줄을 출력해야 한다. i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다. 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.
 * 
    0 2 3 1 4
    12 0 15 2 5
    8 5 0 1 1
    10 7 13 0 3
    7 4 10 6 0
 * 
 * 파싱
 * n = 5, m = 14
 * arr = [
  [ 1, 2, 2 ],  [ 1, 3, 3 ],
  [ 1, 4, 1 ],  [ 1, 5, 10 ],
  [ 2, 4, 2 ],  [ 3, 4, 1 ],
  [ 3, 5, 1 ],  [ 4, 5, 3 ],
  [ 3, 5, 10 ], [ 3, 1, 8 ],
  [ 1, 4, 2 ],  [ 5, 1, 7 ],
  [ 3, 4, 2 ],  [ 5, 2, 4 ]
  ]
 *
 * {{초기 설정}}
 * 최단 거리 2차원 배열 dist
 */

// 파싱
const fs = require("fs");
let [n, m, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
m = +m;
arr = arr.map((a) => a.split(" ").map(Number));

// 초기 설정
let dist = Array.from({ length: n }, () => new Array(n).fill(Infinity));

// 인접 도시 중 갈 수 있는 경로 최단거리
arr.forEach((bus) => {
  if (dist[bus[0] - 1][bus[1] - 1] == Infinity) {
    dist[bus[0] - 1][bus[1] - 1] = bus[2];
  } else {
    dist[bus[0] - 1][bus[1] - 1] = Math.min(
      dist[bus[0] - 1][bus[1] - 1],
      bus[2]
    );
  }
});

// 자기 자신의 도시는 0으로 셋팅
for (let i = 0; i < n; i++) {
  dist[i][i] = 0;
}

// 플로이드
for (let k = 0; k < n; k++) {
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (dist[i][j] > dist[i][k] + dist[k][j]) {
        dist[i][j] = dist[i][k] + dist[k][j];
      }
    }
  }
}

// 갈 수 없는 도시 Infinity -> 0으로 바꿈
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (dist[i][j] == Infinity) {
      dist[i][j] = 0;
    }
  }
}

// 출력
console.log(dist.map((a) => a.join(" ")).join("\n"));
/**
 * 채점 결과
 * 메모리: 45580KB
 * 시간: 388ms
 * 언어: JS
 */
