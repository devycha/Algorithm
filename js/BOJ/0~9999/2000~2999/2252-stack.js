/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/2252
 * 
 * 시간제한: 2초
 * 메모리제한: 128MB
 * 
 * 
 * 문제
 * N명의 학생들을 키 순서대로 줄을 세우려고 한다. 각 학생의 키를 직접 재서 정렬하면 간단하겠지만, 마땅한 방법이 없어서 두 학생의 키를 비교하는 방법을 사용하기로 하였다. 그나마도 모든 학생들을 다 비교해 본 것이 아니고, 일부 학생들의 키만을 비교해 보았다.
 * 일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 32,000), M(1 ≤ M ≤ 100,000)이 주어진다. 
 * M은 키를 비교한 회수이다. 다음 M개의 줄에는 키를 비교한 두 학생의 번호 A, B가 주어진다. 이는 학생 A가 학생 B의 앞에 서야 한다는 의미이다.
 * 학생들의 번호는 1번부터 N번이다.
 * 
    3 2
    1 3
    2 3
 * 
 * 출력
 * 첫째 줄에 학생들을 키 순서대로 줄을 세운 결과를 출력한다. 답이 여러 가지인 경우에는 아무거나 출력한다.
 * 
    1 2 3
 * 
 * 파싱
 * n = 3, m = 2
 * arr = [[1, 3], [2, 3]]
 * 
 * 초기 설정
 * { 앞: [뒤에 있어야 하는 학생 번호] }
 * obj = {}
 * 
 * 해당 학생보다 앞에 있어야 하는 학생의 수(진입차수)
 * degree = [0 * n+1]
 * 
 * 위상 정렬을 위한 큐 혹은 스택
 * queue or stack = []
 * 
 * 위상 정렬에서 뽑힌 순서
 * result = []
 */
const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.trim().split(" ").map(Number);
arr = arr.map(a => a.trim().split(" ").map(Number));

let obj = {};
let degree = new Array(n + 1).fill(0);
arr.forEach(a => {
  if (!obj[a[0]]) obj[a[0]] = [];
  obj[a[0]].push(a[1]);
  degree[a[1]]++;
});

let queue = [];
for (let i = 1; i < degree.length; i++) {
  if (degree[i] == 0) {
    queue.push(i);
  }
}

let result = [];
/**
 * 위상 정렬
 * 큐의 맨 뒤에서부터 학생을 뽑아서 줄을 세운다.
 * 해당 학생보다 뒤에 있어야 하는 학생들의 진입차수를 1 뺀다(현재 학생을 이미 뽑았기 때문에)
 * 진입차수를 1 뺀 학생이 만약 진입차수가 0이 된다면 해당 학생도 큐 혹은 스택에 push
 */
while (queue.length) {
  let current = queue.pop();
  result.push(current);
  if (obj[current]) {
    obj[current].forEach(adj => {
      if (--degree[adj] == 0) {
        queue.push(adj);
      }
    });
  }
}

console.log(result.join(" "));
/**
 * 채점 결과
 * 메모리: 49620KB
 * 시간: 400ms
 * 언어: JS
 * 참고 링크: https://blog.naver.com/y2kdj9723/222713290827
 */
