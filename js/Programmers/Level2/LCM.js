// 문제 설명
// 두 수의 최소공배수(Least Common Multiple)란 입력된 두 수의 배수 중 공통이 되는 가장 작은 숫자를 의미합니다. 예를 들어 2와 7의 최소공배수는 14가 됩니다. 정의를 확장해서, n개의 수의 최소공배수는 n 개의 수들의 배수 중 공통이 되는 가장 작은 숫자가 됩니다. n개의 숫자를 담은 배열 arr이 입력되었을 때 이 수들의 최소공배수를 반환하는 함수, solution을 완성해 주세요.

// 제한 사항
// arr은 길이 1이상, 15이하인 배열입니다.
// arr의 원소는 100 이하인 자연수입니다.
// 입출력 예
// arr	result
// [2,6,8,14]	168
// [1,2,3]	6

function solution(arr) {
  let answer = 0;
  while (arr.length) {
      if (answer) {
          answer = LCM(answer, arr.pop());
      } else {
          answer = arr.pop()
      }
  }
  return answer;
}
function LCM(a,b) {
  let i = 1;
  if (a > b) {
      while ((a*i) % b !== 0) {
          i++;
      }
      return a*i;
  }
  if (a < b) {
      while((b*i) % a !== 0) {
          i++;
      }
      return b*i;
  }
  else {
      return a;
  }
}
// 다른 사람 풀이 해석
function nlcm(num) {
  return num.reduce((a,b) => a*b / gcd(a,b))  
}
function gcd(a, b) {    
  return a % b ? gcd(b, a%b) : b
}

// 2
var gcd = (a, b) => (b % a === 0) ? a : gcd(b % a, a);
var lcm = (a, b) => a * b / gcd(a, b);
var nlcm = num => num.sort().reduce((a, b) => lcm(a, b));

// 해석: 
// A = a*b
// B = a*c
// A*B = a*a*b*c
// gcd(A,B) = a;
// LCM(A,B) = A*B / gcd(A,B)
// 두 수의 곱셈을 최대공약수로 나누면 최소공배수가 된다는 점을 이용