// 문제 설명
// https://programmers.co.kr/learn/courses/30/lessons/17683?language=javascript
// 나의 풀이
function solution(m, musicinfos) {
  let melody = melodyParse(m, m.split('#').join("").length);
  let musicInfo = [];
  for (let i = 0 ; i < musicinfos.length; i++) {
      let arr = musicinfos[i].split(',');
      let hour = (parseInt(arr[1].split(':')[0]) - parseInt(arr[0].split(':')[0]))*60
      let minute = parseInt(arr[1].split(':')[1]) - parseInt(arr[0].split(':')[1])
      let time = hour+minute
      musicInfo.push([time, arr[2], melodyParse(arr[3], time)])
  }
  let sameMelody = [];
  for (let j = 0 ; j < musicInfo.length; j++) {
      if (musicInfo[j][2].match(melody)) sameMelody.push([musicInfo[j][0], musicInfo[j][1]])
  }
  let maxTime = [[0, '']];
  if (sameMelody.length == 0) {
      return '(None)'
  } else if (sameMelody.length == 1) {
      return sameMelody[0][1]
  } else {
      for (let i = 0; i < sameMelody.length; i++) {
          maxTime = maxTime[0] >= sameMelody[i][0] ? maxTime : sameMelody[i];
      }
      return maxTime[1];
  }
}
function melodyParse(string, time) {
  let melody = [];
  let count = 0;
  let i = 0;
  while (count < time)  {
      if (string[i%string.length] !== '#') {
          if (string[(i+1)%string.length] == '#' && (i+1)%string.length) {
              melody.push(string[i%string.length].toLowerCase());
              count++;
          } else {
              melody.push(string[i%string.length]);
              count++;
          }
      }
      i++;
  }
  melody = melody.join('');
  return melody;
}
// 후기: 입력 조건의 문자열 길이가 매우 긴 경우에 조그마한 실수 하나 때문에 많은 것이 틀릴 수 있기 떄문에
// 문제를 천천히 잘 읽어가면서 풀어야겠다 특히 대소문자나 길이에 유의해서 풀어야겠다.
// 다른 사람 풀이 해석
const solution = (m, musicInfos) => {
  let answer = '';

  musicInfos = musicInfos.map(e => {
      let eArr = e.split(',');
      let timeDiff = (new Date(`1970-01-01 ${eArr[1]}:00`) - new Date(`1970-01-01 ${eArr[0]}:00`)) / 60000;
      let melody = eArr[3].replace(/[A-Z]#/g,m => m[0].toLowerCase());
      melody = melody.repeat(Math.ceil(timeDiff / melody.length)).substr(0, timeDiff);
      return `${timeDiff},${eArr[2]},${melody}`;
  });

  musicInfos.sort((a,b) => b.split(',')[0] - a.split(',')[0]);

  answer = musicInfos.filter(e => e.split(',')[2].indexOf(m.replace(/[A-Z]#/g,m => m[0].toLowerCase())) != -1);

  return answer.length == 0 ? '(None)' : answer[0].split(',')[1];
}