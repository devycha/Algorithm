// 정수 배열 numbers가 주어집니다. numbers에서 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를 배열에 오름차순으로 담아 return 하도록 solution 함수를 완성해주세요.
// 나의 풀이
function solution(numbers) {
  var answer = [];
  numbers.forEach((num, i) => {
      for (let j = i+1; j < numbers.length; j++) {
          let sum = num + numbers[j];
          if(!answer.includes(sum)) {
              answer.push(sum);
          }
      }
  })
  return answer.sort((a,b) => a-b);
}
// 후기: primitive 함수 Set을 알고있었는데 정확한 사용법을 숙지하지 못했다. 복습을 철저히 해야겠다.
// 다른 사람 풀이 해석
function solution(numbers) {
  const answer = [];

  for (let i = 0; i < numbers.length; i++) {
    for (let j = 1; j < numbers.length; j++) {
      if (i === j) continue;
      answer.push(numbers[i] + numbers[j]);
    }
  }

  return [...new Set(answer)].sort(((a, b) => a - b));
}
