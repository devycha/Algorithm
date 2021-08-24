// https://programmers.co.kr/learn/courses/30/lessons/84325?language=javascript
function solution(table, languages, preference) {
  let answer = [];
  let score = table.map(string => {
      let a = string.split(" ")
      let arr = [a[0], ...a.slice(1).reverse()];
      let sum = 0;
      for (let i = 0; i < languages.length; i++) {
          if (arr.indexOf(languages[i]) == -1) sum += 0
          else sum += preference[i]*arr.indexOf(languages[i])
      }
      return sum;
  })
  let max = Math.max(...score);
  for (let i = 0; i < score.length; i++) {
      if (score[i] == max) answer.push(table[i].split(" ")[0])
  }
  return answer.sort()[0];
}
// 후기: 천천히 문제를 이해하면서 순서대로 풀어나가는 법을 습관화 시켜야겠다.
// 다른 사람 풀이 해석
const solution = (table, languages, preference) => 
    table
        .map(e => [e.split(' ')[0], e.split(' ').slice(1).reduce((sum, f, idx) => sum + ((preference[languages.indexOf(f)] || 0) * (5 - idx)), 0)])
        .reduce((a, b) => a[1] > b[1] ? a : (a[1] === b[1] && a[0] < b[0] ? a : b))[0];