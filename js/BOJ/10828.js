// 문제 링크: https://www.acmicpc.net/problem/10828
/**
 * 입력값 예시
   14
   push 1
   push 2
   top
   size
   empty
   pop
   pop
   pop
   size
   empty
   pop
   push 3
   empty
   top
 * 
 * 출력값 예시
   2
   2
   0
   2
   1
   -1
   0
   1
   -1
   0
   3
 * 
 * 파싱
 * N = 14
 * commands = [
    [ 'push', '1\r' ], [ 'push', '2\r' ],
    [ 'top\r' ],       [ 'size\r' ],
    [ 'empty\r' ],     [ 'pop\r' ],
    [ 'pop\r' ],       [ 'pop\r' ],
    [ 'size\r' ],      [ 'empty\r' ],
    [ 'pop\r' ],       [ 'push', '3\r' ],
    [ 'empty\r' ],     [ 'top' ]
  ]
 * 
 * {{초기 설정}}
 * 
 * 스택
 * arr = [] 
 * 
 * 출력을 위한 결과 값 저장
 * result = [] 
 */
const fs = require("fs");
let [N, ...commands] = fs.readFileSync("input.txt").toString().split("\n");
commands = commands.map((com) => com.split(" "));
let arr = [];
let result = [];

commands.forEach((com) => {
  switch (com[0].trim()) {
    case "push":
      arr.push(+com[1]);
      break;
    case "pop":
      result.push(arr.length ? arr.pop() : -1);
      break;
    case "size":
      result.push(arr.length);
      break;
    case "empty":
      result.push(arr.length ? 0 : 1);
      break;
    case "top":
      result.push(arr.length ? arr[arr.length - 1] : -1);
      break;
    default:
      break;
  }
});
console.log(result.join("\n"));
/**
 * 채점 결과
 * 메모리: 12980KB
 * 시간: 200ms
 * 언어: JS
 */
