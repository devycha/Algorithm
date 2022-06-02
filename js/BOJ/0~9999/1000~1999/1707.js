/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1707
 * 
 * * 시간제한: 2초
 * * 메모리제한: 256MB
 * 
 * * 입력
 * 입력은 여러 개의 테스트 케이스로 구성되어 있는데, 첫째 줄에 테스트 케이스의 개수 K가 주어진다. 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V와 간선의 개수 E가 빈 칸을 사이에 두고 순서대로 주어진다. 각 정점에는 1부터 V까지 차례로 번호가 붙어 있다. 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데, 각 줄에 인접한 두 정점의 번호 u, v (u ≠ v)가 빈 칸을 사이에 두고 주어진다. 
 * 
    2
    3 2
    1 3
    2 3
    4 4
    1 2
    2 3
    3 4
    4 2
 * 
 * * 출력
 * K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.
 * 
    YES
    NO
 * 
 * 
 * * 제한
    2 ≤ K ≤ 5
    1 ≤ V ≤ 20,000
    1 ≤ E ≤ 200,000
 * 
 * * 파싱
 * k = 2
 * arr = [[3, 2], [1, 3], [2, 3], [4, 4], [1, 2], [2, 3], [3, 4], [4, 2]]
 * 
 * * 초기 설정(각 케이스마다)
 * 노드의 개수 n
 * 간선의 개수 m
 * 이분그래프인지 여부 answer = "YES"
 * 방문리스트 visited = [0 * n+1]
 * 인접 노드 리스트 객체 obj = {}
 */
const fs = require("fs");
let [k, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
k = +k;
arr = arr.map(a => a.trim().split(" ").map(Number));
let result = [];

for (let i = 0; i < arr.length; i += arr[i][1] + 1) {
  // 각 케이스마다
  let [n, m] = arr[i]; // n = 노드의 개수, m = 간선의 개수
  let answer = "YES"; // 이분그래프인지 여부
  let visited = new Array(n + 1).fill(0); // 방문 리스트
  let obj = Array.from({ length: n + 1 }, () => new Array()); // 인접 노드 리스트 객체
  for (let j = i + 1; j < i + arr[i][1] + 1; j++) {
    // 간선 정보를 이용하여 인접 노드 리스트 객체 업데이트
    let [start, end] = arr[j];
    obj[start].push(end);
    obj[end].push(start);
  }

  for (let k = 1; k <= n; k++) {
    // 1번노드부터 시작해서 마지막 노드까지
    if (answer == "NO") break; // 이미 이분그래프가 아님이 확인되었을 때

    if (visited[k] == 0) {
      // 방문하지 않은 노드일 때
      let queue = [k]; // DFS 스택에 넣는다
      visited[k] = 1; // 방문 표시

      while (queue.length) {
        // DFS 수행
        if (answer == "NO") break; // 이분 그래프가 아님이 확인되었을 때 즉시 종료
        let current = queue.pop();

        if (obj[current]) {
          obj[current].forEach(next => {
            let nextValue = visited[current] % 2 ? 2 : 1; // 현재 노드의 그룹이 1이라면 다음 노드의 그룹은 2, 반대로 2이면 1
            if (!visited[next]) {
              // 방문하지 않은 노드일 때
              visited[next] = nextValue; // 현재 노드의 그룹과 반대되는 그룹을 방문리스트에 표시
              queue.push(next); // DFS스택에 추가
            } else {
              // 이미 그룹이 정해진 노드일 때
              if (visited[next] !== nextValue) {
                // (이미 정해진 그룹이) (현재 노드의 그룹과 반대되는 그룹)이 다를 때
                answer = "NO"; // 이분 그래프가 아니게 됨
              }
            }
          });
        }
      }
    }
  }
  result.push(answer);
}

console.log(result.join("\n"));
/**
 * 채점 결과
 * 메모리: 264568KB
 * 시간: 1876ms
 * 언어: JS
 */
