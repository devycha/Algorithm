// https://programmers.co.kr/learn/courses/30/lessons/12900?language=javascript

function solution(n) {
  let arr = [1, 1];
  for (let i = 2; i <= n; i++) {
      arr[i] = (arr[i-1]%1000000007 + arr[i-2]%1000000007)%1000000007;
  }
  return arr[n]%1000000007;
}
