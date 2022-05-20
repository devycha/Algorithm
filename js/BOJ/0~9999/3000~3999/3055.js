/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/3055
 * 
 * 시간제한: 1초
 * 메모리제한: 128MB
 * 
 * 입력
 * 첫째 줄에 50보다 작거나 같은 자연수 R과 C가 주어진다.
 * 다음 R개 줄에는 티떱숲의 지도가 주어지며, 문제에서 설명한 문자만 주어진다. 'D'와 'S'는 하나씩만 주어진다.
 * 
    3 3
    D.*
    ...
    .S.
 * 
 * 출력
 * 첫째 줄에 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간을 출력한다. 만약, 안전하게 비버의 굴로 이동할 수 없다면, "KAKTUS"를 출력한다.
 * 
    3
 * 
 * 파싱
 * r = 3, c = 3
 * arr = [ [ 'D', '.', '*' ], [ '.', '.', '.' ], [ '.', 'S', '.' ] ]
 * 
 * {{초기 설정}}
 * 비버의 굴 위치
 * d = [0, 0]
 * 
 * 고슴도치 위치 (x, y, 고슴도치라서 0)
 * s = [2, 1, 0]
 * 
 * 홍수난 곳 위치들([[x, y, 물이라서 1], ...])
 * water = [[0, 2, 1]]
 * 
 * 상하좌우
 * dx = [1, -1, 0, 0]
 * dy = [0, 0, 1, -1]
 */
const fs = require("fs");
let [rc, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [r, c] = rc.split(" ").map(Number);
arr = arr.map(a => a.trim().split(""));

let d, s;
let water = [];
let checkList = Array.from({ length: r }, () => new Array(c).fill(0));
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];

// d, s, water의 위치 파악
for (let i = 0; i < r; i++) {
  for (let j = 0; j < c; j++) {
    if (arr[i][j] == "D") {
      d = [i, j];
    } else if (arr[i][j] == "*") {
      water.push([i, j, 1]);
      checkList[i][j] = 1;
    } else if (arr[i][j] == "S") {
      s = [i, j, 0];
      checkList[i][j] = 1;
    }
  }
}

// BFS를 위해 고슴도치의 위치를 water 배열 맨 뒤에 push
water.push(s);
let pointer = 0; // BFS용 포인터
// BFS
while (pointer < water.length) {
  let [x, y, z] = water[pointer++];
  // 고슴도치일 때
  if (z == 0) {
    if (x == d[0] && y == d[1]) {
      // 비버의 굴의 위치로 도착했다면
      break; // 종료
    } else {
      for (let i = 0; i < 4; i++) {
        // 4가지 방향에 대해
        let nx = x + dx[i];
        let ny = y + dy[i];

        if (
          // 범위 체크
          0 <= nx &&
          nx < r &&
          0 <= ny &&
          ny < c &&
          !checkList[nx][ny] && // 방문하지 않은 곳
          (arr[nx][ny] == "." || arr[nx][ny] == "D") // 비어있는곳 혹은 비버의 굴
        ) {
          checkList[nx][ny] = checkList[x][y] + 1; // 시간 저장
          water.push([nx, ny, 0]); // BFS를 위해 water 배열에 push
        }
      }
    }
  } else {
    // 물일 때
    for (let i = 0; i < 4; i++) {
      // 4가지 방향에 대해
      let nx = x + dx[i];
      let ny = y + dy[i];

      // 인접한 곳에 빈곳이 있으면
      if (0 <= nx && nx < r && 0 <= ny && ny < c && arr[nx][ny] == ".") {
        arr[nx][ny] = "*"; // 물로 채움
        water.push([nx, ny, 1]); // BFS를 위해 water 배열에 push
      }
    }
  }
}

let answer = checkList[d[0]][d[1]];
console.log(answer > 0 ? answer - 1 : "KAKTUS");
/**
 * 채점 결과
 * 메모리: 10728KB
 * 시간: 128ms
 * 언어: JS
 */
