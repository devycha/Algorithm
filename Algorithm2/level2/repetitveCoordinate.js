// 문제 설명
// https://programmers.co.kr/learn/courses/30/lessons/49994?language=javascript
// 나의 풀이
function solution(dirs) {
  let answer = [[0, 0, 0, 0]];
  dirs.split('').forEach((dir) => {
      let current = answer[answer.length-1]
      switch (dir) {
          case 'U':
              if (current[3] < 5) answer.push([current[2], current[3], current[2], current[3]+1])
              break;
          case 'D':
              if (current[3] > -5) answer.push([current[2], current[3], current[2], current[3]-1])
              break;
          case 'L':
              if (current[2] > -5) answer.push([current[2], current[3], current[2]-1, current[3]])
              break;
          case 'R':
              if (current[2] < 5) answer.push([current[2], current[3], current[2]+1, current[3]])
              break;    
      }
  })
  let arr2 = [];
  let count = 0;
  for (let i = 0 ; i < answer.length; i++) {
      let path = answer[i].join('');
      let reversepath = answer[i].slice(2).join('') + answer[i].slice(0,2).join('');
      if (!arr2.includes(path)) {
          arr2.push(path);
          arr2.push(reversepath);
          count += 1;
      }
  }
  return count-1;
}
// 후기: 모든 경우를 빠짐없이 생각해주어야 하기 때문에 꼼꼼하게 문제를 천천히 읽어나가자!!