/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1743
 * 
 * 문제
 * 코레스코 콘도미니엄 8층은 학생들이 3끼의 식사를 해결하는 공간이다. 그러나 몇몇 비양심적인 학생들의 만행으로 음식물이 통로 중간 중간에 떨어져 있다. 이러한 음식물들은 근처에 있는 것끼리 뭉치게 돼서 큰 음식물 쓰레기가 된다. 
 * 이 문제를 출제한 선생님은 개인적으로 이러한 음식물을 실내화에 묻히는 것을 정말 진정으로 싫어한다. 참고로 우리가 구해야 할 답은 이 문제를 낸 조교를 맞추는 것이 아니다. 
 * 통로에 떨어진 음식물을 피해가기란 쉬운 일이 아니다. 따라서 선생님은 떨어진 음식물 중에 제일 큰 음식물만은 피해 가려고 한다. 
 * 선생님을 도와 제일 큰 음식물의 크기를 구해서 “10ra"를 외치지 않게 도와주자.
 * 
 * 입력
 * 첫째 줄에 통로의 세로 길이 N(1 ≤ N ≤ 100)과 가로 길이 M(1 ≤ M ≤ 100) 그리고 음식물 쓰레기의 개수 K(1 ≤ K ≤ N×M)이 주어진다.  그리고 다음 K개의 줄에 음식물이 떨어진 좌표 (r, c)가 주어진다.
 * 좌표 (r, c)의 r은 위에서부터, c는 왼쪽에서부터가 기준이다. 입력으로 주어지는 좌표는 중복되지 않는다.
 * 
    3 4 5
    3 2
    2 2
    3 1
    2 3
    1 1
 * 
 * 출력
 * 첫째 줄에 음식물 중 가장 큰 음식물의 크기를 출력하라.  
 * 
    4
 * 
 * 파싱
 * n = 3, m = 4, k = 5
 * arr = [[3, 2], [2, 2], [3, 1], [2, 3], [1, 1]]
 * 
 * {{초기 설정}}
 * 음식물이 들어있는 위치를 1로 표시하고 나머지는 0으로 표시
 * leftover = n * m 2차원 배열
 * 
 * DFS를 위한 체크리스트
 * checkList = [[0 * n+1] * m+1] 인덱스 접근을 쉽게 하기 위해 1씩 늘림
 * 
 * 상하좌우
 * dx = [1, -1, 0, 0]
 * 요 = [0, 0, 1, -1]
 * 
 * 음식물 덩어리의 크기 최댓값
 * max = 0 
 */
const fs = require("fs");
let [nmk, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
let [n, m, k] = nmk.split(" ").map(Number);
arr = arr.map((a) => a.split(" ").map(Number));

let leftover = [];
let checkList = [];
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let max = 0;

/** 2차원 배열 생성하고 0으로 초기화 */
for (let i = 0; i <= n; i++) {
  leftover.push(new Array(m + 1).fill(0));
  checkList.push(new Array(m + 1).fill(0));
}

/** 음식물이 있는 곳에 1 표시 */
arr.forEach((a) => {
  leftover[a[0]][a[1]] = 1;
});

/**
 * 음식물 덩어리 크기를 세기 위해
 * 이미 방문하지 않은 인덱스인지
 * 음식물이 있는지 판단한 후
 *
 * 방문한 인덱스를 표시하고(checkList[i][j] = 1)
 * 덩어리의 갯수를 세기 위해 reference 값을 참조하기 위해서 배열 안에 1을 넣어서 사용(arr.push(1))
 * 해당 인덱스에 대해서 DFS수행
 * */
for (let i = 1; i < leftover.length; i++) {
  for (let j = 1; j < leftover[0].length; j++) {
    if (!checkList[i][j] && leftover[i][j]) {
      checkList[i][j] = 1;
      arr.push(1);
      dfs(i, j);
    }
  }
}

/**
 * DFS
 * 상하좌우에 대하여
 * 범위를 벗어나지 않았는지 체크하고
 * 이미 방문했는지 체크하고
 * 음식물이 있는지 체크하고
 *
 * arr의 맨 마지막 요소의 값을 1 증가시킴(음식물 덩어리의 갯수)
 * 음식물 덩어리의 갯수가 최댓값일 경우 최댓값 업데이트
 *
 * 해당 인덱스에 대하여 DFS수행
 */
function dfs(x, y) {
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (
      1 <= nx &&
      nx <= n &&
      1 <= ny &&
      ny <= m &&
      !checkList[nx][ny] &&
      leftover[nx][ny]
    ) {
      checkList[nx][ny] = 1;
      arr[arr.length - 1]++;

      if (arr[arr.length - 1] > max) {
        max = arr[arr.length - 1];
      }
      dfs(nx, ny);
    }
  }
}

console.log(max);
/**
 * 채점 결과
 * 메모리: 12932KB
 * 시간: 188ms
 * 언어: JS
 * 참고 링크: https://blog.naver.com/y2kdj9723/222707250340
 */
