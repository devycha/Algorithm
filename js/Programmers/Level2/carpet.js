// 문제 설명
// https://programmers.co.kr/learn/courses/30/lessons/42842?language=javascript

function solution(brown, yellow) {
  var answer = [];
  for (let i = 1; i <= Math.sqrt(yellow); i++) {
      let nature = (yellow/i)%1;
      let right = 2*(i+2) + 2*(yellow/i);
      if (!nature && (right == brown)) {
          answer.push(yellow/i + 2);
          answer.push(i + 2)
          break;
      }
  }
  return answer;
}

// 다른 사람 풀이 해석
function solution(brown, yellow) {
    var xpy=brown/2+2;
    for(var a=1;a<xpy;a++) if(a*(xpy-a)==brown+yellow)  return [Math.max(a,xpy-a),Math.min(a,xpy-a)];
}