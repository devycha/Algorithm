/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1926
 * 
 * 문제
 * 어떤 큰 도화지에 그림이 그려져 있을 때, 
 * 그 그림의 개수와, 그 그림 중 넓이가 가장 넓은 것의 넓이를 출력하여라. 
 * 단, 그림이라는 것은 1로 연결된 것을 한 그림이라고 정의하자. 
 * 가로나 세로로 연결된 것은 연결이 된 것이고 대각선으로 연결이 된 것은 떨어진 그림이다. 
 * 그림의 넓이란 그림에 포함된 1의 개수이다.
 * 
 * 입력
 * 첫째 줄에 도화지의 세로 크기 n(1 ≤ n ≤ 500)과 가로 크기 m(1 ≤ m ≤ 500)이 차례로 주어진다. 
 * 두 번째 줄부터 n+1 줄 까지 그림의 정보가 주어진다. 
 * (단 그림의 정보는 0과 1이 공백을 두고 주어지며, 0은 색칠이 안된 부분, 1은 색칠이 된 부분을 의미한다)
 * 
    6 5
    1 1 0 1 1
    0 1 1 0 0
    0 0 0 0 0
    1 0 1 1 1
    0 0 1 1 1
    0 0 1 1 1
 * 
 * 출력
 * 첫째 줄에는 그림의 개수, 둘째 줄에는 그 중 가장 넓은 그림의 넓이를 출력하여라. 
 * 단, 그림이 하나도 없는 경우에는 가장 넓은 그림의 넓이는 0이다.
 * 
    4
    9
 * 
 * 파싱
 * n = 6, m = 6
 * drawing = [
    [ 1, 1, 0, 1, 1 ],
    [ 0, 1, 1, 0, 0 ],
    [ 0, 0, 0, 0, 0 ],
    [ 1, 0, 1, 1, 1 ],
    [ 0, 0, 1, 1, 1 ],
    [ 0, 0, 1, 1, 1 ] 
  ]
 * 
 * {{초기 설정}}
 * 상하좌우
 * dx = [1, -1, 0, 0]
 * dy = [0, 0, 1, -1]
 * 
 * 체크리스트
 * checkList = [[0 * m] * n]
 * 
 * 그림 갯수
 * count = 0
 * 
 * 넓이의 최댓값
 * max = 0
 * 
 * 각각의 그림 넓이들
 * area = []
 */

// 파싱
const fs = require("fs");
let [nm, ...drawing] = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n");
const [n, m] = nm.split(" ").map(Number);
drawing = drawing.map((a) => a.trim().split(" ").map(Number));

// 초기 설정
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let checkList = [];
for (let i = 0; i < n; i++) {
  checkList.push(new Array(m).fill(0));
}
let count = 0;
let max = 0;
let area = [];

// 방문하지 않은 인덱스이면서(checkList[i][j] == 0) 색칠되어있는 부분에 대해(drawing[i][j] == 1)
for (let i = 0; i < drawing.length; i++) {
  for (let j = 0; j < drawing[0].length; j++) {
    if (checkList[i][j] == 0 && drawing[i][j] == 1) {
      count++; // 그림의 갯수 증가
      checkList[i][j] = 1; // 체크리스트 업데이트
      area.push(1); // 새롭게 넓이 추가
      if (max < area[area.length - 1]) {
        max = area[area.length - 1]; // 최댓값 업데이트
      }
      dfs(i, j); // DFS 수행
    }
  }
}

/**
 * DFS
 * 상하좌우에 대하여
 * 범위가 넘어가지 않고
 * 방문하지 않은 노드이면서
 * 색칠되어있는 인덱스에 대해
 *
 * 체크리스트에 체크하고
 * 제일 최근에 추가된 그림의 넓이 + 1
 * 그림의 넓이가 max값을 넘게 되면 업데이트
 * 해당 인덱스에 대하여 계속해서 DFS 수행
 */
function dfs(x, y) {
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      0 <= nx &&
      nx < n &&
      0 <= ny &&
      ny < m &&
      !checkList[nx][ny] &&
      drawing[nx][ny]
    ) {
      checkList[nx][ny] = 1;
      area[area.length - 1]++;
      if (max < area[area.length - 1]) {
        max = area[area.length - 1];
      }
      dfs(nx, ny);
    }
  }
}

console.log(count + "\n" + max);
/**
 * 채점 결과
 * 메모리: 43228KB
 * 시간: 268ms
 * 언어: JS
 */
