function solution(n, computers) {
  let answer = 0;
  let obj = {};
  let connect = Array(n).fill(0)
  computers.forEach((computer, i) => {
      if (!obj[i]) obj[i] = [];
      computer.forEach((net, j) => {
          if (net) obj[i].push(j)
      })
  })

  for (let key in obj) {
      let stack = [];
      if (!connect[+key]) {
          stack.push(+key)
          answer++;
          while (stack.length) {
              let pop = stack.pop();
              obj[pop].forEach(con => {
                  if (con && connect[con] == 0) {
                      connect[+con] = 1;
                      stack.push(con);
                  }
              })
          }
      }
  }
  return answer;
}