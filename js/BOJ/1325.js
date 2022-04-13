/**
 * 문제 출처
 * https://www.acmicpc.net/problem/1325
 * 
 * 문제 설명
 * 해커 김지민은 잘 알려진 어느 회사를 해킹하려고 한다. 이 회사는 N개의 컴퓨터로 이루어져 있다. 김지민은 귀찮기 때문에, 한 번의 해킹으로 여러 개의 컴퓨터를 해킹 할 수 있는 컴퓨터를 해킹하려고 한다.
 * 이 회사의 컴퓨터는 신뢰하는 관계와, 신뢰하지 않는 관계로 이루어져 있는데, A가 B를 신뢰하는 경우에는 B를 해킹하면, A도 해킹할 수 있다는 소리다.
 * 이 회사의 컴퓨터의 신뢰하는 관계가 주어졌을 때, 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에, N과 M이 들어온다. N은 10,000보다 작거나 같은 자연수, M은 100,000보다 작거나 같은 자연수이다. 둘째 줄부터 M개의 줄에 신뢰하는 관계가 A B와 같은 형식으로 들어오며, "A가 B를 신뢰한다"를 의미한다. 컴퓨터는 1번부터 N번까지 번호가 하나씩 매겨져 있다.
 * 
 * 입력값 예시
    5 4
    3 1
    3 2
    4 3
    5 3
 * 
 * 출력
 * 첫째 줄에, 김지민이 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 오름차순으로 출력한다.
 * 
 * 출력값 예시
    1 2
 * 
 * 파싱
 * N = 5, M = 4
 * coms = [ [ 3, 1 ], [ 3, 2 ], [ 4, 3 ], [ 5, 3 ] ]
 * 
 * {{초기 설정}}
 * 연속 해킹 가능한 인접 컴퓨터 리스트
 * obj = { '1': [ 3 ], '2': [ 3 ], '3': [ 4, 5 ] }
 * 
 * 결과 저장용 배열
 * answer = []
 * 
 * 각 컴퓨터마다 연속 해킹 가능한 컴퓨터의 총 수(인덱스 접근 쉽게 하기 위해 N+1)
 * result = [1 * N+1]
 * 
 * 연속 해킹 가능한 컴퓨터의 총 수
 * max = 0
 * 
 * {{각 케이스마다}}
 * 각 케이스에서 시작한 DFS를 위한 체크리스트(인덱스 접근을 쉽게 하기 위해 N+1)
 * checkList = [0 * N+1]
 * 
 */

// 파싱
const fs = require("fs");
let [NM, ...coms] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [N, M] = NM.split(" ").map(Number);
coms = coms.map(a => a.split(" ").map(Number));

// 초기 설정
let obj = {}; // 연속 해킹 가능한 인접 컴퓨터 리스트 객체
coms.forEach(com => {
  // 주의
  if (!obj[com[1]]) obj[com[1]] = []; // A가 B를 신뢰하면
  obj[com[1]].push(com[0]); // B를 해킹했을 때 A를 해킹할 수 있다.
});

let answer = []; // 결과 출력용 배열
let result = new Array(N + 1).fill(1); // 각 컴퓨터마다 연속 해킹 가능한 컴퓨터의 총 수(인덱스 접근 쉽게 하기 위해 N+1)
let max = 0; // 연속 해킹 가능한 컴퓨터의 총 수

// 각 케이스마다
for (let key in obj) {
  let checkList = new Array(N + 1).fill(0); // 각 케이스에서 시작한 DFS를 위한 체크리스트(인덱스 접근을 쉽게 하기 위해 N+1)
  checkList[key] = 1; // 현재 케이스의 컴퓨터를 해킹했다고 체크
  dfs(+key, +key, checkList); // DFS 수행
}

// max값인 index를 answer 배열에 저장
for (let i = 1; i < result.length; i++) {
  if (result[i] === max) {
    answer.push(i);
  }
}

// answer 출력
console.log(answer.join(" "));

/**
 * DFS 수행
 * 해킹 가능한 컴퓨터 리스트 중
 * 이미 해킹되지 않은 컴퓨터에 한해서
 * 해킹했다고 체크하고
 * 처음 해킹이 시작된 컴퓨터의 해킹수를 1 증가시키고
 * 해당 컴퓨터에 한해서 DFS를 수행.
 */
function dfs(base, current, checkList) {
  if (obj[current]) {
    for (let i = 0; i < obj[current].length; i++) {
      if (!checkList[obj[current][i]]) {
        checkList[obj[current][i]] = 1;
        result[base]++;
        if (max < result[base]) {
          max = result[base];
        }
        dfs(base, obj[current][i], checkList);
      }
    }
  }
}
/**
 * 채점 결과
 * 메모리: 64880KB
 * 시간: 9468ms
 * 언어: JS
 */
