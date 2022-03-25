// 문제 설명 (포스팅 완료)
// https://programmers.co.kr/learn/courses/30/lessons/17680?language=javascript
function solution(cacheSize, cities) {
  let answer = 0;
  let cache = [];
  for (let i = 0; i < cities.length; i++) {
      if (!cache.includes(cities[i].toUpperCase())) {
          if (cacheSize == 0) {
              return cities.length*5;
          }
          if (cache.length < cacheSize) cache.push(cities[i].toUpperCase())
          else {
              cache.shift();
              cache.push(cities[i].toUpperCase())
          }
          answer += 5;
      } else {
          let pop = cache.indexOf(cities[i].toUpperCase());
          cache.splice(pop, 1);
          cache.push(cities[i].toUpperCase());
          answer++;
      }
  }
  return answer;
}

// 후기: cacheSize가 0인 경우를 예외로 잡지 않고 시작해서 시간이 좀더 걸렸다. 예외상황이 있는지 시작 전에 체크하는 습관을 들이자.
