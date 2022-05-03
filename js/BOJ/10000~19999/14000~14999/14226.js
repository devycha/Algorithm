/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/14226
 * 
 * 문제
 * 영선이는 매우 기쁘기 때문에, 효빈이에게 스마일 이모티콘을 S개 보내려고 한다.
 * 영선이는 이미 화면에 이모티콘 1개를 입력했다. 이제, 다음과 같은 3가지 연산만 사용해서 이모티콘을 S개 만들어 보려고 한다.
 * 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
 * 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
 * 화면에 있는 이모티콘 중 하나를 삭제한다.
 * 모든 연산은 1초가 걸린다. 또, 클립보드에 이모티콘을 복사하면 이전에 클립보드에 있던 내용은 덮어쓰기가 된다. 클립보드가 비어있는 상태에는 붙여넣기를 할 수 없으며, 일부만 클립보드에 복사할 수는 없다. 또한, 클립보드에 있는 이모티콘 중 일부를 삭제할 수 없다. 화면에 이모티콘을 붙여넣기 하면, 클립보드에 있는 이모티콘의 개수가 화면에 추가된다.
 * 영선이가 S개의 이모티콘을 화면에 만드는데 걸리는 시간의 최솟값을 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 S (2 ≤ S ≤ 1000) 가 주어진다.
 * 
    2
 * 
 * 출력
 * 첫째 줄에 이모티콘을 S개 만들기 위해 필요한 시간의 최솟값을 출력한다.
 * 
    2
 * 
 * 파싱
 * s = 2
 * 
 * {{초기 설정}}
 * 체크리스트 객체 obj = {}
 * BFS용 queue = [[1, 0, 0]] => [[current, clipboard, time], ...]
 * BFS용 포인터 i = 0
 * 최소시간 result = 0
 */
const fs = require("fs");
const s = +fs.readFileSync("input.txt").toString().trim();

let obj = {};
let queue = [[1, 0, 0]];
obj[1] = 0;
let i = 0;
let result = 0;

while (i < queue.length) {
  let [current, clipboard, time] = queue[i++];

  if (!obj[current]) {
    obj[current] = {};
  }

  // 이모티콘이 S개가 됐을 때 break
  if (current == s) {
    result = time;
    break;
  }

  /**
   * 클립보드에 이모티콘이 들어있고
   * 현재 화면의 이모티콘의 개수와 클립보드의 이모티콘의 개수가 2개 이상일 때
   *
   * 화면 이모티콘의 개수와 그때의 클립보드의 이모티콘의 개수에 대한 2차원 객체에 값을 지정하면서
   * 체크리스트에 저장
   *
   * obj[화면 이모티콘의 개수]가 없다면 새로운 객체를 생성
   * obj[화면 이모티콘의 개수][클립보드의 개수]의 값이 없을 때
   * 해당 위치를 방문해주면서 queue에 정보를 표시(시간 + 1)
   */
  if (clipboard !== 0 && current + clipboard >= 2) {
    if (!obj[current + clipboard]) {
      obj[current + clipboard] = {};
    }
    if (!obj[current + clipboard][clipboard]) {
      obj[current + clipboard][clipboard] = 1;
      queue.push([current + clipboard, clipboard, time + 1]);
    }
  }

  if (clipboard != current) {
    queue.push([current, current, time + 1]);
  }

  if (!obj[current - 1] && current >= 3) {
    obj[current - 1] = 1;
    queue.push([current - 1, clipboard, time + 1]);
  }
}

console.log(result);
/**
 * 채점 결과
 * 메모리: 29520KB
 * 시간: 236ms
 * 언어: JS
 */
