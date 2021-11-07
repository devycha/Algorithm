// https://programmers.co.kr/learn/courses/30/lessons/87389?language=javascript
// https://blog.naver.com/y2kdj9723/222561089554

function solution(n) {
  for (let i = 2; i <= n-1; i++) {
      if ((n-1) % i == 0) return i
  }
}