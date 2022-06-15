/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/11657
 * 
 * 시간제한: 1초
 * 메모리제한: 256MB
 * 
 * 입력 & 파싱
 * 첫째 줄에 도시의 개수 N (1 ≤ N ≤ 500), 버스 노선의 개수 M (1 ≤ M ≤ 6,000)이 주어진다. 둘째 줄부터 M개의 줄에는 버스 노선의 정보 A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)가 주어진다. 
 * 
    3 4     -> n m
    1 2 4   -> arr[0]
    1 3 3
    2 3 -1
    3 1 -2  -> arr[m-1]
 * 
 * 출력
 * 만약 1번 도시에서 출발해 어떤 도시로 가는 과정에서 시간을 무한히 오래 전으로 되돌릴 수 있다면 첫째 줄에 -1을 출력한다. 그렇지 않다면 N-1개 줄에 걸쳐 각 줄에 1번 도시에서 출발해 2번 도시, 3번 도시, ..., N번 도시로 가는 가장 빠른 시간을 순서대로 출력한다. 만약 해당 도시로 가는 경로가 없다면 대신 -1을 출력한다.
 * 
    4
    3
 * 
 * 초기 설정
 * visited: 최단거리 배열
 * cycle: 음수 싸이클이 발생하는지 여부
 */
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map(a => a.split(" ").map(Number));

let visited = new Array(n + 1).fill(Infinity);
visited[1] = 0;
let cycle = false;

// 벨만-포드 알고리즘
for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    let cur = arr[j][0]; // 출발
    let next = arr[j][1]; // 도착
    let time = arr[j][2]; // 걸리는 시간

    if (visited[cur] + time < visited[next]) {
      visited[next] = visited[cur] + time;
      // 마지막 점검 때도 최단거리가 변경되면 음수 싸이클이 발생한다고 판단
      if (i == n - 1) {
        cycle = true;
      }
    }
  }
}

console.log(cycle ? -1 : print());

function print() {
  return visited
    .slice(2)
    .map(a => (a == Infinity ? -1 : a))
    .join("\n");
}
/**
 * 채점 결과
 * 메모리: 13572KB
 * 시간: 212ms
 * 언어: JS
 */
