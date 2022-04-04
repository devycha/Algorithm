// 문제 링크:
/**
 * 입력값 예시
    5
    6 8 2 6 2
    3 2 3 4 6
    6 7 3 3 2
    7 2 5 3 6
    8 9 5 2 7
 *
 * 출력값 예시
    5
 *
 * 파싱
 * N = 5
 * height = [
    [ 6, 8, 2, 6, 2 ],
    [ 3, 2, 3, 4, 6 ],
    [ 6, 7, 3, 3, 2 ],
    [ 7, 2, 5, 3, 6 ],
    [ 8, 9, 5, 2, 7 ]
  ]
 *
 * {{초기 설정}}
 * 높이 최댓값, 최솟값
 * max = 0, min = Infinity
 * 
 * 상하좌우
 * dx = [1, -1, 0, 0], dy = [0, 0, 1, -1]
 * 
 * 체크리스트 (모든 강수량을 체크할때마다 초기화)
   checkList = [
    [ 0, 0, 0, 0, 0 ],
    [ 0, 0, 0, 0, 0 ],
    [ 0, 0, 0, 0, 0 ],
    [ 0, 0, 0, 0, 0 ],
    [ 0, 0, 0, 0, 0 ]
  ]
 *
 * 안전 영역 개수의 최댓값
 * maxSafe = 0
 * 
 * 안전 영역의 개수
 * count = 0
 */
const fs = require("fs");
let [N, ...height] = fs.readFileSync("input.txt").toString().split("\n");
let max = 0;
let min = Infinity;
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
height = height.map(a =>
  a.split(" ").map(b => {
    max = Math.max(max, +b);
    min = Math.min(min, +b);
    return +b;
  })
);
console.log(height);

let checkList = [];
let maxSafe = 0;
let count = 0;

/**
 * 지역 높이의 최솟값부터 최댓값-1 까지만 구한다
 * (최댓값이 강수량이 되버리면 모두 잠기기 때문에 의미가 없음)
 */
for (let rain = min; rain < max; rain++) {
  // 강수량마다 체크리스트와 안전 영역의 개수를 초기화해준다.
  checkList = [];
  count = 0;
  for (let i = 0; i < height.length; i++) {
    checkList.push(new Array(height[0].length).fill(0));
  }

  /**
   * 현재 강수량보다 높은 위치이면서 체크리스트에 방문하지 않은 인덱스일 때마다
   * count를 1 증가시키고 dfs를 수행함.
   */
  for (let i = 0; i < height.length; i++) {
    for (let j = 0; j < height[0].length; j++) {
      if (height[i][j] > rain && checkList[i][j] === 0) {
        count++;
        dfs(checkList, [i, j], rain);
      }
    }
  }
  maxSafe = Math.max(maxSafe, count);
}

console.log(maxSafe);

function dfs(checkList, current, rain) {
  // 현재 위치
  let x = current[0];
  let y = current[1];

  for (let i = 0; i < 4; i++) {
    // 다음 위치(상하좌우)
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      0 <= nx && // 범위 체크
      nx < height.length && // 범위 체크
      0 <= ny && // 범위 체크
      ny < height[0].length && // 범위 체크
      checkList[nx][ny] === 0 && // 이미 안전영역으로 포함되어있는지 체크
      height[nx][ny] > rain // 물에 잠기지 않았는지 체크
    ) {
      checkList[nx][ny] = 1; // 안전영역으로 포함
      dfs(checkList, [nx, ny], rain); // 해당 영역에 대해서 똑같이 dfs 수행
    }
  }
}
/**
 * 채점 결과
 * 메모리: 21068KB
 * 시간: 336ms
 * 언어: JS
 */
