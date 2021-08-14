// 문제 설명
// https://programmers.co.kr/learn/courses/30/lessons/81302?language=javascript#fn1
// 나의 풀이
// 후기: 모든 경우의 수를 하나씩 다 생각해주어야 하는 단순한 문제지만 놓치는게 하나라도 생기면 시간이 많이 소요되는 문제이다.
// 손으로 꼼꼼하게 다 체크하면서 풀어야겠다.
function solution(places) {
  var answer = [];
  places.forEach((room) => {
      let corona = 1;
      room.forEach((table, i) => {
          room[i] = room[i].split('')
      })
      for (let i = 0; i < 5; i++) {
          for (let j = 0; j < 5; j++) {
              if (room[i][j] == 'P') {
                  if (i > 0) {
                      if (room[i-1][j] == 'P') {
                          corona = 0;
                          break;
                      }
                      if (room[i-1][j+1] == 'P' && (room[i-1][j] != 'X' || room[i][j+1] != 'X')) {
                          corona = 0;
                          break;
                      }
                  }
                  
                  if (i < 4) {
                      if (room[i+1][j] == 'P') {
                      corona = 0;
                      break;
                      }
                      if (j < 4){
                          if (room[i+1][j+1] == 'P' && (room[i+1][j] != 'X' || room[i][j+1] != 'X')) {
                              corona = 0;
                              break;
                          }    
                      }
                  }
                  if (j < 4) {
                      if (room[i][j+1] == 'P') {
                      corona = 0;
                      break;
                      }
                      if (i < 4) {
                          if (room[i+1][j+1] == 'P' && (room[i+1][j] != 'X' || room[i][j+1] != 'X')) {
                              corona = 0;
                              break;
                          }
                      }
                  }
                  if (i > 1) {
                      if (room[i-2][j] == 'P' && room[i-1][j] != 'X') {
                      corona = 0;
                      break;
                      }
                  }
                  if (i < 3) {
                      if (room[i+2][j] == 'P' && room[i+1][j] != 'X') {
                      corona = 0;
                      break;
                      }
                  }
                  if (j < 3) {
                      if (room[i][j+2] == 'P' && room[i][j+1] != 'X') {
                      corona = 0;
                      break;
                      }    
                  }
              }
              if (corona == 0) break;
          }
      }
      answer.push(corona);
  })
  return answer;
}