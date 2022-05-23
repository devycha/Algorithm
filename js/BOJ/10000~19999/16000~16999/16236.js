/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/16236
 *
 * 입력
 * 첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.
 * 둘째 줄부터 N개의 줄에 공간의 상태가 주어진다. 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고, 아래와 같은 의미를 가진다.
 *    0: 빈 칸
 *    1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
 *    9: 아기 상어의 위치
 * 아기 상어는 공간에 한 마리 있다.
 *
    4
    4 3 2 1
    0 0 0 0
    0 0 9 0
    1 2 3 4
 *
 * 출력
 * 첫째 줄에 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력한다.
 *
    14
 *
 * 파싱
 * n = 4
 * arr = [[4, 3, 2, 1], [0, 0, 0, 0], [0, 0, 9, 0], [1, 2, 3, 4]]
 * 
 * {{초기 설정}}
 * 상하좌우
 * dx = [-1, 0, 0, 1]
 * dy = [0, -1, 1, 0]
 * 
 * 아기 상어의 위치 sharkLoc = findShark() => 위치 찾은 뒤에 상어의 위치에 있는 값 0으로 변경
 * 
 * BFS
 * queue = [sharkLoc]
 * pointer = 0
 * 
 * 먹이를 먹은 횟수 feeds = 0
 * 아기 상어의 크기 shark = 2
 * 시간 time = 0
 */
// 파싱
const fs = require("fs");
let [n, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
arr = arr.map(a => a.split(" ").map(Number));

// 초기 설정
let dx = [-1, 0, 0, 1];
let dy = [0, -1, 1, 0];
let sharkLoc = findShark();
arr[sharkLoc[0]][sharkLoc[1]] = 0;
let queue = [sharkLoc];
let pointer = 0;
let feeds = 0;
let shark = 2;
let time = 0;

// BFS
while (pointer < queue.length) {
  let [x, y] = queue[pointer++];

  let nextFeed = findFeed(x, y); // 다음으로 갈 먹이 찾기
  if (!nextFeed) {
    // 다음으로 갈 먹이가 없는 경우 종료
    break;
  }

  arr[nextFeed[0]][nextFeed[1]] = 0; // 다음으로 갈 먹이를 먹어서 값을 0으로 변경
  time += nextFeed[2]; // 다음으로 갈 먹이까지의 이동시간만큼 시간 증가
  queue.push(nextFeed); // BFS를 위해 queue에 삽입
  feeds++; // 먹이를 먹은 횟수 + 1
  if (feeds == shark) {
    // 먹이를 먹은 횟수가 상어의 크기와 같을 때
    feeds = 0; // 먹이를 먹은 횟수 0으로 초기화
    shark++; // 상어의 크기 1 증가
  }
}
console.log(time);

// 상어의 위치 찾기
function findShark() {
  for (let i = 0; i < arr.length; i++) {
    for (let j = 0; j < arr[0].length; j++) {
      if (arr[i][j] == 9) {
        return [i, j];
      }
    }
  }
}

// (x, y) 현재 위치에서 다음으로 먹을 먹이 찾기
function findFeed(x, y) {
  // BFS
  let queue = [[x, y, 0]];
  let pointer = 0;
  // 방문 리스트
  let visited = Array.from({ length: n }, () => new Array(n).fill(0));
  visited[x][y] = 1;
  // 가장 가까운 거리의 먹이만 찾기
  let min = null;
  let result = [];

  while (pointer < queue.length) {
    let [cx, cy, d] = queue[pointer++];
    if (min) {
      // 가장 가까운 먹이보다 거리가 긴 경우 패스
      if (min < d) continue;
    }

    for (let i = 0; i < 4; i++) {
      let nx = cx + dx[i];
      let ny = cy + dy[i];

      if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]) {
        if (arr[nx][ny] && arr[nx][ny] < shark) {
          // 먹을 수 있는 먹이일 때ㅐ
          visited[nx][ny] = 1; // 방문 표시
          if (!min) {
            // 가장 가까운 먹이를 찾기 전일 때
            min = d + 1; // 가장 가까운 먹이까지의 거리를 min에 저장
            result.push([nx, ny, d + 1]); // result에 해당 위치와 거리 저장
          } else {
            // 가장 가까운 먹이까지의 거리를 구한 상태일 때
            if (min == d + 1) {
              // 현재 찾은 먹이도 가장 가까운 거리일 경우에
              result.push([nx, ny, d + 1]); // result에 해당 위치와 거리 저장
            }
          }
        } else {
          if (arr[nx][ny] <= shark) {
            // 먹을 순 없고 지나갈 순 있을 때
            visited[nx][ny] = 1; // 방문 표시
            queue.push([nx, ny, d + 1]); // queue에 삽입
          }
        }
      }
    }
  }
  // 가장 가까운 먹이들 중 가장 왼쪽 위의 먹이만 리턴
  return result.sort((a, b) => {
    if (a[0] == b[0]) {
      return a[1] - b[1];
    }
    return a[0] - b[0];
  })[0];
}
/**
 * 채점 결과
 * 메모리: 13104KB
 * 시간: 200ms
 * 언어: JS
 */
