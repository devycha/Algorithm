/**
 * !문제 출처: 백준 온라인 져지
 * address: https://www.acmicpc.net/problem/1043
 * 
 * !시간제한: 2초
 * !메모리제한: 128MB
 * 
 * !문제
 * 지민이는 파티에 가서 이야기 하는 것을 좋아한다. 파티에 갈 때마다, 지민이는 지민이가 가장 좋아하는 이야기를 한다. 지민이는 그 이야기를 말할 때, 있는 그대로 진실로 말하거나 엄청나게 과장해서 말한다. 당연히 과장해서 이야기하는 것이 훨씬 더 재미있기 때문에, 되도록이면 과장해서 이야기하려고 한다. 하지만, 지민이는 거짓말쟁이로 알려지기는 싫어한다. 문제는 몇몇 사람들은 그 이야기의 진실을 안다는 것이다. 따라서 이런 사람들이 파티에 왔을 때는, 지민이는 진실을 이야기할 수 밖에 없다. 당연히, 어떤 사람이 어떤 파티에서는 진실을 듣고, 또다른 파티에서는 과장된 이야기를 들었을 때도 지민이는 거짓말쟁이로 알려지게 된다. 지민이는 이런 일을 모두 피해야 한다.
 * 사람의 수 N이 주어진다. 그리고 그 이야기의 진실을 아는 사람이 주어진다. 그리고 각 파티에 오는 사람들의 번호가 주어진다. 지민이는 모든 파티에 참가해야 한다. 이때, 지민이가 거짓말쟁이로 알려지지 않으면서, 과장된 이야기를 할 수 있는 파티 개수의 최댓값을 구하는 프로그램을 작성하시오.
 * 
 * !입력 & 파싱
 * 첫째 줄에 사람의 수 N과 파티의 수 M이 주어진다.
 * 둘째 줄에는 이야기의 진실을 아는 사람의 수와 번호가 주어진다. 진실을 아는 사람의 수가 먼저 주어지고 그 개수만큼 사람들의 번호가 주어진다. 사람들의 번호는 1부터 N까지의 수로 주어진다.
 * 셋째 줄부터 M개의 줄에는 각 파티마다 오는 사람의 수와 번호가 같은 방식으로 주어진다.
 * N, M은 50 이하의 자연수이고, 진실을 아는 사람의 수는 0 이상 50 이하의 정수, 각 파티마다 오는 사람의 수는 1 이상 50 이하의 정수이다.
 * 
    4 3     -> n m
    0       -> arr[0]
    2 1 2
    1 3
    3 2 3 4 -> arr[m-1]
 * 
 * !출력
 * 첫째 줄에 문제의 정답을 출력한다.
 * 
 * !초기 설정
 * knowLies: 진실을 알고있는지에 대한 배열(idx: 사람번호, value: 1(진실을 알고있음), 0(진실을 모름))
 * parent: 같은 집합의 사람들(union-find)
 * obj: 연결리스트
 * visited: 방문리스트
 * count: 거짓말을 해도 되는 파티의 갯수
 */
// 파싱
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map(a => a.trim().split(" ").map(Number));

// 초기 설정
let knowLies = new Array(n + 1).fill(0);
let parent = Array.from({ length: n + 1 }, (v, i) => i);
let obj = Array.from({ length: n + 1 }, () => new Array());

// 각 파티에서 인접한 사람들을 담은 연결리스트 업데이트
for (let i = 1; i < arr.length; i++) {
  for (let j = 1; j <= arr[i][0]; j++) {
    for (let k = 1; k <= arr[i][0]; k++) {
      if (j == k) continue;
      obj[arr[i][j]].push(arr[i][k]);
    }
  }
}

// UNION_FIND 실행
let visited = new Array(n + 1).fill(0);
for (let i = 1; i <= n; i++) {
  if (!visited[i]) dfs(i);
}

// 진실을 알고있는 사람 초기 설정
for (let i = 1; i < arr[0].length; i++) {
  knowLies[arr[0][i]] = 1;
}

// 진실을 알고있는 사람들에 대해 같은 집합에 속해있는 사람들도 진실을 알고있다고 표시
for (let i = 1; i <= n; i++) {
  if (knowLies[i] == 1) {
    for (let j = 1; j <= n; j++) {
      if (parent[j] == parent[i]) {
        knowLies[j] = 1;
      }
    }
  }
}

// 각 파티에서 진실을 알고있는 사람이 한명이라도 있을 때 break
// 모두 진실을 모른다면 count + 1
let count = 0;
for (let i = 1; i < arr.length; i++) {
  for (let j = 1; j <= arr[i][0]; j++) {
    if (knowLies[arr[i][j]]) break;
    if (j == arr[i][0]) count++;
  }
}

// count 출력
console.log(count);

// UNION_FIND
function findParent(parent, node) {
  if (parent[node] !== node) {
    parent[node] = findParent(parent, parent[node]);
  }
  return parent[node];
}

function union(parent, a, b) {
  a = findParent(parent, a);
  b = findParent(parent, b);

  if (a < b) parent[b] = a;
  else parent[a] = b;
}

// 인접한 사람들 중(같은 파티에 한번이라도 속했던 사람들 중) 방문하지 않은 사람들에 대하여 UNION_FIND 실행
function dfs(node) {
  if (obj[node].length) {
    obj[node].forEach(v => {
      union(parent, node, v);
      if (!visited[v]) {
        visited[v] = 1;
        dfs(v);
      }
    });
  }
}
/**
 * 채점 결과
 * 메모리: 9392KB
 * 시간: 152ms
 * 언어: JS
 */
