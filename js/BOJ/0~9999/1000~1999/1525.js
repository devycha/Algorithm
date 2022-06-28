/**
 * !문제 출처: 백준 온라인 져지
 * address: https://www.acmicpc.net/problem/1525
 * 
 * !시간제한: 1초
 * !메모리제한: 32MB
 * 
 * !문제
 * 3×3 표에 다음과 같이 수가 채워져 있다. 오른쪽 아래 가장 끝 칸은 비어 있는 칸이다.
 * 1 2 3
 * 4 5 6
 * 7 8 0	 
 * 어떤 수와 인접해 있는 네 개의 칸 중에 하나가 비어 있으면, 수를 그 칸으로 이동시킬 수가 있다. 물론 표 바깥으로 나가는 경우는 불가능하다. 우리의 목표는 초기 상태가 주어졌을 때, 최소의 이동으로 위와 같은 정리된 상태를 만드는 것이다. 다음의 예를 보자.
 * 1 0 3
 * 4 2 5
 * 7 8 6

 * 1 2 3
 * 4 0 5
 * 7 8 6

 * 1 2 3
 * 4 0 5	 
 * 7 8 6

 * 1 2 3
 * 4 5 6
 * 7 0 8	 
 
 * 가장 윗 상태에서 세 번의 이동을 통해 정리된 상태를 만들 수 있다. 이와 같이 최소 이동 횟수를 구하는 프로그램을 작성하시오.

 * !입력 & 파싱
 * 세 줄에 걸쳐서 표에 채워져 있는 아홉 개의 수가 주어진다. 한 줄에 세 개의 수가 주어지며, 빈 칸은 0으로 나타낸다.
 * 
    1 0 3
    4 2 5
    7 8 6
 * 
 * !출력
 * 첫째 줄에 최소의 이동 횟수를 출력한다. 이동이 불가능한 경우 -1을 출력한다.
 * 
    3
 * 
 * !초기 설정
 * dx, dy: 상하좌우
 * visited: 방문리스트
 * min: 정리된 상태를 만들 수 있는 최소 이동 횟수
 * answer: 정리된 상태를 나타낸 문자열
 */
// @ 파싱
const fs = require("fs");
let arr = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n")
  .map((a) => a.split(" ").map(Number));

// @ 초기 설정
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let visited = {};
let min = -1;
let answer = "123456780";

// @ 빈칸(0)인 곳을 찾아서 BFS 수행
for (let i = 0; i < 3; i++) {
  for (let j = 0; j < 3; j++) {
    if (arr[i][j] == 0) {
      bfs(i, j);
      break;
    }
  }
}

// @ 정답 출력
console.log(min);

// @ BFS
function bfs(i, j) {
  // * 초기 위치 (2차원 배열)를 문자열로 변환
  let start = arrToString(arr);
  // * 해당 위치(문자열)을 방문 표시
  visited[start] = 1;
  // * 스택에 넣고
  let queue = [start];
  // * 포인터(스택으로 BFS 구현)
  let pt = 0;
  // * BFS 시작
  while (pt < queue.length) {
    // * 현재 위치(문자열)
    let cur = queue[pt++];

    // * 현재 위치(문자열)이 정리된 상태("123456780")과 같을 때
    if (cur == answer) {
      // * 방문리스트의 값에 접근하여 횟수 리턴(시작부터 1로 시작하기 때문에 1을 뺴줌)
      min = visited[cur] - 1;
      return;
    }

    // * 현재 위치(문자열)에서 0의 index를 찾는다
    let zero = cur.indexOf("0");
    // * 4 -> (1, 1), 5 -> (1, 2)
    // * index = (x: 3으로나눈 몫, y: 3으로 나눈 나머지)
    let x = Math.floor(zero / 3);
    let y = zero % 3;

    // * x와 y를 가지고 상하좌우의 nx, ny를 구한다
    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      // * nx와 ny가 범위를 벗어나지 않는다면
      if (0 <= nx && nx < 3 && 0 <= ny && ny < 3) {
        // * (x, y) -> 3*x + y
        // * (nx, ny) -> 3*nx + ny => np
        let np = 3 * nx + ny;
        // * (x, y)와 (nx, ny)에 해당하는 문자열 위치의 값을 바꿔준다.
        let next = swap(cur, 3 * x + y, np);
        // * 바꿔준 뒤의 문자열이 방문한 적이 없다면
        if (!visited[next]) {
          // * 이전 위치의 방문리스트의 값 + 1을 현재위치의 방문리스트 값으로 저장
          visited[next] = visited[cur] + 1;
          // * 큐(스택)에 삽입
          queue.push(next);
        }
      }
    }
  }
}

// @ 2차원 배열을 하나의 문자열로 이어붙이는 함수
function arrToString(arr) {
  return arr.map((a) => a.join("")).join("");
}

// @ 문자열의 a위치와 b위치의 값을 서로 바꿔주는 함수
function swap(str, a, b) {
  let newStr = str.split("");
  [newStr[a], newStr[b]] = [newStr[b], newStr[a]];
  return newStr.join("");
}
/**
 * ?채점 결과
 * 메모리: 53676KB
 * 시간: 760ms
 * 언어: JS
 */
