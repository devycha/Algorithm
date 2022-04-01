// 문제 링크: https://www.acmicpc.net/problem/1012
/**
 * 입력값 예시
   2
   10 8 17
   0 0
   1 0
   1 1
   4 2
   4 3
   4 5
   2 4
   3 4
   7 4
   8 4
   9 4
   7 5
   8 5
   9 5
   7 6
   8 6
   9 6
   10 10 1
   5 5
 * 출력값 예시
   5
   1
 * 파싱
 * T = 2
 * arr = [
 * [10, 8, 7],
 * [0, 0],
 * [1, 0],
 * [1, 1],
 * [4, 2],
 * [4, 3],
 * [4, 5],
 * [2, 4],
 * [3, 4],
 * [7, 4],
 * [8, 4],
 * [9, 4],
 * [7, 5],
 * [8, 5],
 * [9, 5],
 * [7, 6],
 * [8, 6],
 * [9, 6],
 * [10, 10, 1],
 * [5, 5]
 * ]
 * 
 * 초기 설정
 * 위, 아래, 오른쪽, 왼쪽
 * dx = [1, -1, 0, 0]
 * dy = [0, 0, 1, -1]
 * 
 * 결과값 축적 배열
 * result = []
 */
const fs = require("fs");
let [T, ...arr] = fs.readFileSync("input.txt").toString().split("\n");
arr = arr.map(a => a.split(" ").map(Number));
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let result = [];

for (let i = 0; i < arr.length; i++) {
  // 파싱한 arr에서 케이스별로 나누기 위해 길이가 3일 때만 수행
  if (arr[i].length === 3) {
    /**
     * 배추리스트 bachu
     * 체크리스트 checkList
     * 지렁이개수 count
     */
    let bachu = [];
    let checkList = [];
    let count = 0;

    /**
     * 배추리스트와 체크리스트 초기화
     */
    for (let j = 0; j < arr[i][1]; j++) {
      bachu.push(new Array(arr[i][0]).fill(0));
      checkList.push(new Array(arr[i][0]).fill(0));
    }

    /**
     * 배추리스트에 배추가 있는 곳 표시
     */
    for (let j = i + 1; j < i + arr[i][2] + 1; j++) {
      bachu[arr[j][1]][arr[j][0]] = 1;
    }

    /**
     * 배추리스트에서 배추가 있는데
     * 지렁이가 지나가지 않은 곳만
     * dfs를 이용하여 지렁이 보내기
     * count++
     */
    for (let j = 0; j < bachu.length; j++) {
      for (let k = 0; k < bachu[0].length; k++) {
        if (bachu[j][k] === 1 && checkList[j][k] === 0) {
          count++;
          jirung2(bachu, checkList, [k, j]);
        }
      }
    }
    result.push(count); // 지렁이 갯수 result에 푸쉬
  }
}
console.log(result.join("\n")); // 결과값 출력

/**
 * DFS
 */
function jirung2(bachu, checkList, current) {
  let x = current[1];
  let y = current[0];

  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      0 <= nx &&
      nx < bachu.length &&
      0 <= ny &&
      ny < bachu[0].length &&
      bachu[nx][ny] === 1 &&
      checkList[nx][ny] === 0
    ) {
      checkList[nx][ny] = 1;
      jirung2(bachu, checkList, [ny, nx]);
    }
  }
}
/**
 * 채점 결과
 * 메모리: 13092KB
 * 시간: 196ms
 * 언어: JS
 * 참고 링크: https://blog.naver.com/y2kdj9723/222688877846
 */
