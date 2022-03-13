// 문제 설명
// https://programmers.co.kr/learn/courses/30/lessons/17684?language=javascript
// 나의 풀이
function solution(msg) {
  var answer = [];
  let obj = {};
  let num = 1;
  for (let i = 65; i <= 90; i++) {
      obj[String.fromCharCode(i)] = num++;
  }
  let arr = msg.split('')
  let j = 0;
  let w = arr[j]
  let c = ''
  while (true) {
      if (obj[w+arr[j+1]]) {
          w += arr[j+1]
          j++;
      }
      else {
          c = arr[j+1]
          answer.push(obj[w])
          obj[w+arr[j+1]] = num++;
          w = arr[j+1];
          j = j+1
      }
      if (j >= arr.length) {
          break;
      }
  }            
  return answer;
}
// 후기: 잔실수를 줄여서 구현력을 높이자!