// https://programmers.co.kr/learn/courses/30/lessons/12914?language=javascript
function solution(n) {
  let arr = [1, 1];
  for (let i = 2; i <= n; i++) {
      arr[i] = (arr[i-1]%1234567 + arr[i-2]%1234567)%1234567
  }  
  return arr[n]%1234567;
}

// 동적 프로그래밍을 이용한 풀이는 생각하기 정말 힘든것같다... 문제에 많이 익숙해져야겠다.