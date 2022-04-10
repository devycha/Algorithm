// 문제 링크: https://www.acmicpc.net/problem/11725
/**
 * 문제 설명
 * 루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.
 * 
 * 입력값 예시
    7
    1 6
    6 3
    3 5
    4 1
    2 4
    4 7
 * 
 * 출력
 * 첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.
 * 
 * 출력값 예시
    4
    6
    1
    3
    1
    4
 * 
 * 파싱
 * N = 7
 * tree = [ [ 1, 6 ], [ 6, 3 ], [ 3, 5 ], [ 4, 1 ], [ 2, 4 ], [ 4, 7 ] ]
 * 
 * {{초기 설정}}
 * 인접 리스트
 * obj = {}
 * 
 * 부모 노드 체크리스트
 * checkList = [0 * N+1]
 * 
 * DFS용 stack
 * stack = [1]
 */
const fs = require("fs");
let [N, ...tree] = fs.readFileSync("input.txt").toString().trim().split("\n");
tree = tree.map((a) => a.split(" ").map(Number));

let obj = {};
let checkList = new Array(+N + 1).fill(0);
let stack = [1];
checkList[1] = 1;

tree.forEach((t) => {
  if (!obj[t[0]]) obj[t[0]] = [];
  if (!obj[t[1]]) obj[t[1]] = [];
  obj[t[0]].push(t[1]);
  obj[t[1]].push(t[0]);
});

while (stack.length) {
  let parent = stack.pop();
  if (obj[parent]) {
    obj[parent].forEach((sib) => {
      if (!checkList[sib]) {
        checkList[sib] = parent;
        stack.push(sib);
      }
    });
  }
}

console.log(checkList.slice(2).join("\n"));
/**
 * 채점 결과
 * 메모리: 89716KB
 * 시간: 584ms
 * 언어: JS
 */
