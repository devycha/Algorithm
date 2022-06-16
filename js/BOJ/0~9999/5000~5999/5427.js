/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/5427
 * 
 * 시간제한: 1초
 * 메모리제한: 256MB
 * 
 * 입력 & 파싱
 * 첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스는 최대 100개이다.
 * 각 테스트 케이스의 첫째 줄에는 빌딩 지도의 너비와 높이 w와 h가 주어진다. (1 ≤ w,h ≤ 1000)
 * 다음 h개 줄에는 w개의 문자, 빌딩의 지도가 주어진다.
 * '.': 빈 공
 * '#': 
 * '@': 상근이의 시작 위
 * '*': 
 * 각 지도에 @의 개수는 하나이다.
 * 
    5       -> t
    4 3     -> arr[0]
    ####
    #*@.
    ####
    7 6
    ###.###
    #*#.#*#
    #.....#
    #.....#
    #..@..#
    #######
    7 4
    ###.###
    #....*#
    #@....#
    .######
    5 5
    .....
    .***.
    .*@*.
    .***.
    .....
    3 3
    ###
    #@#
    ###     -> arr[arr.length-1]
 * 
 * 출력
 * 각 테스트 케이스마다 빌딩을 탈출하는데 가장 빠른 시간을 출력한다. 빌딩을 탈출할 수 없는 경우에는 "IMPOSSIBLE"을 출력한다.
 * 
 * 초기 설정
 * dx, dy: 상하좌우
 * table: 각 테스트케이스마다 빌딩의 정보
 * m: 열번호, n: 행번호
 * visited: 방문리스트
 */
const fs = require("fs");
let [t, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
arr = arr.map(a => a.trim());
let cnt = 0;
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let result = [];

while (cnt < arr.length) {
  let [m, n] = arr[cnt++].split(" ").map(Number);
  let table = [];
  for (let i = 0; i < n; i++) {
    table.push(arr[cnt++].split(""));
  }

  let answer = "IMPOSSIBLE";
  let queue = findDog(table);
  let pointer = 0;
  let visited = Array.from({ length: n }, () => new Array(m).fill(0));
  queue.forEach(e => {
    visited[e[0]][e[1]] = 1;
  });
  while (pointer < queue.length) {
    let [x, y, isFire] = queue[pointer++];
    if (!isFire && (x == 0 || x == n - 1 || y == 0 || y == m - 1)) {
      answer = visited[x][y];
      break;
    }

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (0 <= nx && nx < n && 0 <= ny && ny < m) {
        if (isFire && table[nx][ny] == ".") {
          table[nx][ny] = "#";
          queue.push([nx, ny, 1]);
        } else if (!isFire && table[nx][ny] == "." && !visited[nx][ny]) {
          visited[nx][ny] = visited[x][y] + 1;
          queue.push([nx, ny, 0]);
        }
      }
    }
  }
  result.push(answer);
}
console.log(result.join("\n"));

function findDog(table) {
  let fire = [];
  let dog;
  for (let i = 0; i < table.length; i++) {
    for (let j = 0; j < table[0].length; j++) {
      if (table[i][j] == "*") {
        fire.push([i, j, 1]);
      } else if (table[i][j] == "@") {
        dog = [i, j, 0];
      }
    }
  }
  fire.push(dog);
  return fire;
}
/**
 * 채점 결과
 * 메모리: 285040KB
 * 시간: 996ms
 * 언어: JS
 */
