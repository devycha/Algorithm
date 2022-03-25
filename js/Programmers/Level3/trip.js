// 문제 설명 (포스팅 완료)
// https://programmers.co.kr/learn/courses/30/lessons/43164
// 풀이
function solution(tickets) {
  let answer = [];
  function dfs(leftTickets, tripped, now) {
      if (leftTickets.length == 0) return answer.push(tripped);
      for (let i = 0; i < leftTickets.length; i++) {
          if (leftTickets[i][0] == now) {
              dfs([...leftTickets.slice(0, i), ...leftTickets.slice(i+1)], [...tripped, leftTickets[i][1]], leftTickets[i][1])
          }
      }
  }
  for (let i = 0; i < tickets.length; i++) {
      if (tickets[i][0] == "ICN") dfs([...tickets.slice(0, i), ...tickets.slice(i+1)], tickets[i], tickets[i][1]);
  }
  return answer.sort()[0];
}