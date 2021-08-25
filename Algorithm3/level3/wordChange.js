// https://programmers.co.kr/learn/courses/30/lessons/43163#
// 나의 풀이
function solution(begin,target,words) {
  let count = 0;
  let results = [];
  
  function sol(begin, target, words, count) {
      if (begin == target) {
          results.push(count);
          return count;
      } else {
          if (words.indexOf(target) == -1) return -1;
          else {
              for (let i = 0; i < words.length; i++) {
                  if (check(begin, words[i])) sol(words[i], target, [...words.slice(0, i), ...words.slice(i+1)], count+1);
              }
          }
      }
  }
  sol(begin, target, words, count);
  return results.length ? Math.min(...results) : 0;
}

function check(string1, string2) {
  let count = 0; 
  if (string1 == string2) return true;
  else {
      for (let i = 0; i < string1.length; i++) {
          if (string1[i] !== string2[i]) count++;
          if (count >= 2) return false
      }
  }
  return true;
}

// 후기 : 처음으로 BFS/DFS를 깔끔하게 풀어낸 것 같아서 뿌듯하다... 