// 문제 링크: https://www.acmicpc.net/problem/2583
/**
 * 문제 설명(그림은 링크 참조)
 * 눈금의 간격이 1인 M×N(M,N≤100)크기의 모눈종이가 있다. 이 모눈종이 위에 눈금에 맞추어 K개의 직사각형을 그릴 때, 이들 K개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어진다.
 * 예를 들어 M=5, N=7 인 모눈종이 위에 <그림 1>과 같이 직사각형 3개를 그렸다면, 그 나머지 영역은 <그림 2>와 같이 3개의 분리된 영역으로 나누어지게 된다.
 * <그림 2>와 같이 분리된 세 영역의 넓이는 각각 1, 7, 13이 된다.
 * M, N과 K 그리고 K개의 직사각형의 좌표가 주어질 때, K개의 직사각형 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어지는지, 그리고 분리된 각 영역의 넓이가 얼마인지를 구하여 이를 출력하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 M과 N, 그리고 K가 빈칸을 사이에 두고 차례로 주어진다. M, N, K는 모두 100 이하의 자연수이다. 둘째 줄부터 K개의 줄에는 한 줄에 하나씩 직사각형의 왼쪽 아래 꼭짓점의 x, y좌표값과 오른쪽 위 꼭짓점의 x, y좌표값이 빈칸을 사이에 두고 차례로 주어진다. 모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0)이고, 오른쪽 위 꼭짓점의 좌표는(N,M)이다. 입력되는 K개의 직사각형들이 모눈종이 전체를 채우는 경우는 없다.
 * 
 * 입력값 예시
    5 7 3
    0 2 4 4
    1 1 2 5
    4 0 6 2
 * 
 * 출력
 * 첫째 줄에 분리되어 나누어지는 영역의 개수를 출력한다. 둘째 줄에는 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 사이에 두고 출력한다.
 * 
 * 출력값 예시
    3
    1 7 13
 * 
 * 파싱
 * M = 5, N = 7, K = 3
 * arr = [ [ 0, 2, 4, 4 ], [ 1, 1, 2, 5 ], [ 4, 0, 6, 2 ] ]
 * 
 * {{초기 설정}}
 * 상하좌우
 * dx = [1, -1, 0, 0]
 * dy = [0, 0, 1, -1]
 * 
 * 모눈종이 배열(x축을 기준으로 뒤집음) -> 문제에서 (0,0)이 좌측하단부터 시작하기 때문에
 * paper = createPaper(M, N, arr)
 * 
 * paper 배열 만드는 함수
 * createPaper: 사각형 정보와 가로, 세로 정보를 가지고 paper배열을 만들어내는 함수
 * 
 * 체크리스트
 * checkList = [[0 * N] * M]
 * 
 * 출력용 결과
 * result = []
 * 
 * 영역 갯수
 * count = 0
 * 
 */
// 파싱
const fs = require("fs");
let [MNK, ...arr] = fs.readFileSync("input.txt").toString().split("\n");
const [M, N, K] = MNK.split(" ").map(Number);
arr = arr.map(a => a.split(" ").map(Number));

// 초기 설정
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let paper = createPaper(M, N, arr);
let checkList = [];
let result = [];
let count = 0;
// 체크리스트 초기화
for (let i = 0; i < M; i++) {
  checkList.push(new Array(N).fill(0));
}

// paper배열에서 사각형에 감춰지지 않고 이미 영역에 포함되지 않은 인덱스에 대하여
for (let i = 0; i < M; i++) {
  for (let j = 0; j < N; j++) {
    if (paper[i][j] === 1 && checkList[i][j] === 0) {
      count = 1; // 영역 개수를 1로 초기화(자기자신)
      checkList[i][j] = 1; // 체크리스트 체크
      dfs(i, j); // dfs시작
      result.push(count); // 영역 개수를 결과에 푸쉬
    }
  }
}
// 출력
console.log(result.length + "\n" + result.sort((a, b) => a - b).join(" "));

/**
 * DFS
 * 상하좌우 네개의 영역에 대해
 * 범위체크 후 => rectangle에 가려지지 않고 이미 영역에 포함되지 않은 곳에 대하여
 * 체크리스트에 영역에 포함시키고
 * 전역변수 count를 증가시키면서 영역 개수를 증가시킴
 * 해당 인접 영역에 대하여 DFS 실행
 */
function dfs(currentX, currentY) {
  for (let i = 0; i < 4; i++) {
    let nx = currentX + dx[i];
    let ny = currentY + dy[i];

    if (
      0 <= nx &&
      nx < M &&
      0 <= ny &&
      ny < N &&
      paper[nx][ny] === 1 &&
      checkList[nx][ny] === 0
    ) {
      count++;
      checkList[nx][ny] = 1;
      dfs(nx, ny);
    }
  }
}

/**
 * paper 배열을 만들어주는 함수
 * 사각형에 가려진 곳은 0
 * 가려지지 않은 곳은 1
 * x축에 대하여 대칭으로 만듬
 *
 * 0000
 * 0101
 * 그림이 위와 같으면
 *
 * 배열은
 * 0101
 * 0000
 *
 * 어차피 영역의 개수만 세면 되기 때문.
 */
function createPaper(M, N, rectangles) {
  let arr = [];
  for (let i = 0; i < M; i++) {
    arr.push(new Array(N).fill(1));
  }

  rectangles.forEach(rectangle => {
    for (let i = rectangle[1]; i < rectangle[3]; i++) {
      for (let j = rectangle[0]; j < rectangle[2]; j++) {
        arr[i][j] = 0;
      }
    }
  });
  return arr;
}
/**
 * 채점 결과
 * 메모리: 11208KB
 * 시간: 180ms
 * 언어: JS
 */
