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

function reverseString(s) {
  let str = "";
  for (let i = s.length - 1; i >= 0; i--) {
    str += s[i];
  }
  return str;
}

while (S.length < T.length) {
  if (T[T.length - 1] === "A") {
    T = T.substring(0, T.length - 1);
  } else {
    T = reverseString(T.substring(0, T.length - 1));
  }
}
console.log(S === T ? 1 : 0);
/**
 * 채점 결과
 * 메모리: 11872KB
 * 시간: 220ms
 * 언어: JS
 */
