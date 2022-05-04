/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2668
 *
 * 시간제한: 1초
 * 메모리제한: 128MB
 * 
 * 문제
 * 세로 두 줄, 가로로 N개의 칸으로 이루어진 표가 있다. 첫째 줄의 각 칸에는 정수 1, 2, …, N이 차례대로 들어 있고 둘째 줄의 각 칸에는 1이상 N이하인 정수가 들어 있다. 첫째 줄에서 숫자를 적절히 뽑으면, 그 뽑힌 정수들이 이루는 집합과, 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치한다. 이러한 조건을 만족시키도록 정수들을 뽑되, 최대로 많이 뽑는 방법을 찾는 프로그램을 작성하시오. 예를 들어, N=7인 경우 아래와 같이 표가 주어졌다고 하자.
 * 이 경우에는 첫째 줄에서 1, 3, 5를 뽑는 것이 답이다. 첫째 줄의 1, 3, 5밑에는 각각 3, 1, 5가 있으며 두 집합은 일치한다. 이때 집합의 크기는 3이다. 만약 첫째 줄에서 1과 3을 뽑으면, 이들 바로 밑에는 정수 3과 1이 있으므로 두 집합이 일치한다. 그러나, 이 경우에 뽑힌 정수의 개수는 최대가 아니므로 답이 될 수 없다.
 * 
 * 입력
 * 첫째 줄에는 N(1≤N≤100)을 나타내는 정수 하나가 주어진다. 그 다음 줄부터는 표의 둘째 줄에 들어가는 정수들이 순서대로 한 줄에 하나씩 입력된다.
 * 
    7
    3
    1
    1
    5
    5
    4
    6
 * 
 * 출력
 * 첫째 줄에 뽑힌 정수들의 개수를 출력하고, 그 다음 줄부터는 뽑힌 정수들을 작은 수부터 큰 수의 순서로 한 줄에 하나씩 출력한다.
 * 
    3
    1
    3
    5
 * 
 * 파싱
 * arr = [7, 3, 1, 1, 5, 5, 4, 6]
 * 
 * {{초기 설정}}
 * 체크리스트 checkList= [0 * arr[0]+1] => arr[0] = N
 * 뽑을 수 있는 정수 모두 result = []
 */
const fs = require("fs");
let arr = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n")
  .map(Number);

let checkList = new Array(arr[0] + 1).fill(0);
let result = [];

// 1부터 N까지 DFS 수행
for (let i = 1; i < arr.length; i++) {
  if (!checkList[i]) {
    checkList[i] = 1;
    dfs(i, [], []);
  }
}

console.log(result.length + "\n" + result.sort((a, b) => a - b).join("\n"));

function dfs(current, first, second) {
  let next = arr[current]; // current번호에 해당하는 둘째 줄 정수

  if (!checkList[next]) {
    // 이미 그 지점에서 시작한 경우가 있는 경우는 제외
    checkList[next] = 1; // 방문했다는 체크
    dfs(next, [...first, current], [...second, next]); // DFS수행
    checkList[next] = 0; // 다른 지점에서 시작해서 갈 수도 있기 때문에 DFS를 완료한 뒤에 방문했다는 체크를 해제
  } else {
    // DFS를 모두 수행해서 더이상 체크할 정수가 없을 때
    first.push(current); // 현재 경우도 첫째 줄에서 뽑은 배열에 넘겨줌
    second.push(next); // 현재 경우도 둘째 줄에서 뽑은 정수 배열에 넘겨줌
    first.sort((a, b) => a - b); // 정렬
    second.sort((a, b) => a - b); // 정렬

    if (check(first, second)) {
      // 두 정렬된 배열이 일치할 경우
      result.push(...first); // 결과 배열에 정수를 넣는다
    }
  }
}

function check(arr1, arr2) {
  if (arr1.length !== arr2.length) {
    return false;
  }
  for (let i = 0; i < arr1.length; i++) {
    if (arr1[i] !== arr2[i]) {
      return false;
    }
  }
  return true;
}
/**
 * 채점 결과
 * 메모리: 9556KB
 * 시간: 120ms
 * 언어: JS
 */
