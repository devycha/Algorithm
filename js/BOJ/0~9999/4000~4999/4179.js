/**
 * !문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/4179
 * 
 * !시간제한: 1초
 * !메모리제한: 256MB
 * 
 * !문제
 * 지훈이는 미로에서 일을 한다. 지훈이를 미로에서 탈출하도록 도와주자!
 * 미로에서의 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기전에 탈출할 수 있는지의 여부, 그리고 얼마나 빨리 탈출할 수 있는지를 결정해야한다.
 * 지훈이와 불은 매 분마다 한칸씩 수평또는 수직으로(비스듬하게 이동하지 않는다)  이동한다. 
 * 불은 각 지점에서 네 방향으로 확산된다. 
 * 지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다. 
 * 지훈이와 불은 벽이 있는 공간은 통과하지 못한다.
 * 
 * !입력 & 파싱
 * 입력의 첫째 줄에는 공백으로 구분된 두 정수 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1000 이다. R은 미로 행의 개수, C는 열의 개수이다.
 * 다음 입력으로 R줄동안 각각의 미로 행이 주어진다.
 * 각각의 문자들은 다음을 뜻한다.
 * #: 벽
 * .: 지나갈 수 있는 공간
 * J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
 * F: 불이 난 공간
 * J는 입력에서 하나만 주어진다.
 * 
    4 4   -> n m
    ####  -> arr[0][0] ~ arr[0][m-1]
    #JF#
    #..#
    #..#  -> arr[n-1][0] ~ arr[n-1][m-1]
 * 
 * !출력
 * 지훈이가 불이 도달하기 전에 미로를 탈출 할 수 없는 경우 IMPOSSIBLE 을 출력한다.
 * 지훈이가 미로를 탈출할 수 있는 경우에는 가장 빠른 탈출시간을 출력한다. 
 * 
    3
 * 
 * !초기 설정
 * dx, dy: 상하좌우 인덱스
 * visited: 방문리스트(시간+1 저장)
 * queue, pt: BFS 구현용 스택과 포인터
 * answer: 탈출할 수 있는 가장 빠른 시간
 * 
 */
// @ 파싱
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map((a) => a.split(""));

// @ 초기 설정
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];

let visited = Array.from({ length: n }, () => new Array(m).fill(0));
let queue = findLocation();
let pt = 0;

let answer = "IMPOSSIBLE";

// @ BFS 풀이
while (pt < queue.length) {
  let [x, y, isFire] = queue[pt++];

  // ? 지훈이가 가장자리에 도착했을 경우 그 시간을 answer에 반환
  // ? 지훈이가 있는 위치에서 시간이 1부터 시작하기 때문에 가장자리에 도착할 때의 시간은 visited에서 1을 빼주어야 하지만
  // ? 가장자리에서 탈출하는 시간도 1초 추가되기 때문에 따로 처리하지 않았음.
  if (!isFire && (x == 0 || x == n - 1 || y == 0 || y == m - 1)) {
    answer = visited[x][y];
    break;
  }

  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (0 <= nx && nx < n && 0 <= ny && ny < m) {
      // * 불이 지훈의 위치 혹은 지나갈 수 있는 공간을 마주치면
      if (isFire && (arr[nx][ny] == "." || arr[nx][ny] == "J")) {
        arr[nx][ny] = "F"; // 불로 변환
        queue.push([nx, ny, 1]); // BFS
      } else if (!isFire && arr[nx][ny] == "." && !visited[nx][ny]) {
        // * 지훈이 방문하지 않은 곳이면서 지나갈 수 있는 공간을 마주치면
        visited[nx][ny] = visited[x][y] + 1; // 방문리스트에 이전에 도착한 시간 + 1을 저장
        queue.push([nx, ny, 0]); // BFS
      }
    }
  }
}

// @ 정답 출력
console.log(answer);

// @ queue의 처음 설정값: [...불의위치, 지훈의 위치]
// ? 시간상으로 다음에 불이 번지는 곳에 지훈이 미리 갈 수 없기 때문에 불의 위치가 더 앞
function findLocation() {
  let fire = [];
  let jihun;

  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      if (arr[i][j] == "F") {
        fire.push([i, j, 1]);
        visited[i][j] = 1;
      }
      if (arr[i][j] == "J") {
        jihun = [i, j, 0];
        visited[i][j] = 1;
      }
    }
  }

  fire.push(jihun);
  return fire;
}
/**
 * ?채점 결과
 * 메모리: 160976KB
 * 시간: 576ms
 * 언어: JS
 */
