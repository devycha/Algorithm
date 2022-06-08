/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/9466
 * 
 * * 시간제한: 3초
 * * 메모리제한: 256MB
 * 
 * * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫 줄에는 학생의 수가 정수 n (2 ≤ n ≤ 100,000)으로 주어진다. 각 테스트 케이스의 둘째 줄에는 선택된 학생들의 번호가 주어진다. (모든 학생들은 1부터 n까지 번호가 부여된다.)
 * 
    2
    7
    3 1 3 7 3 4 6
    8
    1 2 3 4 5 6 7 8
 * 
 * * 출력
 * 각 테스트 케이스마다 한 줄에 출력하고, 각 줄에는 프로젝트 팀에 속하지 못한 학생들의 수를 나타내면 된다.
 * 
    3
    0
 * 
 * * 파싱
 * t = 2
 * arr = [[7], [3, 1, 3, 7, 3, 4, 6], [8], [1, 2, 3, 4, 5, 6, 7, 8]]
 * 
 * * 초기 설정
 * 학생의 수 n = arr[i][0]
 * 선택된 학생들의 번호 배열 st = arr[i+1]
 * 
 * 방문리스트
 * visited = [0 * n+1]
 * 
 * 팀에 속한 학생 수
 * count = 0
 */
const fs = require("fs");
let [t, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
t = +t;
arr = arr.map(a => a.split(" ").map(Number));

let visited, n, st, count, group;
let result = [];

for (let i = 0; i < arr.length; i += 2) {
  n = arr[i][0];
  st = [0, ...arr[i + 1]];
  visited = new Array(n + 1).fill(0);
  count = 0;

  // 1번학생부터 n번 학생까지 방문 안한 학생의 경우 DFS를 수행
  for (let i = 1; i <= n; i++) {
    if (!visited[i]) {
      visited[i] = 1; // 시작 지점 방문 표시
      dfs(i);
    }
  }

  result.push(n - count);
}

console.log(result.join("\n"));

function dfs(start) {
  let next = st[start]; // 현재 학생이 선택한 학생의 번호

  if (visited[next] == 0) {
    // 선택한 학생이 다른 학생에 의해 아직 선택되지 않은 경우
    visited[next] = 1; // 선택한 학생 방문 표시
    dfs(next); // DFS 재귀 실행
  } else if (visited[next] == 1) {
    // 선택한 학생을 이미 방문한 경우(싸이클이 발생한 것으로 추정)
    if (st[next] == next) {
      // 자기가 자신을 선택한 경우
      visited[next] = 2; // 자기 자신을 팀에 속했다고 표시(2);
      count++; // 팀에 속한 인원 +1
    } else {
      // 싸이클을 찾기 위해 선택한 학생이 또 선택한 학생의 번호를 선택
      // ex) 4 -> 7 -> 6(current) -> 4(next: 여기서 방문함) -> 7(cycle)
      let cycle = st[next];
      visited[next] = 2; // 싸이클의 시작 지점을 팀에 속했다고 표시(2)
      let c = 1; // 싸이클에 속해있는 인원(next)을 1로 잡고 시작

      while (cycle != next) {
        // 싸이클의 시작점으로 돌아올때까지
        if (visited[cycle] == 2) {
          // 싸이클을 도는 도중에 만약 이미 팀에 속한 팀의 학생이 선택됐다면
          c = 0; // 이제까지 카운트했던 인원을 초기화하고 종료
          break;
        } else {
          visited[cycle] = 2; // 팀원에 속했다고 표시(2)
          cycle = st[cycle]; // 싸이클을 그다음 학생으로 지정
          c++; // 싸이클에 속해있는 인원 + 1
        }
      }

      count += c; // 최종적으로 싸이클에 속한 팀원의 수를 count에 추가
    }
  }
}
/**
 * 채점 결과
 * 메모리: 371960KB
 * 시간: 1416ms
 * 언어: JS
 */
