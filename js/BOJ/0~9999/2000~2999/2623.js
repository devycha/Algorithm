/**
 * ! 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2623
 * 
 * ! 시간제한: 1초
 * ! 메모리제한: 128MB
 * 
 * ! 입력 & 파싱
 * 첫째 줄에는 가수의 수 N과 보조 PD의 수 M이 주어진다. 가수는 번호 1, 2,…,N 으로 표시한다. 둘째 줄부터 각 보조 PD가 정한 순서들이 한 줄에 하나씩 나온다. 각 줄의 맨 앞에는 보조 PD가 담당한 가수의 수가 나오고, 그 뒤로는 그 가수들의 순서가 나온다. N은 1이상 1,000이하의 정수이고, M은 1이상 100이하의 정수이다.
 * 
    6 3       -> n m
    3 1 4 3   -> arr[0]
    4 6 2 5 4
    2 2 3     -> arr[m-1]
 * 
 * ! 출력
 * 출력은 N 개의 줄로 이뤄지며, 한 줄에 하나의 번호를 출력한 다. 이들은 남일이가 정한 가수들의 출연 순서를 나타낸다. 답이 여럿일 경우에는 아무거나 하나를 출력 한다. 만약 남일이가 순서를 정하는 것이 불가능할 경우에는 첫째 줄에 0을 출력한다.
 * 
    6
    2
    1
    5
    4
    3
 * 
 * ! 초기 설정
 * degree: 선행-후행 작업의 위상(차수) 배열
 * obj: obj[선행작업 번호] = [후행작업번호 리스트]
 * * 1번 가수 다음 2번 가수 및 3번 가수가 다음 순서이다? obj[1] = [2, 3]
 * queue, pointer: BFS용 큐를 스택으로 표현하기 위한 스택과 포인터
 * result: 결과 출력용 배열
 */

// 파싱
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map((a) => a.trim().split(" ").map(Number));

// 초기 설정
let degree = new Array(n + 1).fill(0);
let obj = Array.from({ length: n + 1 }, () => new Array());

arr.forEach((e) => {
  for (let i = 1; i < e.length - 1; i++) {
    obj[e[i]].push(e[i + 1]);
    degree[e[i + 1]]++;
  }
});

let result = [];
let queue = [];
let pointer = 0;
for (let i = 1; i < degree.length; i++) {
  if (degree[i] == 0) queue.push(i);
}

while (pointer < queue.length) {
  let cur = queue[pointer++];
  result.push(cur);

  if (obj[cur].length) {
    obj[cur].forEach((next) => {
      if (--degree[next] == 0) queue.push(next);
    });
  }
}

console.log(result.length == n ? result.join("\n") : 0);
/**
 * 채점 결과
 * 메모리: 9988KB
 * 시간: 120ms
 * 언어: JS
 */
