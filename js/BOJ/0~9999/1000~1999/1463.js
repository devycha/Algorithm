// 문제 링크: https://www.acmicpc.net/problem/1463
/**
 * 문제 설명
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
 * X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * X가 2로 나누어 떨어지면, 2로 나눈다.
 * 1을 뺀다.
 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
 *
 * 입력
 * 첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.
 *
 * 입력값 예시
 * 10
 *
 * 출력
 * 첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
 *
 * 출력값 예시
 * 3
 *
 * 파싱
 * N = 10
 *
 * {{초기 설정}}
 * arr = [Infinity * 11]
 */
const fs = require("fs");
const N = +fs.readFileSync("input.txt").toString();

let arr = new Array(N + 1).fill(Infinity);
(arr[0] = 0), (arr[1] = 0);

/**
 * 1부터 N-1까지 DP를 이용하여 누적 저장
 * 1인 케이스가 0이고
 * 1 => 2(1+1) 2(1*2) 3(1*3)
 * 총 세 방향으로 계속 이어나감.
 */
for (let i = 1; i < N; i++) {
  arr[i + 1] = Math.min(arr[i + 1], arr[i] + 1);
  arr[i * 2] = Math.min(arr[i * 2], arr[i] + 1);
  arr[i * 3] = Math.min(arr[i * 3], arr[i] + 1);
}

console.log(arr[N]);
/**
 * 채점 결과
 * 메모리: 84532KB
 * 시간: 276ms
 * 언어: JS
 */
