// 문제 링크: https://www.acmicpc.net/problem/4963
/**
 * 입력값 예시
    1 1
    0
    2 2
    0 1
    1 0
    3 2
    1 1 1
    1 1 1
    5 4
    1 0 1 0 0
    1 0 0 0 0
    1 0 1 0 1
    1 0 0 1 0
    5 4
    1 1 1 0 1
    1 0 1 0 1
    1 0 1 0 1
    1 0 1 1 1
    5 5
    1 0 1 0 1
    0 0 0 0 0
    1 0 1 0 1
    0 0 0 0 0
    1 0 1 0 1
    0 0
 *
 * 출력값 예시
    0
    1
    1
    3
    1
    9
 *
 * 파싱
    arr = [
      [ 1, 1 ],          [ 0 ],
      [ 2, 2 ],          [ 0, 1 ],
      [ 1, 0 ],          [ 3, 2 ],
      [ 1, 1, 1 ],       [ 1, 1, 1 ],
      [ 5, 4 ],          [ 1, 0, 1, 0, 0 ],
      [ 1, 0, 0, 0, 0 ], [ 1, 0, 1, 0, 1 ],
      [ 1, 0, 0, 1, 0 ], [ 5, 4 ],
      [ 1, 1, 1, 0, 1 ], [ 1, 0, 1, 0, 1 ],
      [ 1, 0, 1, 0, 1 ], [ 1, 0, 1, 1, 1 ],
      [ 5, 5 ],          [ 1, 0, 1, 0, 1 ],
      [ 0, 0, 0, 0, 0 ], [ 1, 0, 1, 0, 1 ],
      [ 0, 0, 0, 0, 0 ], [ 1, 0, 1, 0, 1 ]
    ]
 *
 * {{초기 설정}}
 * 지도 정보를 담은 2차원 배열 초기화(1차원 배열 안에 1차원 배열을 push할 예정)
 * landMap = []
 * 
 * 체크리스트
 * checkList = [](테스트케이스마다 다르게 설정해줘야 하기 때문에 초기화)
 * 
 * 섬의 갯수 카운트 초기화
 * count = 0
 * 
 * 출력 결과 저장
 * result = [];
 * 
 * 가로 세로 대각선
 * dx = [1, 1, 1, -1, -1, -1, 0, 0]
 * dy = [0, 1, -1, 0, 1, -1, 1, -1]
 * 
 * DFS 함수
 * 가로 세로 대각선 총 8가지 방향으로 재귀함수를 이용한 DFS
 */

// 파싱
const fs = require("fs");
let arr = fs
  .readFileSync("input.txt")
  .toString()
  .split("\n")
  .map(a => a.split(" ").map(Number));
arr.pop();

// 초기 설정
let landMap = [];
let checkList = [];
let count = 0;
let result = [];
let dx = [1, 1, 1, -1, -1, -1, 0, 0];
let dy = [0, 1, -1, 0, 1, -1, 1, -1];

// 문제 풀이
/**
 * 여러개의 테스트케이스가 한번에 들어있으므로
 * ex)
 * [5, 4] -> arr[i]
 * [1, 0, 1, 0, 0] -> arr[i+1]
 * [1, 0, 0, 0, 0] -> ...
 * [1, 0, 1, 0, 1] -> ...
 * [1, 0, 0, 1, 0] -> arr[i+4] = arr[i + arr[i][1]]
 * next case -> arr[i + arr[i][1] + 1]
 *
 * 5 4 -> i번째 index라고 하면
 * i+1번째 index부터 i+arr[i][1]번째 index까지
 * landMap에 push한 뒤 로직이 완료되면
 * i를 arr[i][1] + 1만큼 증가시킨다
 */
for (let i = 0; i < arr.length - 1; i += arr[i][1] + 1) {
  landMap = [];
  checkList = [];
  count = 0;
  for (let j = i + 1; j < i + arr[i][1] + 1; j++) {
    landMap.push(arr[j]); // landMap 완성시키기
    checkList.push(new Array(arr[j].length).fill(0)); // 그때마다 checkList도 생성
  }

  /**
   * 체크리스트에 방문하지 않았으며 섬이 있는 index에 대해서
   * count를 증가시키고
   * dfs를 이용하여 인접한 섬들을 모두 방문했다고 표시
   */
  for (let x = 0; x < landMap.length; x++) {
    for (let y = 0; y < landMap[0].length; y++) {
      if (landMap[x][y] === 1 && checkList[x][y] === 0) {
        count++;
        checkList[x][y] = 1;
        dfs(landMap, checkList, [x, y]);
      }
    }
  }

  // 출력결과를 저장
  result.push(count);
}

// 출력
console.log(result.join("\n"));

function dfs(landMap, checkList, current) {
  let x = current[0]; // 현재 x -> 행
  let y = current[1]; // 현재 y -> 열

  for (let i = 0; i < 8; i++) {
    let nx = x + dx[i]; // 다음으로 갈 수 있는 index -> 행
    let ny = y + dy[i]; // 다음으로 갈 수 있는 index -> 열

    if (
      0 <= nx && // 범위 체크
      nx < landMap.length && // 범위 체크
      0 <= y && // 범위 체크
      y < landMap[0].length && // 범위 체크
      landMap[nx][ny] === 1 && // 섬이 있는지 체크
      checkList[nx][ny] === 0 // 이미 방문한 섬인지 체크
    ) {
      checkList[nx][ny] = 1; // 방문했다고 체크
      dfs(landMap, checkList, [nx, ny]); // 해당 섬에 대해서 똑같이 수행
    }
  }
}
/**
 * 채점 결과
 * 메모리: 11716KB
 * 시간: 188ms
 * 언어: JS
 * 참고 링크: https://blog.naver.com/y2kdj9723/222690558619
 */
