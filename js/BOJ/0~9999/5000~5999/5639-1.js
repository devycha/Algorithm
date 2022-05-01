/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/5639
 * 
 * 시간제한: 1초
 * 메모리제한: 256MB
 * 
 * 문제
 * 이진 검색 트리는 다음과 같은 세 가지 조건을 만족하는 이진 트리이다.
 * 노드의 왼쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 작다.
 * 노드의 오른쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 크다.
 * 왼쪽, 오른쪽 서브트리도 이진 검색 트리이다.
 * 전위 순회 (루트-왼쪽-오른쪽)은 루트를 방문하고, 왼쪽 서브트리, 오른쪽 서브 트리를 순서대로 방문하면서 노드의 키를 출력한다. 후위 순회 (왼쪽-오른쪽-루트)는 왼쪽 서브트리, 오른쪽 서브트리, 루트 노드 순서대로 키를 출력한다. 예를 들어, 위의 이진 검색 트리의 전위 순회 결과는 50 30 24 5 28 45 98 52 60 이고, 후위 순회 결과는 5 28 24 45 30 60 52 98 50 이다.
 * 이진 검색 트리를 전위 순회한 결과가 주어졌을 때, 이 트리를 후위 순회한 결과를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 트리를 전위 순회한 결과가 주어진다. 노드에 들어있는 키의 값은 106보다 작은 양의 정수이다. 모든 값은 한 줄에 하나씩 주어지며, 노드의 수는 10,000개 이하이다. 같은 키를 가지는 노드는 없다.
 * 
    50
    30
    24
    5
    28
    45
    98
    52
    60
 * 
 * 출력
 * 입력으로 주어진 이진 검색 트리를 후위 순회한 결과를 한 줄에 하나씩 출력한다.
 * 
    5
    28
    24
    45
    30
    60
    52
    98
    50
 * 
 * 파싱
 * arr = [50, 30, 24, 5, 28, 45, 98, 52, 60]
 * 
 * {{초기 설정}}
 * 결과
 * result = []
 * 
 * DFS용 스택
 * stack = []
 */
const fs = require("fs");
let arr = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n")
  .map(Number);

const stack = [];
const result = [];

stack.push([0, arr.length - 1]); // 처음부터 끝까지

// 1st
/**
 * [50, 30, 24, 5, 28, 45, 98, 52, 60] => start
 * [30, 24, 5, 28, 45], [98, 52, 60] => 50
 * [30, 24, 5, 28, 45], [52, 60] => 98
 * [30, 24, 5, 28, 45], [60] => 52
 * [30, 24, 5, 28, 45] => 60
 * [24, 5, 28], [45] => 30
 * [24, 5, 28] => 45
 * [5], [28] => 24
 * [5] => 28
 * [] => 5
 */
while (stack.length) {
  let [start, end] = stack.pop();

  if (start == end) {
    result.push(arr[start]); // 길이가 1인 배열일 때 result에 푸쉬
    continue;
  } else if (start > end) {
    continue; // 예외처리
  }

  let p = arr[start]; // 부모노드의 값
  let l = start + 1; // 왼쪽자식의 인덱스
  let r = null; // 오른쪽자식의 인덱스 null로 초기화

  // 부모노드보다 큰 값이 나오는 가장 첫번째 값이 현재 노드의 오른쪽 자식노드들 중 가장 루트노드에 가까운 노드
  // 오른쪽 자식 노드 중 루트를 찾기
  for (let i = l; i <= end; i++) {
    if (p < arr[i]) {
      r = i;
      break;
    }
  }

  // 부모(p), 왼쪽루트(l), 오른쪽루트(r) 총 3덩이로 나눠서 작업
  result.push(p); // 부모 노드의 값 result에 푸쉬
  if (!r) {
    // 오른쪽 자식 노드 중 루트가 없을 경우
    stack.push([l, end]); // 왼쪽 루트의 인덱스부터 끝까지 stack에 푸쉬
  } else {
    // 오른쪽 자식 노드가 있을 경우
    if (r >= end) {
      // 오른쪽 루트의 인덱스가 맨 끝일 경우
      stack.push([l, r - 1]); // 왼쪽 루트의 인덱스부터 오른쪽 루트 인덱스 전까지 stack에 푸쉬
      result.push(arr[r]); // 현재 남은 오른쪽 자식노드가 한개 뿐이므로 result에 그 값을 푸쉬
    } else {
      stack.push([l, r - 1]); // 왼쪽 루트의 인덱스부터 오른쪽 루트의 인덱스 전까지 stack에 푸쉬
      stack.push([r, end]); // 오른쪽 루트의 인덱스부터 끝까지 stack에 푸쉬
    }
  }
}
// 결과 값의 reverse를 출력
console.log(result.reverse().join("\n"));
/**
 * 채점 결과
 * 메모리: 13900KB
 * 시간: 228ms
 * 언어: JS
 */

// // 2nd
// while (stack.length) {
//   const [start, end] = stack.pop();
//   if (start > end) {
//     continue;
//   }

//   let p = start;
//   let l = start + 1;
//   let r;

//   for (let i = l; i <= end; i++) {
//     if (arr[i] < arr[p]) {
//       continue;
//     }
//     r = i;
//     break;
//   }

//   if (r) {
//     stack.push([l, r - 1]);
//     stack.push([r, end]);
//   } else {
//     stack.push([l, end]);
//   }
//   result.push(arr[p]);
// }
// console.log(result.reverse().join("\n"));
/**
 * 채점 결과
 * 메모리: 13892KB
 * 시간: 248ms
 * 언어: JS
 */
