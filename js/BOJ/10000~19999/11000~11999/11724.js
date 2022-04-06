// 문제 링크: https://www.acmicpc.net/problem/11724
/**
 * 입력값 예시
 * 6 5
 * 1 2
 * 2 5
 * 5 1
 * 3 4
 * 4 6
 *
 * 출력값 예시
 * 2
 *
 * 파싱
 * N = 6
 * M = 5
 * arr = [[1, 2], [2, 5], [5, 1], [3, 4], [4, 6]]
 *
 * 초기 설정
 * obj = {} (인접 노드 객체)
 * checkList = [0, 0, 0, 0, 0, 0, 0] (방문 여부 체크)
 * count = 0 (연결 요소의 개수)
 */
const fs = require("fs");
let [NM, ...arr] = fs.readFileSync("input.txt").toString().split("\n");
const [N, M] = NM.split(" ").map(Number);
arr = arr.map((a) => a.split(" ").map(Number));
let obj = {};
let checkList = new Array(N + 1).fill(0);
let count = 0;

/**
 * 인접 노드 객체를 생성 (방향이 없기 때문에 양쪽 모두 업데이트)
 * ex) [1, 5] 간선일 때
 * {
 *    1: [5],
 *    5: [1],
 * }
 */
for (let i = 0; i < arr.length; i++) {
  if (!obj[arr[i][0]]) obj[arr[i][0]] = [];
  if (!obj[arr[i][1]]) obj[arr[i][1]] = [];
  obj[arr[i][0]].push(arr[i][1]);
  obj[arr[i][1]].push(arr[i][0]);
}

/**
 * DFS 이용
 * 주의할 점: 연결 요소의 갯수인데 연결이 안된 빈 노드도 카운트에 포함해야 함.
 * 정점의 갯수가 3개일 때, 정점은 1, 2, 3이지
 * 1, 100, 1000이지 않음. (1 <= u, v <=N)
 */
for (let i = 1; i < checkList.length; i++) {
  if (checkList[i]) {
    continue; // 이미 방문했다면 연결 요소에 포함되어 있기 때문에 패스
  } else {
    let stack = [i]; // 방문하지 않았다면 그 요소부터 시작해서 연결 요소에 포함할 노드를 스택으로 만듦.
    checkList[i] = 1; // 방문했다고 체크

    while (stack.length) {
      // DFS
      let pop = stack.pop();

      if (obj[pop]) {
        for (let j = 0; j < obj[pop].length; j++) {
          if (!checkList[obj[pop][j]]) {
            // 방문하지 않은 연결 노드에 대해서
            checkList[obj[pop][j]] = 1; // 방문했다고 체크
            stack.push(obj[pop][j]); // 해당 노드에 대해서 반복해서 연결 노드를 구하기 위해 스택에 저장
          }
        }
      }
    }

    count++; // 연결 노드를 다 구해서 더이상 없다면 카운트+1
  }
}

console.log(count);
/**
 * 채점 결과
 * 메모리: 133672KB
 * 시간: 1096ms
 * 언어: JS
 * 문제 풀이 참고 링크: https://blog.naver.com/y2kdj9723/222684414477
 */
