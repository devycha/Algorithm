// 문제 링크: https://www.acmicpc.net/problem/14502
/**
 * 입력값 예시
 * 4 6
   0 0 0 0 0 0
   1 0 0 0 0 2
   1 1 1 0 0 2
   0 0 0 0 0 2
 *
 * 출력값 예시
 * 9
 * 
 * 파싱
 * N = 4, M = 6
 * lab = [
    [0, 0, 0, 0, 0, 0],
    [1, 0, 0, 0, 0, 2],
    [1, 1, 1, 0, 0, 2],
    [0, 0, 0, 0, 0, 2],
 * ]
 * 
 * 초기 설정
 *  바이러스가 있는 위치
 *  stack = [];
 *  
 *  빈 공간이 있는 위치
 *  empty = [];
 *  
 *  상하좌우
 *  dx = [1, -1, 0, 0]
 *  dy = [0, 0, 1, -1]   
 *  
 *  안전 영역 크기 최댓값
 *  max = 0
 * 
 * 주의할 점
 * NodeJS에서 할 경우 입력값을 파싱할 때 trim함수를 써서 양쪽에 공간을 한번 처리해주어야 한다.
 */
const fs = require("fs");
let [NM, ...lab] = fs.readFileSync("input.txt").toString().split("\n");
const [N, M] = NM.split(" ").map(Number);
let stack = [];
let empty = [];
let dy = [0, 0, 1, -1];
let dx = [1, -1, 0, 0];
let max = 0;
/**
 * lab을 파싱하면서 바이러스 위치와 빈공간 위치를 각각 배열에 담는다.
 */
for (let i = 0; i < lab.length; i++) {
  lab[i] = lab[i].split(" ").map(Number);
  for (let j = 0; j < lab[0].length; j++) {
    if (lab[i][j] === 2) {
      stack.push([i, j]);
    } else if (lab[i][j] === 0) {
      empty.push([i, j]);
    }
  }
}

let lab_slice = []; // 메모리 절약

/**
 * 벽을 꼭 3개를 두어야 하기 때문에
 * 3중 for문으로 brute force를 사용
 */
for (let a = 0; a < empty.length - 2; a++) {
  for (let b = a + 1; b < empty.length - 1; b++) {
    for (let c = b + 1; c < empty.length; c++) {
      lab_slice = []; // dfs를 이용하기 위해 배열을 그때마다 새로운 reference로 만들어서 사용
      for (let d = 0; d < lab.length; d++) {
        lab_slice.push([...lab[d]]);
      }

      // 벽 3개 세우는 중
      lab_slice[empty[a][0]][empty[a][1]] = 1;
      lab_slice[empty[b][0]][empty[b][1]] = 1;
      lab_slice[empty[c][0]][empty[c][1]] = 1;

      // 바이러스 퍼지는 중
      for (let i = 0; i < stack.length; i++) {
        dfs(lab_slice, stack[i]);
      }

      // 바이러스가 다 퍼지고 나서 안전 영역을 구한 뒤에 최댓값과 비교하여 업데이트
      max = Math.max(virus(lab_slice), max);
    }
  }
}

// 결과 출력
console.log(max);

// 바이러스 퍼지는 함수 기본 DFS(재귀)
function dfs(lab, cur) {
  let x = cur[0];
  let y = cur[1];

  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      0 <= nx &&
      nx < lab.length &&
      0 <= ny &&
      ny < lab[0].length &&
      lab[nx][ny] === 0
    ) {
      lab[nx][ny] = 2;
      dfs(lab, [nx, ny]);
    }
  }
}

// 안전영역 갯수 세는 단순 2중 for문
function virus(lab) {
  let count = 0;
  for (let i = 0; i < lab.length; i++) {
    for (let j = 0; j < lab[0].length; j++) {
      if (lab[i][j] === 0) {
        count++;
      }
    }
  }
  return count;
}
/**
 * 채점 결과
 * 메모리: 13784KB
 * 시간: 708ms
 * 언어: JS
 * 참고 링크: https://blog.naver.com/y2kdj9723/222689732498
 */
