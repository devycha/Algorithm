/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2251
 * 
 * 시간제한 2초
 * 메모리제한: 128MB
 * 
 * 문제
 * 각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다. 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다. 이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 이 과정에서 손실되는 물은 없다고 가정한다.
 * 이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다. 첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 세 정수 A, B, C가 주어진다.
 * 
    8 9 10
 * 
 * 출력
 * 첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.
 * 
    1 2 8 9 10
 * 
 * 파싱
 * limit = [8, 9, 10]
 * 
 * {{초기 설정}}
 * 처음 상태: C에만 가득차있음
 * current = [0, 0, 10]
 * 
 * 물통에 들어있는 양의 모든 조합을 저장하는 객체
 * obj = {}
 * 
 * A의 물통의 값이 0일 때만 C의 물통의 값을 저장하는 객체
 * answer = {}
 * 
 * 문제풀이관련: 모든 물통의 케이스마다 6가지방향으로 물을 붓는 모든 경우를 구한다.
 * A => B, A => C, B => A, B => C, C => A, C => B
 * 
 * 예시
 * current = [0, 0, 10]
 *
 * 3 -> 1 : 8 0 2
 * 3 -> 2 : 0 9 1
 */

/**
 * limit  : 8 9 10
 * 3 -> 1 : 8 0 2
 *
 * 1 -> 2 : 0 8 2
 * 1 -> 3 : 0 0 10
 * 2 -> 1 : x
 * 2 -> 3 : x
 * 3 -> 1 : x
 * 3 -> 2 : 8 2 0
 */

/**
 * 3 -> 2 : 0 9 1
 *
 * 1 -> 2 : x
 * 1 -> 3 : x
 * 2 -> 1 : 8 1 1
 * 2 -> 3 : 0 0 10
 * 3 -> 1 : 1 9 0
 * 3 -> 2 : x
 */

// 파싱
const fs = require("fs");
let limit = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split(" ")
  .map(Number);

// 초기 설정
const current = [0, 0, limit[2]]; // 현재 상태: C에 가득 차있음.
let obj = {}; // 물통에 들어있는 양의 모든 조합을 저장하는 객체
let answer = {}; // A값이 0일 때만 C의 경우를 중복값 없이 저장하는 객체
dfs(current); // DFS 수행

// 문제 풀이
let keys = Object.keys(obj); // 모든 물통에 들어있는 양의 모든 조합을 배열에 저장
keys.forEach(key => {
  // 조합마다
  let [A, B, C] = key.split(","); // ,를 이용하여 join하였으므로 ,를 이용하여 split하여 A, B, C의 양을 따로 저장
  if (A == "0" && !answer[C]) {
    // A에 있는 양이 0이고 기존에 없는 C의 물의 양일 경우에
    answer[C] = 1; // answer 객체에 해당 C의 양을 키값으로 저장
  }
});

// answer 객체의 모든 키값을 배열로 받아서 오름차순으로 정렬한 뒤 " "(공백)과 함께 join하여 출력
console.log(
  Object.keys(answer)
    .sort((a, b) => +a - +b)
    .join(" ")
);

function dfs(arr) {
  /**
   * Base Case
   * 이미 같은 물통 조합이 이전에 나와있을 때는 종료
   * 그렇지 않으면 ,와 함께 join하여 해당 문자열을 객체(obj)에 저장
   */
  if (obj[arr.join(",")]) {
    return;
  }
  obj[arr.join(",")] = 1;

  // A가 비어있지 않을 때
  if (arr[0] !== 0) {
    /**
     * A => B
     */
    if (limit[1] - arr[1] < arr[0]) {
      // B에 부을 수 있는양 < A에 남아있는 양 (A를 모두 부을 수 없음)
      // A = arr[0] - (limit[1] - arr[1])
      // B = limit[1]
      // C = arr[2]
      dfs([arr[0] - limit[1] + arr[1], limit[1], arr[2]]);
    } else {
      // B에 부을 수 있는양 >= A에 남아있는 양 (A를 모두 부을 수 있음)
      // A = 0
      // B = arr[1] + arr[0]
      // C = arr[2]
      dfs([0, arr[1] + arr[0], arr[2]]);
    }

    /**
     * A => C
     */
    if (limit[2] - arr[2] < arr[0]) {
      // C에 부을 수 있는 양 < A에 남아있는 양
      // A = arr[0] - (limit[2] - arr[2]);
      // B = arr[1]
      // C = limit[2]
      dfs([arr[0] - limit[2] + arr[2], arr[1], limit[2]]);
    } else {
      // C에 부을 수 있는 양 >= A에 남아있는 양
      // A = 0
      // B = arr[1]
      // C = arr[2] + arr[0]
      dfs([0, arr[1], arr[2] + arr[0]]);
    }
  }

  // B가 비어있지 않을 때
  if (arr[1] !== 0) {
    /**
     * B => A
     */
    if (limit[0] - arr[0] < arr[1]) {
      // A에 부을 수 있는 양 < B에 남아있는 양
      // A = limit[0]
      // B = arr[1] - (limit[0] - arr[0])
      // C = arr[2]
      dfs([limit[0], arr[1] - limit[0] + arr[0], arr[2]]);
    } else {
      // A에 부을 수 있는 양 >= B에 남아있는 양
      // A = arr[0] + arr[1]
      // B = 0
      // C = arr[2]
      dfs([arr[0] + arr[1], 0, arr[2]]);
    }

    /**
     * B => C
     */
    if (limit[2] - arr[2] < arr[1]) {
      // C에 부을 수 잇는 양 < B에 남아있는 양
      // A = arr[0]
      // B = arr[1] - (limit[2] - arr[2])
      // C = limit[2]
      dfs([arr[0], arr[1] - limit[2] + arr[2], limit[2]]);
    } else {
      // C에 부을 수 잇는 양 >= B에 남아있는 양
      // A = arr[0]
      // B = 0
      // C = arr[2] + arr[1]
      dfs([arr[0], 0, arr[2] + arr[1]]);
    }
  }

  // C가 비어있지 않을 때
  if (arr[2] !== 0) {
    /**
     * C => A
     */
    if (limit[0] - arr[0] < arr[2]) {
      // A에 부을 수 있는 양 < C에 남아있는 양
      // A = limit[0]
      // B = arr[1]
      // C = arr[2] - (limit[0] - arr[0])
      dfs([limit[0], arr[1], arr[2] - limit[0] + arr[0]]);
    } else {
      // A에 부을 수 있는 양 >= C에 남아있는 양
      // A = arr[0] + arr[2]
      // B = arr[1]
      // C = 0
      dfs([arr[0] + arr[2], arr[1], 0]);
    }

    /**
     * C => B
     */
    if (limit[1] - arr[1] < arr[2]) {
      // B에 부을 수 있는 양 < C에 남아있는 양
      // A = arr[0]
      // B = limit[1]
      // C = arr[2] - (limit[1] - arr[1])
      dfs([arr[0], limit[1], arr[2] - limit[1] + arr[1]]);
    } else {
      // B에 부을 수 있는 양 < C에 남아있는 양
      // A = arr[0]
      // B = arr[1] + arr[2]
      // C = 0
      dfs([arr[0], arr[1] + arr[2], 0]);
    }
  }
}
/**
 * 채점 결과
 * 메모리: 9800KB
 * 시간: 120ms
 * 언어: JS
 * 참고 링크: https://blog.naver.com/y2kdj9723/222708252044
 */
