// 문제 설명
// https://programmers.co.kr/learn/courses/30/lessons/64065?language=javascript
function solution(s) {
  var answer = [];
  let regExp = /[\{\},]/g
  let arr = s.split(regExp);
  let arr2 = [];
  let arr3 = [];
  let arr4 = [];
  arr.forEach(a => {
      if (a) arr3.push(a);
      else {
          if (arr3.length) {
              arr2.push(arr3)
              arr3 = [];
          }
      }
  })
  arr2.sort((a,b) => a.length-b.length);
  arr2.forEach(a => arr4.push(...a));
  arr4.forEach(a => {
      if (!answer.includes(parseInt(a))) answer.push(parseInt(a));
  })
  return answer;
}

// 다른 사람 풀이 해석
const solution = (s) => 
s
  .match(/(\d+,)*\d+/g)
  .map((s) => s.split(',')
  .map((n) => +n))
  .sort((a, b) => a.length - b.length)
  .reduce((a, s) => [...a, ...s.filter((n) => a.indexOf(n) === -1)], []);