/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1647
 * 
 * * 시간제한: 2초
 * * 메모리제한: 256MB
 * 
 * * 입력
 * 첫째 줄에 집의 개수 N, 길의 개수 M이 주어진다. N은 2이상 100,000이하인 정수이고, M은 1이상 1,000,000이하인 정수이다. 그 다음 줄부터 M줄에 걸쳐 길의 정보가 A B C 세 개의 정수로 주어지는데 A번 집과 B번 집을 연결하는 길의 유지비가 C (1 ≤ C ≤ 1,000)라는 뜻이다.
 * 
    7 12
    1 2 3
    1 3 2
    3 2 1
    2 5 2
    3 4 4
    7 3 6
    5 1 5
    1 6 2
    6 4 1
    6 5 3
    4 5 3
    6 7 4
 * 
 * * 출력
 * 첫째 줄에 없애고 남은 길 유지비의 합의 최솟값을 출력한다.
 * 
    8
 * 
 * * 파싱
 * n = 7, m = 12
 * arr = [
    [ 1, 2, 3 ], [ 1, 3, 2 ],
    [ 3, 2, 1 ], [ 2, 5, 2 ],
    [ 3, 4, 4 ], [ 7, 3, 6 ],
    [ 5, 1, 5 ], [ 1, 6, 2 ],
    [ 6, 4, 1 ], [ 6, 5, 3 ],
    [ 4, 5, 3 ], [ 6, 7, 4 ]
  ]
 * 
 * * 초기 설정
 * 마을을 최종적으로 분할했을 때
 * 총 비용: total = 0
 * 선택한 도로 중 가장 비싼 비용: max = 0
 * 선택한 도로의 갯수 count = 0
 * 
 * union-find기법
 */
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map(a => a.split(" ").map(Number)).sort((a, b) => a[2] - b[2]);

let total = 0;
let max = 0;
let count = 0;
let parent = Array.from({ length: n + 1 }, (v, i) => i);

for (let i = 0; i < arr.length; i++) {
  if (count == n - 1) break;
  // UNION_FIND를 이용하여 싸이클이 발생하지 않는 선에서 비용이 작은것들을 먼저 채택한다.
  if (findParent(parent, arr[i][0]) !== findParent(parent, arr[i][1])) {
    union(parent, arr[i][0], arr[i][1]);
    max = arr[i][2]; // 항상 마지막에 선택한 도로의 비용이 가장 크기 때문에 별도의 조건없이 업데이트(이미 도로정보들을 비용 오름차순으로 정렬했기 때문)
    total += arr[i][2];
    count++;
  }
}

console.log(total - max);

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
/**
 * 채점 결과
 * 메모리: 260264KB
 * 시간: 2440ms
 * 언어: JS
 */
