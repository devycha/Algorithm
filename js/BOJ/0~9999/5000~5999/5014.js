/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/5014
 * 
 * 시간제한: 1초
 * 메모리제한: 256MB
 * 
 * 문제
 * 강호는 코딩 교육을 하는 스타트업 스타트링크에 지원했다. 오늘은 강호의 면접날이다. 하지만, 늦잠을 잔 강호는 스타트링크가 있는 건물에 늦게 도착하고 말았다.
 * 스타트링크는 총 F층으로 이루어진 고층 건물에 사무실이 있고, 스타트링크가 있는 곳의 위치는 G층이다. 강호가 지금 있는 곳은 S층이고, 이제 엘리베이터를 타고 G층으로 이동하려고 한다.
 * 보통 엘리베이터에는 어떤 층으로 이동할 수 있는 버튼이 있지만, 강호가 탄 엘리베이터는 버튼이 2개밖에 없다. U버튼은 위로 U층을 가는 버튼, D버튼은 아래로 D층을 가는 버튼이다. (만약, U층 위, 또는 D층 아래에 해당하는 층이 없을 때는, 엘리베이터는 움직이지 않는다)
 * 강호가 G층에 도착하려면, 버튼을 적어도 몇 번 눌러야 하는지 구하는 프로그램을 작성하시오. 만약, 엘리베이터를 이용해서 G층에 갈 수 없다면, "use the stairs"를 출력한다
 * 
 * 입력
 * 첫째 줄에 F, S, G, U, D가 주어진다. (1 ≤ S, G ≤ F ≤ 1000000, 0 ≤ U, D ≤ 1000000) 건물은 1층부터 시작하고, 가장 높은 층은 F층이다.
 * 
    10 1 10 2 1
 * 
 * 출력
 * 첫째 줄에 강호가 S층에서 G층으로 가기 위해 눌러야 하는 버튼의 수의 최솟값을 출력한다. 만약, 엘리베이터로 이동할 수 없을 때는 "use the stairs"를 출력한다.
 * 
    6
 * 
 * 파싱
 * f = 10, s = 1, g = 10, u = 2, d = 1
 * 
 * {{초기 설정}}
 * 체크리스트
 * checkList = [0 * f+1] => checkList[s] = 1
 * 
 * BFS용 큐
 * queue = [s]
 * 
 * BFS용 포인터
 * i = 0
 * 
 * 다음으로 갈 수 있는 위치
 * dx = [u, -d]
 */

// 파싱
const fs = require("fs");
const [f, s, g, u, d] = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split(" ")
  .map(Number);

// 초기 설정
let checkList = new Array(f + 1).fill(0);
checkList[s] = 1;
let queue = [s];
let i = 0;
let dx = [u, -d];

// BFS
while (i <= queue.length) {
  let current = queue[i++]; // 방문하지 않은 맨 앞의 요소를 가져옴(포인터 + 1증가)
  if (current == g) {
    // 목적지에 도착했을 때 break
    break;
  }

  for (let i = 0; i < 2; i++) {
    // 다음으로 갈 수 있는 위치에 대하여
    let next = current + dx[i];
    if (next <= f && next > 0 && !checkList[next]) {
      // 범위체크 & 방문했는지 체크
      checkList[next] = checkList[current] + 1; // 이전 위치의 카운트 + 1을 체크리스트에 저장
      queue.push(next); // BFS용 queue에 해당 위치 push
    }
  }
}

console.log(checkList[g] ? checkList[g] - 1 : "use the stairs"); // 처음 시작부터 1로 시작했기 때문에 최종적으로 1을 빼준 값을 출력, 값이 0이라면 "use the stairs"를 출력
/**
 * 채점 결과
 * 메모리: 50732KB
 * 시간: 248ms
 * 언어: JS
 */
