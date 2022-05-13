/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2660
 * 
 * 시간제한: 1초
 * 메모리제한: 128MB
 * 
 * 입력
 * 입력의 첫째 줄에는 회원의 수가 있다. 단, 회원의 수는 50명을 넘지 않는다. 둘째 줄 이후로는 한 줄에 두 개의 회원번호가 있는데, 이것은 두 회원이 서로 친구임을 나타낸다. 회원번호는 1부터 회원의 수만큼 붙어 있다. 마지막 줄에는 -1이 두 개 들어있다.
 * 
    5
    1 2
    2 3
    3 4
    4 5
    2 4
    5 3
    -1 -1
 * 
 * 출력
 * 첫째 줄에는 회장 후보의 점수와 후보의 수를 출력하고, 두 번째 줄에는 회장 후보를 오름차순으로 모두 출력한다.
 * 
    2 3
    2 3 4
 * 
 * 파싱
 * n = 5
 * arr = [[1, 2], [2, 3], [3, 4], [4, 5], [2, 4], [5, 3]]
 * 
 * {{초기 설정}}
 * 인접 친구 리스트 객체 obj = {}
 * 모든 친구들의 점수 result = [0 * n+1]
 * 회장 후보의 점수(점수들 중 최솟값) min = n+1
 * 
 * {{각 케이스마다}}
 * 체크리스트 checkList = [0 * n+1] => checkList[i] = 1
 * BFS용 큐와 포인터 queue = [[i, 0]], pointer = 0
 * 현재 후보의 친구점수의 최댓값 max = 0
 * 
 * {{출력}}
 * min ->> 후보들의 점수 중 가장 작은 값
 * count ->> min의 점수를 갖고있는 후보의 수
 * answer ->> min의 점수를 갖고있는 후보 배열
 */

// 파싱
const fs = require("fs");
let [n, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
n = +n;
arr = arr.map(a => a.split(" ").map(Number));
arr.pop();

// 초기 설정
let obj = {};
arr.forEach(fnd => {
  if (!obj[fnd[0]]) obj[fnd[0]] = [];
  if (!obj[fnd[1]]) obj[fnd[1]] = [];
  obj[fnd[0]].push(fnd[1]);
  obj[fnd[1]].push(fnd[0]);
});

let result = new Array(n + 1).fill(0);
let min = n + 1;

// 각 케이스마다(모든 후보에 대해)
for (let i = 1; i <= n; i++) {
  let checkList = new Array(n + 1).fill(0);
  let queue = [[i, 0]];
  checkList[i] = 1;
  let pointer = 0;
  let max = 0;
  /**
   * 모든 후보에 대해 BFS를 수행해서
   * 가장 멀리(친구의 친구의 친구의...)있는 친구까지의 거리를 구함
   */
  while (pointer < queue.length) {
    let [current, point] = queue[pointer++];

    if (obj[current]) {
      obj[current].forEach(friend => {
        if (!checkList[friend]) {
          checkList[friend] = point + 1;
          if (max < point + 1) {
            max = point + 1;
          }
          queue.push([friend, point + 1]);
        }
      });
    }
  }

  result[i] = max;
  if (min > max) {
    min = max;
  }
}

let count = 0;
let answer = [];
for (let i = 1; i < result.length; i++) {
  if (result[i] == min) {
    count++;
    answer.push(i);
  }
}

console.log(min + " " + count + "\n" + answer.join(" "));
/**
 * 채점 결과
 * 메모리: 9772KB
 * 시간: 120ms
 * 언어: JS
 */
