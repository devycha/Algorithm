// 문제 설명
// https://programmers.co.kr/learn/courses/30/lessons/83201?language=javascript
// 나의 풀이
function solution(scores) {
  let answer = '';
  
  for (let j = 0; j < scores[0].length; j++) {
      let score = [];
      for (let i = 0; i < scores.length; i++) {
          score.push(scores[i][j]);
      }
      if (score[j] == Math.max(...score) || score[j] == Math.min(...score)) {
          let pop = score.splice(j, 1);
          if (pop == Math.max(...score) || pop == Math.min(...score)) score.push(pop[0]);
      }
      let avg = score.reduce((sum, el) => sum + el) / score.length;
      answer += avg >= 90 ? 'A' : avg >= 80 ? 'B' : avg >= 70 ? 'C' : avg >= 50 ? 'D' : 'F';
  }
  
  return answer;
}

// 다른 사람 풀이 해석
let solution = scores =>
    (scores[0].map((_, c) => scores.map(r => r[c])))
        .map((s, i) => [...s.splice(i, 1), s])
        .map(([m, s]) => Math.min(...s) <= m && m <= Math.max(...s) ? [m, ...s] : s)
        .map(s => "FDDCBAA"[Math.max(parseInt(s.reduce((a, c) => a + c) / s.length / 10) - 4, 0)])
        .join("")