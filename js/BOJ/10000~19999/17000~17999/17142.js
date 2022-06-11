/**
 * * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/17142
 *
 * * 시간제한: 0.25초
 * * 메모리제한: 512MB
 * 
 * 입력
 * 첫째 줄에 연구소의 크기 N(4 ≤ N ≤ 50), 놓을 수 있는 바이러스의 개수 M(1 ≤ M ≤ 10)이 주어진다.
 * 둘째 줄부터 N개의 줄에 연구소의 상태가 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스를 놓을 수 있는 위치이다. 2의 개수는 M보다 크거나 같고, 10보다 작거나 같은 자연수이다.
 * 
    7 3
    2 0 0 0 1 1 0
    0 0 1 0 1 2 0
    0 1 1 0 1 0 0
    0 1 0 0 0 0 0
    0 0 0 2 0 1 1
    0 1 0 0 0 0 0
    2 1 0 0 0 0 2
 * 
 * 출력
 * 연구소의 모든 빈 칸에 바이러스가 있게 되는 최소 시간을 출력한다. 바이러스를 어떻게 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1을 출력한다.
 * 
    4
 * 
 * * 파싱
 * n: 연구소크기
 * m: 놓을 수 있는 바이러스의 갯수
 * arr = 바이러스, 벽, 빈공간 위치 배열
 * 
 * * 초기 설정
 * dx, dy: 상하좌우
 * viruses: 바이러스를 놓을 수 있는 장소들(값이 2인 인덱스)
 * min: 바이러스가 전체로 퍼지기 위한 최소한의 시간(정답출력)
 * f: getCombinations(viruses: int[][], m: int): int[][][]: 조합경우의수 구하는 함수
 */
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map(a => a.trim().split(" ").map(Number));

let viruses = [];
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let min = Infinity;

for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (arr[i][j] == 2) {
      viruses.push([i, j]);
    }
  }
}

getCombinations(viruses, m).forEach(v => {
  let queue = [...v];
  let pointer = 0;
  let visited = Array.from({ length: n }, () => new Array(n).fill(0));

  v.forEach(e => {
    visited[e[0]][e[1]] = 1;
  });

  while (pointer < queue.length) {
    let [x, y] = queue[pointer++];

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (
        0 <= nx &&
        nx < n &&
        0 <= ny &&
        ny < n &&
        !visited[nx][ny] &&
        (arr[nx][ny] == 0 || arr[nx][ny] == 2)
      ) {
        visited[nx][ny] = visited[x][y] + 1;
        queue.push([nx, ny]);
      }
    }
  }
  let max = findMaxTime(visited);
  if (max != -1) {
    min = Math.min(max, min);
  }
});

console.log(min == Infinity ? -1 : min);

function getCombinations(arr, selectNumber) {
  const results = [];
  if (selectNumber === 1) return arr.map(value => [value]); // 1개씩 택할 때, 바로 모든 배열의 원소 return

  arr.forEach((fixed, index, origin) => {
    const rest = origin.slice(index + 1); // 해당하는 fixed를 제외한 나머지 뒤
    const combinations = getCombinations(rest, selectNumber - 1); // 나머지에 대해서 조합을 구한다.
    const attached = combinations.map(combination => [fixed, ...combination]); //  돌아온 조합에 떼 놓은(fixed) 값 붙이기
    results.push(...attached); // 배열 spread syntax 로 모두다 push
  });

  return results; // 결과 담긴 results return
}

function findMaxTime(visited) {
  let complete = true;
  let maxtime = 1;
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (arr[i][j] == 0) {
        if (visited[i][j] > maxtime) {
          maxtime = visited[i][j];
        }

        if (visited[i][j] == 0) {
          complete = false;
        }
      }
    }
  }
  return complete ? maxtime - 1 : -1;
}
