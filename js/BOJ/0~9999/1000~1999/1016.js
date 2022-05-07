/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1016
 * 
 * 시간제한: 2초
 * 메모리제한: 512MB
 * 
 * 문제
 * 어떤 정수 X가 1보다 큰 제곱수로 나누어 떨어지지 않을 때, 그 수를 제곱ㄴㄴ수라고 한다. 제곱수는 정수의 제곱이다. min과 max가 주어지면, min보다 크거나 같고, max보다 작거나 같은 제곱ㄴㄴ수가 몇 개 있는지 출력한다.
 * 
 * 입력
 * 첫째 줄에 두 정수 min과 max가 주어진다.
 * 
    1 10
 * 
 * 출력
 * 첫째 줄에 min보다 크거나 같고, max보다 작거나 같은 제곱ㄴㄴ수의 개수를 출력한다.
 * 
    7
 * 
 * 파싱
 * min = 1, max = 10
 * 
 * {{초기 설정}}
 * result = [0 * (max-min+1)]
 */
const fs = require("fs");
const [min, max] = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split(" ")
  .map(Number);

let result = new Array(max - min + 1).fill(true);

// max보다 작은 제곱수들 모두
for (let i = 2; i ** 2 <= max; i++) {
  let k = Math.ceil(min / i ** 2); // min보다 크거나 같은 배수부터 시작해서
  for (let j = k * i ** 2; j <= max; j += i ** 2) {
    // max보다 작거나 같은 배수까지
    if (min <= j && j <= max) {
      result[j - min] = 0; // 0으로 체크
    }
  }
}

// 지워지지 않고 true로 남아있는 수들은 제곱수의 배수가 아니므로 filter함수를 통해 길이를 구한다.
const answer = result.filter((a) => a == true).length;
console.log(answer);
/**
 * 채점 결과
 * 메모리: 40160KB
 * 시간: 300ms
 * 언어: JS
 */
