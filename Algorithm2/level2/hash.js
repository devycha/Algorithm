// 문제 설명
// https://programmers.co.kr/learn/courses/30/lessons/42578?language=javascript
function solution(clothes) {
  let dic = {}
  let answer = 1;
  clothes.forEach((cloth) => {
      if (dic[cloth[1]]) dic[cloth[1]].push(cloth[0]);
      else dic[cloth[1]] = [cloth[0]];
  });
  for (let key in dic) {
     answer *= (dic[key].length+1);
  }
  return answer-1;
}
// 후기: javascript obj를 for문으로 돌 때는 in을 써야한다. 마지막 answer 에서 1을 빼준 것은 아무것도 선택하지 않은 경우를 빼준 것이다.
// 다른 사람 풀이 해석
function solution(clothes) {
  return Object.values(clothes.reduce((obj, t)=> {
      obj[t[1]] = obj[t[1]] ? obj[t[1]] + 1 : 1;
      return obj;
  } , {})).reduce((a,b)=> a*(b+1), 1)-1;    
}