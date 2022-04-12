// 문제 출처: https://www.acmicpc.net/problem/16953
/**
 * 문제 설명
 * 정수 A를 B로 바꾸려고 한다. 가능한 연산은 다음과 같은 두 가지이다.
 * 2를 곱한다.
 * 1을 수의 가장 오른쪽에 추가한다. 
 * A를 B로 바꾸는데 필요한 연산의 최솟값을 구해보자.
 * 
 * 입력
 * 첫째 줄에 A, B (1 ≤ A < B ≤ 10^9)가 주어진다.
 * 
 * 입력값 예시
    2 162
 * 
 * 출력
 * A를 B로 바꾸는데 필요한 연산의 최솟값에 1을 더한 값을 출력한다. 만들 수 없는 경우에는 -1을 출력한다.
 * 
 * 출력값 예시
    5
 * 
 * 파싱
 * A = 2, B = 162
 * 
 * {{초기 설정}}
 * A부터 BFS를 시작하기 위한 배열 [[현재숫자, 연산횟수]]
 * stack = [[A, 0]]
 * 
 * BFS를 위한 포인터
 * i = 0
 */
const fs = require("fs");
let [A, B] = fs.readFileSync("input.txt").toString().split(" ").map(Number);

let stack = [[A, 0]];
let i = 0;
/**
 * BFS 시작
 */
while (i < stack.length) {
  let [num, count] = stack[i];
  if (num === B) {
    // A가 B에 도달하면 종료
    break;
  } else {
    if (num * 2 <= B) {
      // 2배한 값이 B보다 작거나 같을 때
      stack.push([num * 2, count + 1]); // 스택에 카운트를 1 증가시킨 값을 push
    }

    if (+(num + "1") <= B) {
      // 뒤에 1을 붙인 숫자가 B보다 작거나 같을 때
      stack.push([+(num + "1"), count + 1]); // 스택에 카운트를 1 증가시킨 값을 push
    }
  }
  i++; // 포인터 1 증가
}
/**
 * BFS가 끝날 때까지 못찾은 경우(i가 stack 배열을 모두 다 돌아서 끝나는 경우)
 * -1 출력
 * 그외에는 stack[i][1] => 연산횟수 카운트  + 1 출력
 */

console.log(i !== stack.length ? stack[i][1] + 1 : -1);
/**
 * 채점 결과
 * 메모리: 25152KB
 * 시간: 288ms
 * 언어: JS
 */
