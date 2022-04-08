// 문제 링크: https://www.acmicpc.net/problem/17216
/**
 * 문제 설명
 * 수열 A가 주어졌을 때, 그 수열의 감소 부분 수열 중에서 합이 가장 큰 것을 구하는 프로그램을 작성하시오.
 * 예를 들어, 수열 A = {1, 100, 2, 50, 60, 8, 7, 3, 6, 5} 인 경우에 합이 가장 큰 감소 부분 수열은 A = {1, 100, 2, 50, 60, 8, 7, 3, 6, 5} 이고, 합은 186이다.
 * 
 * 입력
 * 첫째 줄에 수열 A의 크기 N(1 ≤ N ≤ 1000)이 주어진다.
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다.(1 ≤ Ai ≤ 1,000)
 * 
 * 입력값 예시
    10
    1 100 2 50 60 8 7 3 6 5
 * 
 * 출력
 * 첫째 줄에 수열 A의 합이 가장 큰 감소 부분 수열의 합을 출력한다.
 * 
 * 출력값 예시
    186
 * 
 * 파싱
 * N = 10
 * A = [1, 100, 2, 50, 60, 8, 7, 3, 6, 5]
 * 
 * {{초기 설정}}
 * 수열 A중 최고값(가장 큰 감소하는 수열의 합 중 최솟값)
 * max = Math.max(...A) 
 * 
 * 감소하는 수열의 합 중 최고값을 나타낸 배열
 * sums = [...A]
 */
const fs = require("fs");
let [N, A] = fs.readFileSync("input.txt").toString().trim().split("\n");
let max = A[0];
A = A.split(" ").map(num => {
  if (+num > max) {
    max = +num;
  }
  return +num;
});
let sums = A.slice();

for (let i = 0; i < A.length; i++) {
  for (let j = 0; j < i; j++) {
    if (A[j] > A[i]) {
      sums[i] = Math.max(sums[i], sums[j] + A[i]);
      max = Math.max(max, sums[i]);
    }
  }
}

console.log(max);
/**
 * 채점 결과
 * 메모리: 9652KB
 * 시간: 188ms
 * 언어: JS
 */
