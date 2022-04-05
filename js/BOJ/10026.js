// 문제 링크: https://www.acmicpc.net/problem/10026
/**
 * 입력값 예시
    5
    RRRBB
    GGBBB
    BBBRR
    BBRRR
    RRRRR
 *
 * 출력값 예시
    4 3
 *
 * 파싱
 * N = 5
 * arr = [
    [ 'R', 'R', 'R', 'B', 'B' ],
    [ 'G', 'G', 'B', 'B', 'B' ],
    [ 'B', 'B', 'B', 'R', 'R' ],
    [ 'B', 'B', 'R', 'R', 'R' ],
    [ 'R', 'R', 'R', 'R', 'R' ]
  ]
 *
 * {{초기 설정}}
 * 상하좌우
 * dx = [1, -1, 0, 0], dy = [0, 0, 1, -1]
 * 
 * 색깔영역의 갯수(적록색약 O, 적록색약 X)
 * count1 = 0, count2 = 0
 * 
 * 체크리스트(적록색약 O, 적록색약 X)
 * checkList1 = [], checkList2 = [] -> 2차원 배열(모두 0으로 초기화)
 */
const fs = require("fs");
let [N, ...arr] = fs.readFileSync("input.txt").toString().split("\n");
arr = arr.map(a => a.split(""));

let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];

let count1 = 0;
let count2 = 0;

let checkList1 = [];
let checkList2 = [];

for (let i = 0; i < arr.length; i++) {
  checkList1.push(new Array(arr[0].length).fill(0));
  checkList2.push(new Array(arr[0].length).fill(0));
}

for (let i = 0; i < arr.length; i++) {
  for (let j = 0; j < arr.length; j++) {
    // 적록색약 DFS 실행
    if (checkList1[i][j] === 0) {
      count1++;
      rgDrugDFS(checkList1, [i, j]);
    }

    // 적록색약 아닌 DFS 실행
    if (checkList2[i][j] === 0) {
      count2++;
      rgbDFS(checkList2, [i, j]);
    }
  }
}

console.log(count2, count1);

// 적록색약 DFS
function rgDrugDFS(checkList, current) {
  let x = current[0];
  let y = current[1];

  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    // 범위 체크 및 체크리스트 체크
    if (
      0 <= nx &&
      nx < arr.length &&
      0 <= ny &&
      ny < arr.length &&
      checkList[nx][ny] === 0
    ) {
      /**
       * 현재 인덱스의 색상이 R이나 G이고,
       * 다음 인덱스의 색상이 R이나 G이면,
       * 실행
       */
      if (
        (arr[x][y] === "R" || arr[x][y] === "G") &&
        (arr[nx][ny] === "R" || arr[nx][ny] === "G")
      ) {
        checkList[nx][ny] = 1;
        rgDrugDFS(checkList, [nx, ny], arr[x][y]);
      }

      /**
       * 현재 인덱스의 색상이 B이고,
       * 다음 인덱스의 색상이 B이면,
       * 실행
       */
      if (arr[x][y] === "B" && arr[nx][ny] === "B") {
        checkList[nx][ny] = 1;
        rgDrugDFS(checkList, [nx, ny], arr[x][y]);
      }
    }
  }
}

// 적록색약 아닌 DFS
function rgbDFS(checkList, current) {
  let x = current[0];
  let y = current[1];

  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      0 <= nx &&
      nx < arr.length &&
      0 <= ny &&
      ny < arr.length &&
      checkList[nx][ny] === 0
    ) {
      /**
       * 현재 인덱스의 색상이 R이고,
       * 다음 인덱스의 색상이 R이면,
       * B, G도 마찬가지로 현재 인덱스와 다음인덱스의 색상이 같으면
       * 실행
       */
      if (arr[x][y] === "R" && arr[nx][ny] === "R") {
        checkList[nx][ny] = 1;
        rgbDFS(checkList, [nx, ny], arr[x][y]);
      } else if (arr[x][y] === "G" && arr[nx][ny] === "G") {
        checkList[nx][ny] = 1;
        rgbDFS(checkList, [nx, ny], arr[x][y]);
      } else if (arr[x][y] === "B" && arr[nx][ny] === "B") {
        checkList[nx][ny] = 1;
        rgbDFS(checkList, [nx, ny], arr[x][y]);
      }
    }
  }
}
/**
 * 채점 결과
 * 메모리: 14180KB
 * 시간: 228ms
 * 언어: JS
 * 참고 링크: https://blog.naver.com/y2kdj9723/222692482880
 */
