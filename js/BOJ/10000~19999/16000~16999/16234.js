/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/16234
 * 
 * 시간제한: 2초
 * 메모리제한: 512MB
 * 
 * 문제
 * N×N크기의 땅이 있고, 땅은 1×1개의 칸으로 나누어져 있다. 각각의 땅에는 나라가 하나씩 존재하며, r행 c열에 있는 나라에는 A[r][c]명이 살고 있다. 인접한 나라 사이에는 국경선이 존재한다. 모든 나라는 1×1 크기이기 때문에, 모든 국경선은 정사각형 형태이다.
 * 오늘부터 인구 이동이 시작되는 날이다.
 * 인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.
 * 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
 * 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
 * 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
 * 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
 * 연합을 해체하고, 모든 국경선을 닫는다.
 * 각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)
 * 둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 c열에 주어지는 정수는 A[r][c]의 값이다. (0 ≤ A[r][c] ≤ 100)
 * 인구 이동이 발생하는 일수가 2,000번 보다 작거나 같은 입력만 주어진다.
 * 
    2 20 50
    50 30
    20 40
 * 
 * 출력
 * 인구 이동이 며칠 동안 발생하는지 첫째 줄에 출력한다.
 * 
    1
 * 
 * 파싱
 * n = 2, l = 20, r = 50
 * arr = [[50, 30], [20, 40]]
 * 
 * {{초기 설정}}
 * 상하좌우 dx = [1, -1, 0, 0], dy = [0, 0, 1, -1]
 * 체크리스트 checkList
 * 결과 result
 * 각 연합마다 인구 총합 sum = []
 * 각 연합마다 나라 수 count = []
 * 
 * {{각 케이스마다}}
 * checkList = [[0 * n] * n]
 * sum = []
 * count = []
 */

// 파싱
const fs = require("fs");
let [nlr, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
let [n, l, r] = nlr.trim().split(" ").map(Number);
arr = arr.map((a) => a.split(" ").map(Number));

// 초기 설정
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let checkList;
let result = 0;
let sum = [];
let count = [];

/**
 * 더이상 연합할 나라가 없는 경우에는 sum과 count배열의 길이가 n**2의 값이 됨
 * why?
 * 모든 인덱스마다 DFS를 수행해야하기 때문에
 * sum과 count에 그때마다 값이 push 되기 때문
 */
while (sum.length < n ** 2) {
  checkList = Array.from({ length: n }, () => new Array(n).fill(0)); // 체크리스트 초기화
  sum = []; // 연합한 인구의 총합 초기화
  count = []; // 연합한 나라의 총합 초기화
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (!checkList[i][j]) {
        // sum에 현재 인덱스의 나라의 인구의 수를 push하면서 연합을 추가
        sum.push(arr[i][j]);
        // count에 1을 push하면서 연합을 추가
        count.push(1);
        // 해당 인덱스에 현재 sum의 길이(연합의 넘버라고 봐도 무방)
        checkList[i][j] = sum.length;
        // 해당 인덱스에 대해 DFS 수행
        dfs(i, j);
      }
    }
  }
  /**
   * checkList의 값에 연합의 넘버가 들어있기 때문에
   * 해당 연합의 넘버로 sum과 count에 들어있는 값을 통해
   * 인구이동이 완료된 값을 넣어줌.
   */
  alias();

  if (sum.length > 0 && sum.length < n ** 2) {
    result++;
  }
}
console.log(result);

function dfs(x, y) {
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    // 범위 체크 & 이미 연합된 나라인지 체크
    if (0 <= nx && nx < n && 0 <= ny && ny < n && !checkList[nx][ny]) {
      // 현재 나라와 인접한 나라에 대해 인구수의 차
      let gap = Math.abs(arr[x][y] - arr[nx][ny]);

      // 인구수의 차가 조건을 만족할 경우
      if (l <= gap && gap <= r) {
        // checkList에 해당 인덱스에 있는 값에 연합의 넘버를 저장
        checkList[nx][ny] = sum.length;
        // 현재 연합의 인구수에 다음 나라의 인구수 더하기
        sum[sum.length - 1] += arr[nx][ny];
        // 현재 연합의 연합수 + 1
        count[count.length - 1]++;
        // 해당 인덱스에 대하여 DFS 재귀 수행
        dfs(nx, ny);
      }
    }
  }
}

/**
 * checkList를 돌면서
 * 현재 연합의 팀 넘버가 있는 인덱스에 대하여
 * sum과 count에 접근하여 arr(인구수배열)의 값을 바꿈
 */
function alias() {
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      let team = checkList[i][j] - 1;
      if (count[team] == 1) continue;
      if (checkList[i][j] !== 0) {
        arr[i][j] = Math.floor(sum[team] / count[team]);
      }
    }
  }
}
/**
 * 채점 결과
 * 메모리: 21060KB
 * 시간: 612ms
 * 언어: JS
 */
