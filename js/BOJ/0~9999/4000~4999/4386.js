/**
 * !문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/4386
 * 
 * !시간제한: 1초
 * !메모리제한: 128MB
 * 
 * !문제
 * 도현이는 우주의 신이다. 이제 도현이는 아무렇게나 널브러져 있는 n개의 별들을 이어서 별자리를 하나 만들 것이다. 별자리의 조건은 다음과 같다.
 * 별자리를 이루는 선은 서로 다른 두 별을 일직선으로 이은 형태이다.
 * 모든 별들은 별자리 위의 선을 통해 서로 직/간접적으로 이어져 있어야 한다.
 * 별들이 2차원 평면 위에 놓여 있다. 선을 하나 이을 때마다 두 별 사이의 거리만큼의 비용이 든다고 할 때, 별자리를 만드는 최소 비용을 구하시오.
 * 
 * !입력 & 파싱
 * 첫째 줄에 별의 개수 n이 주어진다. (1 ≤ n ≤ 100)
 * 둘째 줄부터 n개의 줄에 걸쳐 각 별의 x, y좌표가 실수 형태로 주어지며, 최대 소수점 둘째자리까지 주어진다. 좌표는 1000을 넘지 않는 양의 실수이다.
 * 
    3       -> n
    1.0 1.0 -> arr[0]
    2.0 2.0
    2.0 4.0 -> arr[n-1]
 * 
 * !출력
 * 첫째 줄에 정답을 출력한다. 절대/상대 오차는 10-2까지 허용한다.
 * 
    3.41
 * 
 * !초기 설정
 * distance: 각 정점간의 거리들이 들어있는 배열([정점1, 정점2, 거리])
 */

// 파싱
const fs = require("fs");
let [n, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
arr = arr.map((a) => a.trim().split(" ").map(Number));

// 초기 설정
let distance = [];
for (let i = 0; i < arr.length - 1; i++) {
  for (let j = i + 1; j < arr.length; j++) {
    let dist = Math.sqrt(
      (arr[i][0] - arr[j][0]) ** 2 + (arr[i][1] - arr[j][1]) ** 2
    );

    distance.push([i, j, dist]);
  }
}

distance.sort((a, b) => a[2] - b[2]);
kruskal();

// 크루스칼 알고리즘
function kruskal() {
  let count = 0;
  let parent = Array.from({ length: n }, (_, i) => i);
  let dist = 0;

  for (let i = 0; i < distance.length; i++) {
    const [a, b, d] = distance[i];
    if (findParent(parent, a) !== findParent(parent, b)) {
      union(parent, a, b);
      dist += d;
      count++;
    }

    if (count == n - 1) break;
  }
  console.log(dist);

  // UNION_FIND
  function union(parent, a, b) {
    a = findParent(parent, a);
    b = findParent(parent, b);

    if (a < b) parent[b] = a;
    else parent[a] = b;
  }

  function findParent(parent, node) {
    if (parent[node] !== node) {
      parent[node] = findParent(parent, parent[node]);
    }
    return parent[node];
  }
}
