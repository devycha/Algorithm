// https://programmers.co.kr/learn/courses/30/lessons/87946?language=javascript
// https://blog.naver.com/y2kdj9723/222561112994

function solution(k, dungeons) {
  let answer = -1;
  function explore(k, dungeons, count=0) {
      let min = Infinity;
      for (let i = 0; i < dungeons.length; i++) {
          min = Math.min(dungeons[i][0], min);
      }
      if (k < min || dungeons.length == 0) return answer = Math.max(count, answer);
      for (let i = 0; i < dungeons.length; i++) {
          if (k >= dungeons[i][0]) explore(k-dungeons[i][1], [...dungeons.slice(0, i), ...dungeons.slice(i+1)], count+1);
      }
  }
  explore(k, dungeons);
  return answer;
}