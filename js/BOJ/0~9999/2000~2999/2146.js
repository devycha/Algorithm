/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2146
 * 
 * * 시간제한: 2초
 * * 메모리제한: 192MB
 * 
 * * 입력
 * 첫 줄에는 지도의 크기 N(100이하의 자연수)가 주어진다. 그 다음 N줄에는 N개의 숫자가 빈칸을 사이에 두고 주어지며, 0은 바다, 1은 육지를 나타낸다. 항상 두 개 이상의 섬이 있는 데이터만 입력으로 주어진다.
 * 
    10
    1 1 1 0 0 0 0 1 1 1
    1 1 1 1 0 0 0 0 1 1
    1 0 1 1 0 0 0 0 1 1
    0 0 1 1 1 0 0 0 0 1
    0 0 0 1 0 0 0 0 0 1
    0 0 0 0 0 0 0 0 0 1
    0 0 0 0 0 0 0 0 0 0
    0 0 0 0 1 1 0 0 0 0
    0 0 0 0 1 1 1 0 0 0
    0 0 0 0 0 0 0 0 0 0
 * 
 * * 출력
 * 첫째 줄에 가장 짧은 다리의 길이를 출력한다.
 * 
    3
 * 
 * * 파싱
 * n = 10
 * arr = 입력값의 2번째 줄부터 마지막 줄까지 담은 2차원 배열
 * 
 * * 초기 설정
 * 상하좌우
 * dx, dy
 * 
 * 섬들의 좌표들을 모두 모은 2차원 배열(다른 섬이면 다른 인덱스에 위치)
 * islands = []
 * 
 * 방문리스트
 * visited = [[0 * n] * n]
 * 
 * 다리 길이의 최솟값
 * min = Infinity
 */

// 파싱
const fs = require("fs");
let [n, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
arr = arr.map(a => a.split(" ").map(Number));

// 초기 설정
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let islands = [];
let visited = Array.from({ length: n }, () => new Array(n).fill(0));
let min = Infinity;

// DFS를 이용하여 섬의 종류별로 따로 모든 섬의 인덱스를 저장
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (!visited[i][j] && arr[i][j]) {
      islands.push([[i, j]]);
      visited[i][j] = 1;
      dfs(i, j);
    }
  }
}

// 모든 섬의 모든 거리들을 측정하고, 그 중 가장 작은 값을 도출
for (let a = 0; a < islands.length - 1; a++) {
  for (let b = a + 1; b < islands.length; b++) {
    let is1 = islands[a];
    let is2 = islands[b];

    for (let c = 0; c < is1.length; c++) {
      for (let d = 0; d < is2.length; d++) {
        let distance =
          Math.abs(is1[c][0] - is2[d][0]) + Math.abs(is1[c][1] - is2[d][1]);
        min = Math.min(min, distance);
      }
    }
  }
}

// 출력
console.log(min - 1);

function dfs(x, y) {
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      0 <= nx &&
      nx < n &&
      0 <= ny &&
      ny < n &&
      !visited[nx][ny] &&
      arr[nx][ny]
    ) {
      visited[nx][ny] = 1;
      islands[islands.length - 1].push([nx, ny]);
      dfs(nx, ny);
    }
  }
}
/**
 * 채점 결과
 * 메모리: 13732KB
 * 시간: 360ms
 * 언어: JS
 */
