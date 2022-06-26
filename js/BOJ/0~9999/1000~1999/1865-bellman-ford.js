/**
 * !문제 출처: 백준 온라인 져지
 * address: https://www.acmicpc.net/problem/1865
 * 
 * !시간제한: 2초
 * !메모리제한: 128MB
 * 
 * !문제
 * 때는 2020년, 백준이는 월드나라의 한 국민이다. 월드나라에는 N개의 지점이 있고 N개의 지점 사이에는 M개의 도로와 W개의 웜홀이 있다. (단 도로는 방향이 없으며 웜홀은 방향이 있다.) 웜홀은 시작 위치에서 도착 위치로 가는 하나의 경로인데, 특이하게도 도착을 하게 되면 시작을 하였을 때보다 시간이 뒤로 가게 된다. 웜홀 내에서는 시계가 거꾸로 간다고 생각하여도 좋다.
 * 시간 여행을 매우 좋아하는 백준이는 한 가지 궁금증에 빠졌다. 한 지점에서 출발을 하여서 시간여행을 하기 시작하여 다시 출발을 하였던 위치로 돌아왔을 때, 출발을 하였을 때보다 시간이 되돌아가 있는 경우가 있는지 없는지 궁금해졌다. 여러분은 백준이를 도와 이런 일이 가능한지 불가능한지 구하는 프로그램을 작성하여라.
 * 
 * !입력 & 파싱
 * 첫 번째 줄에는 테스트케이스의 개수 TC(1 ≤ TC ≤ 5)가 주어진다. 그리고 두 번째 줄부터 TC개의 테스트케이스가 차례로 주어지는데 각 테스트케이스의 첫 번째 줄에는 지점의 수 N(1 ≤ N ≤ 500), 도로의 개수 M(1 ≤ M ≤ 2500), 웜홀의 개수 W(1 ≤ W ≤ 200)이 주어진다. 그리고 두 번째 줄부터 M+1번째 줄에 도로의 정보가 주어지는데 각 도로의 정보는 S, E, T 세 정수로 주어진다. S와 E는 연결된 지점의 번호, T는 이 도로를 통해 이동하는데 걸리는 시간을 의미한다. 그리고 M+2번째 줄부터 M+W+1번째 줄까지 웜홀의 정보가 S, E, T 세 정수로 주어지는데 S는 시작 지점, E는 도착 지점, T는 줄어드는 시간을 의미한다. T는 10,000보다 작거나 같은 자연수 또는 0이다.
 * 두 지점을 연결하는 도로가 한 개보다 많을 수도 있다. 지점의 번호는 1부터 N까지 자연수로 중복 없이 매겨져 있다.
 * 
    2      -> n
    3 3 1  -> arr[0]
    1 2 2
    1 3 4
    2 3 1
    3 1 3
    3 2 1
    1 2 3
    2 3 4
    3 1 8  -> arr[arr.length-1]
 * 
 * !출력
 * TC개의 줄에 걸쳐서 만약에 시간이 줄어들면서 출발 위치로 돌아오는 것이 가능하면 YES, 불가능하면 NO를 출력한다.
 * 
    NO
    YES
 * 
 * !초기 설정
 * result: 결과출력용 배열
 * cnt: 입력값 순차적 대입
 */
const fs = require("fs");
let [t, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
arr = arr.map((a) => a.split(" ").map(Number));

let result = [];
let cnt = 0;
let n, m, w, adj;

// @ 모든 케이스에 대하여
while (cnt < arr.length) {
  // @ 인접 현재노드: [[다음 노드, 가중치], ...] 리스트 만들기
  [n, m, w] = arr[cnt++];
  let r = m + w;
  adj = Array.from({ length: n + 1 }, () => new Array());
  for (let i = 0; i < r; i++) {
    const [s, e, t] = arr[cnt++];
    if (i >= m) {
      adj[s].push([e, -t]);
    } else {
      adj[s].push([e, t]);
      adj[e].push([s, t]);
    }
  }
  // @ 벨만 포드 수행시 싸이클이 발생하면 "YES" 아니면 "NO"
  result.push(bellmanFord() ? "YES" : "NO");
}
// @ 정답 출력
console.log(result.join("\n"));

// @ 벨만 포드
function bellmanFord() {
  let distance = new Array(n + 1).fill(5000000);
  let cycle = false;
  distance[1] = 0;

  // @ 정점의 갯수만큼
  for (let i = 1; i <= n; i++) {
    // @ 모든 간선을 검사하며
    for (let cur = 1; cur <= n; cur++) {
      if (adj[cur]) {
        adj[cur].forEach(([next, cost]) => {
          // @ 거리가 줄어들면 업데이트
          if (distance[next] > distance[cur] + cost) {
            distance[next] = distance[cur] + cost;
            // @ N번째 반복에서도 값 변경이 일어나면 음수싸이클이 일어난 것
            if (i == n) cycle = true;
          }
        });
      }
    }
  }
  return cycle;
}
/**
 * ?채점 결과
 * 메모리: 21804KB
 * 시간: 664ms
 * 언어: JS
 */
