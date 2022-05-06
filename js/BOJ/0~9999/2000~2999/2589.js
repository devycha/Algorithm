/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2589
 * 
 * 시간제한: 1초
 * 메모리제한: 512MB
 * 
 * 문제
 * 보물섬 지도를 발견한 후크 선장은 보물을 찾아나섰다. 보물섬 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다. 각 칸은 육지(L)나 바다(W)로 표시되어 있다. 이 지도에서 이동은 상하좌우로 이웃한 육지로만 가능하며, 한 칸 이동하는데 한 시간이 걸린다. 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다. 육지를 나타내는 두 곳 사이를 최단 거리로 이동하려면 같은 곳을 두 번 이상 지나가거나, 멀리 돌아가서는 안 된다.\
 * 예를 들어 위와 같이 지도가 주어졌다면 보물은 아래 표시된 두 곳에 묻혀 있게 되고, 이 둘 사이의 최단 거리로 이동하는 시간은 8시간이 된다.\
 * 보물 지도가 주어질 때, 보물이 묻혀 있는 두 곳 간의 최단 거리로 이동하는 시간을 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에는 보물 지도의 세로의 크기와 가로의 크기가 빈칸을 사이에 두고 주어진다. 이어 L과 W로 표시된 보물 지도가 아래의 예와 같이 주어지며, 각 문자 사이에는 빈 칸이 없다. 보물 지도의 가로, 세로의 크기는 각각 50이하이다.
 * 
    5 7
    WLLWWWL
    LLLWLLL
    LWLWLWW
    LWLWLLL
    WLLWLWW
 * 
 * 출력
 * 첫째 줄에 보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동하는 시간을 출력한다.
 * 
    8
 * 
 * 파싱
 * n = 5, m = 7
 * arr = [
  ['W', 'L', 'L', 'W', 'W', 'W', 'L'],
  ['L', 'L', 'L', 'W', 'L', 'L', 'L'],
  ['L', 'W', 'L', 'W', 'L', 'W', 'W'],
  ['L', 'W', 'L', 'W', 'L', 'L', 'L'],
  ['W', 'L', 'L', 'W', 'L', 'W', 'W']
  ]
 * 
 * {{초기 설정}}
 * 상하좌우 dx = [1, -1, 0, 0], dy = [0, 0, 1, -1]
 * 최단거리의 최댓값 max = 0
 * 
 * {{각 케이스마다}}
 * checkList = [[0 * m] * n]
 * queue = [[i, j]] (BFS 큐)
 * k = 0 (BFS 포인터)
 * count = 0 (각 인덱스마다 갈 수 있는 최단거리의 최댓값)
 */

// 파싱
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map((a) => a.trim().split(""));

// 초기 설정
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let max = 0;

// 모든 인덱스에 대하여 'L'(육지)이면 BFS수행
for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (arr[i][j] == "L") {
      let checkList = Array.from({ length: n }, () => new Array(m).fill(0));
      let queue = [[i, j]];
      checkList[i][j] = 1;
      let k = 0;
      let count = 0;
      while (k < queue.length) {
        let [x, y] = queue[k++];

        for (let w = 0; w < 4; w++) {
          let nx = x + dx[w];
          let ny = y + dy[w];
          if (
            0 <= nx &&
            nx < n &&
            0 <= ny &&
            ny < m &&
            !checkList[nx][ny] &&
            arr[nx][ny] == "L"
          ) {
            checkList[nx][ny] = checkList[x][y] + 1;
            queue.push([nx, ny]);
            if (checkList[nx][ny] > count) {
              count = checkList[nx][ny];
            }
          }
        }
      }
      if (count > max) {
        max = count;
      }
    }
  }
}

console.log(max - 1);
/**
 * 채점 결과
 * 메모리: 29540KB
 * 시간: 1004ms
 * 언어: JS
 */
