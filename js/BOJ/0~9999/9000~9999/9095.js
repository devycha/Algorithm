/**
 * 문제 설명
 * 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 7가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.
 * 1+1+1+1
 * 1+1+2
 * 1+2+1
 * 2+1+1
 * 2+2
 * 1+3
 * 3+1
 * 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 11보다 작다.
 * 
 * 입력값 예시
    3
    4
    7
    10
 * 
 * 출력
 * 각 테스트 케이스마다, n을 1, 2, 3의 합으로 나타내는 방법의 수를 출력한다.
 * 
 * 출력값 예시
    7
    44
    274
 * 
 * 파싱
 * T = 3
 * arr = [4, 7, 10]
 * 
 * {{초기 설정}}
 * 테스트케이스 중 최고값
 * max = 0
 * 
 * 결과 출력용 배열
 * result = []
 * 
 * 1,2,3의 합으로 나타내는 방법의 수를 담는 memoization 배열
 * sums = [0, 1, 2, 4, ...0 * (max-3)]
 */
const fs = require("fs");
let max = 0;
let [T, ...arr] = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n")
  .map(b => {
    if (+b > max) {
      max = +b;
    }
    return +b;
  });
let result = [];

let sums = new Array(max + 1).fill(0);
(sums[1] = 1), (sums[2] = 2), (sums[3] = 4);
/**
 * i는
 * i-1의 경우의수 + 1
 * i-2의 경우의수 + 1
 * i-3의 경우의수 + 1
 * 를 갖고 있으므로...
 */
for (let i = 4; i <= max; i++) {
  sums[i] = sums[i - 1] + sums[i - 2] + sums[i - 3];
}

for (let i = 0; i < arr.length; i++) {
  result.push(sums[arr[i]]);
}
console.log(result.join("\n"));
/**
 * 채점 결과
 * 메모리: 9332KB
 * 시간: 124ms
 * 언어: JS
 */
