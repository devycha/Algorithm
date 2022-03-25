/**
 * 입력값 예시
 * 10
 * 1 100 2 50 60 3 5 6 7 8
 *
 * 파싱
 * N = 10
 * arr = [1, 100, 2, 50, 60, 3, 5, 6, 7, 8]
 *
 * 초기 설정
 * sum = [1, 100, 2, 50, 60, 3, 5, 6, 7, 8]
 * max = arr[0] = 1
 */
const fs = require("fs");
let [N, arr] = fs.readFileSync("input.txt").toString().split("\n");
arr = arr.split(" ").map(Number);
let sum = arr.slice();
let max = arr[0];

/**
 * arr[i]이전의 수 중 arr[i]보다 작은 수들에 대하여 (arr[j] < arr[i])
 * sum[i], sum[j] + arr[i] 중 큰 값을 설정(sum[i])
 * max, sum[i]를 비교하여 max값 업데이트
 */
for (let i = 0; i < arr.length; i++) {
  let num = arr[i];
  for (let j = 0; j < i; j++) {
    if (num > arr[j]) {
      sum[i] = Math.max(sum[i], arr[i] + sum[j]);
      max = Math.max(max, sum[i]);
    }
  }
}
console.log(max);
/**
 * 메모리: 9896KB
 * 시간: 196ms
 * 언어: JS
 * 해설 참조 링크: https://blog.naver.com/y2kdj9723/222681642602
 */
