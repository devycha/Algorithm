// https://programmers.co.kr/learn/courses/30/lessons/12904?language=javascript#
// 실패
function solution(s) {
  let answer = 0;
  for (let i = 1; i <= s.length/2; i++) {
      // console.log(s[i])
      for (let j = 1; (j <= i); j++) {
          let string = s.substr(i-j, 2*j+1)
          console.log(string, answer);
          if (isPalindrom(string)) {
              answer = Math.max(2*j+1, answer);
          }
      }
  }
  return answer;
}

function isPalindrom(string) {
  let start = 0;
  let end = string.length-1;
  while (start <= end) {
      if (string[start] !== string[end]) {
          return false;
      }
      start++;
      end--;
  }
  return true;
}

// 성공
function solution(s) {
  for (let i = s.length; i > 0; i--) {
      for (let j = 0; j <= s.length-i; j++) {
          let string = s.substr(j, i)
          if (isPalindrom(string)) return string.length
      }
  }
  return -1;
}

function isPalindrom(string) {
  let start = 0;
  let end = string.length-1;
  while (start < end) {
      if (string[start] !== string[end]) {
          return false;
      }
      start++;
      end--;
  }
  return true;
}