// 문제 링크: https://www.acmicpc.net/problem/7562
/**
 * 문제 설명
 * 체스판 위에 한 나이트가 놓여져 있다. 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다. 나이트가 이동하려고 하는 칸이 주어진다. 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?
 * 
 * 입력
 * 입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.
 * 각 테스트 케이스는 세 줄로 이루어져 있다. 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다. 체스판의 크기는 l × l이다. 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다. 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
 * 
 * 입력값 예시
    3
    8
    0 0
    7 0
    100
    0 0
    30 50
    10
    1 1
    1 1
 * 
 * 출력
 * 각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.
 * 
 * 출력값 예시
    5
    28
    0
 * 
 * 파싱
 * T = 3
 * arr = [
    [ 8 ], [ 0, 0 ], [ 7, 0 ], 
    [ 100 ], [ 0, 0 ], [ 30, 50 ],
    [ 10 ], [ 1, 1 ], [ 1, 1 ]
  ]
 * 
 * {{초기 설정}}
 * 나이트가 갈 수 있는 방향 총 8가지
 * dx = [-2, -2, -1, -1, 1, 1, 2, 2]
 * dy = [-1, 1, -2, 2, -2, 2, -1, 1]
 * 
 * 각 테스트케이스마다 최소 몇번만에 이동할 수 있는지 결과를 담은 배열
 * result = [];
 * 
 * {{각 테스트 케이스마다}}
 * N: 체스판의 길이
 * start: 시작위치 [X, Y]
 * end: 도착 위치 [X, Y]
 * stack = [start]: BFS용 스택
 * j = 0 : BFS용 포인터
 * checkList = [[0 * N] * N]
 */
const fs = require("fs");
let [T, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
arr = arr.map((a) => a.trim().split(" ").map(Number));

let dx = [-2, -2, -1, -1, 1, 1, 2, 2];
let dy = [-1, 1, -2, 2, -2, 2, -1, 1];
let result = [];
for (let i = 0; i < arr.length; i += 3) {
  // {{각 테스트케이스 마다}}
  let N = arr[i][0];
  let start = arr[i + 1];
  let end = arr[i + 2];

  /**
   * BFS 초기 셋팅
   * 스택과 포인터
   */
  let stack = [start];
  let checkList = [];
  let j = 0; // pointer for BFS
  for (let k = 0; k < N; k++) {
    checkList.push(new Array(N).fill(0));
  }
  checkList[start[0]][start[1]] = 1;

  while (j < stack.length) {
    let current = stack[j];
    let x = current[0];
    let y = current[1];
    // BFS이므로 도착하자마자 그 값이 최솟값이기 때문에 BFS 종료
    if (x === end[0] && y === end[1]) {
      result.push(checkList[x][y] - 1);
      break;
    }

    for (let w = 0; w < 8; w++) {
      let nx = x + dx[w];
      let ny = y + dy[w];
      // 범위 체크 && 방문했는지 체크
      if (0 <= nx && nx < N && 0 <= ny && ny < N && !checkList[nx][ny]) {
        checkList[nx][ny] = checkList[x][y] + 1;
        stack.push([nx, ny]);
      }
    }
    j++;
  }
}
console.log(result.join("\n"));
/**
 * 채점 결과
 * 메모리: 122032KB
 * 시간: 516ms
 * 언어: JS
 */
