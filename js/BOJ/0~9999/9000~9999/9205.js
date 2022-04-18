/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/9205
 * 
 * 문제
 * 송도에 사는 상근이와 친구들은 송도에서 열리는 펜타포트 락 페스티벌에 가려고 한다. 
 * 올해는 맥주를 마시면서 걸어가기로 했다. 출발은 상근이네 집에서 하고, 맥주 한 박스를 들고 출발한다. 
 * 맥주 한 박스에는 맥주가 20개 들어있다. 목이 마르면 안되기 때문에 50미터에 한 병씩 마시려고 한다. 
 * 즉, 50미터를 가려면 그 직전에 맥주 한 병을 마셔야 한다.
 * 상근이의 집에서 페스티벌이 열리는 곳은 매우 먼 거리이다. 따라서, 
 * 맥주를 더 구매해야 할 수도 있다. 
 * 미리 인터넷으로 조사를 해보니 다행히도 맥주를 파는 편의점이 있다. 
 * 편의점에 들렸을 때, 빈 병은 버리고 새 맥주 병을 살 수 있다. 
 * 하지만, 박스에 들어있는 맥주는 20병을 넘을 수 없다. 
 * 편의점을 나선 직후에도 50미터를 가기 전에 맥주 한 병을 마셔야 한다.
 * 편의점, 상근이네 집, 펜타포트 락 페스티벌의 좌표가 주어진다. 
 * 상근이와 친구들이 행복하게 페스티벌에 도착할 수 있는지 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 t가 주어진다. (t ≤ 50)
 * 각 테스트 케이스의 첫째 줄에는 맥주를 파는 편의점의 개수 n이 주어진다. (0 ≤ n ≤ 100).
 * 다음 n+2개 줄에는 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표가 주어진다. 각 좌표는 두 정수 x와 y로 이루어져 있다. (두 값 모두 미터, -32768 ≤ x, y ≤ 32767)
 * 송도는 직사각형 모양으로 생긴 도시이다. 두 좌표 사이의 거리는 x 좌표의 차이 + y 좌표의 차이 이다. (맨해튼 거리)
 * 
    2
    2
    0 0
    1000 0
    1000 1000
    2000 1000
    2
    0 0
    1000 0
    2000 1000
    2000 2000
 * 
 * 출력
 * 각 테스트 케이스에 대해서 상근이와 친구들이 행복하게 페스티벌에 갈 수 있으면 "happy", 중간에 맥주가 바닥나서 더 이동할 수 없으면 "sad"를 출력한다. 
 * 
    happy
    sad
 * 
 * 파싱
 * T = 2
 * arr = [
        [ 2 ], [ 0, 0 ], [ 1000, 0 ], [ 1000, 1000 ], [ 2000, 1000 ], 
        [ 2 ], [ 0, 0 ], [ 1000, 0 ], [ 2000, 1000 ], [ 2000, 2000 ]
    ]
 * 
 * {{초기 설정}}
 * 결과 출력용 배열 (T개만큼 "sad"로 초기화)
 * answer = ["sad" * T]
 * 
 * DFS 결과를 answer에 저장하기 위한 인덱스
 * count = 0 (한번의 테스트케이스가 끝날때마다 +1)
 * 
 * {{각 케이스마다}}
 * 
 * 현재케이스에서 다음케이스로 넘어갈 때
 * 현재 케이스 포인트 i
 * arr[i][0] => 편의점 개수: i + arr[i][0]
 * 집개수(1) + 페스티벌개수(1): i + arr[i][0] + 2 (n+2개의 좌표가 테스트케이스마다 주어짐)
 * 다음 케이스 포인트 i + arr[i][0] + 2 + 1 => i + arr[i][0] + 3
 * 
 * 현재 케이스의 편의점 개수
 * arr[i][0]
 * 
 * 집 좌표
 * start = arr[i+1]
 * 
 * 페스티벌 좌표
 * end = arr[i + arr[i][0] + 2]
 * 
 * 편의점 배열
 * store = arr.slice(i+2, i + arr[i][0] + 3) => 집 다음 좌표(i+2)부터 페스티벌 좌표직전까지(i+arr[i][0]+3)
 * 
 * 체크리스트(해당 편의점을 방문 했는지)
 * checkList = [0 * arr[i][0]] => 편의점개수(arr[i][0])만큼 0으로 초기화
 */

// 파싱
const fs = require("fs");
let [T, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
arr = arr.map(a => a.split(" ").map(Number));

// 초기 설정
let answer = new Array(+T).fill("sad"); // 결과 출력용 배열
let count = 0; // DFS 결과를 answer에 저장하기 위한 인덱스(한번의 케이스가 끝날때마다 +1)

// 각 케이스마다
for (let i = 0; i < arr.length; i += arr[i][0] + 3) {
  // i = 편의점 개수가 표시되어있는 인덱스 arr[i][0] => 편의점 개수
  let start = arr[i + 1]; // 시작점: i+1
  let end = arr[i + arr[i][0] + 2]; // 종료지점: i + arr[i][0] + 2
  let store = arr.slice(i + 2, i + arr[i][0] + 3); // 편의점 위치 배열
  let checkList = new Array(arr[i][0]).fill(0); // 편의점을 방문했는지 체크하는 체크리스트
  dfs(start[0], start[1], store, 20, end[0], end[1], checkList, count); // DFS수행
  count++; // 다음 케이스로 넘어가기전에 인덱스 증가
}

console.log(answer.join("\n")); // 정답 출력

// 맨해튼 거리 함수
function mDistance(currentX, currentY, nextX, nextY) {
  return Math.abs(nextX - currentX) + Math.abs(nextY - currentY);
}

/**
 * DFS 함수
 * 현재 좌표가 페스티벌 장소의 좌표와 같으면
 * 현재 answer[count]의 값을 "happy"로 저장 (reference 이용)
 *
 * 편의점 리스트를 돌면서
 * 거리가 1000보다 작거나 같고 && 방문하지 않았던 편의점이라면
 * 방문한 편의점이라고 표시하고
 * 해당 편의점 포인트로 현재 포인트를 옮겨 재귀 DFS 수행
 */
function dfs(currentX, currentY, store, beers, endX, endY, checkList, count) {
  if (currentX === endX && currentY === endY) {
    answer[count] = "happy";
  }
  store.forEach((s, i) => {
    if (!checkList[i]) {
      let nextX = s[0];
      let nextY = s[1];
      let distance = mDistance(currentX, currentY, nextX, nextY);

      if (distance <= 50 * beers) {
        checkList[i] = 1;
        dfs(nextX, nextY, store, 20, endX, endY, checkList, count);
      }
    }
  });
}
/**
 * 채점 결과
 * 메모리: 11072KB
 * 시간: 184ms
 * 언어: JS
 */
