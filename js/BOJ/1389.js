// 문제 출처: https://www.acmicpc.net/problem/1389
/**
 * 문제 설명
 * 케빈 베이컨의 6단계 법칙에 의하면 지구에 있는 모든 사람들은 최대 6단계 이내에서 서로 아는 사람으로 연결될 수 있다. 케빈 베이컨 게임은 임의의 두 사람이 최소 몇 단계 만에 이어질 수 있는지 계산하는 게임이다.
 * 예를 들면, 전혀 상관없을 것 같은 인하대학교의 이강호와 서강대학교의 민세희는 몇 단계만에 이어질 수 있을까?
 * 천민호는 이강호와 같은 학교에 다니는 사이이다. 천민호와 최백준은 Baekjoon Online Judge를 통해 알게 되었다. 최백준과 김선영은 같이 Startlink를 창업했다. 김선영과 김도현은 같은 학교 동아리 소속이다. 김도현과 민세희는 같은 학교에 다니는 사이로 서로 알고 있다. 즉, 이강호-천민호-최백준-김선영-김도현-민세희 와 같이 5단계만 거치면 된다.
 * 케빈 베이컨은 미국 헐리우드 영화배우들 끼리 케빈 베이컨 게임을 했을때 나오는 단계의 총 합이 가장 적은 사람이라고 한다.
 * 오늘은 Baekjoon Online Judge의 유저 중에서 케빈 베이컨의 수가 가장 작은 사람을 찾으려고 한다. 케빈 베이컨 수는 모든 사람과 케빈 베이컨 게임을 했을 때, 나오는 단계의 합이다.
 * 예를 들어, BOJ의 유저가 5명이고, 1과 3, 1과 4, 2와 3, 3과 4, 4와 5가 친구인 경우를 생각해보자.
 * 1은 2까지 3을 통해 2단계 만에, 3까지 1단계, 4까지 1단계, 5까지 4를 통해서 2단계 만에 알 수 있다. 따라서, 케빈 베이컨의 수는 2+1+1+2 = 6이다.
 * 2는 1까지 3을 통해서 2단계 만에, 3까지 1단계 만에, 4까지 3을 통해서 2단계 만에, 5까지 3과 4를 통해서 3단계 만에 알 수 있다. 따라서, 케빈 베이컨의 수는 2+1+2+3 = 8이다.
 * 3은 1까지 1단계, 2까지 1단계, 4까지 1단계, 5까지 4를 통해 2단계 만에 알 수 있다. 따라서, 케빈 베이컨의 수는 1+1+1+2 = 5이다.
 * 4는 1까지 1단계, 2까지 3을 통해 2단계, 3까지 1단계, 5까지 1단계 만에 알 수 있다. 4의 케빈 베이컨의 수는 1+2+1+1 = 5가 된다.
 * 마지막으로 5는 1까지 4를 통해 2단계, 2까지 4와 3을 통해 3단계, 3까지 4를 통해 2단계, 4까지 1단계 만에 알 수 있다. 5의 케빈 베이컨의 수는 2+3+2+1 = 8이다.
 * 5명의 유저 중에서 케빈 베이컨의 수가 가장 작은 사람은 3과 4이다.
 * BOJ 유저의 수와 친구 관계가 입력으로 주어졌을 때, 케빈 베이컨의 수가 가장 작은 사람을 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 유저의 수 N (2 ≤ N ≤ 100)과 친구 관계의 수 M (1 ≤ M ≤ 5,000)이 주어진다. 둘째 줄부터 M개의 줄에는 친구 관계가 주어진다. 친구 관계는 A와 B로 이루어져 있으며, A와 B가 친구라는 뜻이다. A와 B가 친구이면, B와 A도 친구이며, A와 B가 같은 경우는 없다. 친구 관계는 중복되어 들어올 수도 있으며, 친구가 한 명도 없는 사람은 없다. 또, 모든 사람은 친구 관계로 연결되어져 있다. 사람의 번호는 1부터 N까지이며, 두 사람이 같은 번호를 갖는 경우는 없다.
 * 
 * 입력값 예시
    5 5
    1 3
    1 4
    4 5
    4 3
    3 2
 * 
 * 출력
 * 첫째 줄에 BOJ의 유저 중에서 케빈 베이컨의 수가 가장 작은 사람을 출력한다. 그런 사람이 여러 명일 경우에는 번호가 가장 작은 사람을 출력한다.
 * 
 * 출력값 예시
    3
 * 
 * 파싱
 * N = 5, M = 5
 * arr = [ [ 1, 3 ], [ 1, 4 ], [ 4, 5 ], [ 4, 3 ], [ 3, 2 ] ]
 * 
 * {{초기 설정}}
 * 인접 노드 리스트 방향성 없는 간선
 * obj = {
    '1': [ 3, 4 ],
    '2': [ 3 ],
    '3': [ 1, 4, 2 ],
    '4': [ 1, 5, 3 ],
    '5': [ 4 ]
  }
 * 
 * 각 유저넘버마다 N+1개의 체크리스트(index 편하게 사용하기 위해 N+1 선택)
 * checkList = [0 * N+1]
 * 
 * BFS를 위한 queue
 * queue = [[key, count]]
 * 
 * 케빈베이컨 수가 최소일 때 케빈베이컨 수
 * minSum = Infinity
 * 
 * 케빈베이컨 수가 최소일 때의 유저 넘버
 * minIndex = 0
 * 
 * BFS를 위한 queue pointer
 * i = 0
 * 
 */
