// 실패
function solution(n, results) {
  var answer = 0;
  let lose = {};
  let win = {};
  results.forEach(game => {
      if (!lose[game[1]]) lose[game[1]] = [];
      lose[game[1]].push(game[0])
      
      if (!win[game[0]]) win[game[0]] = [];
      win[game[0]].push(game[1])
  })
  for (let i = 1; i <= n; i++) {
      let willLose = [];
      let willWin = [];
      let rank = Array(n).fill(0)
      let stack = [i];
      let present;
      while (stack.length) {
          present = stack.pop();
          rank[present-1] = 1;
          if (lose[present]) {
              willLose.push(...lose[present])
              stack.push(...lose[present])    
          }
      }
      stack = [i];
      while (stack.length) {
          present = stack.pop();
          rank[present-1] = 1;
          if (win[present]) {
              willWin.push(...win[present])
              stack.push(...win[present])    
          }
      }
      // console.log(willLose)
      // console.log(willWin)
      // console.log(rank)
      for (let j = 0; j < rank.length; j++) {
          if (rank[j] == 0) break;
          if (j == rank.length-1) answer++;
      }        
  }
  return answer;
}

// 성공
function solution(n, results) {
  var answer = 0;
  let lose = {};
  let win = {};
  results.forEach(game => {
      if (!lose[game[1]]) lose[game[1]] = [];
      lose[game[1]].push(game[0])
      
      if (!win[game[0]]) win[game[0]] = [];
      win[game[0]].push(game[1])
  })
  for (let i = 1; i <= n; i++) {
      let willLose = [];
      let willWin = [];
      let rank = Array(n).fill(0)
      let stack = [i];
      let present;
      while (stack.length) {
          present = stack.pop();
          if (lose[present]) {
              lose[present].forEach(l => {
                  if (!rank[+l-1]) {
                      rank[+l-1] = 1;
                      willLose.push(l)
                      stack.push(l)
                  }
              })
          }
          rank[present-1] = 1;
      }
      stack = [i];
      while (stack.length) {
          present = stack.pop();
          if (win[present]) {
              win[present].forEach(w => {
                  if (!rank[+w-1]) {
                      rank[+w-1] = 1
                      willWin.push(w)
                      stack.push(w)
                  }
              })
          }
      }
      if (willWin.length + willLose.length == n-1) answer++;
 
  }
  return answer;
}