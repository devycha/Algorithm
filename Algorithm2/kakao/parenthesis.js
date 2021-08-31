// 문제 설명 (포스팅 완료)
// https://programmers.co.kr/learn/courses/30/lessons/60058?language=javascript
// 나의 풀이
function solution(p) {
  if (p == '') return '';
  else {
      let a = 0;
      let b = 0;
      for (let i = 0; i < p.length; i++) {
          if (p[i] == '(') a += 1;
          else b += 1;
          if (a == b) {
              let u = p.substr(0, i+1);
              let v = p.substr(i+1);
              if (correct(u)) return u + solution(v);
              else {
                  return `(${solution(v)})${reverse(u.substr(1,u.length-2))}`;
              }
              break;
          }
      }
  }
}

function reverse(s) {
  let string = ''
  for (let i = 0; i < s.length; i++) {
      if (s[i] == '(') string += ')';
      else string += '('
  }
  return string;
}

function correct(s) {
  let counta = 0;
  let countb = 0;
  for (let i = 0; i < s.length; i++) {
      if (s[i] == '(') counta += 1;
      else countb += 1;
      if (counta < countb) return false
  }
  return true;
}


// 다른 사람 풀이 해석
function reverse(str) {
  return str.slice(1, str.length - 1).split("").map((c) => (c === "(" ? ")" : "(")).join("");
}

// string을 배열로 바꾼 뒤에 map함수를 통해서 바꿔준 뒤에 다시 string으로 변환하는 reverse함수를 만듬(나와 다른점)

function solution(p) {
  if (p.length < 1) return "";

  let balance = 0;
  let pivot = 0;
  do { balance += p[pivot++] === "(" ? 1 : -1 } while (balance !== 0);

  const u = p.slice(0, pivot);
  const v = solution(p.slice(pivot, p.length));

  if (u[0] === "(" && u[u.length - 1] == ")") return u + v;
  else return "(" + v + ")" + reverse(u);
}
