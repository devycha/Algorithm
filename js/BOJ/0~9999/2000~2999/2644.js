// 문제 링크: https://www.acmicpc.net/problem/2644
/**
 * 문제 설명
 * 우리 나라는 가족 혹은 친척들 사이의 관계를 촌수라는 단위로 표현하는 독특한 문화를 가지고 있다. 이러한 촌수는 다음과 같은 방식으로 계산된다. 기본적으로 부모와 자식 사이를 1촌으로 정의하고 이로부터 사람들 간의 촌수를 계산한다. 예를 들면 나와 아버지, 아버지와 할아버지는 각각 1촌으로 나와 할아버지는 2촌이 되고, 아버지 형제들과 할아버지는 1촌, 나와 아버지 형제들과는 3촌이 된다.
 * 여러 사람들에 대한 부모 자식들 간의 관계가 주어졌을 때, 주어진 두 사람의 촌수를 계산하는 프로그램을 작성하시오.
 * 
 * 입력
 * 사람들은 1, 2, 3, …, n (1 ≤ n ≤ 100)의 연속된 번호로 각각 표시된다. 입력 파일의 첫째 줄에는 전체 사람의 수 n이 주어지고, 둘째 줄에는 촌수를 계산해야 하는 서로 다른 두 사람의 번호가 주어진다. 그리고 셋째 줄에는 부모 자식들 간의 관계의 개수 m이 주어진다. 넷째 줄부터는 부모 자식간의 관계를 나타내는 두 번호 x,y가 각 줄에 나온다. 이때 앞에 나오는 번호 x는 뒤에 나오는 정수 y의 부모 번호를 나타낸다.
 * 각 사람의 부모는 최대 한 명만 주어진다.
 * 
 * 입력값 예시
    9
    7 3
    7
    1 2
    1 3
    2 7
    2 8
    2 9
    4 5
    4 6
 * 
 * 출력
 * 입력에서 요구한 두 사람의 촌수를 나타내는 정수를 출력한다. 어떤 경우에는 두 사람의 친척 관계가 전혀 없어 촌수를 계산할 수 없을 때가 있다. 이때에는 -1을 출력해야 한다.
 * 
 * 출력값 예시 
    3
 * 
 * 파싱
 * n = 9
 * A = 7, B = 3
 * m = 7
 * arr = [
  [ 1, 2 ], [ 1, 3 ],
  [ 2, 7 ], [ 2, 8 ],
  [ 2, 9 ], [ 4, 5 ],
  [ 4, 6 ]
]
 *
 * 초기 설정
 * 자식-부모 객체
 * obj = {
 *  자식: 부모
 * }
 * 
 * A의 부모의 부모의 부모...(자신포함)
 * result1 = [A]
 * 
 * B의 부모의 부모의 부모...(자신포함)
 * result2 = [B]
 */
const fs = require("fs");
let [n, AB, m, ...arr] = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n");
const [A, B] = AB.split(" ").map(Number);
(n = +n), (m = +m);
arr = arr.map(a => a.trim().split(" ").map(Number));
let obj = {};
arr.forEach(a => {
  obj[a[1]] = a[0];
});

console.log(arr);

let result1 = [A];
let result2 = [B];

dfs(result1, A);
dfs(result2, B);

let answer = check(result1, result2);

console.log(answer);

/**
 * 부모가 같은 값이 있을 경우 몇촌인지 계산
 * [3 1 2 4]
 * [7 1]
 * 이면 1 + 1 => 2촌
 */
function check(result1, result2) {
  for (let i = 0; i < result1.length; i++) {
    for (let j = 0; j < result2.length; j++) {
      if (result1[i] === result2[j]) {
        return i + j;
      }
    }
  }
  return -1;
}

function dfs(result, current) {
  if (obj[current]) {
    result.push(obj[current]);
    dfs(result, obj[current]);
  }
}
/**
 * 채점 결과
 * 메모리: 9344KB
 * 시간: 124ms
 * 언어: JS
 */
