// 문제 설명
// 자연수 n이 주어졌을 때, n의 다음 큰 숫자는 다음과 같이 정의 합니다.

// 조건 1. n의 다음 큰 숫자는 n보다 큰 자연수 입니다.
// 조건 2. n의 다음 큰 숫자와 n은 2진수로 변환했을 때 1의 갯수가 같습니다.
// 조건 3. n의 다음 큰 숫자는 조건 1, 2를 만족하는 수 중 가장 작은 수 입니다.
// 예를 들어서 78(1001110)의 다음 큰 숫자는 83(1010011)입니다.

// 자연수 n이 매개변수로 주어질 때, n의 다음 큰 숫자를 return 하는 solution 함수를 완성해주세요.

// 제한 사항
// n은 1,000,000 이하의 자연수 입니다.
// 입출력 예
// n	result
// 78	83
// 15	23
// 나의 풀이
function solution(n) {
  let num = n.toString(2).split('');
  let test = (2**num.length-1).toString(2).split('');
  let c = count(num,test);
  while (true) {
      n++;
      let number = n.toString(2).split('');
      let tester = (2**number.length-1).toString(2).split('');
      let counter = count(number, tester)
      if (counter == c) {
          return parseInt(number.join(''),2);  // 굳이 다시 바꿔주는 과정이 아닌 n을 리턴했으면 됐는데 생각이 짧았다.
      }
  }
}

function count(arr1, arr2) {
  let answer = 0;
  for (let i = 0 ; i < arr1.length; i++) {
      if (parseInt(arr1[i]) && parseInt(arr2[i])) answer++;
  }
  return answer;
}
// 후기: 10진수를 2진수로 바꾸는 것도 parseInt(숫자, 2)
// 2진수를 10진수로 바꾸는 것도 parseInt(숫자,2) 이다.
// 다른 사람 풀이 해석
function solution(n,a=n+1) {
  return n.toString(2).match(/1/g).length == a.toString(2).match(/1/g).length ? a : solution(n,a+1);
}
// 1의 갯수를 셀 때 정규표현식을 활용한 match함수를 통해 쉽게 해결한 모습이 인상적이다.
// 