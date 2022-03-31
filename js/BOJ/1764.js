// 문제 링크: https://www.acmicpc.net/problem/1764
/**
 * 입력값 예시
 * 3 4
 * ohhenrie
 * charlie
 * baesangwook
 * obama
 * baesangwook
 * ohhenrie
 * clinton
 *
 * 출력값 예시
 * 2
 * baesangwook
 * ohhenrie
 *
 * 파싱
 * N = 3, M = 4
 * arr = [
    'baesangwook',
    'baesangwook',
    'charlie',
    'clinton',
    'obama',
    'ohhenrie',
    'ohhenrie'
  ]
 * 
 * 초기 설정
 * result = []
 */
const fs = require("fs");
let [NM, ...arr] = fs.readFileSync("input.txt").toString().split("\n");
arr = arr.sort(); // 사전순 출력을 위해 미리 사전순으로 정렬
let result = [];
for (let i = 0; i < arr.length - 1; i++) {
  if (arr[i] === arr[i + 1]) {
    // 사전순으로 정렬했을때 바로 다음 인덱스의 값과 같은 경우에 듣보잡이름이 나오기 때문에 result에 푸쉬
    result.push(arr[i]);
  }
}

console.log(result.length + "\n" + result.join("\n"));
/**
 * 채점 결과
 * 메모리: 28556KB
 * 시간: 248ms
 * 언어: JS
 */
