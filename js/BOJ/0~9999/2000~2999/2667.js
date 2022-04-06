// 문제 링크: https://www.acmicpc.net/problem/2667
/**
 * 입력값 예시
 * 7
 * 0110100
 * 0110101
 * 1110101
 * 0000111
 * 0100000
 * 0111110
 * 0111000
 *
 * 출력값 예시
 * 3
 * 7
 * 8
 * 9
 *
 * 파싱
 * N = 7
 * arr = [
 * [0, 1, 1, 0, 1, 0, 0],
 * [0, 1, 1, 0, 1, 0, 1],
 * [1, 1, 1, 0, 1, 0, 1],
 * [0, 0, 0, 0, 1, 1, 1],
 * [0, 1, 0, 0, 0, 0, 0],
 * [0, 1, 1, 1, 1, 1, 0],
 * [0, 1, 1, 1, 0, 0, 0]
 * ]
 *
 * 초기 설정 (단지번호 체크)
 * checkList = [
 * [0, 0, 0, 0, 0, 0, 0],
 * [0, 0, 0, 0, 0, 0, 0],
 * [0, 0, 0, 0, 0, 0, 0],
 * [0, 0, 0, 0, 0, 0, 0],
 * [0, 0, 0, 0, 0, 0, 0],
 * [0, 0, 0, 0, 0, 0, 0],
 * [0, 0, 0, 0, 0, 0, 0]
 * ]
 *
 * 위 아래 왼쪽 오른쪽
 * dx = [1, -1, 0, 0]
 * dy = [0, 0, 1, -1]
 *
 * 단지번호
 * num = 1
 *
 * 단지수
 * count = [0]
 *
 * 출력결과
 * result = []
 */
const fs = require("fs");
let [N, ...arr] = fs.readFileSync("input.txt").toString().split("\n");
arr = arr.map((a) => a.trim().split("").map(Number));
let checkList = [];
for (let i = 0; i < +N; i++) {
  checkList.push(new Array(+N).fill(0));
}
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let num = 1;
let count = [0];
let result = [];

/**
 * DFS 재귀함수
 */
function danji(current, num) {
  let x = current[0];
  let y = current[1];

  for (let i = 0; i < 4; i++) {
    // 위 아래 오른쪽 왼쪽
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      0 <= nx && // 범위 체크
      nx < +N && // 범위 체크
      0 <= ny && // 범위 체크
      ny < +N && // 범위 체크
      arr[nx][ny] && // 집이 있는 경우
      !checkList[nx][ny] // 단지번호가 없는 경우
    ) {
      checkList[nx][ny] = num; // 단지 번호를 붙여줌
      count[num] += 1; // 해당 단지 번호의 집수 + 1
      danji([nx, ny], num); // 현재 집에 대해서 똑같이 실행
    }
  }
}

/**
 * 단지번호가 없는데 집이 있는 경우
 * 단지를 새롭게 추가하기 때문에 count(단지수)에 1을 새롭게 push
 * 단지번호에 num을 입력하고 재귀함수 danji를 호출
 * 해당 단지에 인접한 모든 집들을 같은 단지번호로 설정
 * 모두 완료하면 단지번호 1 증가
 */
for (let i = 0; i < +N; i++) {
  for (let j = 0; j < +N; j++) {
    if (!checkList[i][j] && arr[i][j]) {
      checkList[i][j] = num;
      count.push(1);
      danji([i, j], num);
      num++;
    }
  }
}

/**
 * 출력을 위한 설정
 * 단지번호에 따른 단지수들을 result에 넣기
 * 단지수가 없는 경우 === count의 길이가 1인 경우
 * "0"을 출력
 * 단지수가 있는 경우
 * count의 1번 index부터 끝까지 오름차순 정렬후 줄바꿈문자를 이용해 join
 */
console.log(
  count.length == 1
    ? "0"
    : count.length -
        1 +
        "\n" +
        count
          .slice(1)
          .sort((a, b) => a - b)
          .join("\n")
);
/**
 * 채점 결과
 * 메모리: 9468KB
 * 시간: 120ms
 * 언어: JS
 * 참고 링크: https://blog.naver.com/y2kdj9723/222686777795
 */
