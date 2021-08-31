// 문제 설명 (포스팅 완료)
// https://programmers.co.kr/learn/courses/30/lessons/64064?language=javascript
// 나의 풀이
function solution(user_id, banned_id) {
  let banObject = {};
  let result = [];
  
  for (let i = 0; i < banned_id.length; i++) {
      let count = 0;
      let arr = [];
      for (let j = 0; j < user_id.length; j++) {
          if (check(user_id[j], banned_id[i])) {
              arr.push(user_id[j]);
          }
      }
      if (!banObject[banned_id[i]]) banObject[banned_id[i]] = arr;
  }
  function bfs(obj, arr1, arr2=[], index) {
      if (index == arr1.length) return result.push(arr2);
      else {
          for (let i = 0; i < obj[arr1[index]].length; i++) {
              if (!arr2.includes(obj[arr1[index]][i])) {
                  bfs(obj, arr1, [...arr2, obj[arr1[index]][i]], index+1)
              }
          }
      }
  }
  bfs(banObject, banned_id, [], 0);

  let answer = [];
  result.forEach((banList) => {
      let sortedBanString = banList.sort().join("");
      if (!answer.includes(sortedBanString)) answer.push(sortedBanString);
  })
  
  return answer.length;
}

function check(string1, string2) {
  let length = string1.length > string2.length ? string1.length : string2.length;
  for (let i = 0; i < length; i++) {
      if (string2[i] == "*") {
          if (string1[i]) continue;
          else return false;
      } else {
          if (string1[i] !== string2[i]) return false;
      }
  }
  return true;
}