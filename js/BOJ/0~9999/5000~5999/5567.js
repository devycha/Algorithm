/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/5567
 * 
 * 문제
 * 상근이는 자신의 결혼식에 학교 동기 중 자신의 친구와 친구의 친구를 초대하기로 했다. 
 * 상근이의 동기는 모두 N명이고, 이 학생들의 학번은 모두 1부터 N까지이다. 
 * 상근이의 학번은 1이다.
 * 상근이는 동기들의 친구 관계를 모두 조사한 리스트를 가지고 있다. 
 * 이 리스트를 바탕으로 결혼식에 초대할 사람의 수를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 상근이의 동기의 수 n (2 ≤ n ≤ 500)이 주어진다. 
 * 둘째 줄에는 리스트의 길이 m (1 ≤ m ≤ 10000)이 주어진다. 
 * 다음 줄부터 m개 줄에는 친구 관계 ai bi가 주어진다. 
 * (1 ≤ ai < bi ≤ n) ai와 bi가 친구라는 뜻이며, bi와 ai도 친구관계이다. 
 * 
    6
    5
    1 2
    1 3
    3 4
    2 3
    4 5
 * 
 * 출력
 * 첫째 줄에 상근이의 결혼식에 초대하는 동기의 수를 출력한다.
 * 
    3
 * 
 * 파싱
 * n = 6, m = 5
 * friends = [ [ 1, 2 ], [ 1, 3 ], [ 3, 4 ], [ 2, 3 ], [ 4, 5 ] ]
 * 
 * {{초기 설정}}
 * 친구 관계 리스트
 * obj = {}
 * 
 * 친구의 친구의 친구의 친구(몇번 걸쳐 친구인지 나타내는 배열)
 * result = [Infinity * n+1] => 인덱스를 편하게 쓰기 위해 n+1까지 초기화
 * result[1] = 1 => 자기 자신 1
 * 
 * 초대할 친구 수
 * count = 0
 * 
 * BFS를 위한 queue와 포인터
 * queue = [1] => 자기 자신
 * i = 0
 */

// 파싱
const fs = require("fs");
let [n, m, ...friends] = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n");
friends = friends.map((a) => a.split(" ").map(Number));

// 초기 설정
let obj = {};
let result = new Array(n + 1).fill(Infinity);
result[1] = 1;
let count = 0;
let queue = [1];
let i = 0;

friends.forEach((friend) => {
  if (!obj[friend[0]]) obj[friend[0]] = [];
  if (!obj[friend[1]]) obj[friend[1]] = [];

  obj[friend[0]].push(friend[1]);
  obj[friend[1]].push(friend[0]);
});

/**
 * BFS
 */
while (i < queue.length) {
  let shift = queue[i];

  if (obj[shift]) {
    obj[shift].forEach((friend) => {
      // BFS이므로 이미 result에 값이 있으면 이미 최소한으로 걸쳐서 갈 수 있는 횟수를 구한 상태
      if (!result[friend]) {
        result[friend] = result[shift] + 1; // 바로 전 친구(shift)의 최소 횟수 + 1을 입력
        queue.push(friend);
      }
    });
  }
  i++;
}

for (let i = 2; i < result.length; i++) {
  if (result[i] <= 3) count++;
}

console.log(count);
/**
 * 채점 결과
 * 메모리: 14256KB
 * 시간: 216ms
 * 언어: JS
 */
