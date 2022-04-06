// 문제 링크: https://www.acmicpc.net/problem/1987
/**
 * 문제 설명
 * 세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.
 * 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 * 좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
 *
 * 입력
 * 첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1 ≤ R,C ≤ 20) 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.
 *
 * 입력값 예시
    2 4
    CAAB
    ADCB
 *
 * 출력
 * 첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.
 * 
 * 출력값 예시
    3
 *
 * 파싱
 * N = 2, M = 4
 * arr = [
 *  [ 2, 0, 0, 1 ],
 *  [ 0, 3, 2, 1 ]
 * ]
 *
 * {{초기설정}}
 * 체크리스트
 * checkList = [0 * 26] 알파벳 개수 26개
 *
 * 지날 수 있는 최대의 칸수
 * max = 1
 *
 * 상하좌우
 * dx = [1, -1, 0, 0]
 * dy = [0, 0, 1, -1]
 *
 */
const fs = require("fs");
let [NM, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [N, M] = NM.split(" ").map(Number);
arr = arr.map((a) =>
  a
    .trim()
    .split("")
    .map((b) => b.charCodeAt() - 65)
);
let checkList = new Array(26).fill(0);
checkList[arr[0][0]] = 1;
let max = 1;
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];

dfs(1, 0, 0);
console.log(max);

/**
 * 현재 인덱스 x, y를 배열안에 넣어서 하는 것과 아닌 것의 차이가 매우 큼.
 * 기존에는 dfs([currentX, currentY]) 이런식으로 넣었다면
 * 현재는 dfs(currentX, currentY) 이렇게 넣는게
 * 이번 문제에서는 4328ms -> 1516ms 까지 단축되는 것을 확인함.
 */

function dfs(count, currentX, currentY) {
  let x = currentX;
  let y = currentY;

  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      0 <= nx &&
      nx < N &&
      0 <= ny &&
      ny < M &&
      checkList[arr[nx][ny]] === 0
    ) {
      checkList[arr[nx][ny]] = 1;
      max = Math.max(max, count + 1);
      dfs(count + 1, nx, ny);
    }
  }

  checkList[arr[x][y]] = 0; // DFS에서의 체크리스트 초기화
}
/**
 * 채점 결과
 * 메모리: 10108KB
 * 시간: 1516ms
 * 언어: JS
 */
