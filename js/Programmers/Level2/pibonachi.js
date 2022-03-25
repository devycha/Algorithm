// 문제 설명
// 피보나치 수는 F(0) = 0, F(1) = 1일 때, 1 이상의 n에 대하여 F(n) = F(n-1) + F(n-2) 가 적용되는 수 입니다.

// 예를들어

// F(2) = F(0) + F(1) = 0 + 1 = 1
// F(3) = F(1) + F(2) = 1 + 1 = 2
// F(4) = F(2) + F(3) = 1 + 2 = 3
// F(5) = F(3) + F(4) = 2 + 3 = 5
// 와 같이 이어집니다.

// 2 이상의 n이 입력되었을 때, n번째 피보나치 수를 1234567으로 나눈 나머지를 리턴하는 함수, solution을 완성해 주세요.

// 제한 사항
// * n은 1이상, 100000이하인 자연수입니다.

// 입출력 예
// n	return
// 3	2
// 5	5
function solution(n) {
  let obj = {
      '0' : 0,
      '1' : 1,
  }
  for (let i = 2; i <= n; i++) {
      obj[i] = obj[i-1]%1234567 + obj[i-2]%1234567;
  }
  return obj[n]%1234567;
}
// 후기: (A+B)%C = ((A%C)+(B%C))%C 를 이용하여야 오류가 안나고 잘 실행된다. 왜냐하면 
//      자바스크립트는 Number.MAX_SAFE_INTEGER라는 것이 있다. 9007199254740991가 넘어가게되면 오류가 되므로 계산 중간중간에 나머지를 구해서 더해주는 방법을 사용하는 것이 좋다.

function fib(n, memo = [0, 1, 1]) {
  if (memo[n]) return memo[n]
  let num = fib(n-1, memo) + fib(n-2, memo);
  memo[n] = num;
  return num;
}

function fib2(n) {
  let fibonachi = [0, 1, 1];
  if (n <= 2) return fibonachi[n]
  for (let i = 3; i <= n; i++) {
    fibonachi[i] = fibonachi[i-1] + fibonachi[i-2];
  }
  return fibonachi[n]
}

console.log(fib2(7))