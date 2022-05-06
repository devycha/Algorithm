/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2133
 * 
 * 시간제한: 2초
 * 메모리제한: 128MB
 * 
 * 문제
 * 3×N 크기의 벽을 2×1, 1×2 크기의 타일로 채우는 경우의 수를 구해보자.
 * 
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 30)이 주어진다.
 * 
    2
 * 
 * 출력
 * 첫째 줄에 경우의 수를 출력한다.
 * 
    3
 * 
 */
const fs = require("fs");
let n = +fs.readFileSync("input.txt").toString().trim();
let arr = new Array(n + 1).fill(0);
arr[0] = 1;
arr[2] = 3;

for (let i = 4; i <= n; i += 2) {
  // 2칸(3) + (N-2)칸
  arr[i] += 3 * arr[i - 2];
  for (let j = 4; j <= i; j += 2) {
    // 4칸(2) + (N-4)칸 , 6칸(2) + (N-6)칸, 8칸(2) + (N-8)칸, ...
    arr[i] += 2 * arr[i - j];
  }
}

console.log(arr[n]);
/**
 * 채점 결과
 * 메모리: 9328KB
 * 시간: 120ms
 * 언어: JS
 */
