/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/12852
 * 
 * 문제
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
 * X가 3으로 나누어 떨어지면, 3으로 나눈다
 * X가 2로 나누어 떨어지면, 2로 나눈다
 * 1을 뺀다
 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
 * 
 * 입력
 * 첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 자연수 N이 주어진다.
 * 
    10
 * 
 * 출력
 * 첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
 * 둘째 줄에는 N을 1로 만드는 방법에 포함되어 있는 수를 공백으로 구분해서 순서대로 출력한다. 정답이 여러 가지인 경우에는 아무거나 출력한다.
    3
    10 9 3 1
 * 
 * 
 * 파싱
 * N = 10
 * 
 * {{초기 설정}}
 * 1로 만들때 까지의 경로(방법)
 * result
 * 
 * BFS를 위한 배열
 * stack = [[N]]
 * 
 * 최소한의 방법으로 이미 만들 수 있는 경우가 있을 때
 * 중복을 방지하기 위한 obj - O(1)의 접근 방법을 사용하기 위함.
 * obj = {}
 */

// 파싱
const fs = require("fs");
let N = +fs.readFileSync("input.txt").toString();

// 초기 설정
let result;
let stack = [[N]];
let obj = {};

// BFS
let i = 0; // 포인터 (Queue의 Shift를 대신하는 포인터임.)
while (i < stack.length) {
  // 포인터가 stack을 모두 돌 때까지
  let shift = stack[i]; // 맨 앞 요소(stack에서 돌지 않은 요소 중)
  let lastNum = shift[shift.length - 1]; // 맨 앞 요소(배열)의 맨 마지막 값
  if (lastNum == 1) {
    // 그 값이 1이면
    result = shift; // result에 맨 앞 요소(배열)을 저장하고
    break; // BFS 종료
  }

  // 3으로 나누어 떨어지고 && 최소한의 방법으로 이미 만들 수 있는 경우가 주어지지 **않은** 경우
  if (lastNum % 3 == 0 && !obj[lastNum / 3]) {
    stack.push([...shift, lastNum / 3]); // BFS를 위한 (stack)에 마지막 값을 3으로 나눈 몫을 추가해서 push
    obj[lastNum / 3] = 1; // 최소한의 방법으로 만들 수 있는 경우가 있다고 체크
  }

  // 2로 나누어 떨어지고 && 최소한의 방법으로 이미 만들 수 있는 경우가 주어지지 **않은** 경우
  if (lastNum % 2 == 0 && !obj[lastNum / 2]) {
    stack.push([...shift, lastNum / 2]); // BFS를 위한 (stack)에 마지막 값을 2로 나눈 몫을 추가해서 push
    obj[lastNum / 2] = 1; // 최소한의 방법으로 만들 수 있는 경우가 있다고 체크
  }

  // 최소한의 방법으로 이미 만들 수 있는 경우가 주어지지 **않은** 경우
  if (!obj[lastNum - 1]) {
    stack.push([...shift, lastNum - 1]); // BFS를 위한 (stack)에 마지막 값에서 1을 뺀 수를 추가해서 push
    obj[lastNum - 1] = 1; // 최소한의 방법으로 만들 수 있는 경우가 있다고 체크
  }

  i++; // 포인터 + 1 (Queue의 Shift를 대신하는 포인터임.)
}
console.log(result.length - 1 + "\n" + result.join(" "));
/**
 * 채점 결과
 * 메모리: 10512KB
 * 시간: 128ms
 * 언어: JS
 */
