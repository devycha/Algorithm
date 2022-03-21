// 문제 설명
// https://programmers.co.kr/learn/courses/30/lessons/12981?language=javascript
// 나의 풀이
function solution(n, words) {
  var answer = [0, 0];
  for (let i = 1; i < words.length; i++) {
      let a = words[i-1]
      let b = words[i]
      if (a[a.length-1] !== b[0]) {
          return [i%n + 1, Math.floor(i/n) + 1];
      }
      if (i !== words.indexOf(words[i])) {
          return [i%n + 1, Math.floor(i/n) + 1];
      } 
  }
  return answer;
}
// 다른 사람 풀이 해석
function solution(n, words) {
  let answer = 0;
  words.reduce((prev, now, idx) => {
      answer = answer || ((words.slice(0, idx).indexOf(now) !== -1 || prev !== now[0]) ? idx : answer);
      return now[now.length-1];
  }, "")

  return answer ? [answer%n+1, Math.floor(answer/n)+1] : [0,0];
}
// prev에는 지금 단어의 끝글자를 리턴하고 answer에 아무것도 입력되지 않았을 때 
// 지금 단어 전까지 words를 slice하여 지금 단어가 포함되어있는지 여부를 indexOf를 써서 -1이 나오지 않는 경우와
// 이전 단어의 끝자리이인 prev와 현재단어의 맨 앞자리가 같지 않은 경우를 모두 판단하여
// 끝말잇기가 틀렸다면 현재 idx를 틀리지 않았다면 기존 answer의 값인 0을 반환