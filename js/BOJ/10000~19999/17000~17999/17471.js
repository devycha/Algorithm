/**
 * !문제 출처: 백준 온라인 져지
 * address: https://www.acmicpc.net/problem/17471
 * 
 * !시간제한: 0.5초
 * !메모리제한: 512MB
 * 
 * !문제
 * 백준시의 시장 최백준은 지난 몇 년간 게리맨더링을 통해서 자신의 당에게 유리하게 선거구를 획정했다. 견제할 권력이 없어진 최백준은 권력을 매우 부당하게 행사했고, 심지어는 시의 이름도 백준시로 변경했다. 이번 선거에서는 최대한 공평하게 선거구를 획정하려고 한다.
 * 백준시는 N개의 구역으로 나누어져 있고, 구역은 1번부터 N번까지 번호가 매겨져 있다. 구역을 두 개의 선거구로 나눠야 하고, 각 구역은 두 선거구 중 하나에 포함되어야 한다. 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다. 구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있을 때, 두 구역은 연결되어 있다고 한다. 중간에 통하는 인접한 구역은 0개 이상이어야 하고, 모두 같은 선거구에 포함된 구역이어야 한다.
 * 아래 그림은 6개의 구역이 있는 것이고, 인접한 구역은 선으로 연결되어 있다.
 * 아래는 백준시를 두 선거구로 나눈 4가지 방법이며, 가능한 방법과 불가능한 방법에 대한 예시이다.
 * 공평하게 선거구를 나누기 위해 두 선거구에 포함된 인구의 차이를 최소로 하려고 한다. 백준시의 정보가 주어졌을 때, 인구 차이의 최솟값을 구해보자.
 * 
 * !입력 & 파싱
 * 첫째 줄에 구역의 개수 N이 주어진다. 둘째 줄에 구역의 인구가 1번 구역부터 N번 구역까지 순서대로 주어진다. 인구는 공백으로 구분되어져 있다.
 * 셋째 줄부터 N개의 줄에 각 구역과 인접한 구역의 정보가 주어진다. 각 정보의 첫 번째 정수는 그 구역과 인접한 구역의 수이고, 이후 인접한 구역의 번호가 주어진다. 모든 값은 정수로 구분되어져 있다.
 * 구역 A가 구역 B와 인접하면 구역 B도 구역 A와 인접하다. 인접한 구역이 없을 수도 있다.
 * 
    6             -> n
    5 2 3 4 1 2   -> people: 각 지역번호에 존재하는 인구 수(people[1] = 1번 지역에 존재하는 인구 수)
    2 2 4         -> arr[0]
    4 1 3 6 5
    2 4 2
    2 1 3
    1 2
    1 2           -> arr[n-1]
 * 
 * !출력
 * 첫째 줄에 백준시를 두 선거구로 나누었을 때, 두 선거구의 인구 차이의 최솟값을 출력한다. 두 선거구로 나눌 수 없는 경우에는 -1을 출력한다.
 * 
    1
 * 
 * !제한
 * 2 ≤ N ≤ 10
 * 1 ≤ 구역의 인구 수 ≤ 100
 * 
 * !초기 설정
 * total: 총 인구 수의 합
 * min: 2개 선거구의 인원수의 차의 최솟값
 * adj: 인접 선거구 리스트
 * groups: 선거구 2개를 나눌 수 있는 모든 조합의 경우의 수(비트마스크)
 */

// @ 파싱
const fs = require("fs");
let [n, people, ...arr] = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n");
n = +n;
people = people.split(" ").map(Number);
people.unshift(0);
arr = arr.map((a) => a.split(" ").map(Number));

// @ 초기설정
let total = people.reduce((a, b) => a + b, 0);
let min = Infinity;
let adj = Array.from({ length: n + 1 }, () => new Array());
let groups = getCombination(n);

for (let i = 0; i < arr.length; i++) {
  for (let j = 1; j <= arr[i][j]; j++) {
    adj[i + 1].push(arr[i][j]);
  }
}

// @ 메인로직
for (let i = 0; i < groups.length / 2; i++) {
  vote(groups[i]);
}

// @ 정답출력
console.log(min == Infinity ? -1 : min);

// @ 비트마스크를 이용하여 00...001부터 11...110까지 배열에 담는 함수(1 ~ 2^(n) - 1)
function getCombination(n) {
  let result = [];

  for (let i = 1; i < (1 << n) - 1; i++) {
    result.push(i.toString(2));
  }

  return result;
}

// @ Team1과 Team2 선거구를 DFS를 통해서 인원수의 합을 구한 뒤
// @ 두 선거구의 인원수의 총 합이 total값과 같으면 그 차이값을 업데이트 시키는 함수
function vote(group1) {
  let visited = new Array(n).fill(0); // * 방문리스트
  let team1 = [],
    team2 = [];

  // * 비트마스크 조합의 결과를 통해서 100001이면 team1 = [1, 6], team2 = [2, 3, 4, 5]
  for (let j = 0; j < n; j++) {
    if ((parseInt(group1, 2) & (1 << j)) == 0) {
      team1.push(j + 1);
    } else {
      team2.push(j + 1);
    }
  }

  /**
   * !중요 포인트
   * 두 선거구의 인원수의 합,
   * DFS의 방문리스트를 이용하기 때문에
   * 중간에 Team1의 지역에 의해서 이미 방문된 곳을 거쳐서 가야만 하는 지역은
   * 방문할 수 없게 된다.
   * 따라서, 이러한 경우가 발생하게 되면, 선거구1의 인원수 + 선거구2의 인원수 != 총 인원수가 되기 때문에
   * 반대로 선거구1의 인원수 + 선거구2의 인원수 == 총 인원수가 되는 경우에만
   * 그 차이의 최솟값을 업데이트시켜준다.
   */
  let cost1 = dfs(team1); // 선거구1의 인원수
  let cost2 = dfs(team2); // 선거구2의 인원수(중간에 끊기지 않은)

  // 선거구1의 인원수 + 선거구2의 인원수 == 총 인원수
  if (cost1 + cost2 == total) {
    let gap = Math.abs(cost1 - cost2);
    if (gap < min) {
      min = gap;
    }
  }

  // DFS: 해당팀에 포함되어있으며 방문하지 않은 지역에 대해서 DFS를 수행하고
  // 해당 지역에 인원수를 누적해서 더한 뒤 최종으로 리턴하는 함수
  function dfs(team) {
    let start = team[0];
    visited[start] = 1;
    let stack = [start];
    let cost = 0;

    while (stack.length) {
      let cur = stack.pop();
      cost += people[cur];

      if (adj[cur]) {
        adj[cur].forEach((next) => {
          if (!visited[next] && team.indexOf(next) !== -1) {
            visited[next] = 1;
            stack.push(next);
          }
        });
      }
    }
    return cost;
  }
}
/**
 * ? 채점 결과
 * 메모리: 11536KB
 * 시간: 200ms
 * 언어: JS
 */
