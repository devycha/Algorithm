// https://programmers.co.kr/learn/courses/30/lessons/43165?language=javascript
// 나의 풀이
function solution(numbers, target) {
  let answer = [[numbers[0], -numbers[0]]];
  for (let i = 1; i < numbers.length; i++) {
      let results = [];
      for (let j = 0; j < answer[i-1].length; j++) {
          results.push(answer[i-1][j] + numbers[i]);
          results.push(answer[i-1][j] - numbers[i]);
      }
      answer.push(results);
  }
  return answer[answer.length-1].filter(num => num == target).length;
}

// 재귀적 풀이
function solution(numbers, target) {
  let answer = 0;
  const search = (index , now) => {
      if(index < numbers.length) {
          search(index+1, now + numbers[index]);
          search(index+1, now - numbers[index]);
      } else {
          if(now === target) {
              answer++;
          }
      }
  }
  search(0, 0);
  return answer;
}
// BFS를 이용한 재귀적 풀이를 이용하면 시간소모를 줄일 수 있다.