// 파싱
const fs = require("fs");
let [NM, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [N, M] = NM.split(" ").map(Number);
arr = arr.map((a) => a.split(" ").map(Number));

// 초기 설정
let obj = {}; // 인접 노드 리스트 객체
arr.forEach((friend) => {
  if (!obj[friend[0]]) obj[friend[0]] = [];
  if (!obj[friend[1]]) obj[friend[1]] = [];
  obj[friend[0]].push(friend[1]);
  obj[friend[1]].push(friend[0]);
});

let checkList; // 체크리스트
let queue; // BFS를 위한 queue
let i; // BFS를 위한 포인터
let minSum = Infinity; // 케빈베이컨 수가 최소일 때 케빈베이컨 수
let minIndex = 0; // 케빈베이컨 수가 최소일 때 유저 넘버
/**
 * 인접 노드 리스트 객체의 키값들을 순서대로 돌면서
 * 그 키들을 기준으로 총 N번 BFS를 수행
 * count를 이용하여 몇번 걸쳐서 갔는지 기록
 */
for (let key in obj) {
  checkList = new Array(N + 1).fill(0); // 체크리스트 초기화 [0 * N+1]
  checkList[+key] = 1; // 시작 유저 넘버를 방문했다고 표시
  queue = [[+key, 0]]; // queue에 현재 시작 유저 넘버(+key)와 몇번 걸쳐서 갔는지(count)
  let sum = 0; // 케빈베이컨 수 초기화
  i = 0; // BFS 포인터 초기화
  /**
   * BFS 시작
   * 방문하지 않은 유저 넘버에 한해서
   * 현재 count보다 1 증가시킨 값으로 checkList에 표시하고
   * sum에도 count+1을 더해준다.
   * queue에 해당 [유저넘버, count+1]를 push해준다.
   * i++(포인터 증가)
   */
  while (i < queue.length) {
    let [current, count] = queue[i];
    if (obj[current]) {
      obj[current].forEach((friend) => {
        if (!checkList[friend]) {
          checkList[friend] = count + 1;
          sum += count + 1;
          queue.push([friend, count + 1]);
        }
      });
    }
    i++;
  }

  // 각 유저 넘버의 BFS가 끝나고 난 뒤에 sum값을 현재까지의 케빈베이컨수의 최솟값과 비교하여 업데이트
  if (sum < minSum) {
    minSum = sum; // 케빈베이컨 수의 최솟값 업데이트
    minIndex = key; // 그 때의 유저 넘버 업데이트
  }
}

console.log(minIndex);
/**
 * 채점 결과
 * 메모리: 10384KB
 * 시간: 124ms
 * 언어: JS
 */

/**
 * *** *** *** *** ***
 * 메모리 초과 실패 풀이
 * *** *** *** *** ***
 */
// arr.forEach((friend) => {
//   friends[friend[0]][friend[1]] = 1;
//   friends[friend[1]][friend[0]] = 1;
// });

// let i = 0;
// let friend1;
// let friend2;
// while (i < arr.length) {
//   friend1 = arr[i][0];
//   friend2 = arr[i][1];
//   for (let j = 1; j <= N; j++) {
//     if (j == friend1 || j == friend2) continue;
//     if (friends[friend2][j]) {
//       if (!friends[friend1][j]) {
//         friends[friend1][j] = friends[friend2][j] + friends[friend1][friend2];
//         arr.push([friend1, j]);
//       } else {
//         if (friends[friend1][j] > friends[friend2][j] + 1) {
//           friends[friend1][j] = friends[friend2][j] + friends[friend1][friend2];
//           arr.push([friend1, j]);
//         }
//       }
//     }
//     if (friends[friend1][j]) {
//       if (!friends[friend2][j]) {
//         friends[friend2][j] = friends[friend1][j] + friends[friend1][friend2];
//         arr.push([friend2, j]);
//       } else {
//         if (friends[friend2][j] > friends[friend1][j] + 1) {
//           friends[friend2][j] = friends[friend1][j] + friends[friend1][friend2];
//           arr.push([friend2, j]);
//         }
//       }
//     }
//   }
//   i++;
// }

// friends = friends.map((a) =>
//   a.reduce((sum, el) => {
//     if (Number.isInteger(el)) {
//       return sum + el;
//     }
//     return sum;
//   }, 0)
// );

// let min = Infinity;
// let minIndex = 0;
// for (let i = 1; i < friends.length; i++) {
//   if (friends[i] < min) {
//     min = friends[i];
//     minIndex = i;
//   }
// }
// console.log(minIndex);
