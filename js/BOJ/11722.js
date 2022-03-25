// 문제 링크: https://www.acmicpc.net/problem/11722
/**
 * 입력값 예시
 * 6
 * 10 30 10 20 20 10
 *
 * 파싱
 * N = 6
 * arr = [10, 30, 10, 20, 20, 10]
 *
 * 초기 설정
 * count = [1, 1, 1, 1, 1, 1]
 * max = 1
 */
const fs = require("fs");
let [N, arr] = fs.readFileSync("input.txt").toString().split("\n");
arr = arr.split(" ").map(Number);
let count = new Array(+N).fill(1);
let max = 1;

/**
 * 주어진 arr의 뒤에서부터 시작한다.
 * arr[i] 이후의 수 중 arr[i]보다 작은 수들에 대하여 (arr[j] < arr[i])
 * count[i], count[j] + 1 의 대소비교 후 큰 값을 count[i]값으로 지정
 * max값 업데이트
 */
for (let i = arr.length - 2; i >= 0; i--) {
  for (let j = arr.length - 1; j > i; j--) {
    if (arr[j] < arr[i]) {
      count[i] = Math.max(count[i], count[j] + 1);
      max = Math.max(max, count[i]);
    }
  }
}
console.log(max);
/**
 * 메모리: 9676KB
 * 시간: 220ms
 * 언어: JS
 * 해설 참조 링크: https://blog.naver.com/y2kdj9723/222681642602
 */
