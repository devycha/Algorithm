// 문제 설명
// Finn은 요즘 수학공부에 빠져 있습니다. 수학 공부를 하던 Finn은 자연수 n을 연속한 자연수들로 표현 하는 방법이 여러개라는 사실을 알게 되었습니다. 예를들어 15는 다음과 같이 4가지로 표현 할 수 있습니다.

// 1 + 2 + 3 + 4 + 5 = 15
// 4 + 5 + 6 = 15
// 7 + 8 = 15
// 15 = 15
// 자연수 n이 매개변수로 주어질 때, 연속된 자연수들로 n을 표현하는 방법의 수를 return하는 solution를 완성해주세요.

// 제한사항
// n은 10,000 이하의 자연수 입니다.
// 입출력 예
// n	result
// 15	4
function solution(n) {
  let answer = 0;
  for (let i = 1; i <= n; i++) {
      let j = i+1;
      let sum = i;
      while (sum < n) {
          sum += j;
          j++
      }
      if (sum == n) answer += 1;
  }
  return answer;
}


// 오답이었던 풀이 해석
function solution(n) {
  let answer = 0;
  for (let i = 1; i <= n; i++) {
      let j = i+1;
      let sum = i;
      while (sum <= n) {
          if (sum == n) answer += 1;
          sum += j;
          j++
      }
  }
  return answer;
}
// while문에서 덧셈을 한번 더하는 것이 숫자가 커지면 커질수록 시간에 더 크게 반영되기 때문에 최소한 하는게 좋은 것 같다.