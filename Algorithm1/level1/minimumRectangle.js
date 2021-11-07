// https://programmers.co.kr/learn/courses/30/lessons/86491?language=javascript
// https://blog.naver.com/y2kdj9723/222561085715

function solution(sizes) {
  let verticalMax = 0;
  let horizontalMax = 0;
  sizes.map(card => {
      if (card[0] < card[1]) {
          horizontalMax = Math.max(horizontalMax, card[1])
          verticalMax = Math.max(verticalMax, card[0])
          return [card[1], card[0]]
      }
      horizontalMax = Math.max(horizontalMax, card[0])
      verticalMax = Math.max(verticalMax, card[1])
      return [card[0], card[1]]
  })
  return verticalMax * horizontalMax;
}