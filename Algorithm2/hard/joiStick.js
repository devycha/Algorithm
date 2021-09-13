// 문제 설명 (포스팅 완료)
// https://programmers.co.kr/learn/courses/30/lessons/42860?language=javascript
// 나의 풀이
function solution(name) {
  var answer = [];
  let arr1 = name.split('').map(o => {
      let num = o.charCodeAt()-65;
      if (num >= 13) return 26-num;
      else return num;
  })
  let sum = arr1.reduce((a,b) => a+b);
  for (let i = 0; i < arr1.length; i++) {
      let reverse = [...arr1.slice(i+1), ...arr1.slice(0, i+1)];
      for (let j = 0; j < reverse.length; j++) {
          if (reverse[j]) {
              answer.push(i+reverse.slice(j).length-1);
              break;
          }
      }
  }
  for (let i = 0; i < arr1.length; i++) {
      i+1
      let reverse = [...arr1.slice(arr1.length-1-i), ...arr1.slice(0, arr1.length-1-i)].reverse();
      for (let j = 0; j < reverse.length; j++) {
          if (reverse[j]) {
              answer.push(i+reverse.slice(j).length)
              break;
          }
      }
  }
  let straight = 0;
  for (let i = 0; i < arr1.length; i++) {
      if (arr1[arr1.length-1-i]) {
          straight = arr1.slice(0, arr1.length-i).length-1;
          break;
      }
  }
  straight += sum;
  let min = answer.length ? sum + Math.min(...answer) : sum;
  return Math.min(straight, min);
}
// 후기 : 너무 어려웠다... 완전 탐색에 대해서 이제 좀 감이 잡히는 것 같다. 화이팅!!