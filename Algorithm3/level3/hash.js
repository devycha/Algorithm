function solution(genres, plays) {
  // https://programmers.co.kr/learn/courses/30/lessons/42579?language=javascript

  let answer = [];
  let bestgenre = {};
  for (let i = 0; i < genres.length; i++) {
      if (bestgenre[genres[i]]) bestgenre[genres[i]]["play"] += plays[i];
      else {
          bestgenre[genres[i]] = {"play": plays[i]};
      }
      bestgenre[genres[i]][`${i}`] = plays[i]
  }
  let keys = Object.keys(bestgenre);
  keys.sort((a, b) => bestgenre[b]["play"] - bestgenre[a]["play"])
  keys.forEach((genre) => {
      let key = Object.keys(bestgenre[genre]);
      let count = 0;
      key.sort((a, b) => bestgenre[genre][b] - bestgenre[genre][a])
      key.forEach(song => {
          if (song !== 'play' && count < 2) {
              answer.push(+song);
              count++;
          }
      })
  })
  return answer;
}

// hash를 정렬하는 방법은 따로 존재하지 않고 hahs의 키들을 배열로 받아와서
// hash를 통해 읽은 값을 통해 키들의 배열을 정렬하는 방법이 있다.