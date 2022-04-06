// 문제 링크: https://www.acmicpc.net/problem/12904
/**
 * 입력값 예시
 * B
 * ABBA
 *
 * 출력값 예시
 * 1
 *
 * 파싱
 * S = "B"
 * T = "ABBA"
 *
 * 초기 설정
 * reverseString(s: string): 문자열 뒤집기 함수
 */
const fs = require("fs");
let [S, T] = fs.readFileSync("input.txt").toString().split("\n");

// 문자열 뒤집기 함수
function reverseString(s) {
  let str = "";
  for (let i = s.length - 1; i >= 0; i--) {
    str += s[i];
  }
  return str;
}

/**
 * 두가지 연산에 대해서 T를 거꾸로 지워나간 뒤에
 * S의 길이와 T의 길이가 같아질 때 비교하는 방법을 사용
 */
while (S.length < T.length) {
  // 맨 끝자리가 A로 끝나면 맨 끝의 A를 지움
  if (T[T.length - 1] === "A") {
    T = T.substring(0, T.length - 1);
  } else {
    // 맨 끝자리가 B로 끝나면 맨 끝의 B를 지우고 문자열을 뒤집음
    T = reverseString(T.substring(0, T.length - 1));
  }
}
// S와 T의 길이가 같을 때 두 값이 같으면 1 다르면 0을 출력
console.log(S === T ? 1 : 0);
/**
 * 채점 결과
 * 메모리: 11872KB
 * 시간: 220ms
 * 언어: JS
 */
