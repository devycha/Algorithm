/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1068
 *
 * 시간제한: 2초
 * 메모리제한: 128MB
 * 
 * 입력
 * 첫째 줄에 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 만약 부모가 없다면 (루트) -1이 주어진다. 셋째 줄에는 지울 노드의 번호가 주어진다.
 * 
    5
    -1 0 0 1 1
    2
 * 
 * 출력
 * 첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.
 * 
    2
 * 
 * 파싱
 * n = 5
 * arr = [-1, 0, 0, 1, 1]
 * del = 2
 * 
 * {{초기 설정}}
 * 자식 리스트 객체 obj[부모] = [...자식]
 * 삭제 여부 배열 deleted = [0 * n]
 * 리프 노드의 갯수 count = 0
 */
const fs = require("fs");
let [n, arr, del] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
arr = arr.trim().split(" ").map(Number);
del = +del;

let obj = Array.from({ length: n }, () => new Array());
let deleted = new Array(n).fill(0);
let count = 0;

for (let i = 0; i < n; i++) {
  if (arr[i] == -1) continue;
  obj[arr[i]].push(i);
}

deleted[del] = 1;
dfs(del);

for (let i = 0; i < obj.length; i++) {
  if (deleted[i]) continue;

  if (obj[i].length == 0) {
    count++;
  } else {
    let childCount = 0;
    for (let j = 0; j < obj[i].length; j++) {
      if (!deleted[obj[i][j]]) {
        childCount++;
      }
    }
    if (childCount == 0) {
      count++;
    }
  }
}

console.log(count);

function dfs(del) {
  if (obj[del]) {
    obj[del].forEach(newDel => {
      deleted[newDel] = 1;
      dfs(newDel);
    });
  }
}
/**
 * 채점 결과
 * 메모리: 9348KB
 * 시간: 132ms
 * 언어: JS
 */
